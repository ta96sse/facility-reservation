package jp.co.ginga.domain.service;

import java.util.List;

import jp.co.ginga.domain.entity.ReservationEntity;
import jp.co.ginga.domain.repository.ReservationRepository;

public interface ReservationService {

	public List<ReservationEntity> getReservationList();

	public List<ReservationEntity> getReservationList(int facilityId);

	public List<ReservationEntity> getReservationList(int facilityId, int year, int month);

	public ReservationEntity getReservation(int id);

	public int delete(int id);

	public int update(ReservationEntity reservation);

	public int add(ReservationEntity reservation);

	public void setReservationRepository(ReservationRepository reservationRepository);

}