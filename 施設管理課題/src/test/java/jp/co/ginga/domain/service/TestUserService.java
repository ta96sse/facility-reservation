package jp.co.ginga.domain.service;

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

import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.UserRepository;
import jp.co.ginga.domain.repository.imp.StubUserRepository;

/**
 * @author suwayama
 * 
 *         UserServiceのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

	/**
	 * @author suwayama
	 * 
	 *         UserServiceをオートワイヤ―ド
	 */
	@Autowired
	UserService userService;

	/**
	 * 例外発生を期待しない場合のために
	 * 
	 * ExpectedException.none() を設定しておく
	 */
	@Rule
	public ExpectedException excepted = ExpectedException.none();

	/**
	 * getUserList正常系
	 */
	@Test
	public void getUserList_Ok_001() {
		System.out.println("getUserList_Ok_001");

		// テストデータ
		String id = "testuser1";
		String password = "testuser1";
		int permissionLevel = 1;
		String note = "テストgetUserList";

		// テスト用にスタブを呼び出してオーバーライド
		UserRepository userRepository = new StubUserRepository() {
			@Override
			public List<UserEntity> findAll() {
				List<UserEntity> userList = new ArrayList<>();

				UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);

				userList.add(userEntity);

				return userList;
			}
		};

		// スタブオブジェクトの設定
		userService.setUserRepository(userRepository);

		// 実行結果
		List<UserEntity> result = userService.getUserList();

		// 実行結果とテストデータの比較
		assertEquals(id, result.get(0).getId());
		assertEquals(password, result.get(0).getPassword());
		assertEquals(permissionLevel, result.get(0).getPermissionLevel());
		assertEquals(note, result.get(0).getNote());
	}

	/**
	 * getUserList正常系2
	 * 
	 * Listサイズ:0
	 */
	@Test
	public void getUserList_Ok_002() {
		System.out.println("getuserList_Ok_002 : ListSize:0");

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public List<UserEntity> findAll() {
				List<UserEntity> userList = new ArrayList<>();
				return userList;
			}
		};

		userService.setUserRepository(userRepository);

		List<UserEntity> userList = userService.getUserList();

		assertEquals(0, userList.size());

	}

	/**
	 * getUser正常系
	 * 
	 * 
	 */

	@Test
	public void getUser_Ok_001() {
		System.out.println("findOneById_Ok_001");

		// テスト用データ
		String id = "testUser1";
		String password = "testUser1";
		int permissionLevel = 2;
		String note = "テストgetUser";

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public UserEntity findOneById(String id) {
				UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);

				return userEntity;
			}
		};

		userService.setUserRepository(userRepository);

		UserEntity userEntity = userService.getUser(id);

		assertEquals(id, userEntity.getId());
		assertEquals(password, userEntity.getPassword());
		assertEquals(permissionLevel, userEntity.getPermissionLevel());
		assertEquals(note, userEntity.getNote());

	}

	/**
	 * getUser正常系2
	 * 
	 * return null
	 */
	@Test
	public void getUser_Ok_002() {
		System.out.println("getUser_Ok_002, return null");

		String id = "";

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public UserEntity findOneById(String id) {
				return null;
			}
		};

		userService.setUserRepository(userRepository);

		UserEntity userEntity = userService.getUser(id);

		assertEquals(null, userEntity);

	}

	/**
	 * add正常系
	 * 
	 * return 1(true);
	 */
	@Test
	public void add_Ok_001() {
		System.out.println("add_Ok_001");

		String id = "addTest";
		String password = "addTest";
		int permissionLevel = 1;
		String note = "addTest";

		UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);

		UserRepository userRepository = new StubUserRepository();

		userService.setUserRepository(userRepository);

		int result = userService.add(userEntity);

		assertEquals(1, result);
	}

	/**
	 * add正常系2
	 * 
	 * return 0(false);
	 */
	@Test
	public void add_Ok_002() {
		System.out.println("add_Ok_002");

		UserEntity userEntity = new UserEntity();

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean add(UserEntity user) {
				return false;
			}
		};

		userService.setUserRepository(userRepository);

		int result = userService.add(userEntity);

		assertEquals(0, result);
	}

	/**
	 * delete正常系
	 * 
	 * return 1(true);
	 */
	@Test
	public void delete_Ok_001() {
		System.out.println("delete_Ok_001");

		String userId = "test";

		UserRepository userRepository = new StubUserRepository();

		userService.setUserRepository(userRepository);

		int result = userService.delete(userId);

		assertEquals(1, result);
	}

	/**
	 * delete正常系2
	 * 
	 * return 0(false);
	 */
	@Test
	public void delete_OK_002() {
		System.out.println("delete_OK_002");

		String userId = null;

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean delete(String userName) {
				return false;
			}
		};

		userService.setUserRepository(userRepository);

		int result = userService.delete(userId);

		assertEquals(0, result);

	}

	/**
	 * update正常系
	 * 
	 * return 1(true);
	 */
	@Test
	public void update_Ok_001() {
		System.out.println("update_Ok_001");

		String id = "testUser";
		String password = "testUser";
		int permissionLevel = 2;
		String note = "update_Ok_001";

		UserEntity userEntity = new UserEntity(id, password, permissionLevel, note);

		UserRepository userRepository = new StubUserRepository();

		int result = userService.update(userEntity);

		assertEquals(1, result);
	}

	/**
	 * update正常系
	 * 
	 * return 0(false);
	 */
	@Test
	public void update_Ok_002() {
		System.out.println("update_Ok_002");

		UserEntity userEntity = new UserEntity();

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean update(UserEntity user) {
				return false;
			}
		};

		userService.setUserRepository(userRepository);

		int result = userService.update(userEntity);

		assertEquals(0, result);
	}

	/**
	 * NGバターンのテスト Mybatisの例外がどのような形で返ってくるのか調査中のため
	 * 
	 * @Ignore をメソッドにつけてスキップしています。
	 */

	/**
	 * getUserList異常系
	 * 
	 * return true;
	 */
	@Ignore
	@Test
	public void getUserList_Ng_001() {
		System.out.println("getUserList_Ng_001");
		String errorMsg = "getUserList RuntimeException";

		excepted.expect(RuntimeException.class);
		excepted.expectMessage(errorMsg);

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public List<UserEntity> findAll() {
				throw new RuntimeException(errorMsg);

			}
		};

		userService.setUserRepository(userRepository);

		List<UserEntity> userList = userService.getUserList();

	}

	/**
	 * getUser異常系
	 */
	@Ignore
	@Test
	public void getUser_Ng_001() {
		System.out.println("getUser_Ng_001");
		String errorMsg = "getUser RuntimeException";

		excepted.expect(RuntimeException.class);
		excepted.expectMessage(errorMsg);

		// テストデータ
		String id = "testUser1";

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public UserEntity findOneById(String id) {
				throw new RuntimeException(errorMsg);
			}
		};

		userService.setUserRepository(userRepository);

		UserEntity userEntity = userService.getUser(id);

	}

	/**
	 * add異常系
	 */
	@Ignore
	@Test
	public void add_Ng_001() {
		System.out.println("add_Ng_001");
		String errorMsg = "add RuntimeException";

		excepted.expect(RuntimeException.class);
		excepted.expectMessage(errorMsg);

		UserEntity userEntity = new UserEntity();

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean add(UserEntity user) {
				throw new RuntimeException(errorMsg);
			}
		};

		userService.setUserRepository(userRepository);

		int result = userService.add(userEntity);
	}

	/**
	 * delete異常系
	 */
	@Ignore
	@Test
	public void delete_Ng_001() {
		System.out.println("delete_Ng_001");

		String errorMsg = "Error Now";

		excepted.expect(RuntimeException.class);
		excepted.expectMessage(errorMsg);

		String userId = null;

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean delete(String userName) {
				throw new RuntimeException(errorMsg);
			}
		};

		int result = userService.delete(userId);
	}

	/**
	 * update異常系
	 */
	@Ignore
	@Test
	public void update_Ng_001() {
		System.out.println("update_Ng_001");
		String errorMsg = "User Update RuntimeException";

		excepted.expect(RuntimeException.class);
		excepted.expectMessage(errorMsg);

		UserEntity userEntity = new UserEntity();

		UserRepository userRepository = new StubUserRepository() {
			@Override
			public boolean update(UserEntity user) {
				throw new RuntimeException(errorMsg);
			}
		};

		userService.setUserRepository(userRepository);

		int result = userService.update(userEntity);
	}

}
