package com.example.jwtpractice.dto;


import lombok.*;


@Getter
@ToString
public class TokenDTO {

    private final String accessToken;

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
