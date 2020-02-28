package jp.co.ginga.application.form.facility;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class FacilitySessionForm implements Serializable {

	private String systemMsg;

	@Valid
	private FacilityForm facilityForm;

	private List<FacilityTypeForm> facilityTypeList;

	public String getSystemMsg() {
		return systemMsg;
	}

	public void setSystemMsg(String systemMsg) {
		this.systemMsg = systemMsg;
	}

	public FacilitySessionForm() {
	}

	public FacilitySessionForm(FacilityForm facilityForm, List<FacilityTypeForm> facilityTypeList) {
		this.facilityForm = facilityForm;
		this.facilityTypeList = facilityTypeList;
	}

	public FacilityForm getFacilityForm() {
		return facilityForm;
	}

	public void setFacilityForm(FacilityForm facilityForm) {
		this.facilityForm = facilityForm;
	}

	public List<FacilityTypeForm> getFacilityTypeList() {
		return facilityTypeList;
	}

	public void setFacilityTypeList(List<FacilityTypeForm> facilityTypeList) {
		this.facilityTypeList = facilityTypeList;
	}

}
