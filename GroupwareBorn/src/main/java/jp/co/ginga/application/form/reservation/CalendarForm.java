package jp.co.ginga.application.form.reservation;

import java.io.Serializable;
import java.util.List;

public class CalendarForm implements Serializable {

	private int year;
	private int month;
	private String[] weekName;
	private List<DayForm> dayFormList;

	public CalendarForm(int year, int month, String[] weekName, List<DayForm> dayFormList) {
		super();
		this.year = year;
		this.month = month;
		this.weekName = weekName;
		this.dayFormList = dayFormList;
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

	public String[] getWeekName() {
		return weekName;
	}

	public void setWeekName(String[] weekName) {
		this.weekName = weekName;
	}

	public List<DayForm> getDayFormList() {
		return dayFormList;
	}

	public void setDayFormList(List<DayForm> dayFormList) {
		this.dayFormList = dayFormList;
	}

}
