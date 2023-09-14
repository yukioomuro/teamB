//package com.example.demo.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entity.Costomer;
//import com.example.demo.repository.CostomerRepository;
//import com.example.demo.service.userdetails.UserDetailsImpl;
//
//@Service
//
//public class UserDetailServiceImpl implements UserDetailsService {
//@Autowired
//CostomerRepository loginUserRepository;
////@Autowired
////RoleRepository roleRepository;
//
//
//@Override
//public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//// login_user テーブルからusername に対応するデータを取得する
//Iterable<Costomer> loginUserIte = loginUserRepository.findAll();
//Costomer r=null;
//
//
//for(Costomer s:loginUserIte) {
//	if(s.getMail().equals(mail)){
//		r=s;
//		break;
//	}
//		
//	}
//
//Collection<GrantedAuthority> authorities = new ArrayList<>();
////if ( loginUserOpt.isPresent()) {
////}
//// ログインユーザーが存在するとき
//// 権限データを取得
////Iterable<Role> roleIte = roleRepository.findAll();
////Integer roleId = loginUserOpt.get().getRoleId();
////for (Role role : roleIte) {
////if (roleId == role.getId()) {
//	
//// ユーザーの権限に対応する権限名を設定する
//// "ROLE_◯◯ の名前で設定するとhasRole の権限になる
//// " をつけない場合はhasAuthority の権限になる
//	
////authorities.add(new SimpleGrantedAuthority( SimpleGrantedAuthority("ROLE_" + role.
//return new UserDetailsImpl(r, authorities);
//}
//}