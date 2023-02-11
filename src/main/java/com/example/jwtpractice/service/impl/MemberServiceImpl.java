package com.example.jwtpractice.service.impl;

import com.example.jwtpractice.dto.RequestMemberDTO;
import com.example.jwtpractice.dto.ResponseMemberDTO;
import com.example.jwtpractice.dto.TokenDTO;
import com.example.jwtpractice.entity.Member;
import com.example.jwtpractice.jwt.JwtProvider;
import com.example.jwtpractice.repository.MemberRepository;
import com.example.jwtpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtTokenProvider;


    @Override
    public ResponseMemberDTO signUp(RequestMemberDTO requestMemberDTO) {

        String encodingPassword = encodingPassword(requestMemberDTO.getPassword());
        requestMemberDTO.setPassword(encodingPassword);
        Member savedMember = memberRepository.save(requestMemberDTO.toEntity());
        return new ResponseMemberDTO(savedMember);
    }

    @Override
    public TokenDTO signIn(RequestMemberDTO requestMemberDTO) {
        try {
            Member member = memberRepository.findByEmail(requestMemberDTO.getEmail())
                    .orElseThrow(IllegalArgumentException::new);
            passwordMustBeSame(requestMemberDTO.getPassword(), member.getPassword());
            String token = jwtTokenProvider.makeJwtToken(member);
            return new TokenDTO(token);

        }catch (IllegalArgumentException e) {
            return null;
        }

    }

    private void passwordMustBeSame(String requestPassword, String password) {
        if (!passwordEncoder.matches(requestPassword, password)) {
            throw new IllegalArgumentException();
        }
    }

    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
