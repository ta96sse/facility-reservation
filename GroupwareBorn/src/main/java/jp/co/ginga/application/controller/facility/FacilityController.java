package jp.co.ginga.application.controller.facility;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilitySessionForm;
import jp.co.ginga.application.form.facility.FacilityTypeForm;
import jp.co.ginga.application.form.session.AccountSessionForm;
import jp.co.ginga.application.helper.facility.FacilityHelper;
import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.entity.FacilityTypeEntity;
import jp.co.ginga.domain.service.FacilityService;
import jp.co.ginga.domain.service.FacilityTypeService;

/**
 * @author yoshi
 *
 */
@Controller
@SessionAttributes(names = "facilitySessionForm")
public class FacilityController {
	private static final String FORM_NAME = "facilitySessionForm";

	/**
	 * 施設サービス
	 */
	@Autowired
	FacilityService service;

	@Autowired
	FacilityTypeService typeService;

	/**
	 * ヘルパー
	 */
	@Autowired
	FacilityHelper helper;

	@Autowired
	AccountSessionForm accountSessionForm;

	/**
	 * 施設コントローラー内で使用するセッションオブジェクトの生成処理
	 *
	 * @return
	 */
	@ModelAttribute(value = "facilitySessionForm")
	public FacilitySessionForm facilitySessionForm() {
		return new FacilitySessionForm();
	}

