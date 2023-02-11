package com.example.jwtpractice.jwt;


import com.example.jwtpractice.dto.RequestMemberDTO;
import com.example.jwtpractice.service.TokenService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Builder
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;


    @Override //모든 요청에 최상단 필터 시큐리티 필터보다 먼저 있음.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //request : 사용자가 보내는 모든 정보를 가지고잇음
        //response : 응답을할때 어떻게 응답을 줄지 영향을 줄수 있음
        //filterChain : 필터를 넘길때 사용

        //request 가 제일 중요함. 이 request에서 토큰정보를 가져온다. 헤더 안에 오쏘리제이션에 있음.
        //토큰안에 로그인 사용자 정보가 들어있음. doFilterInternal 그정보에서 UserDto로 바꾸는 일을 한다.

        //filter에서 header를 가져옴
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!tokenService.tokenVerification(token)) {

            //token 값에서 유효값 (email)을 추출하여 requestMember를 만듦
            RequestMemberDTO requestMemberDTO = jwtProvider.tokenToRequestMember(token);

//           분석이 끝난 유저 객체에 있는 정보를 시큐리티컨텍스트 빈객체에 넘겨준다. (정보와, 권한을 넘겨준다.)
            if (requestMemberDTO != null) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        requestMemberDTO,
                        "",
                        requestMemberDTO.getAuthorities()));
            }
        }
        filterChain.doFilter(request, response); //해당 필터의 역할은 끝낫고. 다음 필터로 넘긴다.
    }
}
