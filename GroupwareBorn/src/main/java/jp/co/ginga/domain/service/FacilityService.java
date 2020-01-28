package jp.co.ginga.domain.service;

import java.util.List;

import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.repository.FacilityRepository;

public interface FacilityService {

	public List<FacilityEntity> getFacilityList();

	public List<FacilityEntity> getFacilityList(int typeId);

	public FacilityEntity getFacility(int id);

	public int delete(int id);

	public int update(FacilityEntity facility);

	public int add(FacilityEntity facility);

	public void setFacilityRepository(FacilityRepository facilityRepository);

}
