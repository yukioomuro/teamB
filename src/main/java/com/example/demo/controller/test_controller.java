package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test_controller {

	@GetMapping("/")
	public String login() {
		return "manager_login";
	}
}
