package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//顧客リストテーブル

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Costomer {

	//顧客ID
	@Id
	private Integer id;

	//名前
	private String name;

	//メアド
	private String mail;

	@Transient
	private String mailView;

	//パスワード
	private String pass;

	@Transient
	private String passView;

	//メモ
	private String memo;
	
	//暗号可PASS
	private String secretPass;
}