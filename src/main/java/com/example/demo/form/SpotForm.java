package com.example.demo.form;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotForm {

	//観光スポットリストテーブルへのテーブル
	//観光地連番
	@Id
	private Integer spotId;

	//観光地名
	@NotBlank
	private String spotName;

	//観光地住所
	@NotBlank
	private String address;

	//観光地webページ
	private String web;

	//電話番号
	@NotBlank
	private String tel;

	//おすすめポイント
	private String point;

	//目的ID
	private Integer purposeId;

	//画像ファイル
	private String imageFileName;
}
