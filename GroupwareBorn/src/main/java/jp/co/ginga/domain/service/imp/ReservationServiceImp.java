package jp.co.ginga.domain.service.imp;

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
		return null;
	}

	@Override
	public List<ReservationEntity> getReservationList(int facilityId, int year, int month) {

		return reservationRepository.findListByFacilityId(facilityId, year, month);
	}

	@Override
	public ReservationEntity getReservation(int id) {

		return reservationRepository.findOneById(id);
	}

	@Override
	public int checkAdd(ReservationEntity reservation) {
		if (reservationRepository.checkAdd(reservation) != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int checkUpdate(ReservationEntity reservation) {
		if (reservationRepository.checkUpdate(reservation) != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int add(ReservationEntity reservation) {
		if (reservationRepository.add(reservation)) {
			return 1;
		}
		return 0;
	}

	@Override
	public int update(ReservationEntity reservation) {
		if (reservationRepository.update(reservation)) {
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		if (reservationRepository.delete(id)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void setReservationRepository(ReservationRepository reservationRepository) {

	}

}
