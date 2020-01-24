/**
 *
 */
package jp.co.ginga.application.controller.facilityreservation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityTypeForm;
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

	//	施設一覧
	@RequestMapping(path = "/facilityreservation/list", method = RequestMethod.GET)
	public String createFacilityListFormGet(Model model, Integer page) throws SQLException {

		List<FacilityEntity> listEntity = service.getFacilityList();
		List<FacilityForm> listForm = helper.convertFromEntityListToFormList(listEntity);

		List<FacilityTypeEntity> typeListEntity = typeService.getFacilityTypeList();
		List<FacilityTypeForm> typeListForm = helper.convertFromTypeEntityListToTypeFormList(typeListEntity);

		model.addAttribute("facilityListForm", listForm);
		model.addAttribute("typeListForm", typeListForm);

		// ページネーション
		PagedListHolder<FacilityForm> pagedListHolder = new PagedListHolder<>(listForm);
		pagedListHolder.setPageSize(5);
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

		return "facilityreservation/reservation-list";
	}
}
