package jp.co.ginga.application.controller.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.application.form.authority.AuthorityLoginForm;
import jp.co.ginga.application.form.session.AccountSessionForm;
import jp.co.ginga.application.helper.authority.AuthorityHelper;
import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.service.AuthorityService;

@Controller
@SessionAttributes("scopedTarget.accountSessionForm")
public class AuthorityController {

	/**
	 * 認証コントローラーヘルパー
	 */
	@Autowired
	AuthorityHelper helper;

	/**
	 * 認証サービスオブジェクト
	 */
	@Autowired
	AuthorityService service;

	/**
	 * セッションオブジェクト
	 */
	@Autowired
	AccountSessionForm accountSessionForm;

	/**
	 *
	 * ログイン画面フォーム作成
	 * @return
	 */
	@ModelAttribute
	AuthorityLoginForm setUpForm() {
		return new AuthorityLoginForm();
	}

	/**
	 * ログイン画面遷移処理
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String createLoginFormGet(AuthorityLoginForm form, Model model) {

		model.addAttribute("authorityLoginForm", form);

		return "authority/authority-login";
	}

	/**
	 * ログインボタン押下処理
	 * @param form
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String clickLoginButtonPost(@Validated AuthorityLoginForm form, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println("clickLoginButtonPost");

		//バリデータチェック処理
		if (result.hasErrors()) {
			result.rejectValue("errorMsg", null, "ログインエラー");
			return "authority/authority-login";
			//			System.out.println("エラー★");
			//			form.setErrorMsg("ログインエラーログインエラーログインエラーログインエラー");
			//			System.out.println(result.toString());
			//			redirectAttributes.addFlashAttribute("authorityLoginForm", form);
			//			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorityLoginForm",result);
			//			return "ridirect:login";
		}

		System.out.println(form.toString());
		//ログインチェック処理
		UserEntity entity = service.checkLoginAccount(helper.convertToUserEntityFromAuthorityLoginForm(form));

		if (entity == null) {
			System.out.println("entity NULL");
			result.rejectValue("errorMsg", null, "ログインID,パスワードが不正です");
//			return "forward:/";
			return "authority/authority-login";

		} else {
			//セッションへの情報登録
			accountSessionForm.setAccountName(entity.getId());
			accountSessionForm.setPermissionLevel(entity.getPermissionLevel());

			//コントローラーを移動する際は、リダイレクトを使用
			return "redirect:menu";
		}

	}

	/**
	 * ログアウト処理
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String createLogoutGet(SessionStatus sessionStatus) {

		//セッションの破棄
		sessionStatus.setComplete();

		return "authority/authority-login";
	}

}
