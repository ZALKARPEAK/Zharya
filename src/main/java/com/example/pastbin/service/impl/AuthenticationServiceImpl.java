package com.example.pastbin.service.impl;

import com.example.pastbin.config.jwt.JwtService;
import com.example.pastbin.dto.Authentication.AuthenticationResponse;
import com.example.pastbin.dto.Authentication.SignInRequest;
import com.example.pastbin.dto.Authentication.SignUpRequest;
import com.example.pastbin.entity.User;
import com.example.pastbin.entity.UserInfo;
import com.example.pastbin.entity.enums.Role;
import com.example.pastbin.exceptions.AlreadyExistsException;
import com.example.pastbin.exceptions.NotFoundException;
import com.example.pastbin.repository.UserInfoRepository;
import com.example.pastbin.repository.UserRepository;
import com.example.pastbin.service.AuthenticationService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userInfoRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .phoneNumber(request.getNumber())
                .build();

        UserInfo userAccount = UserInfo.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .user(user)
                .build();

        userInfoRepository.save(userAccount);

        String jwt = jwtService.generateToken(userAccount);

        return AuthenticationResponse.builder()
                .email(userAccount.getEmail())
                .role(userAccount.getRole())
                .token(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        UserInfo user = userInfoRepository.getUserAccountByEmail(request.getEmail()).orElseThrow(() ->
                new RuntimeException("email or password invalid"));

        String passwordBCrypt = request.getPassword();
        passwordEncoder.encode(passwordBCrypt);

        if (!passwordEncoder.matches(passwordBCrypt, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwt)
                .build();
    }

    @PostConstruct
    void init() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("google.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp.initializeApp(firebaseOptions);
    }

    @Override
    public AuthenticationResponse authWithGoogleAccount(String tokenId) throws FirebaseAuthException {
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(tokenId);

        if (userInfoRepository.existsByEmail(firebaseToken.getEmail())) {
            UserInfo userAc = userInfoRepository.getUserAccountByEmail(firebaseToken.getEmail()).orElseThrow(
                    () -> new NotFoundException("User not found")
            );
            String accessToken = jwtService.generateToken(userAc);
            return AuthenticationResponse.builder()
                    .token(accessToken)
                    .email(userAc.getEmail())
                    .role(userAc.getRole())
                    .build();
        }
        User newUser = new User();
        String[] name = firebaseToken.getName().split(" ");
        newUser.setName(name[0]);
        newUser.setPhoneNumber("+996000000000");
        userRepository.save(newUser);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(firebaseToken.getEmail());
        userInfo.setPassword(passwordEncoder.encode(firebaseToken.getEmail()));
        userInfo.setRole(Role.USER);
        newUser.setUserInfo(userInfo);
        userInfoRepository.save(userInfo);

        UserInfo userInformation = newUser.getUserInfo();
        String accessToken = jwtService.generateToken(userInformation);
        return AuthenticationResponse.builder()
                .token(accessToken)
                .email(userInformation.getEmail())
                .role(userInformation.getRole())
                .build();
    }
}