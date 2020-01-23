package jp.co.ginga.application.form.facility;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class FacilityForm implements Serializable {

	private int id;

	/**
	 * 施設名
	 */
	@NotEmpty(message = "必須項目です")
	@Size(message = "1文字以上20文字以下で入力してください。", min = 1, max = 20)
	private String name;

	/**
	 * 定員
	 */
	@NotEmpty(message = "必須入力です")
	@Pattern(message = "半角数字で入力してください", regexp = "[0-9]*")
	@Range(message = "1以上1000以下で入力してください。", min = 1, max = 1000)
	private String capacity;

	private FacilityTypeForm facilityTypeForm;

	/**
	 * コンストラクタ
	 */
	public FacilityForm() {

	}

	/**
	 * コンストラクタ
	 *
	 * @param name
	 * @param typeId
	 * @param capacity
	 */
	public FacilityForm(int id, String name, String capacity) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	/**
	 * コンストラクタ
	 *
	 * @param id
	 * @param facilityName
	 * @param typeId
	 * @param capacity
	 */
	public FacilityForm(int id, String name, String capacity, FacilityTypeForm facilityTypeForm) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.facilityTypeForm = facilityTypeForm;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return capacity
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity セットする capacity
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public FacilityTypeForm getFacilityTypeForm() {
		return facilityTypeForm;
	}

	public void setFacilityTypeForm(FacilityTypeForm facilityTypeForm) {
		this.facilityTypeForm = facilityTypeForm;
	}

}
