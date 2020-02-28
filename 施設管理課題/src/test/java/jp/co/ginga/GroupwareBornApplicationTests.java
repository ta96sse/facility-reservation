package jp.co.ginga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.ginga.application.helper.user.TestUserHelper;
import jp.co.ginga.domain.service.TestUserService;

/**
 * @author suwayama
 *
 *Test実行クラス 、@SuiteClassesで実行するテストクラスを指定する
 */
@RunWith(Suite.class)
@SuiteClasses({ TestUserService.class, TestUserHelper.class })
@SpringBootTest
public class GroupwareBornApplicationTests {

	@Test
	public void contextLoads() {
	}

}
