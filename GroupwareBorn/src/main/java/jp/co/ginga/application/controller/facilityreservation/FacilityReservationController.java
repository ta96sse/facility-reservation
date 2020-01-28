package jp.co.ginga.application.controller.facilityreservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityListForm;
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
public class FacilityReservationController {

	@Autowired
	FacilityService service;

	@Autowired
	FacilityHelper helper;

	@Autowired
	FacilityTypeService typeService;

	/**
	 * 施設予約施設一覧表示
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/facilityreservation/list", method = RequestMethod.GET)
	public String createFacilityList(Model model) {

		//施設一覧取得
		List<FacilityEntity> listEntity = service.getFacilityList();
		//施設種別一覧取得
		List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();

		//施設一覧をEntityからFormへコンバート処理の後FacilityListFormへ
		FacilityListForm facilityListForm = helper.convertFromEntityToListForm(listEntity, typeListEntity);

		//値にキーをつけてmodelへ格納
		model.addAttribute("facilityListForm", facilityListForm);
		return "facilityreservation/reservation-list";

	}

	/**
	 * セレクターで施設種別一覧表示
	 * @param facilityForm
	 * @return
	 */
	@RequestMapping(path = "/facilityreservation-list", method = RequestMethod.POST)
	@ResponseBody
	public List<FacilityForm> remakeFacilityList(@RequestBody FacilityForm session) {

		System.out.println(session.getTypeId());
		List<FacilityEntity> listEntity;
		//施設種別一致の施設一覧取得
		if (session.getTypeId() == 0) {
			listEntity = service.getFacilityList();
		} else {
			listEntity = service.getFacilityList(session.getTypeId());
		}

		//施設一覧をEntityからFormへコンバート処理の後FacilityListFormへ
		return helper.convertFromEntityListToFormList(listEntity);
	}

	//	//	施設一覧
	//	@RequestMapping(path = "/facilityreservation/list", method = RequestMethod.GET)
	//	public String createFacilityListFormGet(Model model, Integer page) throws SQLException {
	//
	//		List<FacilityEntity> listEntity = service.getFacilityList();
	//		List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();
	//
	//		FacilityListForm facilityListForm = helper.convertFromEntityToListForm(listEntity, typeListEntity);
	//
	//		model.addAttribute("facilityListForm", facilityListForm);
	//
	//		//		// ページネーション
	//		//		PagedListHolder<FacilityForm> pagedListHolder = new PagedListHolder<>(facilityListForm.getFacilityList());
	//		//		pagedListHolder.setPageSize(5);
	//		//		model.addAttribute("maxPages", pagedListHolder.getPageCount());
	//		//		if (page == null || page < 1 || page > pagedListHolder.getPageCount())
	//		//			page = 1;
	//		//		model.addAttribute("page", page);
	//		//		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
	//		//			pagedListHolder.setPage(0);
	//		//			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
	//		//		} else if (page <= pagedListHolder.getPageCount()) {
	//		//			pagedListHolder.setPage(page - 1);
	//		//			model.addAttribute("facilityListForm", pagedListHolder.getPageList());
	//		//		}
	//
	//		return "facilityreservation/reservation-list";
	//	}
	//
	//	//	//	セレクターで施設種別一覧表示
	//	//	@RequestMapping(path = "/facilityreservation-list", method = RequestMethod.POST)
	//	//	@ResponseBody
	//	//	public List<FacilityForm> remakeFacilityList(@RequestBody int typeId) {
	//	//
	//	//		List<FacilityEntity> listEntity;
	//	//
	//	//		//施設種別一致の施設一覧取得
	//	//		if (typeId == 0) {
	//	//			listEntity = service.getFacilityList();
	//	//		} else {
	//	//			listEntity = service.getFacilityList(typeId);
	//	//		}
	//	//		//施設一覧をEntityからFormへコンバート処理の後FacilityListFormへ
	//	//		List<FacilityForm> listForm = helper.convertFromEntityListToFormList(listEntity);
	//	//		return listForm;
	//	//	}

}
