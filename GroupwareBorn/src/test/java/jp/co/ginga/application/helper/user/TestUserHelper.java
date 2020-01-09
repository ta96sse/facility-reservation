package jp.co.ginga.application.helper.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ginga.application.form.user.UserForm;
import jp.co.ginga.domain.entity.UserEntity;

/**
 * @author suwayama UserHelperのテストクラス
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserHelper {

	/**
	 * UserHelperのオートワイヤ―ド
	 */
	@Autowired
	UserHelper userHelper;

	/**
	 * 例外発生を期待しない場合のために
	 * 
	 * ExpectedException.none() を設定しておく
	 */
	@Rule
	public ExpectedException expected = ExpectedException.none();

	/**
	 * convertFromUserEntityListToUserFormListメソッド正常系
	 * 
	 */
	@Ignore
	@Test
	public void convertFromUserEntityListToUserFormList_Ok_001() {
		System.out.println("convertFromUserEntityListToUserFormList_Ok_001");
		List<UserEntity> entityList = new ArrayList<>();

		String id = "testUser";
		String password = "testUser";
		int permissionLevel = 1;
		String note = "convertFromUserEntityListToUserFormList_Ok_001";

		UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);
		entityList.add(userEntity);

		List<UserForm> result = userHelper.convertFromUserEntityListToUserFormList(entityList);

		assertEquals(id, result.get(0).getLoginName());
		assertEquals(password, result.get(0).getPassword1());
		assertEquals(password, result.get(0).getPassword2());
		assertEquals(permissionLevel, result.get(0).getParmissionLevel());
		assertEquals(note, result.get(0).getNote());
	}

	/**
	 * convertFromUserEntityListToUserFormListメソッド正常系2
	 * 
	 * Listサイズ0
	 */
	@Test
	public void convertFromUserEntityListToUserFormList_Ok_002() {
		List<UserEntity> entityList = new ArrayList<>();

		List<UserForm> result = userHelper.convertFromUserEntityListToUserFormList(entityList);

		assertEquals(0, result.size());
	}

	/**
	 * convertFromUserEntityToUserFormメソッド正常系
	 */
	@Ignore
	@Test
	public void convertFromUserEntityToUserForm_Ok_001() {
		System.out.println("convertFromUserEntityToUserForm_Ok_001");

		String id = "testUser";
		String password = "testUser";
		int permissionLevel = 2;
		String note = "convertFromUserEntityToUserForm_Ok_001";

		UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);

		UserForm userForm = userHelper.convertFromUserEntityToUserForm(userEntity);

		assertEquals(id, userForm.getLoginName());
		assertEquals(password, userForm.getPassword1());
		assertEquals(password, userForm.getPassword2());
		assertEquals(permissionLevel, userForm.getParmissionLevel());
		assertEquals(note, userForm.getNote());
	}

	/**
	 * convertEntityUserForm_メソッド正常系
	 */
	@Test
	public void convertEntityUserForm_Ok_001() {
		System.out.println("convertEntityUserForm_Ok_001");

		String id = "testUser";
		String password = "testUser";
		int permissionLevel = 2;
		String note = "convertEntityUserForm_Ok_001";

		UserForm userForm = new UserForm(id, password, permissionLevel, note);

		UserEntity result = userHelper.convertEntityUserForm(userForm);

		assertEquals(id, result.getId());
		assertEquals(password, result.getPassword());
		assertEquals(permissionLevel, result.getPermissionLevel());
		assertEquals(note, result.getNote());

	}

	/**
	 * checkPasswordメソッド正常系OK
	 */
	@Test
	public void checkPassword_Ok_001() {
		System.out.println("checkPassword_Ok_001");

		String password1 = "checkPassword";
		String password2 = "checkPassword";

		UserForm userForm = new UserForm();
		userForm.setPassword1(password1);
		userForm.setPassword2(password2);

		boolean result = userHelper.checkPassword(userForm);

		assertEquals(true, result);
	}

	/**
	 * checkPasswordメソッド正常系2NG
	 */
	@Test
	public void checkPassword_Ok_002() {
		System.out.println("checkPassword_Ok_002");

		String password1 = "checkPassword1";
		String password2 = "checkPassword2";

		UserForm userForm = new UserForm();
		userForm.setPassword1(password1);
		userForm.setPassword2(password2);

		boolean result = userHelper.checkPassword(userForm);

		assertEquals(false, result);
	}

	/**
	 * convertFromUserEntityToUserFormメソッド異常系
	 * 
	 * thorw new NullPointerException
	 */
	@Test
	public void convertFromUserEntityToUserForm_Ng_001() {
		System.out.println("convertFromUserEntityToUserForm_Ng_001");

		expected.expect(NullPointerException.class);

		List<UserEntity> entityList = null;

		userHelper.convertFromUserEntityListToUserFormList(entityList);

	}

	/**
	 * convertEntityUserFormメソッド異常系
	 * 
	 * thorw new NullPointerException
	 */
	@Test
	public void convertEntityUserForm_Ng_001() {
		System.out.println("convertEntityUserForm_Ng_001");

		expected.expect(NullPointerException.class);

		UserEntity userEntity = null;

		userHelper.convertFromUserEntityToUserForm(userEntity);
	}

	/**
	 * checkPasswordメソッド異常系
	 * 
	 * thorw new NullPointerException
	 */
	@Test
	public void checkPassword_Ng_001() {
		System.out.println("checkPassword_Ng_001");

		expected.expect(NullPointerException.class);

		String password1 = null;
		String password2 = null;

		UserForm form = new UserForm();
		form.setPassword1(password1);
		form.setPassword2(password2);

		userHelper.checkPassword(form);

	}

}
