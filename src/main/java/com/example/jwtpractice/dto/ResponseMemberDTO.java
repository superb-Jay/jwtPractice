package com.example.jwtpractice.dto;


import com.example.jwtpractice.entity.Member;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseMemberDTO {

    private String email;
    private String password;

    public ResponseMemberDTO(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
