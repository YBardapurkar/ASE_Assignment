package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Assignment;
import registration.model.AssignmentMessage;
import registration.model.Reservation;
import registration.model.ReservationMessage;
import registration.model.User;

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
	@FileParameters("src/test/AssignmentTestCases.csv")
	public void testAssignment(int testCaseNumber, int reservationId, int marId, String facilityName, String startTime,
			String endTime, String today, String startTimeMessage, String errorMessage) {

//		EasyMock.expect(dateUtils.now()).andReturn(Date.valueOf(today));
//		EasyMock.replay(dateUtils);
//
//		Date todayDate = dateUtils.now();
		//2019-11-01 14:00:00.0 

		reservation.setReservationId(reservationId);
		reservation.setMarId(marId);
		// Timestamp.valueOf(datetimeLocal.replace("T", " "))
		reservation.setStartTime(Timestamp.valueOf(startTime)); // check this once it should be correct format
		reservation.setFacilityName(facilityName);
		reservation.setEndTime(Timestamp.valueOf(startTime));

		reservation.validateReservation(reservationMessage, startTime);

		assertEquals(reservationId, reservation.getReservationId());
		assertEquals(marId, reservation.getMarId());
		assertEquals(facilityName, reservation.getFacilityName());
		assertEquals(startTime, reservation.getStartTime());
		assertEquals(endTime, reservation.getEndTime());

		// reservationMessage.setMessage("Facility Reserved Sucessfully");

		assertEquals(startTimeMessage, reservationMessage.getStartTimeMessage());
		assertEquals(errorMessage, reservationMessage.getErrorMessage());

	}

}
