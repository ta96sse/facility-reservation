package jp.co.ginga.application.controller.reservation;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityListForm;
import jp.co.ginga.application.form.reservation.ReservationStatusForm;
import jp.co.ginga.application.form.reservation.YearAndMonthForm;
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

		System.out.println(session.getFacilityTypeForm().getId());

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

		FacilityEntity entity = facilityService.getFacility(facilityId);
		List<ReservationEntity> reservationEntityList = reservationService.getReservationList(facilityId,
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));

		ReservationStatusForm statusForm = reservationHelper.createStatusForm(entity, reservationEntityList,
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));

		model.addAttribute("statusForm", statusForm);
		return "reservation/reservation-status";
	}

	/**
	 * カレンダー月変更
	 * @param facilityForm
	 * @return
	 */
	@RequestMapping(path = "/facility-reservation/change-calendar", method = RequestMethod.POST)
	@ResponseBody
	public ReservationStatusForm changeCalendarList(@RequestBody ReservationStatusForm session) {

		YearAndMonthForm yearAndMonthForm = reservationHelper.setYearAndMonthFormByFlag(
				session.getCalendarForm().getYear(), session.getCalendarForm().getMonth(),
				session.getCalendarForm().getChangeCalFlag());

		System.out.println(yearAndMonthForm.getMonth());

		FacilityEntity entity = facilityService.getFacility(session.getFacilityForm().getId());
		List<ReservationEntity> reservationEntityList = reservationService.getReservationList(
				session.getFacilityForm().getId(), yearAndMonthForm.getYear(), yearAndMonthForm.getMonth());

		ReservationStatusForm statusForm = reservationHelper.createStatusForm(entity, reservationEntityList,
				yearAndMonthForm.getYear(), yearAndMonthForm.getMonth());

		return statusForm;
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
