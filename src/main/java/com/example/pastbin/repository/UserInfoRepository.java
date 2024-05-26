package com.example.pastbin.repository;

import com.example.pastbin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableRedisRepositories
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> getUserAccountByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM UserInfo u WHERE u.tokenPassword = :tokenPassword")
    Optional<UserInfo> getByUserAccountByTokenPassword(@Param("tokenPassword") String tokenPassword);
}