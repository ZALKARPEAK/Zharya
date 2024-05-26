package com.example.pastbin.email;

import com.example.pastbin.config.jwt.JwtService;
import com.example.pastbin.dto.Authentication.AuthenticationResponse;
import com.example.pastbin.entity.UserInfo;
import com.example.pastbin.exceptions.NotFoundException;
import com.example.pastbin.repository.UserInfoRepository;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${app.verification-code-expiration-minutes}")
    private int verificationCodeExpirationMinutes;
    @Value("${spring.mail.username}")
    private String mailUsername;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final UserInfoRepository userInfoRepository;
    private final TemplateEngine templateEngine;
    private final JwtService jwtService;

    @SneakyThrows
    @Override
    public void forgotPassword(String email, String link) {
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(email).orElseThrow(
                () -> new NotFoundException("UserInfo not found"));

        String token = UUID.randomUUID().toString();

        userInfo.setTokenPassword(token);
        userInfo.setVerificationCodeTime(new Date());

        userInfoRepository.save(userInfo);

        Context context = new Context();
        context.setVariable("link", link);
        context.setVariable("userName", userInfo.getUser().getName());

        String emailContent = templateEngine.process("message", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailUsername);
        helper.setSubject("Reset password");
        helper.setTo(email);
        helper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public AuthenticationResponse passwordRecovery(String token, String newPassword) throws Exception {

        UserInfo userInfo = userInfoRepository.getByUserAccountByTokenPassword(token).orElseThrow(
                () -> new NotFoundException("UserInfo not found"));
        if (token.equals(userInfo.getTokenPassword())) {
            if (isVerificationCodeExpired(userInfo)) {
                userInfo.setTokenPassword(null);
                throw new Exception("Verification code expired");
            } else {
                userInfo.setPassword(passwordEncoder.encode(newPassword));
                userInfo.setTokenPassword(null);
                userInfoRepository.save(userInfo);
                String jwt = jwtService.generateToken(userInfo);
                return AuthenticationResponse.builder()
                        .email(userInfo.getEmail())
                        .role(userInfo.getRole())
                        .token(jwt)
                        .build();
            }
        } else {
            throw new Exception("Verification code time is up");
        }
    }

    public boolean isVerificationCodeExpired(UserInfo request) {
        Date currentTime = new Date();
        Date sentTime = request.getVerificationCodeTime();
        long minutesDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime.getTime() - sentTime.getTime());
        return minutesDifference > verificationCodeExpirationMinutes;
    }
}