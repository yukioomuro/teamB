//package com.example.demo.service.userdetails;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.demo.entity.Costomer;
//
//public class UserDetailsImpl implements UserDetails {
//	
//	private final Costomer loginUser;
//	
//	// 権限コレクション
//	private Collection<GrantedAuthority> authorities;
//	// コンストラクタ
//	public UserDetailsImpl(Costomer loginUser, Collection<GrantedAuthority> authorities) {
//	this.loginUser = loginUser;
//	this.authorities = authorities;
//	
//	}
//	
//	
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO 自動生成されたメソッド・スタブ
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO 自動生成されたメソッド・スタブ
//		return loginUser.getPass();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO 自動生成されたメソッド・スタブ
//		return loginUser.getMail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//}
