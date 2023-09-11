package com.example.demo.form;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurposeForm {

	//目的地リストへのForm
	//ID
	@Id
	@NotBlank
	private Integer purposeId;

	//目的
	@NotBlank
	private String purposeKinds;

}
