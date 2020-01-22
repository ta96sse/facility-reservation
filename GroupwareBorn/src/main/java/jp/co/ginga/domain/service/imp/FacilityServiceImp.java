package jp.co.ginga.domain.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.repository.FacilityRepository;
import jp.co.ginga.domain.service.FacilityService;

@Service
@Transactional
public class FacilityServiceImp implements FacilityService {

	/**
	 * 施設リポジトリ
	 */
	@Autowired
	FacilityRepository repoFacility;

	/**
	 * 施設情報リスト取得処理
	 */
	@Override
	public List<FacilityEntity> getFacilityList() {
		return repoFacility.findAll();
	}

	/**
	 * 指定した施設IDから施設情報を取得する処理
	 */
	@Override
	public FacilityEntity getFacility(int id) {
		return repoFacility.findOneById(id);
	}

	/**
	 * 指定した施設IDから施設情報を削除する処理
	 */
	@Override
	public int delete(int id) {
		if (repoFacility.delete(id)) {
			return 1;
		}
		return 0;
	}

	/**
	 * 指定した施設IDの施設情報を更新する処理
	 */
	@Override
	public int update(FacilityEntity facility) {
		if (repoFacility.update(facility)) {
			return 1;
		}
		System.out.println("error");
		return 0;
	}

	/**
	 * 施設を追加する処理
	 */
	@Override
	public int add(FacilityEntity facility) {
		if (repoFacility.add(facility)) {
			return 1;
		}
		return 0;
	}

	/**
	 * テスト用のスタブオブジェクト設定処理
	 */
	@Override
	public void setFacilityRepository(FacilityRepository facilityRepository) {
		this.repoFacility = facilityRepository;

	}

}