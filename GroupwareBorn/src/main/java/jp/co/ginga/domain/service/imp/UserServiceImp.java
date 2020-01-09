package jp.co.ginga.domain.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.UserRepository;
import jp.co.ginga.domain.service.UserService;

/**
 * ユーザー管理サービスクラス
 * 
 * @author yoshi
 *
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

	/**
	 * ユーザリポジトリ
	 */
	@Autowired
	UserRepository repoUser;

	/**
	 * ユーザー情報リスト取得処理
	 */
	@Override
	public List<UserEntity> getUserList() {

		return repoUser.findAll();
	}

	/**
	 * 指定したユーザー名からユーザー情報を取得する処理
	 */
	@Override
	public UserEntity getUser(String userName) {
		return repoUser.findOneById(userName);
	}

	/**
	 * 指定したユーザー名からユーザー情報を削除する処理
	 */
	@Override
	public int delete(String userName) {
		if (repoUser.delete(userName)) {
			return 1;
		}
		return 0;
	}

	/**
	 * 指定したユーザー名のユーザー情報を更新する処理
	 */
	@Override
	public int update(UserEntity user) {
		if (repoUser.update(user)) {
			return 1;
		}
		System.out.println("error");
		return 0;
	}

	/**
	 * ユーザーを追加する処理
	 */
	@Override
	public int add(UserEntity user) {
		if (repoUser.add(user)) {
			return 1;
		}
		return 0;
	}

	/**
	 * テスト用のスタブオブジェクト設定処理
	 */
	@Override
	public void setUserRepository(UserRepository userRepository) {
		this.repoUser = userRepository;

	}

}
