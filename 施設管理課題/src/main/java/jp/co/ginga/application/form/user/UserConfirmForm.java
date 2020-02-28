package jp.co.ginga.application.form.user;

import java.io.Serializable;

/**
 * ユーザー情報操作確認Form
 * @author yoshi
 *
 */
public class UserConfirmForm implements Serializable{

	/**
	 * システムエラーメッセージ
	 */
	private String errMsg;

	/**
	 * 完了画面へ遷移するボタンのアクション情報
	 */
	private String btnAction;


	/**
	 * 完了画面へ遷移するボタンの表示情報
	 */
	private String btnName;

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
	 * @return btnName
	 */
	public String getBtnName() {
		return btnName;
	}

	/**
	 * @param btnName セットする btnName
	 */
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	/**
	 * @return btnAction
	 */
	public String getBtnAction() {
		return btnAction;
	}

	/**
	 * @param btnAction セットする btnAction
	 */
	public void setBtnAction(String btnAction) {
		this.btnAction = btnAction;
	}

}
