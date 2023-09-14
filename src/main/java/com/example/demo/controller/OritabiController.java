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
import com.example.demo.entity.Spot;
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
		System.out.println("setupSpotForm");
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

	//新規Spt登録時の一覧表示(エラー時に表示される)
	@GetMapping("/manager_page")
	public String showSpotList(SpotForm spotForm, Model model) {
		//新規登録設定
		spotForm.setNewSpot(true);
		//新規登録情報の一覧を取得
		Iterable<Spot> list = service.selectAllSpot();
		for(Spot i : list) {
			System.out.println(i.getSpotName());
		}
		//表示用Modelへ格納
		model.addAttribute("spotList", list);
		//model.addAttribute("title", "登録用フォーム");
		return "manager_page";
	}

	//新規登録画面の操作
	@PostMapping("/register")
	public String insertCostomer(@Validated CostomerForm costomerForm,
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
			return "register";
		}
	}

	//顧客情報や観光スポットの編集用の操作
	@PostMapping("/insertSpot")
	public String insertTourist(@Validated SpotForm spotForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		System.out.println("****************************");
		//Form から entity へ詰め替え
		Spot spot = new Spot();
		spot.setSpotName(spotForm.getSpotName());
		spot.setAddress(spotForm.getAddress());
		spot.setWeb(spotForm.getWeb());
		spot.setTel(spotForm.getTel());
		spot.setPoint(spotForm.getPoint());
		spot.setPurposeId(spotForm.getPurposeId());
		spot.setImageFileName(spotForm.getImageFileName());
		System.out.println(spot);

		//入力チェック
		if (!bindingResult.hasErrors()) {
			System.out.println("--------------");
			service.insertSpot(spot);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			//showSpotList(spotForm, model);
			return "redirect:/oritabi/manager_page";

		} else {
			System.out.println("++++++++++++++++");
			//エラーがある場合は、もう一度新規登録画面へ飛びます。
			return showSpotList(spotForm, model);
		}
	}

//	@GetMapping("/manager_page")
//	public String a(Model model) {
//		return "manager_page";
//	}
}
