package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.demo.entity.History;
import com.example.demo.entity.Spot;
import com.example.demo.form.CheckForm;
import com.example.demo.form.CostomerForm;
import com.example.demo.form.HistoryForm;
import com.example.demo.form.PurposeForm;
import com.example.demo.form.SpotForm;
import com.example.demo.service.OritabiService;
import com.example.demo.service.userdetails.UserDetailsImpl;

//Oritabiのコントローラー-----☆
@Controller
@RequestMapping("/oritabi")
public class OritabiController {

	//DI対象（依存対象）
	@Autowired
	OritabiService service;

	//「form-backing bean」の初期化
	@ModelAttribute
	public SpotForm setUpSpotForm() {
		//		System.out.println("setupSpotForm");
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
		//		System.out.println("setupCostomerForm");
		CostomerForm form = new CostomerForm();
		form.setNewCostomer(true); //データ入れたり、表示しない時はコメントアウト
		return form;
	}

	@ModelAttribute
	public HistoryForm setUpHistoryForm() {
		HistoryForm form = new HistoryForm();
		return form;
	}

	@ModelAttribute
	public CheckForm setUpCheckForm() {
		CheckForm form = new CheckForm();
		return form;
	}

	//	//SPOTView呼び出し
	//	@GetMapping("/spotcall")
	//	public String callSpotView(Model model, UserDetailsImpl user) {
	//		System.out.println("GETcall");
	//		System.out.println("user.getId() : " + user.getId());
	//		if (user.getId() == 1) {
	//			return "manager_page";
	//		} else {
	//			return spotView(model);
	//		}
	//	}

	@GetMapping("/spotcall")
	public String loginOk(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
		System.out.println("Test" + principal.getId());
		if (principal.getId() == 1) {
			return showSpotList(model);
		} else {
			return spotView(model);
		}
	}

	@PostMapping("/spotcall")
	public String callSpotpost(Model model) {
		System.out.println("POSTcall");
		return spotView(model);

	}

	/* ▼▼▼▼▼▼▼▼▼▼ HTML 表示用メソッド ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/manager_login")
	public String showLogin() {
		return "manager_login";
	}

	//	@GetMapping("/manager_page")
	//	public String showManagerPage() {
	//		return "manager_page";
	//	}

	//検証用spot
	//		@GetMapping("/showspot")
	//		public String showSpot() {
	//			return "/spotSelect";
	//		}

	@GetMapping("/out")
	public String showOut() {
		return "out";
	}

	//	@GetMapping("/purpose_osaka")
	//	public String showPurpose() {
	//		return "purpose_osaka";
	//	}

	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}

	//	@PostMapping("/spot")
	//	public String showSpot(Model model) {
	//		spotView(model);
	//		System.out.println("spot入" + model.getAttribute("spottour"));
	//		return "spot";
	//	}

	@GetMapping("/top")
	public String showTop() {
		return "top";
	}

	/* △△△△△△△△△△ HTML 表示用メソッド △△△△△△△△△△ */

	//管理者ページ↓
	/* ▼▼▼▼▼▼▼▼▼▼ 新規観光地Spot登録 ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/manager_page")
	public String showSpotList(Model model) {
		//新規登録設定
		//spotForm.setNewSpot(true);

		//新規登録情報の一覧を取得
		Iterable<Spot> list = service.selectAllSpot();
		for (Spot i : list) {
			System.out.println(i.getSpotName());
		}
		//表示用Modelへ格納
		model.addAttribute("spotList", list);
		//model.addAttribute("title", "登録用フォーム");

		//客情報一覧の取得
		costomerList(model);

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
		//		System.out.println(spot);

		//入力チェック
		if (!bindingResult.hasErrors()) {
			System.out.println("insertTourist は正常に動作しました。");
			service.insertSpot(spot);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/oritabi/manager_page";

		} else {
			System.out.println("insertTourist でエラーが出ています。");
			//エラーがある場合は、もう一度新規登録画面へ飛びます。
			return showSpotList(model);
		}
	}

	/* △△△△△△△△△△ 新規観光地Spot登録 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ 観光地Spot編集 ▼▼▼▼▼▼▼▼▼▼ */
	@GetMapping("/{spotId}")
	public String showUpdateSpot(SpotForm spotForm,
			@PathVariable Integer spotId, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("showUpdateSpot");
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
		costomerList(model);
		spotDel(spotId.toString(), model, redirectAttributes);
		return "manager_page";
	}

