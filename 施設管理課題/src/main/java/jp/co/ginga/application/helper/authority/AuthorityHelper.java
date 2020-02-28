/**
 *
 */
package jp.co.ginga.application.helper.authority;

import org.springframework.stereotype.Component;

import jp.co.ginga.application.form.authority.AuthorityLoginForm;
import jp.co.ginga.domain.entity.UserEntity;

/**
 * 認証コントローラーヘルパー
 * @author yoshi
 *
 */
@Component
public class AuthorityHelper {


	/**
	 * AuthorityLoginFormからUserEntityへのデータ変換処理
	 * @param input
	 * @return
	 */
	public UserEntity convertToUserEntityFromAuthorityLoginForm(final AuthorityLoginForm input) {
		return new UserEntity(input.getLoginId(), input.getPassword());

	}

}
