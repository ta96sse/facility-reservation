package jp.co.ginga.application.controller.reservation;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityListForm;
import jp.co.ginga.application.form.reservation.ReservationForm;
import jp.co.ginga.application.form.reservation.ReservationStatusForm;
import jp.co.ginga.application.form.reservation.YearAndMonthForm;
import jp.co.ginga.application.form.session.AccountSessionForm;
import jp.co.ginga.application.helper.facility.FacilityHelper;
import jp.co.ginga.application.helper.reservation.ReservationHelper;
import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.entity.FacilityTypeEntity;
import jp.co.ginga.domain.entity.ReservationEntity;
import jp.co.ginga.domain.service.FacilityService;
import jp.co.ginga.domain.service.FacilityTypeService;
import jp.co.ginga.domain.service.ReservationService;

/**
 * @author yoshi
 *
 */
@Controller
public class ReservationController {

	@Autowired
	FacilityService facilityService;

	@Autowired
	FacilityHelper facilityHelper;

	@Autowired
	ReservationHelper reservationHelper;

	@Autowired
	ReservationService reservationService;

	@Autowired
	FacilityTypeService typeService;

	@Autowired
	AccountSessionForm accountSession;

	/**
	 * 施設予約施設一覧表示
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/facility-reservation/list", method = RequestMethod.GET)
	public String createFacilityList(Model model) {

		//施設一覧取得
		List<FacilityEntity> listEntity = facilityService.getFacilityList();
		//施設種別一覧取得
		List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();

		//施設一覧をEntityからFormへコンバート処理の後FacilityListFormへ
		FacilityListForm facilityListForm = facilityHelper.convertFromEntityToListForm(listEntity, typeListEntity);

		//値にキーをつけてmodelへ格納
		model.addAttribute("facilityListForm", facilityListForm);
		return "reservation/reservation-list";
	}

	/**
	 * セレクターで施設種別一覧表示
	 * @param facilityForm
	 * @return
	 */
	@RequestMapping(path = "/facility-reservation-list", method = RequestMethod.POST)
	@ResponseBody
	public List<FacilityForm> remakeFacilityList(@RequestBody FacilityForm session) {

		List<FacilityEntity> listEntity;

		if (session.getFacilityTypeForm().getId() == 0) {
			listEntity = facilityService.getFacilityList();
		} else {
			listEntity = facilityService.getFacilityList(session.getFacilityTypeForm().getId());
		}

		List<FacilityForm> listForm = facilityHelper.convertFromEntityListToFormList(listEntity);
		return listForm;
	}

	/*
	 * カレンダー表示
	 */
	@RequestMapping(path = "/facility-reservation/{facilityId}", method = RequestMethod.GET)
	public String createCalendarListGet(@PathVariable int facilityId, Model model) {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		FacilityEntity entity = facilityService.getFacility(facilityId);
		List<ReservationEntity> reservEntityList = reservationService.getReservationList(facilityId, year, month);

		ReservationStatusForm statusForm = reservationHelper.createStatusForm(entity, reservEntityList, year, month);
		model.addAttribute("statusForm", statusForm);
		return "reservation/reservation-status";
	}

	/*
	 * カレンダー月変更
	 */
	@RequestMapping(path = "/facility-reservation/change-calendar", method = RequestMethod.POST)
	@ResponseBody
	public ReservationStatusForm changeCalendarList(@RequestBody ReservationStatusForm session) {

		YearAndMonthForm yearAndMonthForm = reservationHelper.setYearAndMonthByFlag(
				session.getCalendarForm().getYear(), session.getCalendarForm().getMonth(),
				session.getCalendarForm().getChangeCalFlag());

		FacilityEntity entity = facilityService.getFacility(session.getFacilityForm().getId());
		List<ReservationEntity> reservEntityList = reservationService.getReservationList(
				session.getFacilityForm().getId(), yearAndMonthForm.getYear(), yearAndMonthForm.getMonth());

		ReservationStatusForm statusForm = reservationHelper.createStatusForm(entity, reservEntityList,
				yearAndMonthForm.getYear(), yearAndMonthForm.getMonth());

		return statusForm;
	}

