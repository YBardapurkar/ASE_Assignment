package registration.model;

import java.sql.Timestamp;

import registration.util.DateUtils;

public class Reservation {
	private static DateUtils dateUtils = new DateUtils();
	
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
	
	public void validateReservation(ReservationMessage reservationMessage, String startTime, int durationHours, Timestamp today) {
		reservationMessage.setReservationIdMessage(validateReservationId(this.getReservationId()));
		reservationMessage.setMarIdMessage(validateMarId(this.getMarId()));
		reservationMessage.setFacilityNameMessage(validateFacilityName(this.getFacilityName()));
		reservationMessage.setStartTimeMessage(validateStartTime(this.getStartTime(), startTime, durationHours, today));
		reservationMessage.setErrorMessage();
	}
	
	private String validateReservationId(int reservationId) {
		String result;
		if (reservationId <= 0) {
			result = "Assignment Id cannot be 0 or negative";
		} else {
			result = "";
		}
		return result;
	}
	
	private String validateMarId(int marId) {
		String result;
		if (marId <= 0) {
			result = "MAR Id cannot be 0 or negative";
		} else {
			result = "";
		}
		return result;
	}
	
	private String validateFacilityName(String facilityName) {
		String result;
		if (facilityName.isEmpty()) {
			result = "Facility name is required";
		} else {
			result = "";
		}
		return result;
	}
	
	public String validateStartTime(Timestamp startDate , String startTime, int durationHours, Timestamp today) {
		String result;
//		System.out.println(startDate);
//		System.out.println(today);
//		System.out.println();
		if (durationHours >= 18 && !startTime.contains("06:00:00")) {
			result = "Start time should be 6AM for multi day reservation";
		}
		else if (durationHours < 18 && !startDate.toString().contains(today.toString().split(" ")[0])) {
			result = "Start time should be current date for single day reservation";			
		}
//		else if(durationHours <18 && !startTime.contains(DateUtils.nowDate().toString())) {
//			result = "Start time should be current date for single day reservation";
//		}		
		else if (today.after(startDate)) {
			result = "Start date cannot be before current time";
		}
		else {
			result = "";
		}
		return result;
	}
}
