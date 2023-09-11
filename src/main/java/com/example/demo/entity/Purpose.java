package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purpose {

	//目的地リストへのForm
	//ID
	@Id
	@NotBlank
	private Integer purposeId;

	//目的
	@NotBlank
	private String purposeKinds;

}
