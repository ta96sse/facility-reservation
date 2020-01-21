package jp.co.ginga.application.form.user;

import java.io.Serializable;
import java.util.List;

public class UserListForm implements Serializable {

	/**
	 * ユーザー一覧情報
	 */
	private List<UserForm> userList;

	/**
	 * @return userList
	 */
	public List<UserForm> getUserList() {
		return userList;
	}

	public void setUserList(List<UserForm> userFormList) {
		this.userList = userFormList;
	}

}
