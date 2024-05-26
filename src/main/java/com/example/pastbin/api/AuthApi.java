package com.example.pastbin.api;

import com.example.pastbin.api.response.CustomResponse;
import com.example.pastbin.dto.Authentication.AuthenticationResponse;
import com.example.pastbin.dto.Authentication.SignInRequest;
import com.example.pastbin.dto.Authentication.SignUpRequest;
import com.example.pastbin.email.EmailService;
import com.example.pastbin.service.AuthenticationService;
import com.example.pastbin.util.validator.ValidPassword;
import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
@Tag(name = "Auth api", description = "Authentication endpoints")
public class AuthApi {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/sign-up")
    @Operation(summary = "Sign Up", description = "Register a new user")
    public AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign In", description = "Authenticate and sign in the user")
    public AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/google-authenticate")
    @Operation(summary = "Authentication with Google", description = "Authentication via Google using Firebase")
    public AuthenticationResponse authWithGoogleAccount(@RequestParam String tokenId) throws FirebaseAuthException {
        return authenticationService.authWithGoogleAccount(tokenId);
    }

    @SneakyThrows
    @Operation(summary = "Send Email",
            description = "Sends an email to the user with a confirmation link. The user receives the message and clicks the confirmation button.")
    @PostMapping("/send-email")
    public CustomResponse<?> sendVerificationEmail(@RequestParam String email,
                                                   @RequestParam String link) throws IOException {
        emailService.forgotPassword(email, link);
        return new CustomResponse<>(HttpStatus.OK, "Password reset message sent to mail");
    }

    @Operation(summary = "Navigate to Forgot Password Page",
            description = "When the user clicks 'Confirm,' it redirects to the forgot password page. You need to provide the token and new password data.")
    @PostMapping("/forgot-password")
    public AuthenticationResponse completePasswordRecovery(@RequestParam String token,
                                                           @ValidPassword @RequestParam String newPassword) throws Exception {
        return emailService.passwordRecovery(token, newPassword);
    }
}