package jp.co.ginga.application.form.reservation;

import java.io.Serializable;

public class YearAndMonthForm implements Serializable {

	private int year;
	private int month;

	public YearAndMonthForm(int year, int month) {
		super();
		this.year = year;
		this.month = month;
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
}
