package jp.co.ginga.application.form.reservation;

import java.io.Serializable;
import java.util.List;

public class CalendarForm implements Serializable {

	private int year;
	private int month;
	private String[] weekName;
	private List<DayForm> dayFormList;
	private boolean changeCalFlag;
	private int today;

	public CalendarForm() {

	}

	public CalendarForm(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public CalendarForm(int year, int month, String[] weekName, List<DayForm> dayFormList, int today) {
		this.year = year;
		this.month = month;
		this.weekName = weekName;
		this.dayFormList = dayFormList;
		this.today = today;
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

	public boolean getChangeCalFlag() {
		return changeCalFlag;
	}

	public void setChangeCalFlag(boolean changeCalFlag) {
		this.changeCalFlag = changeCalFlag;
	}

	public int getToday() {
		return today;
	}

	public void setToday(int today) {
		this.today = today;
	}

}
