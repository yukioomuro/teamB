package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity



public class SecurityConfig {

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
	http.formLogin(form -> form
	.loginPage("/purpose_osaka") // ログイン画面のURL
	.loginProcessingUrl("/authenticate")// ユーザー名・パスワードの送信先URL
	.defaultSuccessUrl("/spot") // ログイン成功後のリダイレクト先URL
	.failureUrl("/login?failure") // ログイン失敗後のリダイレクト先URL
	.permitAll() // ログイン画面は未ログインでもアクセス可能
	).logout(logout -> logout
	.logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先URL
	).authorizeHttpRequests(authz -> authz
	.requestMatchers("/login").permitAll()// 「/login 」はすべて許可
	.anyRequest().authenticated()); // 他のURL はログイン後のみアクセス可能
	return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	
	System.out.println(new BCryptPasswordEncoder().encode("aaa"));
	return new BCryptPasswordEncoder();
	}
	

}
