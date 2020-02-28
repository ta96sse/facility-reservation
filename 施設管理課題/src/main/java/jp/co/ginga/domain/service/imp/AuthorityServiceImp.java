package jp.co.ginga.domain.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.UserRepository;
import jp.co.ginga.domain.service.AuthorityService;

@Service
public class AuthorityServiceImp implements AuthorityService {


	@Autowired
	UserRepository repo;

	@Override
	public UserEntity checkLoginAccount(UserEntity input) {

		return repo.findOneByIdAndPassword(input.getId(), input.getPassword());
	}

}
