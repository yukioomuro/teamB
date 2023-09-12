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

	//登録・変更の判定用//
	//crud.htmlは「登録」時と「変更」時で表示する内容を変える必要がある。//
	//「true」の場合は「登録」、「false」の場合は「変更」
	private Boolean newCostomer;
}
