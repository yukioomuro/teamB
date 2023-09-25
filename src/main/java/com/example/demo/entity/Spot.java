package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//観光スポットリスト

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

	//連番
	@Id
	private Integer spotId;

	//観光地名
	private String spotName;

	//観光地住所
	private String address;

	//webページ
	private String web;

	//電話番号
	private String tel;

	//おすすめポイント
	private String point;

	//目的ID
	private Integer purposeId;

	//画像ファイル
	private String imageFileName;
}
