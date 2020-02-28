package jp.co.ginga.domain.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import jp.co.ginga.domain.entity.UserEntity;

/**
 * ユーザーテーブルRepository
 * 
 * @author yoshi
 *
 */
@Repository
@Mapper
public interface UserRepository {

	/**
	 * ユーザーID,パスワードによる1件、検索処理
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	@Select("select id,password,permission_level as permissionLevel,note from user where id=#{id} and password=#{password}")
	public UserEntity findOneByIdAndPassword(String id, String password);

	/**
	 * ユーザーIDによる1件、検索処理
	 * 
	 * @param loginId
	 * @return
	 */
	@Select("select id,password,permission_level as permissionLevel,note from user where id=#{id}")
	public UserEntity findOneById(String id);

	/**
	 * ユーザー全件検索処理
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@Select("select id,password,permission_level as permissionLevel,note from user")
	public List<UserEntity> findAll();

	/**
	 * ユーザー新規登録処理
	 * 
	 * @throws SQLException
	 */
	@Insert("insert into user values (#{id},#{password},#{permissionLevel}, #{note}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean add(UserEntity user);

	/**
	 * ユーザー更新処理
	 * 
	 * @throws Exception
	 */
	@Update("update user set password = #{password}, permission_level = #{permissionLevel}, note = #{note} where id = #{id}")
	public boolean update(UserEntity user);

	/**
	 * ユーザー削除処理
	 */
	@Delete("delete from user where id = #{userName}")
	public boolean delete(String userName);

}
