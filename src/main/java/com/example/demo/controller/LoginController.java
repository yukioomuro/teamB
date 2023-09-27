package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	// SecurityConfig のloginPage で指定したURL
	@GetMapping("/login")
	public String loginForm() {
		// ログイン画面を表示
		return "login";
	}

	// SecurityConfig のfailureUrl で指定したURL と のうしろのパラメータ
	@GetMapping(value = "/login", params = "failure")
	public String loginFail(Model model) {
		System.out.println("fail");
		model.addAttribute("failureMessage", " ログインに失敗しました");
		// ログイン画面を表示
		return "manager_login";
	}

	// SecurityConfig のdefaultSuccessUrl で指定したURL
	@GetMapping("loginsuccess")
	public String loginSuccess() {
		// ログインに成功したら表示するURL
		return "success";
	}
}