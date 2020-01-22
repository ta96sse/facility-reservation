/**
 *
 */
package jp.co.ginga.application.helper.facility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityTypeForm;
import jp.co.ginga.application.form.session.AccountSessionForm;
import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.entity.FacilityTypeEntity;

/**
 * @author yoshi
 *
 */
@Component
public class FacilityHelper {

	/**
	 * FacilityEntityListからFacilityFormListへのコンバート処理
	 *
	 * @param list
	 * @return
	 */
	public List<FacilityForm> convertFromFacilityEntityListToFacilityFormList(List<FacilityEntity> list) {
		List<FacilityForm> convertList = new ArrayList<FacilityForm>();

		for (FacilityEntity entity : list) {

			convertList.add(convertFromFacilityEntityToFacilityForm(entity));
		}
		return convertList;
	}

	/**
	 * FacilityEntityからFacilityFormへのコンバート処理
	 *
	 * @param Facility
	 * @return
	 */
	public FacilityForm convertFromFacilityEntityToFacilityForm(FacilityEntity facility) {
		return new FacilityForm(facility.getId(), facility.getName(),
				facility.getCapacity(), new FacilityTypeForm(facility.getFacilityTypeEntity().getId(),
						facility.getFacilityTypeEntity().getName()));
	}

	/**
	 * FacilityFormからFacilityEntityへのコンバート処理
	 *
	 * @param Facility
	 * @return
	 */
	public FacilityEntity convertFromFormToEntity(FacilityForm session,
			AccountSessionForm accountSessionForm) {
		return new FacilityEntity(session.getId(), session.getName(), session.getTypeId(),
				session.getCapacity(), accountSessionForm.getAccountName());
	}

	//FacilityTypeEntityListからFacilityTypeFormListへのコンバート処理
	public List<FacilityTypeForm> convertFromTypeEntityListToTypeFormList(List<FacilityTypeEntity> list) {
		List<FacilityTypeForm> convertList = new ArrayList<FacilityTypeForm>();

		for (FacilityTypeEntity entity : list) {

			convertList.add(convertFromTypeEntityToTypeForm(entity));
		}
		return convertList;
	}

	//	FacilityTypeEntityからFacilityTypeFormへのコンバート処理
	public FacilityTypeForm convertFromTypeEntityToTypeForm(FacilityTypeEntity facilityType) {
		return new FacilityTypeForm(facilityType.getId(), facilityType.getName());
	}

	//	FacilityTypeFormからFacilityTypeEntityへのコンバート処理
	public FacilityTypeEntity convertFromTypeFormToTypeEntity(FacilityTypeForm facilityType) {
		return new FacilityTypeEntity(facilityType.getId());
	}

	//	/**
	//	 * FacilitySessionFormからFacilityEntityへのコンバート処理
	//	 *
	//	 * @param Facility
	//	 * @return
	//	 */
	//	public FacilityEntity convertFromSessionFormToEntity(FacilityForm session,
	//			AccountSessionForm accountSessionForm) {
	//		return new FacilityEntity(session.getId(), session.getName(), session.getFacilityTypeForm().getId(),
	//				session.getCapacity(), accountSessionForm.getAccountName());
	//	}
	//
	//	//	FacilitySessionFormへのコンバート処理
	//	public FacilitySessionForm convertToFacilitySessionForm(FacilityEntity facility, List<FacilityTypeEntity> list) {
	//		FacilitySessionForm form = new FacilitySessionForm();
	//
	//		//FacilityEntityからFacilityFormへの変換
	//		form.setFacilityForm(new FacilityForm(facility.getId(), facility.getName(), facility.getCapacity(),
	//				new FacilityTypeForm(facility.getFacilityTypeEntity().getId(),
	//						facility.getFacilityTypeEntity().getName())));
	//
	//		//		List<FacilityTypeEntity>からList<FacilityTypeForm>への変換
	//		form.setFacilityTypeList(convertFromTypeEntityListToTypeFormList(list));
	//
	//		//		List<FacilityTypeForm> formList = new ArrayList<FacilityTypeForm>();
	//		//		for (FacilityTypeEntity val : list) {
	//		//
	//		//			formList.add(new FacilityTypeForm(val.getId(), val.getName()));
	//		//
	//		//		}
	//
	//		return form;
	//	}
	//
	//	public List<FacilityTypeForm> convertFromEntityListToFacilitySessionForm(List<FacilityTypeEntity> list) {
	//		FacilitySessionForm form = new FacilitySessionForm();
	//
	//		//List<FacilityTypeEntity>からList<FacilityTypeForm>への変換
	//		List<FacilityTypeForm> formList = new ArrayList<FacilityTypeForm>();
	//		for (FacilityTypeEntity val : list) {
	//
	//			formList.add(new FacilityTypeForm(val.getId(), val.getName()));
	//
	//		}
	//
	//		//		form.setFacilityTypeList(formList);
	//
	//		return formList;
	//	}

}
