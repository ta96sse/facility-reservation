package jp.co.ginga.application.form.reservation;

import java.io.Serializable;

import jp.co.ginga.application.form.facility.FacilityForm;

public class ReservationStatusForm implements Serializable {

	private FacilityForm facilityForm;
	private CalendarForm calendarForm;

	public ReservationStatusForm(FacilityForm facilityForm, CalendarForm calendarForm) {
		super();
		this.facilityForm = facilityForm;
		this.calendarForm = calendarForm;
	}

	public FacilityForm getFacilityForm() {
		return facilityForm;
	}

	public void setFacilityForm(FacilityForm facilityForm) {
		this.facilityForm = facilityForm;
	}

	public CalendarForm getCalendarForm() {
		return calendarForm;
	}

	public void setCalendarForm(CalendarForm calendarForm) {
		this.calendarForm = calendarForm;
	}

}
