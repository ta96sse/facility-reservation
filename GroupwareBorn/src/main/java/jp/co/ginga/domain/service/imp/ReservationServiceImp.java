package jp.co.ginga.domain.service.imp;

import java.util.Date;
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
	public List<ReservationEntity> getReservationList(int facilityId, Date yearMonth) {

		return reservationRepository.findListByFacilityId(facilityId, yearMonth);
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
