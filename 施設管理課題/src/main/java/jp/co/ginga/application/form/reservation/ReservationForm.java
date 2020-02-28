package jp.co.ginga.application.form.reservation;

import java.io.Serializable;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.user.UserForm;

public class ReservationForm implements Serializable {

	private int id;
	private int year;
	private int month;
	private int date;
	private String startHour;
	private String startMinute;
	private String endHour;
	private String endMinute;

	private String startTime;
	private String endTime;
	private FacilityForm facilityForm;
	private UserForm userForm;
	private int startDate;

	public ReservationForm() {

	}

	public ReservationForm(int id, int year, int month, int date, String startHour, String startMinute, String endHour,
			String endMinute, FacilityForm facilityForm, UserForm userForm) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.date = date;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.facilityForm = facilityForm;
		this.userForm = userForm;
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

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(String endMinute) {
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

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

}