	/*
	 * 新規予約
	 */
	@RequestMapping(path = "/facility-reservation/{facilityId}/add", method = RequestMethod.GET)
	public String createReservAddFormGet(@PathVariable int facilityId, @ModelAttribute ReservationForm session,
			Model model) {

		FacilityForm facilityForm = facilityHelper.convertFromEntityToForm(facilityService.getFacility(facilityId));
		session.setFacilityForm(facilityForm);

		model.addAttribute("session", session);

		return "reservation/reservation-add";
	}

	/*
	 * 予約詳細
	 */
	@RequestMapping(path = "/facility-reservation/{facilityId}/detail/{reservationId}", method = RequestMethod.GET)
	public String createReservDetailFormGet(@PathVariable int reservationId, @ModelAttribute ReservationForm session,
			Model model) {

		ReservationForm reservationForm = reservationHelper
				.convertFromReservEntityToReservForm(reservationService.getReservation(reservationId));
		model.addAttribute("session", reservationForm);

		return "reservation/reservation-detail";
	}

	/*
	 * 予約時間チェック
	 */
	@RequestMapping(path = "/facility-reservation/check-reservation", method = RequestMethod.POST)
	@ResponseBody
	public int checkReservation(@RequestBody ReservationForm session) {

		int result = reservationService.check(session.getFacilityForm().getId(), session.getYear(), session.getMonth(), session.getDate());
		if (result != 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@RequestMapping(path = "/facility-reservation/confirm", method = RequestMethod.POST)
	public String createReservConfirmPost(@ModelAttribute ReservationForm session, Model model) {

		model.addAttribute("session", session);

		return "reservation/reservation-confirm";
	}

	@RequestMapping(path = "/facility-reservation/complete", params = "add", method = RequestMethod.POST)
	public String createReservCompleteAddPost(ReservationForm session, SessionStatus sessionStatus, Model model)
			throws ParseException {

		ReservationEntity reservationEntity = reservationHelper.convertFromReservFormToReservEntityForAdd(session,
				accountSession);
		int result = reservationService.add(reservationEntity);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "予約");
		sessionStatus.setComplete();

		return "reservation/reservation-complete";
	}

	@RequestMapping(path = "/facility-reservation/complete", params = "update", method = RequestMethod.POST)
	public String createReservCompleteUpdatePost(ReservationForm session, SessionStatus sessionStatus, Model model)
			throws ParseException {

		ReservationEntity reservationEntity = reservationHelper.convertFromReservFormToReservEntityForUpdate(session,
				accountSession);

		int result = reservationService.update(reservationEntity);
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "更新");
		sessionStatus.setComplete();

		return "reservation/reservation-complete";
	}

	@RequestMapping(path = "/facility-reservation/complete", params = "delete", method = RequestMethod.POST)
	public String createReservCompleteDeletePost(ReservationForm session, SessionStatus sessionStatus, Model model) {

		int result = reservationService.delete(session.getId());
		if (result != 1) {
			SQLException e = new SQLException();
			model.addAttribute("message", e);
			model.addAttribute("detail", "例外 : " + e.getMessage() + "");
			return "error/error";
		}

		model.addAttribute("title", "削除");
		sessionStatus.setComplete();

		return "reservation/reservation-complete";
	}

	//		// ページネーション
	//		PagedListHolder<FacilityForm> pagedListHolder = new PagedListHolder<>(facilityListForm.getFacilityList());
	//		pagedListHolder.setPageSize(5);
	//		model.addAttribute("maxPages", pagedListHolder.getPageCount());
	//		if (page == null || page < 1 || page > pagedListHolder.getPageCount())
	//			page = 1;
	//		model.addAttribute("page", page);
	//		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
	//			pagedListHolder.setPage(0);
	//			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
	//		} else if (page <= pagedListHolder.getPageCount()) {
	//			pagedListHolder.setPage(page - 1);
	//			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
	//		}

}
