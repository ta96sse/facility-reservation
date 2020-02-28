/**
 *
 */
package jp.co.ginga.domain.entity;

/**
 * ユーザテーブルEntity
 * @author yoshi
 *
 */
public class UserEntity {

	/**
	 * ログインID
	 */
	private String id;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * システム権限
	 */
	private int permissionLevel;

	/**
	 * 備考
	 */
	private String note;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return permissionLevel
	 */
	public int getPermissionLevel() {
		return permissionLevel;
	}

	/**
	 * @param permissionLevel セットする permissionLevel
	 */
	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	/**
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note セットする note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * コンストラクタ (デフォルト)
	 */
	public UserEntity() {
	}

	public UserEntity(String id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 * @param id
	 * @param password
	 */
	public UserEntity(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	/**
	 * コンストラクタ
	 * @param id
	 * @param password
	 * @param permissionLevel
	 * @param note
	 */
	public UserEntity(String id, String password, int permissionLevel, String note) {
		super();
		this.id = id;
		this.password = password;
		this.permissionLevel = permissionLevel;
		this.note = note;
	}

	/**
	 * オブジェクトの文字列表現処理。
	 */
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", password=" + password + ", permissionLevel=" + permissionLevel + ", note="
				+ note + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
