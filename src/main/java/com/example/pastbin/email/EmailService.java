package com.example.pastbin.email;

import com.example.pastbin.dto.Authentication.AuthenticationResponse;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void forgotPassword(String email, String link) throws MessagingException, IOException;
    AuthenticationResponse passwordRecovery(String token, String newPassword) throws Exception;
}