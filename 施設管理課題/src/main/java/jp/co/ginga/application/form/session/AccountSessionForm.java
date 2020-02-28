/**
 *
 */
package jp.co.ginga.application.form.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * アカウント情報
 * @author yoshi
 *
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountSessionForm implements Serializable{

	/**
	 * ヘッダー表示ネーム
	 */
	private String accountName;

	/**
	 * システム権限レベル
	 */
	private int permissionLevel;

	/**
	 * @return accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName セットする accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	 *
	 */
	public AccountSessionForm() {

	}


	/**
	 * コンストラクタ
	 * @param accountName
	 * @param permissionLevel
	 */
	public AccountSessionForm(String accountName, int permissionLevel) {
		super();
		this.accountName = accountName;
		this.permissionLevel = permissionLevel;
	}


}
