package registration.model;

import java.sql.Timestamp;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.State;

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
	
	public void validateReservation(ReservationMessage reservationMessage, String startTime, int durationHours) {
		reservationMessage.setStartTimeMessage(validateStartTime(this.getStartTime(), startTime, durationHours));
		reservationMessage.setErrorMessage();
	}
	
	public String validateStartTime(Timestamp startDate , String startTime, int durationHours) {
		String result;
		if(startTime.length() != 16 ) {
			result = "Start time should be in mm/dd/yyyy hh:ss AM/PM format";	
		}
		else if (durationHours >= 18 && !startTime.endsWith("06:00:00")) {
			result = "Start time should be 6AM for multi day reservation";
		}		
		else if(durationHours >=18 && startTime.contains(DateUtils.nowDate().toString())) {
			result = "Start time should not be the current date for multi day reservation";
		}
		else if (durationHours <18 && !DateUtils.isTimeStampToday(startTime)) {
			result = "Start time should be current date for single day reservation";			
		}
//		else if(durationHours <18 && !startTime.contains(DateUtils.nowDate().toString())) {
//			result = "Start time should be current date for single day reservation";
//		}		
		else if (DateUtils.now().after(startDate)) {
			result = "Start date cannot be before current time";
		}
		else {
			result = "";
		}
		return result;
	}
}
