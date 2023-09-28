package com.example.demo.service.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Costomer;

public class UserDetailsImpl implements UserDetails {

	private final Costomer loginUser;

	// 権限コレクション
	private Collection<GrantedAuthority> authorities;

	// コンストラクタ
	public UserDetailsImpl(Costomer loginUser, Collection<GrantedAuthority> authorities) {
		if (loginUser != null) {
			System.out.print("loginUser : ");
			System.out.println(loginUser.getId());
		}

		this.loginUser = loginUser;
		this.authorities = authorities;

		System.out.print("this.loginUser : ");
		System.out.println(this.loginUser.getId());
	}

	public Integer getId() {
		System.out.print("ログインユーザー : ");
		System.out.println(loginUser.getId());
		return loginUser.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return loginUser.getSecretPass();
	}

	public String getCusotmname() {
		// TODO 自動生成されたメソッド・スタブ
		return loginUser.getName();
	}
	
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return loginUser.getMail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
