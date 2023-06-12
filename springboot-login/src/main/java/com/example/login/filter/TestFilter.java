package com.example.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init 실행");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			// HTTP 요청 -> WAS -> filter 실행
			log.info("dofilter 실행");
			// 다음에 실행할 필터가 있으면, 그 필터를 호출하고,
			// 아니면 서블릿을 호출한다.
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		} finally {
			// Controller -> Servlet -> Filter 응답 실행 -> WAS -> HTTP 응답
			log.info("doFilter 실행 완료");
		}
	}

	@Override
	public void destroy() {
		log.info("destroy 실행");
	}

}
