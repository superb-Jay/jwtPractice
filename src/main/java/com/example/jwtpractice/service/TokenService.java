package com.example.jwtpractice.service;

public interface TokenService {
    public String logout(String header);

    public boolean tokenVerification(String token);

}
