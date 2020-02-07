package jp.co.ginga.application.form.reservation;

import java.io.Serializable;

import jp.co.ginga.application.form.facility.FacilityForm;

public class ReservationForm implements Serializable {

	private int id;
	private int year;
	private int month;
	private int date;
	private int startHour;
	private int startMinute;
	private int endHour;
	private int endMinute;

	private String startTime;
	private String endTime;
	private FacilityForm facilityForm;
	private String userId;
	private int startDate;

	public ReservationForm() {

	}

	public ReservationForm(int id, String startTime, String endTime, FacilityForm facilityForm, String userId,
			int startDate) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.facilityForm = facilityForm;
		this.userId = userId;
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
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

	public FacilityForm getFacilityForm() {
		return facilityForm;
	}

	public void setFacilityForm(FacilityForm facilityForm) {
		this.facilityForm = facilityForm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

}
