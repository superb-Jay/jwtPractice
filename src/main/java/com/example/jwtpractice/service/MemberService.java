package com.example.jwtpractice.service;


import com.example.jwtpractice.dto.RequestMemberDTO;
import com.example.jwtpractice.dto.ResponseMemberDTO;
import com.example.jwtpractice.dto.TokenDTO;

public interface MemberService {

    public ResponseMemberDTO signUp(RequestMemberDTO requestMemberDTO);
    public TokenDTO signIn(RequestMemberDTO requestMemberDTO);

}
