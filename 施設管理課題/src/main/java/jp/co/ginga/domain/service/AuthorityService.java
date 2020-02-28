package jp.co.ginga.domain.service;

import jp.co.ginga.domain.entity.UserEntity;


public interface AuthorityService {

	public UserEntity checkLoginAccount(final UserEntity input);

}
