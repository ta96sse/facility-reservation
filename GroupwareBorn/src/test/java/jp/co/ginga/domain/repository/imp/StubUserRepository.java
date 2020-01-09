package jp.co.ginga.domain.repository.imp;

import java.util.List;

import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.UserRepository;

/**
 * @author suwayama
 *
 *         servicをテストするためのrepositoryのスタブ
 */
public class StubUserRepository implements UserRepository {

	@Override
	public UserEntity findOneByIdAndPassword(String id, String password) {

		return null;
	}

	@Override
	public UserEntity findOneById(String id) {

		return null;
	}

	@Override
	public List<UserEntity> findAll() {

		return null;
	}

	@Override
	public boolean add(UserEntity user) {

		return true;
	}

	@Override
	public boolean update(UserEntity user) {

		return true;
	}

	@Override
	public boolean delete(String userName) {

		return true;
	}

}
