package com.example.jwtpractice.dto;


import com.example.jwtpractice.entity.Member;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestMemberDTO {

    private String email;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

    public RequestMemberDTO(Claims claims) {
        this.email = claims.get("email",String.class); // 토큰에 페이로더에 이메일만 저장할것임.
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_USER, ROLE_ADMIN 은 정해저 있는 네이밍임, getAuthorities() 이름도 같아야함
        // 만약 하나로 구분이 아니고 여러 개의 권한을 가져야 한다면, 아래와 닽이 쓸것
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_ADMIN"));
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }


}
