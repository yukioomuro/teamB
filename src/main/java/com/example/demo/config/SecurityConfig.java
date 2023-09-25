package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.formLogin(form -> form
				.loginPage("/oritabi/manager_login") // ログイン画面のURL
				.loginProcessingUrl("/spotloginProcessing")// ユーザー名・パスワードの送信先URL
				.defaultSuccessUrl("/oritabi/spotcall") // ログイン成功後のリダイレクト先URL
				.failureUrl("/login?failure") // ログイン失敗後のリダイレクト先URL
				.permitAll() // ログイン画面は未ログインでもアクセス可能
		).logout(logout -> logout
				.logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先URL
		).authorizeHttpRequests(authz -> authz
				.requestMatchers("/login").permitAll()// 「/login 」はすべて許可
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()//CSSを許可
				.requestMatchers("/imag/**").permitAll()//画像許可
				.requestMatchers("/oritabi/register").permitAll()//新規許可
				.requestMatchers("/oritabi/top").permitAll()//新規許可
				.requestMatchers("/oritabi/spot").permitAll()//spot許可
				.requestMatchers("/oritabi/spotSelect")	.permitAll()
				.anyRequest().authenticated()				
						
		//URLごとにRoleの権限を設定
		);

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
	//	System.out.println(new BCryptPasswordEncoder().encode("123"));
		return new BCryptPasswordEncoder();
	}

}
