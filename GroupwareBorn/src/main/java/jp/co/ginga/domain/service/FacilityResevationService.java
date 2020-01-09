package jp.co.ginga.domain.service;

import jp.co.ginga.domain.entity.FacilityReservationEntity;

public interface FacilityResevationService {


	/**
	 * 施設予約一覧情報取得
	 */
	public void getFacilityReservationList();


	/**
	 * 施設予約情報取得
	 */
	public FacilityReservationEntity getFacilityReservationById(int id);

}
