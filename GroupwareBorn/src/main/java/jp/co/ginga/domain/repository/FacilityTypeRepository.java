package jp.co.ginga.domain.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import jp.co.ginga.domain.entity.FacilityTypeEntity;

/**
 * 施設種別テーブルRepository
 * @author yoshi
 *
 */
@Mapper
@Repository
public interface FacilityTypeRepository {

	/**
	 * 施設種別IDによる1件、検索処理
	 *
	 * @param typeId
	 * @return
	 */
	@Select("select * from facility_type where id=#{id}")
	@Results({
			@Result(property = "insertDate", column = "insert_date"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "userId", column = "user_id")
	})
	public FacilityTypeEntity findOneById(int id);

	/**
	 * 施設種別全件検索処理
	 *
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@Select("select * from facility_type")
	@Results({
			@Result(property = "insertDate", column = "insert_date"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "userId", column = "user_id")
	})
	public List<FacilityTypeEntity> findAll();

	/**
	 * 施設種別新規登録処理
	 *
	 * @throws SQLException
	 */
	@Insert("insert into facility_type (name,insert_date,update_date,user_id) values (#{name},now(),now(),#{userId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean add(FacilityTypeEntity facilityType);

	/**
	 * 施設種別更新処理
	 *
	 * @throws Exception
	 */
	@Update("update facility_type set name = #{name}, update_date = now() ,user_id = #{userId} where id = #{id}")
	public boolean update(FacilityTypeEntity facilityType);

	/**
	 * 施設種別削除処理
	 */
	@Delete("delete from facility_type where id = #{id}")
	public boolean delete(int id);

}