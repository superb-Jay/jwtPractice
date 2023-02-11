package com.example.jwtpractice.repository;

import com.example.jwtpractice.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
    boolean existsByToken(String token);
}
