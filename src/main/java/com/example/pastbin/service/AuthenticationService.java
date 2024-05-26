package com.example.pastbin.service;

import com.example.pastbin.dto.Authentication.AuthenticationResponse;
import com.example.pastbin.dto.Authentication.SignInRequest;
import com.example.pastbin.dto.Authentication.SignUpRequest;
import com.google.firebase.auth.FirebaseAuthException;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest request);
    AuthenticationResponse signIn(SignInRequest request);
    AuthenticationResponse authWithGoogleAccount(String tokenId) throws FirebaseAuthException;
}