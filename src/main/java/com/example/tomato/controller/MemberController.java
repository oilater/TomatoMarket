package com.example.tomato.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tomato.dto.SignUpDto;
import com.example.tomato.entity.Member;
import com.example.tomato.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping(value = "/mypage")
	public String myPage(Model model) {
		return "member/mypage";
	}
	
	
	// 회원가입 화면
	@GetMapping(value = "/new")
	public String signUpMember(Model model) {
		model.addAttribute("signUpDto", new SignUpDto());
		return "member/signup";
	}

	@PostMapping(value = "/new")
	public String signUpMember(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model) {

		// 에러가 있다면 회원가입 페이지로 이동
		if (bindingResult.hasErrors()) {
			return "member/signup";
		}

		try {
			Member member = Member.createMember(signUpDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/signup";
		}
		return "redirect:/";
	}

	// 로그인 화면
	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/login";
	}

	//로그인을 실패했을 떄
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/login";
	}

}