package com.example.plan.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

public class LoginFilter implements Filter {
    // 속성
    private static final String[] WHITE_LIST = {"/members/signup", "/members/signin"};

    /**
     * 기능
     * 로그인 필터로, 회원가입 및 로그인 요청에서는 작동하지 않는다.
     *
     * @param request  :  ServletRequest
     * @param response : ServletResponse
     * @param chain    :    FilterChain
     * @throws IOException      : 입력/출력 처리 중 발생할 수 있는 예외
     * @throws ServletException : servlet 요청 처리 중 발생할 수 있는 예외
     */
    @Override
    public void doFilter(
            ServletRequest request
            , ServletResponse response
            , FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("member") == null) {

                httpResponse.sendError(401, "로그인 해주세요.");
                return; // 다음 단계를 실행하지 못하도록 막아야 하므로.
            }
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