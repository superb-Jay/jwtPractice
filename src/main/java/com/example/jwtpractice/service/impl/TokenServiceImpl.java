package com.example.jwtpractice.service.impl;

import com.example.jwtpractice.entity.Token;
import com.example.jwtpractice.repository.TokenRepository;
import com.example.jwtpractice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public String logout(String header) {
        System.out.println(header);
        if(header != null) {
            tokenRepository.save(Token.builder().token(header).build());
            return "success";
        }
        return "failed";
    }

    @Override
    public boolean tokenVerification(String token) {
        return tokenRepository.existsByToken(token);
    }

}
