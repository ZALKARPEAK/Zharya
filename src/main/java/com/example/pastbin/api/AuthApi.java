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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
@Tag(name = "Auth api", description = "Authentication endpoints")
public class AuthApi {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/sign-up")
    @Operation(
            summary = "Sign Up",
            description = "Registers a new user with the provided sign-up details, such as username, email, and password. This endpoint creates a new user account in the system."
    )
    public AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Sign In",
            description = "Authenticates the user and returns an authentication response, which includes an access token and other relevant information. The user must provide their valid sign-in credentials, such as email and password, to be authenticated."
    )
    public AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/google-authenticate")
    @Operation(
            summary = "Authentication with Google",
            description = "Authenticates the user using their Google account. The user must provide a valid Firebase token ID, which is obtained from the Google authentication process. This endpoint integrates with Firebase to verify the token and create an authentication response."
    )
    public AuthenticationResponse authWithGoogleAccount(@RequestParam String tokenId) throws FirebaseAuthException {
        return authenticationService.authWithGoogleAccount(tokenId);
    }

    @SneakyThrows
    @Operation(
            summary = "Send Email",
            description = "Sends a password reset email to the provided email address. The email contains a confirmation link that the user must click to initiate the password recovery process. This endpoint is used for the 'Forgot Password' functionality."
    )
    @PostMapping("/send-email")
    public CustomResponse<?> sendVerificationEmail(@RequestParam String email,
                                                   @RequestParam String link) {
        emailService.forgotPassword(email, link);
        return new CustomResponse<>(HttpStatus.OK, "Password reset message sent to mail");
    }

    @Operation(
            summary = "Navigate to Forgot Password Page",
            description = "Completes the password recovery process by verifying the provided token and updating the user's password with the new one. This endpoint is called after the user clicks the confirmation link in the password reset email."
    )
    @PostMapping("/forgot-password")
    public AuthenticationResponse completePasswordRecovery(@RequestParam String token,
                                                           @ValidPassword @RequestParam String newPassword) throws Exception {
        return emailService.passwordRecovery(token, newPassword);
    }
}