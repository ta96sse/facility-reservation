package jp.co.ginga.application.form.authority;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthorityLoginForm implements Serializable {

	@NotEmpty(message = "必須入力です")
	@Pattern(message = "半角英数字で入力してください", regexp = "[a-zA-Z0-9]*")
	@Size(message = "4文字以上、10文字以内。", min = 4, max = 10)
	private String loginId;

	@NotEmpty(message = "必須入力です")
	@Pattern(message = "半角英数字で入力してください", regexp = "[a-zA-Z0-9]*")
	@Size(message = "4文字以上、10文字以内。", min = 4, max = 10)
	private String password;

	private String errorMsg;

	/**
	 * @return loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId セットする loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg セットする errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "AuthorityLoginForm [loginId=" + loginId + ", password=" + password + ", errorMsg=" + errorMsg + "]";
	}

}
