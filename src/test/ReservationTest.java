package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Reservation;
import registration.model.ReservationMessage;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest {

	Reservation reservation;
	ReservationMessage reservationMessage;
	DateUtils dateUtils;
	// User user;

	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
		reservationMessage = new ReservationMessage();
		dateUtils = EasyMock.strictMock(DateUtils.class);
	}

	@Test
	@FileParameters("src/test/ReservationTestCases.csv")
	public void testReservation(int testCaseNumber, int reservationId, int marId, String facilityName, String startTime,
			String endTime, int duration, String todayTimestamp, String reservationIdMessage, String marIdMessage, 
			String facilityNameMessage, String startTimeMessage, String endTimeMessage, String errorMessage, String description) {

		EasyMock.expect(dateUtils.nowTimeStamp()).andReturn(todayTimestamp);
		EasyMock.expect(dateUtils.nowDate()).andReturn(todayTimestamp.split(" ")[0]);
		EasyMock.expect(dateUtils.isTimeStampToday()).andReturn(startTime.split(" ")[0].equals(todayTimestamp.split(" ")[0]));
		EasyMock.replay(dateUtils);
		
//		System.out.println(startTime + " " + todayTimestamp);
//		System.out.println(startTime.split(" ")[0].equals(todayTimestamp.split(" ")[0]));
		
		Timestamp today = Timestamp.valueOf(dateUtils.nowTimeStamp());
//		2019-11-01 14:00:00.0 

		reservation.setReservationId(reservationId);
		reservation.setMarId(marId);
		reservation.setFacilityName(facilityName);
		reservation.setStartTime(Timestamp.valueOf(startTime)); // check this once it should be correct format
		reservation.setEndTime(Timestamp.valueOf(endTime));
		
		reservation.validateReservation(reservationMessage, startTime, duration, today);

		assertEquals(reservationId, reservation.getReservationId());
		assertEquals(marId, reservation.getMarId());
		assertEquals(facilityName, reservation.getFacilityName());
		assertEquals(Timestamp.valueOf(startTime), reservation.getStartTime());
		assertEquals(Timestamp.valueOf(endTime), reservation.getEndTime());

		assertEquals(reservationIdMessage, reservationMessage.getReservationIdMessage());
		assertEquals(marIdMessage, reservationMessage.getMarIdMessage());
		assertEquals(facilityNameMessage, reservationMessage.getFacilityNameMessage());
		assertEquals(startTimeMessage, reservationMessage.getStartTimeMessage());
		
		assertEquals(errorMessage, reservationMessage.getErrorMessage());
	}

}
