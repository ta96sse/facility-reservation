package jp.co.ginga.application.form.reservation;

import java.io.Serializable;
import java.util.List;

public class DayForm implements Serializable {

	private String date;
	private List<ReservationForm> reservationFormList;
	private int yearMonthDate;

	public DayForm() {

	}

	public DayForm(String date) {
		super();
		this.date = date;
	}

	public DayForm(String date, List<ReservationForm> reservationFormList, int yearMonthDate) {
		super();
		this.date = date;
		this.reservationFormList = reservationFormList;
		this.yearMonthDate = yearMonthDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ReservationForm> getReservationFormList() {
		return reservationFormList;
	}

	public void setReservationFormList(List<ReservationForm> reservationFormList) {
		this.reservationFormList = reservationFormList;
	}

	public int getYearMonthDate() {
		return yearMonthDate;
	}

	public void setYearMonthDate(int yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}

}
