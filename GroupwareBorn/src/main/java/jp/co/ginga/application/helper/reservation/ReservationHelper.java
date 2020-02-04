package jp.co.ginga.application.helper.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jp.co.ginga.application.form.facility.FacilityForm;
import jp.co.ginga.application.form.facility.FacilityTypeForm;
import jp.co.ginga.application.form.reservation.CalendarForm;
import jp.co.ginga.application.form.reservation.DayForm;
import jp.co.ginga.application.form.reservation.ReservationForm;
import jp.co.ginga.application.form.reservation.ReservationStatusForm;
import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.entity.ReservationEntity;

/**
 *
 * @author yoshi
 *
 */
@Component
public class ReservationHelper {

	/*
	 * ReservationStatusForm
	 */
	public ReservationStatusForm createStatusForm(FacilityEntity entity, List<ReservationEntity> entityList) {
		return new ReservationStatusForm(convertFromFacilityEntityToFacilityForm(entity),
				createCalendarForm(entityList));
	}

	/*
	 * FacilityForm
	 */
	public FacilityForm convertFromFacilityEntityToFacilityForm(FacilityEntity entity) {
		return new FacilityForm(entity.getId(), entity.getName(), entity.getCapacity(),
				new FacilityTypeForm(entity.getFacilityTypeEntity().getId(), entity.getFacilityTypeEntity().getName()));
	}

	/*
	 * CalendarForm
	 */
	public CalendarForm createCalendarForm(List<ReservationEntity> entityList) {

		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		String[] weekName = { "月", "火", "水", "木", "金", "土", "日" };
		List<DayForm> dayFormList = createDayFormList(entityList);

		return new CalendarForm(year, month + 1, weekName, dayFormList);
	}

	/*
	 * List<DayForm>
	 */
	public List<DayForm> createDayFormList(List<ReservationEntity> entityList) {

		List<DayForm> dayFormList = new ArrayList<DayForm>();
		List<ReservationForm> reservationFormList = convertFromReservEntityListToReservFormList(entityList);

		Map<Integer, List<ReservationForm>> dayMap = new HashMap<Integer, List<ReservationForm>>();

		List<ReservationForm> dayList = null;

		int checkDay = 0;
		for (ReservationForm reservationForm : reservationFormList) {
			int day = reservationForm.getStartDate();
			if (checkDay != day) {
				dayList = new ArrayList<ReservationForm>();
				dayList.add(reservationForm);
				checkDay = day;
			} else {
				dayList.add(reservationForm);

			}
			dayMap.put(checkDay, dayList);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int monthEndDay = cal.getActualMaximum(Calendar.DATE);

		if (week == 1) {
			week = 8;
		}
		for (int i = 1; i < week - 1; i++) {
			dayFormList.add(new DayForm("-"));
		}
		for (int day = 1; day <= monthEndDay; day++) {
			dayFormList.add(new DayForm(String.valueOf(day), dayMap.get(day)));
		}
		while (dayFormList.size() % 7 != 0) {
			dayFormList.add(new DayForm("-"));
		}
		//					List<ReservationForm> dayReservList = new ArrayList<ReservationForm>();
		//					dayFormList.add(new DayForm(String.valueOf(day), dayReservList));
		//					for (int i = 0; i < reservationFormList.size(); i++) {
		//						if (day == reservationFormList.get(i).getStartDate()) {
		//							dayReservList.add(reservationFormList.get(i));
		//						}
		//					}

		return dayFormList;
	}

	/*
	 * DayForm
	 */
	public DayForm createDayForm(List<ReservationEntity> entityList) {
		return null;
	}

	/*
	 * List<ReservationForm>
	 */
	public List<ReservationForm> convertFromReservEntityListToReservFormList(List<ReservationEntity> entityList) {

		List<ReservationForm> formList = new ArrayList<ReservationForm>();

		for (ReservationEntity entity : entityList) {
			formList.add(convertFromReservEntityToReservForm(entity));
		}

		return formList;
	}

	/*
	 * ReservationForm
	 */
	public ReservationForm convertFromReservEntityToReservForm(ReservationEntity entity) {
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd");

		return new ReservationForm(entity.getId(), sdfTime.format(entity.getStartTime()),
				sdfTime.format(entity.getEndTime()), entity.getFacilityId(), entity.getUserId(),
				Integer.parseInt(sdfDate.format(entity.getStartTime())));
	}

}
