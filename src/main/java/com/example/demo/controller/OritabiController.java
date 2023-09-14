package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	/* ▼▼▼▼▼▼▼▼▼▼ 新規観光地Spot登録 ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/manager_page")
	public String showSpotList(SpotForm spotForm, Model model) {
		//新規登録設定
		spotForm.setNewSpot(true);
		//新規登録情報の一覧を取得
		Iterable<Spot> list = service.selectAllSpot();
		for (Spot i : list) {
			System.out.println(i.getSpotName());
		}
		//表示用Modelへ格納
		model.addAttribute("spotList", list);
		//model.addAttribute("title", "登録用フォーム");
		return "manager_page";
	}

	//観光スポットの登録用の操作
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
			System.out.println("insertTourist は正常に動作しました。");
			service.insertSpot(spot);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/oritabi/manager_page";

		} else {
			System.out.println("insertTourist でエラーが出ています。");
			//エラーがある場合は、もう一度新規登録画面へ飛びます。
			return showSpotList(spotForm, model);
		}
	}

	/* △△△△△△△△△△ 新規観光地Spot登録 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ 観光地Spot編集 ▼▼▼▼▼▼▼▼▼▼ */
	@GetMapping("/{spotId}")
	public String showUpdateSpot(SpotForm spotForm,
			@PathVariable Integer spotId, Model model) {
		//Spotを取得
		Optional<Spot> spotOpt = service.selectOneByIdSpot(spotId);
		//SpotFormへ詰めなおし
		Optional<SpotForm> spotFormOpt = spotOpt.map(t -> makeSpotForm(t));
		//SpotFormがnullでなければ中身を取り出す
		if (spotFormOpt.isPresent()) {
			spotForm = spotFormOpt.get();
		}
		//更新用のModelを作る
		makeUpdateModel(spotForm, model);
		return "redirect:/oritabi/manager_page";
	}

	//更新用のModelを作成
	private void makeUpdateModel(SpotForm spotForm, Model model) {
		model.addAttribute("spotId", spotForm.getSpotId());
		spotForm.setNewSpot(false);
		model.addAttribute("spotForm", spotForm);
		model.addAttribute("title", "更新用フォーム");
	}

	//spotId をキーにしてデータを更新する
	@PostMapping("/spotUpdate")
	public String spotUpdate(@Validated SpotForm spotForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		//SpotForm から Spotに詰めなおす
		Spot spot = makeSpot(spotForm);
		//入力チェック
		if (!result.hasErrors()) {
			//更新処理、フラッシュスコープの使用、リダイレクト（ここの編集ページ）
			service.updateSpot(spot);
			redirectAttributes.addFlashAttribute("updateComp", "更新が完了しました☆");
			//更新画面の表示
			return "redirect:/oritabi/manager_page" + spot.getSpotId();
		} else {
			//更新用のModelを作る
			makeUpdateModel(spotForm, model);
			return "manager_page";
		}
	}

	/*----- 【以下はFormとDomainObjectの詰めなおし】----- */
	/* Form yから entity へ詰めなおし */
	private Spot makeSpot(SpotForm spotForm) {
		Spot spot = new Spot();
		spot.setSpotId(spotForm.getSpotId());
		spot.setSpotName(spotForm.getSpotName());
		spot.setAddress(spotForm.getAddress());
		spot.setWeb(spotForm.getWeb());
		spot.setTel(spotForm.getTel());
		spot.setPoint(spotForm.getPoint());
		spot.setPurposeId(spotForm.getPurposeId());
		spot.setImageFileName(spotForm.getImageFileName());
		return spot;
	}

	/* ----- spot から spotForm に詰めなおす ---- */
	private SpotForm makeSpotForm(Spot spot) {
		SpotForm form = new SpotForm();
		form.setSpotId(spot.getSpotId());
		form.setSpotName(spot.getSpotName());
		form.setAddress(spot.getAddress());
		form.setWeb(spot.getWeb());
		form.setTel(spot.getTel());
		form.setPoint(spot.getPoint());
		form.setPurposeId(spot.getPurposeId());
		form.setImageFileName(spot.getImageFileName());
		return form;
	}
	/* △△△△△△△△△△ 観光地Spot編集 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ 観光地Spot削除 ▼▼▼▼▼▼▼▼▼▼ */
	/*spotIdをキーにして削除する*/
	@PostMapping("/spotDel")
	public String spotDel(@RequestParam("spotId") String spotId, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println(spotId);
		//タスクを１件削除してリダイレクト
		service.deleteSpotById(Integer.parseInt(spotId));
		redirectAttributes.addFlashAttribute("spotDelComp", "削除がしました");
		return "redirect:/oritabi/manager_page";
	}
	/* △△△△△△△△△△ 観光地Spot削除 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ 新規会員登録 ▼▼▼▼▼▼▼▼▼▼ */

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

	/* △△△△△△△△△△ 新規会員登録 △△△△△△△△△△ */

	/***************************************************************************************************/

}
