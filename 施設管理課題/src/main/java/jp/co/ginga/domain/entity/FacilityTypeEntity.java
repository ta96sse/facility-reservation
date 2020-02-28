package jp.co.ginga.domain.entity;

import java.sql.Date;

/**
 * 施設種別テーブルEntity
 * @author yoshi
 *
 */
public class FacilityTypeEntity {

	private int id;
	private String name;
	private Date insertDate;
	private Date updateDate;
	private String userId;

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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FacilityTypeEntity() {
	}

	public FacilityTypeEntity(int id) {
		this.id = id;
	}

	public FacilityTypeEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
