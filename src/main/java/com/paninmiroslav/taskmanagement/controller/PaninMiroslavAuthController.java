package com.paninmiroslav.taskmanagement.controller;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavAuthRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavAuthResponseDto;
import com.paninmiroslav.taskmanagement.entity.PaninMiroslavRole;
import com.paninmiroslav.taskmanagement.entity.PaninMiroslavUser;
import com.paninmiroslav.taskmanagement.repository.PaninMiroslavUserRepository;
import com.paninmiroslav.taskmanagement.security.PaninMiroslavJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PaninMiroslavAuthController {

    private final PaninMiroslavUserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final PaninMiroslavJwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody PaninMiroslavAuthRequestDto dto
    ) {

        PaninMiroslavUser user =
                PaninMiroslavUser.builder()
                        .email(dto.getEmail())
                        .password(
                                passwordEncoder.encode(
                                        dto.getPassword()
                                )
                        )
                        .role(PaninMiroslavRole.ROLE_USER)
                        .build();

        repository.save(user);

        return ResponseEntity.ok(
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<PaninMiroslavAuthResponseDto>
    login(
            @RequestBody PaninMiroslavAuthRequestDto dto
    ) {

        PaninMiroslavUser user =
                repository.findByEmail(dto.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getEmail()
                );

        return ResponseEntity.ok(
                new PaninMiroslavAuthResponseDto(token)
        );
    }
}
