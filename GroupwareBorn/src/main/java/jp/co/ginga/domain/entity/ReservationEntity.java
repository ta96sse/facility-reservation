/**
 *
 */
package jp.co.ginga.domain.entity;

import java.util.Date;

/**
 * 施設予約テーブルEntity
 * @author yoshi
 *
 */
public class ReservationEntity {

	private int id;
	private Date startTime;
	private Date endTime;
	private FacilityEntity facilityEntity;
	private UserEntity userEntity;

	public ReservationEntity() {

	}

	public ReservationEntity(int id, Date startTime, Date endTime, FacilityEntity facilityEntity) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.facilityEntity = facilityEntity;
	}

	public ReservationEntity(int id, Date startTime, Date endTime, FacilityEntity facilityEntity,
			UserEntity userEntity) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.facilityEntity = facilityEntity;
		this.userEntity = userEntity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public FacilityEntity getFacilityEntity() {
		return facilityEntity;
	}

	public void setFacilityEntity(FacilityEntity facilityEntity) {
		this.facilityEntity = facilityEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
