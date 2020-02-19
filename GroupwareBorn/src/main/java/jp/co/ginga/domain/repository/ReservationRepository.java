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

import jp.co.ginga.domain.entity.ReservationEntity;

/**
 * 施設予約テーブルRepository
 * @author yoshi
 *
 */
@Mapper
@Repository
public interface ReservationRepository {
	/**
	 * 予約IDによる1件、検索処理
	 *
	 * @param id
	 * @return
	 */
	@Select("select * from reservation where id=#{id}")
	@Results({
			@Result(property = "startTime", column = "start_time"),
			@Result(property = "endTime", column = "end_time"),
			@Result(property = "facilityEntity", column = "facility_id", one = @One(select = "jp.co.ginga.domain.repository.FacilityRepository.findOneById", fetchType = FetchType.EAGER)),
			@Result(property = "userId", column = "user_id")
	})
	public ReservationEntity findOneById(int id);

	/**
	 * 予約全件検索処理
	 *
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@Select("select * from reservation")
	@Results({
			@Result(property = "startTime", column = "start_time"),
			@Result(property = "endTime", column = "end_time"),
			@Result(property = "facilityId", column = "facility_id"),
			@Result(property = "userId", column = "user_id")
	})
	public List<ReservationEntity> findAll();

	/**
	 * 施設ID、年月による検索処理
	 *
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@Select("select * from reservation where facility_id = #{facilityId} and year(start_time) = #{year} and month(start_time) = #{month} order by start_time asc")
	@Results({
			@Result(property = "startTime", column = "start_time"),
			@Result(property = "endTime", column = "end_time"),
			@Result(property = "facilityEntity", column = "facility_id", one = @One(select = "jp.co.ginga.domain.repository.FacilityRepository.findOneById", fetchType = FetchType.EAGER)),
			@Result(property = "userId", column = "user_id")
	})
	public List<ReservationEntity> findListByFacilityId(int facilityId, int year, int month);

	/**
	 * 施設ID、年月日による検索処理
	 *
	 * @throws SQLException
	 */
	@Select("select count(id) from reservation where id != #{id} and start_time < #{endTime} and end_time > #{startTime} and facility_id = #{facilityEntity.id}")
	public int check(ReservationEntity reservationEntity);

	/**
	 * 新規予約処理
	 *
	 * @throws SQLException
	 */
	@Insert("insert into reservation (start_time,end_time,facility_id,user_id) values (#{startTime},#{endTime},#{facilityEntity.id},#{userId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean add(ReservationEntity reservationEntity);

	/**
	 * 予約更新処理
	 *
	 * @throws Exception
	 */
	@Update("update reservation set start_time = #{startTime}, end_time = #{endTime}, user_id = #{userId} where id = #{id}")
	public boolean update(ReservationEntity reservationEntity);

	/**
	 * 予約削除処理
	 */
	@Delete("delete from reservation where id = #{id}")
	public boolean delete(int id);

}
