package jp.co.ginga.domain.entity;

import java.sql.Date;

/**
 * 施設テーブルEntity
 * @author yoshi
 *
 */
public class FacilityEntity {

	private int id;
	private String name;
	private int typeId;
	private String capacity;
	private Date insertDate;
	private Date updateDate;
	private String userId;
	private FacilityTypeEntity facilityTypeEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUseId(String userId) {
		this.userId = userId;
	}

	public FacilityTypeEntity getFacilityTypeEntity() {
		return facilityTypeEntity;
	}

	public void setFacilityTypeEntity(FacilityTypeEntity facilityTypeEntity) {
		this.facilityTypeEntity = facilityTypeEntity;
	}

	/**
	 * コンストラクタ (デフォルト)
	 */
	public FacilityEntity() {
	}

	public FacilityEntity(int id) {
		super();
		this.id = id;
	}

	public FacilityEntity(int id, String name, FacilityTypeEntity facilityTypeEntity, String capacity,
			String userId) {
		super();
		this.id = id;
		this.name = name;
		this.facilityTypeEntity = facilityTypeEntity;
		this.capacity = capacity;
		this.userId = userId;
	}

}
