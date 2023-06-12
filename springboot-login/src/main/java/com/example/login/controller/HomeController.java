package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	// 메인 페이지 이동

	@GetMapping("/")
	public String home(@CookieValue(name = "cookieLoginId", required = false) String cookieLoginId, Model model) {

		log.info("HomeController home method 실행");
		// log.info("cookieLoginId: {}", cookieLoginId);
		if (cookieLoginId != null) {
			model.addAttribute("cookieLoginId", cookieLoginId);
		}
		model.addAttribute("test", "test message");
		return "index";
	}

}
