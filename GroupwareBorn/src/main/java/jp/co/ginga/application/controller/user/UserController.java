package jp.co.ginga.application.controller.user;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.application.form.user.UserForm;
import jp.co.ginga.application.form.user.UserListForm;
import jp.co.ginga.application.helper.user.UserHelper;
import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.service.UserService;

/**
 * ユーザーコントローラー
 *
 * @author yoshi
 *
 */
@Controller
@SessionAttributes(names = "userFormSession")
public class UserController {

	private static final String FORM_NAME = "userFormSession";

	/**
	 * ユーザーサービス
	 */
	@Autowired
	UserService service;

	/**
	 * ヘルパー
	 */
	@Autowired
	UserHelper helper;

	/**
	 * ユーザーコントローラー内で使用するセッションオブジェクトの生成処理
	 *
	 * @return
	 */
	@ModelAttribute(value = "userFormSession")
	public UserForm userForm() {
		return new UserForm();
	}

	/**
	 * ユーザー一覧画面遷移処理
	 *
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String createUserListFormGet(Model model) throws SQLException {

		// ユーザ一覧データ取得処理
		List<UserEntity> userEntityList = service.getUserList();

		// データ変換処理
		List<UserForm> userFormList = helper.convertFromUserEntityListToUserFormList(userEntityList);

		// ユーザ一覧画面Formオブジェクト生成
		UserListForm form = new UserListForm();

		// ユーザー一覧情報設定処理
		form.setUserList(userFormList);

		// ビューへの値設定処理
		model.addAttribute("userListForm", form);

		return "user/user-list";
	}

	/**
	 * ユーザー登録画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/add", method = RequestMethod.GET)
	public String createUserAddFormGet(@ModelAttribute(FORM_NAME) UserForm session, ModelMap model) {

		String key = BindingResult.MODEL_KEY_PREFIX + FORM_NAME;

		if (model.containsKey("errors")) {
			model.addAttribute(key, model.get("errors"));
		} else {
			model.addAttribute("userFormSession", session);
		}

		// セッションオブジェクトを設定する処理

		return "user/user-add";

	}

	/**
	 * ユーザー詳細情報画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/detail/{loginId}", method = RequestMethod.GET)
	public String createUserDetailGet(@PathVariable String loginId, ModelMap model) {

		String key = BindingResult.MODEL_KEY_PREFIX + FORM_NAME;

		// ユーザーIDからユーザ情報の取得
		UserEntity user = service.getUser(loginId);

		// データ変換処理
		UserForm userForm = helper.convertFromUserEntityToUserForm(user);

		if (model.containsKey("errors")) {
			model.addAttribute(key, model.get("errors"));
		} else {
			model.addAttribute("userFormSession", userForm);
		}

		// ModelオブジェクトへFormオブジェクトを登録する処理
		return "user/user-detail";

	}

	/**
	 * ユーザー情報確認画面遷移処理
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/user/confirm", params = "add", method = RequestMethod.POST)
	public String createUserConfirmAddPost(@ModelAttribute(FORM_NAME) @Valid UserForm session, BindingResult result,
			Model model, RedirectAttributes ra) throws Exception {

		// バリデータチェック処理
		if (result.hasErrors()) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/user/add";
		}
		// passwordチェック処理
		if (!helper.checkPassword(session)) {

		}

		// 確認画面表示オブジェクト生成処理

		// ModelオブジェクトへFormオブジェクトを登録する処理
		model.addAttribute("userFormSession", session);

		model.addAttribute("btnName", "登録");
		model.addAttribute("btnAction", "add");
		return "user/user-confirm";
	}

	/**
	 * ユーザー情報確認画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/confirm", params = "update", method = RequestMethod.POST)
	public String createUserConfirmUpdatePost(@ModelAttribute(FORM_NAME) @Validated UserForm session,
			BindingResult result, Model model, RedirectAttributes ra) {

		if (result.hasErrors()) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/user/detail/" + session.getLoginName() + "";
		}

		model.addAttribute("btnName", "更新");
		model.addAttribute("btnAction", "update");

		return "user/user-confirm";
	}

	/**
	 * ユーザー情報登録完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/complete", params = "add", method = RequestMethod.POST)
	public String createUserCompleteGet(UserForm session, SessionStatus sessionStatus, Model model) {

		UserEntity user = helper.convertEntityUserForm(session);

		int result = service.add(user);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		// セッションの破棄
		sessionStatus.setComplete();

		return "user/user-complete";
	}

	/**
	 * ユーザー情報更新完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/complete", params = "update", method = RequestMethod.POST)
	public String createUserCompleteUpdatePost(UserForm session, SessionStatus sessionStatus, Model model) {

		UserEntity user = helper.convertEntityUserForm(session);

		int result = service.update(user);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}
		// セッションの破棄
		sessionStatus.setComplete();

		return "user/user-complete";
	}

	/**
	 * ユーザー情報削除完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/complete", params = "delete", method = RequestMethod.POST)
	public String createUserCompleteDeletePost(UserForm session, SessionStatus sessionStatus, Model model) {

		UserEntity user = helper.convertEntityUserForm(session);

		int result = service.delete(user.getId());

		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		// セッションの破棄
		sessionStatus.setComplete();
		return "user/user-complete";
	}

	/**
	 * ユーザー戻るボタン遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/user/back/{name}", method = RequestMethod.GET)
	public String userBack(@PathVariable String name, @ModelAttribute(FORM_NAME) UserForm userForm) {

		if (name.equals("add")) {
			return "redirect:/user/add";
		} else if (name.equals("update")) {
			return "redirect:/user/detail/" + userForm.getLoginName() + "";
		}
		return "error/error";
	}

	/**
	 * ajaxユーザ-重複チェック非同期処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/userCheck", method = RequestMethod.POST)
	@ResponseBody
	public int userDuplicateAjax(@RequestBody UserForm form) {
		UserEntity user = service.getUser(form.getLoginName());
		if (user != null) {
			return 1;
		}
		return 0;

	}

}
