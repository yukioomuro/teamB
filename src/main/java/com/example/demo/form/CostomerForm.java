package com.example.demo.form;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostomerForm {

	//顧客リストテーブルへのForm
	//顧客ID
	@Id
	private Integer id;

	//顧客名
	@NotBlank
	private String name;

	//メアド
	@NotBlank
	private String mail;

	//パスワード
	@NotBlank
	private String pass;

	//メモ
	private String memo;

}