	/**
	 * 施設一覧画面遷移処理
	 *
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(path = "/facility/list", method = RequestMethod.GET)
	public String createFacilityListFormGet(SessionStatus sessionStatus, Model model, Integer page)
			throws SQLException {

		sessionStatus.setComplete();

		// 施設一覧データ取得処理
		List<FacilityEntity> listEntity = service.getFacilityList();

		// データ変換処理
		List<FacilityForm> listForm = helper.convertFromEntityListToFormList(listEntity);

		// ビューへの値設定処理
		model.addAttribute("facilityListForm", listForm);

		// ページネーション
		PagedListHolder<FacilityForm> pagedListHolder = new PagedListHolder<>(listForm);
		pagedListHolder.setPageSize(10); // 10件毎に表示
		model.addAttribute("maxPages", pagedListHolder.getPageCount());
		if (page == null || page < 1 || page > pagedListHolder.getPageCount())
			page = 1;
		model.addAttribute("page", page);
		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
		} else if (page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
		}

		return "facility/facility-list";
	}

	/**
	 * 施設登録画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/add", method = RequestMethod.GET)
	public String createFacilityAddFormGet(FacilitySessionForm session, ModelMap model) {

		String key = BindingResult.MODEL_KEY_PREFIX + FORM_NAME;

		if (model.containsKey("errors")) {
			BindingResult br = (BindingResult) model.get("errors");
			br.rejectValue("systemMsg", null, "システムエラー");
			model.addAttribute(key, model.get("errors"));

		} else {
			List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();

			List<FacilityTypeForm> typeListForm = helper.convertFromTypeEntityListToTypeFormList(typeListEntity);
			session.setFacilityTypeList(typeListForm);

			model.addAttribute("facilitySessionForm", session);
		}

		return "facility/facility-add";

	}

	/**
	 * 施設詳細情報画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/detail/{id}", method = RequestMethod.GET)
	public String createFacilityDetailGet(@PathVariable int id, FacilitySessionForm session, ModelMap model) {

		String key = BindingResult.MODEL_KEY_PREFIX + FORM_NAME;

		if (model.containsKey("errors")) {
			model.addAttribute(key, model.get("errors"));
		} else {

			if (session.getFacilityForm() == null) {
				FacilityEntity entity = service.getFacility(id);
				List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();
				FacilitySessionForm sessionForm = helper.convertFromEntityToSessionForm(entity, typeListEntity);
				model.addAttribute("facilitySessionForm", sessionForm);

			} else {
				model.addAttribute("facilitySessionForm", session);
			}

		}

		// ModelオブジェクトへFormオブジェクトを登録する処理
		return "facility/facility-detail";

	}

	/**
	 * 施設情報確認画面遷移処理
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/facility/confirm", params = "add", method = RequestMethod.POST)
	public String createFacilityConfirmAddPost(@ModelAttribute(FORM_NAME) @Validated FacilitySessionForm session,
			BindingResult result, Model model, RedirectAttributes ra) throws Exception {

		// バリデータチェック処理
		if (result.hasErrors()) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/facility/add";
		}

		FacilityTypeEntity typeEntity = typeService
				.getFacilityType(session.getFacilityForm().getFacilityTypeForm().getId());
		session.getFacilityForm().setFacilityTypeForm(helper.convertFromTypeEntityToTypeForm(typeEntity));

		model.addAttribute("facilitySessionForm", session);

		model.addAttribute("title", "施設情報登録");
		model.addAttribute("btnName", "登録");
		model.addAttribute("btnAction", "add");
		return "facility/facility-confirm";
	}

	/**
	 * 施設情報確認画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/confirm", params = "update", method = RequestMethod.POST)
	public String createFacilityConfirmUpdatePost(@ModelAttribute(FORM_NAME) @Validated FacilitySessionForm session,
			BindingResult result, Model model, RedirectAttributes ra) {

		if (result.hasErrors()) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/facility/detail/" + session.getFacilityForm().getId() + "";
		}

		FacilityTypeEntity typeEntity = typeService
				.getFacilityType(session.getFacilityForm().getFacilityTypeForm().getId());
		session.getFacilityForm().setFacilityTypeForm(helper.convertFromTypeEntityToTypeForm(typeEntity));

		model.addAttribute("facilitySessionForm", session);

		model.addAttribute("title", "施設情報更新");
		model.addAttribute("btnName", "更新");
		model.addAttribute("btnAction", "update");

		return "facility/facility-confirm";
	}

	/**
	 * 施設情報登録完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/complete", params = "add", method = RequestMethod.POST)
	public String createFacilityCompleteGet(FacilitySessionForm session,
			SessionStatus sessionStatus, Model model) {

		FacilityEntity entity = helper.convertFromFormToEntity(session.getFacilityForm(), accountSessionForm);

		int result = service.add(entity);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "施設情報登録完了");
		// セッションの破棄
		sessionStatus.setComplete();

		return "facility/facility-complete";
	}

	/**
	 * 施設情報更新完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/complete", params = "update", method = RequestMethod.POST)
	public String createFacilityCompleteUpdatePost(FacilitySessionForm session,
			SessionStatus sessionStatus, Model model) {

		FacilityEntity entity = helper.convertFromFormToEntity(session.getFacilityForm(), accountSessionForm);

		int result = service.update(entity);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "施設情報更新完了");
		// セッションの破棄
		sessionStatus.setComplete();

		return "facility/facility-complete";
	}

	/**
	 * 施設情報削除完了画面遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/complete", params = "delete", method = RequestMethod.POST)
	public String createFacilityCompleteDeletePost(FacilitySessionForm session,
			SessionStatus sessionStatus, Model model) {

		FacilityEntity entity = helper.convertFromFormToEntity(session.getFacilityForm(), accountSessionForm);

		int result = service.delete(entity.getId());
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "施設情報削除完了");
		// セッションの破棄
		sessionStatus.setComplete();

		return "facility/facility-complete";
	}

	/**
	 * 施設戻るボタン遷移処理
	 *
	 * @return
	 */
	@RequestMapping(path = "/facility/back/{param}", method = RequestMethod.GET)
	public String facilityBack(@PathVariable String param, FacilitySessionForm session) {

		if (param.equals("add")) {
			return "redirect:/facility/add";
		} else if (param.equals("update")) {
			return "redirect:/facility/detail/" + session.getFacilityForm().getId() + "";
		}
		return "error/error";
	}

}
