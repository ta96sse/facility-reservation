package jp.co.ginga.application.form.reservation;

import java.io.Serializable;

public class ReservationForm implements Serializable {

	private int id;
	private String startTime;
	private String endTime;
	private int facilityId;
	private String userId;

	public ReservationForm(int id, String startTime, String endTime, int facilityId, String userId) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.facilityId = facilityId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
