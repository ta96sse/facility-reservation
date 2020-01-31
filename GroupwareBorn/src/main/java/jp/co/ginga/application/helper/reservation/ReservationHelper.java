package jp.co.ginga.application.helper.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
		return new ReservationStatusForm(convertFromEntityToForm(entity), createCalendarForm(entityList));
	}

	/*
	 * FacilityForm
	 */
	public FacilityForm convertFromEntityToForm(FacilityEntity entity) {
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

		return new CalendarForm(year, month, weekName, dayFormList);
	}

	/*
	 * List<DayForm>
	 */
	public List<DayForm> createDayFormList(List<ReservationEntity> entityList) {

		List<DayForm> dayFormList = new ArrayList<DayForm>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		cal.set(year, month, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int monthEndDay = cal.getActualMaximum(Calendar.DATE);

		if (week == 1) {
			for (int i = 1; i < week + 6; i++) {
				dayFormList.add(new DayForm("-"));
			}
		} else {
			for (int i = 1; i < week - 1; i++) {
				dayFormList.add(new DayForm("-"));
			}
		}
		for (int day = 1; day <= monthEndDay; day++) {
			dayFormList
					.add(new DayForm(String.valueOf(day), convertFromEntityListToFormList(entityList), "/img/add.png"));
		}
		while (dayFormList.size() % 7 != 0) {
			dayFormList.add(new DayForm("-"));
		}

		return dayFormList;
	}

	/*
	 * DayForm
	 */
	//	public DayForm createDayForm(List<ReservationEntity> entityList) {
	//
	//		Calendar cal = Calendar.getInstance();
	//		int year = cal.get(Calendar.YEAR);
	//		int month = cal.get(Calendar.MONTH);
	//
	//		cal.set(year, month, 1);
	//		int week = cal.get(Calendar.DAY_OF_WEEK);
	//		int monthEndDay = cal.getActualMaximum(Calendar.DATE);
	//
	//		if (week == 1) {
	//			for (int i = 1; i < week + 6; i++) {
	//				new DayForm("-", null);
	//			}
	//		} else {
	//			for (int i = 1; i < week - 1; i++) {
	//				new DayForm("-", null);
	//			}
	//		}
	//		for (int day = 1; day <= monthEndDay; day++) {
	//			new DayForm(String.valueOf(day), convertFromEntityListToFormList(entityList));
	//		}
	//		if (week == 1) {
	//			for (int i = 1; i < week + 6; i++) {
	//				new DayForm("-", null);
	//			}
	//		} else {
	//			for (int i = 1; i < week - 1; i++) {
	//				new DayForm("-", null);
	//			}
	//		}
	//		while (dayFormList.size() % 7 != 0) {
	//			new DayForm("-", null);
	//		}
	//
	//		return null;
	//	}

	/*
	 * List<ReservationForm>
	 */
	public List<ReservationForm> convertFromEntityListToFormList(List<ReservationEntity> entityList) {

		List<ReservationForm> formList = new ArrayList<ReservationForm>();

		for (ReservationEntity entity : entityList) {
			formList.add(convertFromEntityToForm(entity));
		}

		return formList;
	}

	/*
	 * ReservationForm
	 */
	public ReservationForm convertFromEntityToForm(ReservationEntity entity) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.format(entity.getStartTime());
		sdf.format(entity.getEndTime());

		return new ReservationForm(entity.getId(), sdf.format(entity.getStartTime()), sdf.format(entity.getEndTime()),
				entity.getFacilityId(),
				entity.getUserId());
	}

}
