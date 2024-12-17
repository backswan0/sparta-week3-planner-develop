package com.example.plan.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    // 인증을 하지 않아도 되는 URI: 회원가입과 로그인 (로그인하려고 또 로그인할 필요는 없으니까)
    private static final String[] WHITE_LIST = {"/members/signup", "/members/signin"};

    @Override
    public void doFilter(
            ServletRequest request
            , ServletResponse response
            , FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 실제 요청으로 들어온 URI를 requestURI 변수에 저장
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        // login을 체크해야 하는 URL인지 검사해서 만약 체크해야 한다면 인증을 수행하는 로직
        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey")==null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            // 로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }
        chain.doFilter(request, response);
    }

    /**
     * 기능
     * WHITE_LIST에 포함된 URL인지 검사하는 메서드
     *
     * @param requestURI : requestURI, 데이터 타입은 String
     * @return true 또는 false
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}