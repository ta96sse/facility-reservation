package jp.co.ginga.domain.service;

import java.util.List;

import jp.co.ginga.domain.entity.FacilityTypeEntity;
import jp.co.ginga.domain.repository.FacilityTypeRepository;

public interface FacilityTypeService {

	public List<FacilityTypeEntity> getFacilityTypeList();

	public FacilityTypeEntity getFacilityType(int id);

	public int delete(int id);

	public int update(FacilityTypeEntity facilityType);

	public int add(FacilityTypeEntity facilityType);

	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository);
}
