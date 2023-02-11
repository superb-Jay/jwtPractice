package com.example.jwtpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_logout")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @Column(name="token")
    private String token;
}