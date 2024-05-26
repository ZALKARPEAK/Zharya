package com.example.pastbin.repository;

import com.example.pastbin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface UserRepository extends JpaRepository<User, Long> {

}