package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {

	//顧客×観光地(履歴)リストへのForm
	//連番
	@Id
	private Integer historyId;

	//顧客ID
	@NotBlank
	private Integer costomerId;

	//観光地ID
	
	private String touristId_1;
	
	//所要時間
	
	private String time_1;
	
	
	private String touristId_2;
	
	private String time_2;
	
	private String touristId_3;
	
	private String time_3;
	
	private String touristId_4;
	
	private String time_4;

	private String touristId_5;
	
	//総時間
	private String totalDuration;
	
	//顧客情報
	
	private String memo;

}
