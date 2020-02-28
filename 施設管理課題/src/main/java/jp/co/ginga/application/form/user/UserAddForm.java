package jp.co.ginga.application.form.user;

import java.io.Serializable;
import java.util.Map;

/**
 * 新規ユーザー登録Form
 * 
 * @author yoshi
 *
 */
public class UserAddForm implements Serializable {

	/**
	 * システムエラーメッセージ
	 */
	private String errMsg;

	/**
	 * システムエラーメッセージ
	 */
	private Map<String, String> errors;

	/**
	 * @return errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg セットする errMsg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
