package jp.co.ginga.domain.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ginga.domain.entity.FacilityEntity;
import jp.co.ginga.domain.entity.ReservationEntity;
import jp.co.ginga.domain.entity.UserEntity;
import jp.co.ginga.domain.repository.ReservationRepository;
import jp.co.ginga.domain.repository.imp.StubReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService reservationService;

	@Rule
	public ExpectedException excepted = ExpectedException.none();

	@Test
	public void test() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int id = 1;
		Date startTime = df.parse("2020-02-21 12:00");
		Date endTime = df.parse("2020-02-21 14:00");
		FacilityEntity facilityEntity = new FacilityEntity(1, "会議室");
		UserEntity userEntity = new UserEntity("testuser1");

		ReservationRepository reservationRepository = new StubReservationRepository() {
			@Override
			public List<ReservationEntity> findAll() {
				List<ReservationEntity> reservationList = new ArrayList<>();
				ReservationEntity reservationEntity = new ReservationEntity(id, startTime, endTime, facilityEntity,
						userEntity);
				reservationList.add(reservationEntity);

				return reservationList;
			}
		};

		reservationService.setReservationRepository(reservationRepository);

		List<ReservationEntity> result = reservationService.getReservationList();

		assertEquals(id, result.get(0).getId());
		assertEquals(startTime, result.get(0).getStartTime());
		assertEquals(endTime, result.get(0).getEndTime());
		assertEquals(facilityEntity, result.get(0).getFacilityEntity());
		assertEquals(userEntity, result.get(0).getUserEntity());

	}

}
