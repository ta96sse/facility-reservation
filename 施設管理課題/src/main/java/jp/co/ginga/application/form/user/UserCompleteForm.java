package jp.co.ginga.application.form.user;

import java.io.Serializable;

/**
 * ユーザー情報操作完了Form
 * @author yoshi
 *
 */
public class UserCompleteForm implements Serializable{

	/**
	 * システムエラーメッセージ
	 */
	private String errMsg;

	/**
	 * 完了内容表示情報
	 */
	private String workCompleteMessage;


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

	/**
	 * @return workCompleteMessage
	 */
	public String getWorkCompleteMessage() {
		return workCompleteMessage;
	}

	/**
	 * @param workCompleteMessage セットする workCompleteMessage
	 */
	public void setWorkCompleteMessage(String workCompleteMessage) {
		this.workCompleteMessage = workCompleteMessage;
	}

}
