package jp.co.ginga.domain.entity;

/**
 * 施設種別テーブルEntity
 * @author yoshi
 *
 */
public class FacilityTypeEntity {

	private int id;

	private String name;

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

	public FacilityTypeEntity() {
	}

	public FacilityTypeEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
