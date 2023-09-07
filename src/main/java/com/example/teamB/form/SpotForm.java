package com.example.teamB.form;

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
}
