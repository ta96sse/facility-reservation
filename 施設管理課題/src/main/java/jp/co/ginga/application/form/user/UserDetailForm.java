package jp.co.ginga.application.form.user;

import java.io.Serializable;

/**
 * ユーザー情報詳細Form
 * @author yoshi
 *
 */
public class UserDetailForm implements Serializable{

	/**
	 * システムエラーメッセージ
	 */
	private String errMsg;

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

}
