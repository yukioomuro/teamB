package com.example.teamB.entity;

import org.springframework.data.annotation.Id;

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

	//パスワード
	private String pass;

	//メモ
	private String memo;
}
