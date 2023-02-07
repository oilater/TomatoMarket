package com.example.tomato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

//내 물건 팔기
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
	@GetMapping(value = "/mysell")
	public String mysell(Model model) {
		return "user/mysell";
	}

	@GetMapping(value = "/mysellReivse")
	public String mysellRevise(Model model) {
		return "user/mysellRevise";
	}


}

