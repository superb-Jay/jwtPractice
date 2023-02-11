package com.example.jwtpractice.controller;


import com.example.jwtpractice.dto.RequestMemberDTO;
import com.example.jwtpractice.dto.ResponseMemberDTO;
import com.example.jwtpractice.dto.TokenDTO;
import com.example.jwtpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseMemberDTO signUp(RequestMemberDTO requestMemberDTO) {
        return memberService.signUp(requestMemberDTO);
    }

    @PostMapping("/sign-in")
    public TokenDTO signIn(RequestMemberDTO requestMemberDTO) {
        return memberService.signIn(requestMemberDTO);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('USER')") // USER 권한만 호출 가능
    public String hello(@AuthenticationPrincipal RequestMemberDTO requestMemberDTO) {
        return requestMemberDTO.getEmail() + ", 안녕하세요!";
    }
}
