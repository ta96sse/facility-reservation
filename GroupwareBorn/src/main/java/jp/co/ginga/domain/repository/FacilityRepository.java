package jp.co.ginga.domain.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import jp.co.ginga.domain.entity.FacilityEntity;

/**
 * 施設テーブルRepository
 * @author yoshi
 *
 */
@Mapper
@Repository
public interface FacilityRepository {

	/**
	 * 施設IDによる1件、検索処理
	 *
	 * @param id
	 * @return
	 */
	@Select("select * from facility where id=#{id}")
	@Results({
			@Result(property = "facilityTypeEntity", column = "type_id", one = @One(select = "jp.co.ginga.domain.repository.FacilityTypeRepository.findOneById", fetchType = FetchType.EAGER)),
			@Result(property = "typeId", column = "type_id"),
			@Result(property = "insertDate", column = "insert_date"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "userId", column = "user_id")
	})
	public FacilityEntity findOneById(int id);

	/**
	 * 施設全件検索処理
	 *
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@Select("select * from facility")
	@Results({
			@Result(property = "facilityTypeEntity", column = "type_id", one = @One(select = "jp.co.ginga.domain.repository.FacilityTypeRepository.findOneById", fetchType = FetchType.EAGER)),
			@Result(property = "typeId", column = "type_id"),
			@Result(property = "insertDate", column = "insert_date"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "userId", column = "user_id")
	})
	public List<FacilityEntity> findAll();

	/**
	 * 施設新規登録処理
	 *
	 * @throws SQLException
	 */
	@Insert("insert into facility (name,type_id,capacity,insert_date,update_date,user_id) values (#{name},#{typeId},#{capacity},now(),now(),#{userId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean add(FacilityEntity facility);

	/**
	 * 施設更新処理
	 *
	 * @throws Exception
	 */
	@Update("update facility set name = #{name}, type_id = #{typeId}, capacity = #{capacity}, update_date = now() ,user_id = #{userId} where id = #{id}")
	public boolean update(FacilityEntity facility);

	/**
	 * 施設削除処理
	 */
	@Delete("delete from facility where id = #{id}")
	public boolean delete(int id);

}
