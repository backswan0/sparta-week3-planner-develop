package com.example.plan.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.util.PatternMatchUtils;

public class LoginFilter implements Filter {

  private static final String[] WHITE_LIST = {"/members/signup", "/members/signin"};

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

        httpResponse.setContentType("application/json");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorResponse = "{"
            + "\"errorCode\": \"ERROR_LOGIN_REQUIRED\", "
            + "\"errorMessage\": \"Please login\""
            + "}";

        httpResponse.getWriter().write(errorResponse);
        return;
      }
    }
    chain.doFilter(request, response);
  }

  private boolean isWhiteList(String requestURI) {
    return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
  }
}