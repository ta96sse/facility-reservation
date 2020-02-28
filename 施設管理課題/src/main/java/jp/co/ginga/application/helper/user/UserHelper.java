/**
 *
 */
package jp.co.ginga.application.helper.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.application.form.user.UserForm;
import jp.co.ginga.domain.entity.UserEntity;

/**
 * @author yoshi
 *
 */
@Component
public class UserHelper {

	/**
	 * UserEntityListからUserFormListへのコンバート処理
	 * 
	 * @param list
	 * @return
	 */
	public List<UserForm> convertFromUserEntityListToUserFormList(List<UserEntity> list) {
		List<UserForm> convertList = new ArrayList<UserForm>();

		for (UserEntity entity : list) {

			convertList.add(convertFromUserEntityToUserForm(entity));
		}
		return convertList;
	}

	/**
	 * UserEntityからUserFormへのコンバート処理
	 * 
	 * @param user
	 * @return
	 */
	public UserForm convertFromUserEntityToUserForm(UserEntity user) {
		return new UserForm(user.getId(), user.getPassword(), user.getPermissionLevel(), user.getNote());
	}

	/**
	 * UserFormからUserEntityへのコンバート処理
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity convertEntityUserForm(UserForm session) {
		return new UserEntity(session.getLoginName(), session.getPassword1(), session.getParmissionLevel(),
				session.getNote());
	}

	/**
	 * UserFormのPasswordチェック処理
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkPassword(UserForm session) {
		if (session.getPassword1().equals(session.getPassword2())) {
			return true;
		}
		return false;
	}

}
