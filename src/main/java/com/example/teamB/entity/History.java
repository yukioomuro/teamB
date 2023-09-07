package com.example.teamB.entity;

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
	@NotBlank
	private Integer touristId;

	//顧客情報
	@NotBlank
	private String memo;
}
