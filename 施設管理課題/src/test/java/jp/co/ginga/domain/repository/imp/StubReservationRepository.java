package jp.co.ginga.domain.repository.imp;

import java.util.List;

import jp.co.ginga.domain.entity.ReservationEntity;
import jp.co.ginga.domain.repository.ReservationRepository;

public class StubReservationRepository  implements ReservationRepository {

	@Override
	public ReservationEntity findOneById(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<ReservationEntity> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<ReservationEntity> findListByFacilityId(int facilityId, int year, int month) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int check(ReservationEntity reservationEntity) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean add(ReservationEntity reservationEntity) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean update(ReservationEntity reservationEntity) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean delete(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
