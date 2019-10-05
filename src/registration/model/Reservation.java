package registration.model;

import java.sql.Date;
import java.sql.Timestamp;

import registration.util.DateUtils;

public class Reservation {
	
	private int reservationId;
	private int marId;
	private String facilityName;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getMarId() {
		return marId;
	}
	public void setMarId(int marId) {
		this.marId = marId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public void validateReservation(String action, ReservationMessage reservationMessage) {
		reservationMessage.setStartTimeMessage(validateStartTime(action, this.getStartTime()));
		reservationMessage.setErrorMessage();
	}
	
	public String validateStartTime(String action, Timestamp startDate) {
		String result;
		if (DateUtils.now().after(startDate)) {
			result = "Start date cannot be before current time";
		} else {
			result = "";
		}
		return result;
	}
}
