package jp.co.ginga.application.form.facility;

import java.io.Serializable;
import java.util.List;

public class FacilityListForm implements Serializable {

	private String systemMsg;

	private List<FacilityForm> facilityList;

	private List<FacilityTypeForm> facilityTypeList;

	public FacilityListForm() {
	}

	public FacilityListForm(List<FacilityForm> facilityList, List<FacilityTypeForm> facilityTypeList) {
		this.facilityList = facilityList;
		this.facilityTypeList = facilityTypeList;
	}

	public String getSystemMsg() {
		return systemMsg;
	}

	public void setSystemMsg(String systemMsg) {
		this.systemMsg = systemMsg;
	}

	public List<FacilityForm> getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(List<FacilityForm> facilityList) {
		this.facilityList = facilityList;
	}

	public List<FacilityTypeForm> getFacilityTypeList() {
		return facilityTypeList;
	}

	public void setFacilityTypeList(List<FacilityTypeForm> facilityTypeList) {
		this.facilityTypeList = facilityTypeList;
	}

}
