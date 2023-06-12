package com.example.login.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.login.mapper.MemberMapper;
import com.example.login.model.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
@Controller
public class MemberController {

	private final MemberMapper memberMapper;

	@ModelAttribute("genderTypes")
	public GenderType[] genderType() {
		return GenderType.values();
	}

	@ModelAttribute("hobbies")
	public Map<String, String> hobbies() {
		Map<String, String> hobbies = new LinkedHashMap<>();
		hobbies.put("SWIMMING", "수영");
		hobbies.put("SOCCER", "축구");
		hobbies.put("BASEBALL", "야구");
		hobbies.put("HIKING", "하이킹");
		return hobbies;
	}

	@ModelAttribute("cityCodes")
	public List<CityCode> cityCodes() {
		List<CityCode> cities = new ArrayList<>();
		cities.add(new CityCode("SEOUL", "서울"));
		cities.add(new CityCode("DAEJEON", "대전"));
		cities.add(new CityCode("DAEGU", "대구"));
		cities.add(new CityCode("BUSAN", "부산"));
		return cities;
	}

	// 회원가입 페이지 이동
	@GetMapping("joinForm")
	public String joinForm(Model model) {
		model.addAttribute("member", new MemberJoinForm());
		return "member/joinForm";
	}

	// 회원가입
	@PostMapping("join")
	public String join(@Validated @ModelAttribute("member") MemberJoinForm joinForm, BindingResult result) {
		// 회원가입
		log.info("joinForm: {}", joinForm);
		memberMapper.joinMember(MemberJoinForm.toMember(joinForm));

		return "redirect:/";
	}

	// 로그인 페이지 이동
	@GetMapping("loginForm")
	public String loginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "member/loginForm";
	}

	// 로그인
	@PostMapping("login")
	public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult result,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam(defaultValue = "/") String redirectURL) {
		log.info("loginForm: {}", loginForm);
		log.info("redirectURL: {}", redirectURL);

		if (result.hasErrors()) {
			return "member/loginForm";
		}

		// 로그인 처리 로직
		Member findMember = memberMapper.findMemberById(loginForm.getId());
		if (findMember != null && findMember.getPassword().equals(loginForm.getPassword())) {
			log.info("로그인 성공");

//			// 쿠키를 이용한 로그인 처리
//			Cookie cookieLoginId = new Cookie("cookieLoginId", findMember.getId());
//			// 쿠키는 디렉토리 별로 저장이 된다.
//			// path를 / 로 지정하여 모든 경로에서 쿠키를 읽을 수 있도록 처리해준다.
//			cookieLoginId.setPath("/");
//			// 쿠키는 응답 객체에 넣어서 전송
//			response.addCookie(cookieLoginId);

			// 세션을 이용한 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", findMember);

		} else {
			log.info("로그인 실패");
			result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "member/loginForm";
		}
		return "redirect:" + redirectURL;
	}

	@GetMapping("logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		
		// 쿠키 방식 로그아웃
//		Cookie cookie = new Cookie("cookieLoginId", null);
//		cookie.setPath("/");
//		cookie.setMaxAge(0);
//		response.addCookie(cookie);

		// 세션 방식 로그아웃
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

}
