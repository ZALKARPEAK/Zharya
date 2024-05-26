package com.example.pastbin.dto.Authentication;

import com.example.pastbin.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;
}