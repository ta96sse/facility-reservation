package jp.co.ginga.domain.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.domain.entity.FacilityTypeEntity;
import jp.co.ginga.domain.repository.FacilityTypeRepository;
import jp.co.ginga.domain.service.FacilityTypeService;

@Service
@Transactional
public class FacilityTypeServiceImp implements FacilityTypeService {

	/**
	 * 施設種別リポジトリ
	 */
	@Autowired
	FacilityTypeRepository repoFacilityType;

	/**
	 * 施設種別情報リスト取得処理
	 */
	@Override
	public List<FacilityTypeEntity> getFacilityTypeList() {

		return repoFacilityType.findAll();
	}

	/**
	 * 指定した施設IDから施設情報を取得する処理
	 */
	@Override
	public FacilityTypeEntity getFacilityType(int id) {
		return repoFacilityType.findOneById(id);
	}

	/**
	 * 指定した施設IDから施設情報を削除する処理
	 */
	@Override
	public int delete(int id) {
		if (repoFacilityType.delete(id)) {
			return 1;
		}
		return 0;
	}

	/**
	 * 指定した施設IDの施設情報を更新する処理
	 */
	@Override
	public int update(FacilityTypeEntity facilityType) {
		if (repoFacilityType.update(facilityType)) {
			return 1;
		}
		System.out.println("error");
		return 0;
	}

	/**
	 * 施設を追加する処理
	 */
	@Override
	public int add(FacilityTypeEntity facilityType) {
		if (repoFacilityType.add(facilityType)) {
			return 1;
		}
		return 0;
	}

	/**
	 * テスト用のスタブオブジェクト設定処理
	 */
	@Override
	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository) {
		this.repoFacilityType = facilityTypeRepository;

	}

}