	//更新用のModelを作成
	private void makeUpdateModel(SpotForm spotForm, Model model) {
		System.out.println("makeUpdateModel");
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
		System.out.println("spotUpdate");
		//SpotForm から Spotに詰めなおす
		Spot spot = makeSpot(spotForm);
		//入力チェック
		if (!result.hasErrors()) {
			//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ）
			service.updateSpot(spot);
			redirectAttributes.addFlashAttribute("updateComp", "☆更新が完了しました☆");
			//更新画面の表示
			return "redirect:/oritabi/manager_page";
		} else {
			//更新用のModelを作る
			makeUpdateModel(spotForm, model);
			return "manager_page";
		}
	}

	/*----- 【以下はFormとDomainObjectの詰めなおし】----- */
	/* Form から entity へ詰めなおし */
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
		redirectAttributes.addFlashAttribute("spotDelComp", "削除が完了しました");
		return "redirect:/oritabi/manager_page";
	}
	/* △△△△△△△△△△ 観光地Spot削除 △△△△△△△△△△ */
	//管理者ページ　終了

	//Spot ページで表示↓
	/* ▼▼▼▼▼▼▼▼▼▼ 観光地Spot表示 ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/spotView")
	public String spotView(Model model) {
		Iterable<Spot> spotall = service.selectAllSpot();

		List<Spot> spottaberu = new ArrayList<>();
		List<Spot> spottour = new ArrayList<>();
		List<Spot> spotasobu = new ArrayList<>();

		for (Spot s : spotall) {
			switch (s.getPurposeId()) {
			case 1:
				spottaberu.add(s);
				break;

			case 2:
				spottour.add(s);
				break;

			case 3:
				spotasobu.add(s);
				break;
			}
		}
		model.addAttribute("spottaberu", spottaberu);
		model.addAttribute("spottour", spottour);
		model.addAttribute("spotasobu", spotasobu);

		System.out.println(spottour);

		return "spot";

	}
	/* △△△△△△△△△△ 観光地Spot表示 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ 観光地SpotチェックBOX 操作☆ ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/map")
	public String checkBoxview(CheckForm checkform, Model model) {

		//		List<Spot> checkBoxSpot = new ArrayList<>();
		//
		//		for (Spot s : checkBoxSpot) {
		//			checkBoxSpot.add(s);
		//		}

		System.out.println(checkform.getCheckBoxSpot());
		model.addAttribute("spotcheck", checkform.getCheckBoxSpot());

		return "map";
	}

	/* △△△△△△△△△△ 観光地SpotチェックBOX 操作 △△△△△△△△△△ */
	//Spot ページで表示↑

	/***************************************************************************************************/

	//Registerページ↓
	/* ▼▼▼▼▼▼▼▼▼▼ 新規会員登録 ▼▼▼▼▼▼▼▼▼▼ */

	//新規登録画面の操作
	@PostMapping("/spotSelect")
	public String insertCostomer(@Validated CostomerForm costomerForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		System.out.println("+++++++++++++++++++++++");
		//Form から entity へ詰め替え
		Costomer costomer = new Costomer();
		costomer.setName(costomerForm.getName());
		costomer.setMail(costomerForm.getMail());
		costomer.setMailView(costomerForm.getMailView());
		costomer.setPass(costomerForm.getPass());
		costomer.setPassView(costomerForm.getPassView());

		//入力チェック
		if (costomerForm.getMail().equals(costomerForm.getMailView()) &&
				costomerForm.getPass().equals(costomerForm.getPassView())) {
			//        	if(costomerForm.getId()==1) {
			//        		 return "manager_page";
			//        	}

			//            System.out.println("\\\\\\\\\\");
			if (!bindingResult.hasErrors()) {
				//				System.out.println("Form : " + costomerForm);
				//				System.out.println("entity : " + costomer);
				costomer.setSecretPass(new BCryptPasswordEncoder().encode(costomerForm.getPass()));
				service.insertCostomer(costomer);
				redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
				showCostomerList(costomerForm, model);
				spotView(model);
				return "spot";

			} else {
				//エラーがある場合は、もう一度新規登録画面へ飛びます。
				System.out.println(bindingResult.getAllErrors());
				return "register";
			}
		} else {
			System.out.println("ERAAA");
			model.addAttribute("miss", "確認用メールアドレスか、確認用パスワードが違います。");
			return "register";
		}
	}

	@PostMapping("/costomerList")
	public String showCostomerList(CostomerForm costomerForm, Model model) {
		System.out.println("-------- showCostomerList に入りました -------");
		//新規登録設定
		costomerForm.setNewCostomer(true);
		//新規登録情報の一覧を取得
		Iterable<Costomer> list = service.selectAllCostomer();
		for (Costomer i : list) {
			System.out.println(i.getName());
		}
		//表示用Modelへ格納
		model.addAttribute("costomerList", list);
		//model.addAttribute("title", "登録用フォーム");
		return "spot";
	}

	public void costomerList(Model model) {
		System.out.println("-------- costomerList に入りました -------");
		//新規登録情報の一覧を取得
		Iterable<Costomer> list = service.selectAllCostomer();
		for (Costomer i : list) {
			System.out.println(i.getName());
		}
		//表示用Modelへ格納
		model.addAttribute("costomerList", list);
		//model.addAttribute("title", "登録用フォーム");
	}

	/* △△△△△△△△△△ 新規会員登録 △△△△△△△△△△ */

	/* ▼▼▼▼▼▼▼▼▼▼ MyPage に使うメソッド ▼▼▼▼▼▼▼▼▼▼ */

	@GetMapping("/costomer/{id}")
	public String getCostomerInf(@PathVariable Integer id, Model model) {
		Costomer costomer = service.selectOneByIdCostomer(id).orElse(null);

		if (costomer != null) {
			model.addAttribute("costomer", costomer);
		}

		return "myPage";
	}

	//	public String showTop(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
	//		// ログインしたユーザー情報を画面に表示するために記述。
	//		model.addAttribute("loginUsername", userPrincipal.getUsername());
	//
	//		return "myPage";
	//	}
	@GetMapping("/myPage")
	public String showMyPage(HistoryForm historyForm, Model model,
			@AuthenticationPrincipal UserDetailsImpl userPrincipal) {
		History history = new History();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();

		System.out.println("名前" + userPrincipal.getCusotmname());

		history.setCostomerId(principal.getId());
		history.setTouristId_1(historyForm.getTouristId_1());
		history.setTouristId_2(historyForm.getTouristId_2());
		history.setTouristId_3(historyForm.getTouristId_3());
		history.setTouristId_4(historyForm.getTouristId_4());
		history.setTouristId_5(historyForm.getTouristId_5());
		history.setTime_1(historyForm.getTime_1());
		history.setTime_2(historyForm.getTime_2());
		history.setTime_3(historyForm.getTime_3());
		history.setTime_4(historyForm.getTime_4());
		history.setTotalDuration(historyForm.getTotalDuration());

		service.insertHistory(history);
		model.addAttribute("hl", historyForm);
		model.addAttribute("loginUsername", userPrincipal.getCusotmname());

		return "myPage";
	}

	@GetMapping("/myPageShowList")
	public String getHistoryList(Model model, @AuthenticationPrincipal UserDetailsImpl userPrincipal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();

		Integer costomerId = principal.getId();
		System.out.println("id=" + costomerId);

		Optional<History> historyOpt = service.selectOneByIdHistory(costomerId);
		model.addAttribute("hl", historyOpt.get());
		model.addAttribute("loginUsername", userPrincipal.getCusotmname());
		return "myPage";
	}

	//修正ボタン用のメソッド
	@GetMapping("/backToMap")
	public String backToMap(Model model, CheckForm checkForm, @AuthenticationPrincipal UserDetailsImpl userPrincipal) {
		Optional<History> historyOpt = service.selectOneByIdHistory(userPrincipal.getId());

		List<String> checkBoxSpot = new ArrayList<>();

		for (int i = 1; i < 6; i++) {
			switch (i) {
			case 1:
				if (!historyOpt.get().getTouristId_1().isEmpty()) {
					checkBoxSpot.add(historyOpt.get().getTouristId_1());
				}
				break;

			case 2:
				if (!historyOpt.get().getTouristId_2().isEmpty()) {
					checkBoxSpot.add(historyOpt.get().getTouristId_2());
				}
				break;

			case 3:
				if (!historyOpt.get().getTouristId_3().isEmpty()) {
					checkBoxSpot.add(historyOpt.get().getTouristId_3());
				}
				break;

			case 4:
				if (!historyOpt.get().getTouristId_4().isEmpty()) {
					checkBoxSpot.add(historyOpt.get().getTouristId_4());
				}
				break;

			case 5:
				if (!historyOpt.get().getTouristId_5().isEmpty()) {
					checkBoxSpot.add(historyOpt.get().getTouristId_5());
				}
			}

		}

		System.out.println(checkBoxSpot);
		model.addAttribute("spotcheck", checkBoxSpot);
		return "map";
	}
	/* △△△△△△△△△△ MyPage に使うメソッド △△△△△△△△△△ */
}
