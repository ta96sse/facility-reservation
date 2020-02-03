package jp.co.ginga.domain.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.domain.entity.ReservationEntity;
import jp.co.ginga.domain.repository.ReservationRepository;
import jp.co.ginga.domain.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImp implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public List<ReservationEntity> getReservationList() {

		return null;
	}

	@Override
	public List<ReservationEntity> getReservationList(int facilityId) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		System.out.println(sdf.format(cal.getTime()));

		return reservationRepository.findListByFacilityId(facilityId, sdf.format(cal.getTime()));
	}

	@Override
	public List<ReservationEntity> getReservationList(int facilityId, int month) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.set(year,month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		System.out.println(sdf.format(cal.getTime()));
		return reservationRepository.findListByFacilityId(facilityId, "202003");
	}

	@Override
	public ReservationEntity getReservation(int id) {

		return null;
	}

	@Override
	public int delete(int id) {

		return 0;
	}

	@Override
	public int update(ReservationEntity reservation) {

		return 0;
	}

	@Override
	public int add(ReservationEntity reservation) {

		return 0;
	}

	@Override
	public void setReservationRepository(ReservationRepository reservationRepository) {

	}

}
