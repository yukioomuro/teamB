package com.example.demo.form;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryForm {

	//顧客×観光地(履歴)リストへのForm
	//連番
	@Id
	private Integer historyId;

	//顧客ID
	@NotBlank
	private Integer costomerId;

	//観光地ID
	@NotBlank
	private Integer touristId_1;
	
	//所要時間
	@NotBlank
	private String time_1;
	
	@NotBlank
	private Integer touristId_2;
	
	private String time_2;
	
	private Integer touristId_3;
	
	private String time_3;
	
	private Integer touristId_4;
	
	private String time_4;

	private Integer touristId_5;

	
	//顧客情報
	@NotBlank
	private String memo;

}
