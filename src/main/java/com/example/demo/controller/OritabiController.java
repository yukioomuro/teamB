package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Costomer;
import com.example.demo.form.CostomerForm;
import com.example.demo.form.HistoryForm;
import com.example.demo.form.PurposeForm;
import com.example.demo.form.SpotForm;
import com.example.demo.service.OritabiService;

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

	@ModelAttribute
	public PurposeForm setUpPurposeForm() {
		PurposeForm form = new PurposeForm();
		return form;
	}

	@ModelAttribute
	public CostomerForm setUpCostomerForm() {
		CostomerForm form = new CostomerForm();
		return form;
	}

	@ModelAttribute
	public HistoryForm setUpHistoryForm() {
		HistoryForm form = new HistoryForm();
		return form;
	}

	//新規登録の一覧表示(エラー時に表示される)
	@GetMapping
	public String showList(CostomerForm costomerForm, Model model) {
		//新規登録設定
		costomerForm.setNewCostomer(true);
		//新規登録情報の一覧を取得
		Iterable<Costomer> list = service.selectAllCostomer();
		//表示用Modelへ格納
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "register";
	}

	//新規登録画面の操作
	@PostMapping("/register")
	public String insert(@Validated CostomerForm costomerForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		//Form から entity へ詰め替え
		Costomer costomer = new Costomer();
		costomer.setName(costomerForm.getName());
		costomer.setMail(costomerForm.getMail());
		costomer.setPass(costomerForm.getPass());
		costomer.setMemo(costomerForm.getMemo());

		//入力チェック
		if (!bindingResult.hasErrors()) {
			service.insertCostomer(costomer);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "map";

		} else {
			//エラーがある場合は、もう一度新規登録画面へ飛びます。
			return "/register";
		}
	}

	//顧客情報や観光スポットの編集用の操作

}
