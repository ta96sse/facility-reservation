package jp.co.ginga.domain.service;

import java.util.List;

import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.UserRepository;

/**
 * ユーザーサービス
 * 
 * @author yoshi
 *
 */
public interface UserService {

	public List<UserEntity> getUserList();

	public UserEntity getUser(String loginId);

	public int delete(String userName);

	public int update(UserEntity user);

	public int add(UserEntity user);

	public void setUserRepository(UserRepository userRepository);

}
