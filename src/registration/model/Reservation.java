package registration.model;

import java.sql.Date;

import registration.util.DateUtils;

public class Reservation {
	
	private int reservationId;
	private int marId;
	private String facilityName;
	private Date startTime;
	private Date endTime;
	
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void validateReservation(String action, ReservationMessage reservationMessage) {
		reservationMessage.setStartTimeMessage(validateStartTime(action, this.getStartTime()));
		reservationMessage.setErrorMessage();
	}
	
	public String validateStartTime(String action, Date startDate) {
		String result;
		if (DateUtils.now().after(startDate)) {
			result = "Start date cannot be before current time";
		} else {
			result = "";
		}
		return result;
	}
}
