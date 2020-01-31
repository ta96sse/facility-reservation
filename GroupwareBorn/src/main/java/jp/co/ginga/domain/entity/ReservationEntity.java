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
	private int facilityId;
	private String userId;

	public ReservationEntity() {

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

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
