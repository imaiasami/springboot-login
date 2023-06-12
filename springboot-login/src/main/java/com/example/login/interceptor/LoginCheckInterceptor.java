package com.example.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		HttpSession session = request.getSession();
//		String requestURI = request.getRequestURI();
//		log.info("requestURI: {}", requestURI);
//		log.info("인터셉터 사용자 로그인 인증");
//		if (session == null || session.getAttribute("loginMember") == null) {
//			log.info("인터셉터 로그인 인증 실패");
//			// 이 값을 읽으면, 로그인을 해서 어디로 가려고 했는지 알 수 있다.
//			response.sendRedirect("/member/loginForm?redirectURL=" + requestURI);
//			return false;
//		}
		return true;

	}

}
