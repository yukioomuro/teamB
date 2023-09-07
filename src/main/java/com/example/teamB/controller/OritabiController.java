package com.example.teamB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.teamB.form.CostomerForm;
import com.example.teamB.form.HistoryForm;
import com.example.teamB.form.PurposeForm;
import com.example.teamB.form.SpotForm;
import com.example.teamB.service.OritabiService;

//Oritabiのコントローラー
@Controller
@RequestMapping("/oritabi")
public class OritabiController {

	//DI対象（依存対象）
	@Autowired
	OritabiService service;

	//「form-backing bean」の初期化
	@ModelAttribute
	public SpotForm setUpSpotForm() {
		SpotForm form = new SpotForm();
		return form;
	}

	public PurposeForm setUpPurposeForm() {
		PurposeForm form = new PurposeForm();
		return form;
	}

	public CostomerForm setUpCostomerForm() {
		CostomerForm form = new CostomerForm();
		return form;
	}

	public HistoryForm setUpHistoryForm() {
		HistoryForm form = new HistoryForm();
		return form;
	}
}
