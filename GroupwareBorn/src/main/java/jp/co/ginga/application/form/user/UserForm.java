package jp.co.ginga.application.form.user;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザー情報フォーム
 *
 * @author yoshi
 *
 */
public class UserForm implements Serializable {

	/**
	 * ログインネーム
	 */
	@NotEmpty(message = "必須項目です")
	@Pattern(message = "半角英数字で入力してください", regexp = "[a-zA-Z0-9]*")
	@Size(message = "4文字以上10文字以下で入力してください。", min = 4, max = 10)
	private String loginName;

	/**
	 * パスワード
	 */
	@NotEmpty(message = "必須入力です")
	@Pattern(message = "半角英数字で入力してください", regexp = "[a-zA-Z0-9]*")
	@Size(message = "6文字以上20文字以下で入力してください。", min = 6, max = 20)
	private String password1;

	/**
	 * 確認用パスワード
	 */
	@NotEmpty(message = "必須入力です")
	@Pattern(message = "半角英数字で入力してください", regexp = "[a-zA-Z0-9]*")
	@Size(message = "6文字以上、20文字以下。", min = 6, max = 20)
	private String password2;

	/**
	 * システム権限
	 */
	private int parmissionLevel;

	/**
	 * 備考
	 */
	@Size(message = "100文字以内で入力してください", min = 0, max = 100)
	private String note;

	/**
	 * パスワード不一致エラー
	 */
	private String errorDiscord;

	/**
	 * ユーザーID重複エラー
	 */
	private String errorDuplicate;

	public String getErrorDuplicate() {
		return errorDuplicate;
	}

	public void setErrorDuplicate(String errorDuplicate) {
		this.errorDuplicate = errorDuplicate;
	}

	public String getErrorDiscord() {
		return errorDiscord;
	}

	public void setErrorDiscord(String errorDiscord) {
		this.errorDiscord = errorDiscord;
	}

	/**
	 * コンストラクタ
	 */
	public UserForm() {

	}

	public UserForm(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * コンストラクタ
	 *
	 * @param loginName
	 * @param password
	 * @param parmissionLevel
	 * @param note
	 */
	public UserForm(String loginName, String password1, int parmissionLevel, String note) {
		super();
		this.loginName = loginName;
		this.password1 = password1;
		this.password2 = password1;
		this.parmissionLevel = parmissionLevel;
		this.note = note;
	}

	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName セットする loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return password1
	 */
	public String getPassword1() {
		return password1;
	}

	/**
	 * @param password1 セットする password1
	 */
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	/**
	 * @return password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 セットする password2
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * @return parmissionLevel
	 */
	public int getParmissionLevel() {
		return parmissionLevel;
	}

	/**
	 * @param parmissionLevel セットする parmissionLevel
	 */
	public void setParmissionLevel(int parmissionLevel) {
		this.parmissionLevel = parmissionLevel;
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
}
