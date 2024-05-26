package com.example.pastbin.dto.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String number;
    private String email;
    private String password;
}