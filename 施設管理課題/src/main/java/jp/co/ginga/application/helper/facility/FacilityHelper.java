package jp.co.ginga.application.helper.facility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityListForm;
import jp.co.ginga.application.form.facility.FacilitySessionForm;
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
	public List<FacilityForm> convertFromEntityListToFormList(List<FacilityEntity> listEntity) {
		List<FacilityForm> listForm = new ArrayList<FacilityForm>();

		for (FacilityEntity entity : listEntity) {

			listForm.add(convertFromEntityToForm(entity));
		}
		return listForm;
	}

	/**
	 * FacilityEntityからFacilityFormへのコンバート処理
	 *
	 * @param Facility
	 * @return
	 */
	public FacilityForm convertFromEntityToForm(FacilityEntity entity) {
		return new FacilityForm(entity.getId(), entity.getName(),
				entity.getCapacity(), new FacilityTypeForm(entity.getFacilityTypeEntity().getId(),
						entity.getFacilityTypeEntity().getName()));
	}

	/**
	 * FacilityFormからFacilityEntityへのコンバート処理
	 *
	 * @param Facility
	 * @return
	 */
	public FacilityEntity convertFromFormToEntity(FacilityForm form,
			AccountSessionForm accountSessionForm) {
		return new FacilityEntity(form.getId(), form.getName(),
				new FacilityTypeEntity(form.getFacilityTypeForm().getId(),
						form.getFacilityTypeForm().getName()),
				form.getCapacity(), accountSessionForm.getAccountName());
	}

	//	List<FacilityTypeEntity>からList<FacilityTypeForm>へのコンバート処理
	public List<FacilityTypeForm> convertFromTypeEntityListToTypeFormList(List<FacilityTypeEntity> typeListEntity) {
		List<FacilityTypeForm> typeListForm = new ArrayList<FacilityTypeForm>();

		for (FacilityTypeEntity typeEntity : typeListEntity) {

			typeListForm.add(convertFromTypeEntityToTypeForm(typeEntity));
		}
		return typeListForm;
	}

	//	FacilityTypeEntityからFacilityTypeFormへのコンバート処理
	public FacilityTypeForm convertFromTypeEntityToTypeForm(FacilityTypeEntity typeEntity) {
		return new FacilityTypeForm(typeEntity.getId(), typeEntity.getName());
	}

	//	FacilityTypeFormからFacilityTypeEntityへのコンバート処理
	public FacilityTypeEntity convertFromTypeFormToTypeEntity(FacilityTypeForm typeForm) {
		return new FacilityTypeEntity(typeForm.getId());
	}

	//
	//	FacilityEntityからFacilitySessionFormへのコンバート処理
	//
	public FacilitySessionForm convertFromEntityToSessionForm(FacilityEntity entity,
			List<FacilityTypeEntity> typeListEntity) {

		FacilitySessionForm sessionForm = new FacilitySessionForm();

		//	FacilityEntityからFacilityFormへのコンバート処理
		sessionForm.setFacilityForm(convertFromEntityToForm(entity));

		//	List<FacilityTypeEntity>からList<FacilityTypeForm>へのコンバート処理
		sessionForm.setFacilityTypeList(convertFromTypeEntityListToTypeFormList(typeListEntity));

		return sessionForm;
	}

	//
	//	FacilityEntityからFacilityListFormへのコンバート処理
	//
	public FacilityListForm convertFromEntityToListForm(List<FacilityEntity> listEntity,
			List<FacilityTypeEntity> typeListEntity) {

		FacilityListForm facilitylistForm = new FacilityListForm();

		//	List<FacilityEntity>からList<FacilityForm>へのコンバート処理
		facilitylistForm.setFacilityList(convertFromEntityListToFormList(listEntity));

		//	List<FacilityTypeEntity>からList<FacilityTypeForm>へのコンバート処理
		facilitylistForm.setFacilityTypeList(convertFromTypeEntityListToTypeFormList(typeListEntity));

		return facilitylistForm;
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
