package jp.co.ginga.application.helper.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import jp.co.ginga.application.form.reservation.YearAndMonthForm;
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
	public ReservationStatusForm createStatusForm(FacilityEntity entity, List<ReservationEntity> entityList, int year,
			int month) {

		/*
		//	 * FacilityForm
		//	 */
		FacilityForm facilityForm = new FacilityForm(entity.getId(), entity.getName(), entity.getCapacity(),
				new FacilityTypeForm(entity.getFacilityTypeEntity().getId(), entity.getFacilityTypeEntity().getName()));

		/*
		//	 * CalendarForm
		//	 */
		String[] weekName = { "月", "火", "水", "木", "金", "土", "日" };
		List<DayForm> dayFormList = createDayFormList(entityList, year, month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int today = Integer.parseInt(sdf.format(new Date()));

		CalendarForm calendarForm = new CalendarForm(year, month, weekName, dayFormList, today);

		return new ReservationStatusForm(facilityForm, calendarForm);
	}

	/*
	 * List<DayForm>
	 */
	public List<DayForm> createDayFormList(List<ReservationEntity> entityList, int year, int month) {

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
		cal.set(year, month - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int monthEndDay = cal.getActualMaximum(Calendar.DATE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (week == 1) {
			week = 8;
		}
		for (int i = 1; i < week - 1; i++) {
			dayFormList.add(new DayForm("-"));
		}
		for (int day = 1; day <= monthEndDay; day++) {
			cal.set(year, month - 1, day);
			dayFormList.add(
					new DayForm(String.valueOf(day), dayMap.get(day), Integer.parseInt(sdf.format(cal.getTime()))));
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

	//	/*
	//	 * DayForm
	//	 */
	//	public DayForm createDayForm(List<ReservationEntity> entityList) {
	//		return null;
	//	}

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

	public YearAndMonthForm setYearAndMonthByFlag(int year, int month, boolean changeCalFlag) {

		if (changeCalFlag == true) {
			if (month == 12) {
				year++;
				month = 1;
			} else {
				month++;
			}
		} else if (changeCalFlag == false) {
			if (month == 1) {
				year--;
				month = 12;
			} else {
				month--;
			}
		}

		return new YearAndMonthForm(year, month);
	}
}
