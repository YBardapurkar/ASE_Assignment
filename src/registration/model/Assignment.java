package registration.model;

import java.sql.Date;

public class Assignment {
	public static final String ACTION_ASSIGN_MAR = "assign_mar";
	
	private int assignmentId;
	private int marId;
	private String assignedTo;
	private Date assignedDate;
	private String urgency;
	private int estimate;
	
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public int getMarId() {
		return marId;
	}
	public void setMarId(int marId) {
		this.marId = marId;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public int getEstimate() {
		return estimate;
	}
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}
	
	public void validateAssignment (String action, AssignmentMessage assignmentMessage) {
		if (action.equals(ACTION_ASSIGN_MAR)) {
			assignmentMessage.setUrgencyMessage(validateUrgency(action, this.getUrgency()));
			assignmentMessage.setAssignedToMessage(validateAssignedTo(action, this.getAssignedTo()));
			
			assignmentMessage.setErrorMessage();
		}
	}
	
	private String validateUrgency(String action, String urgency) {
		String result;
//		assign mar
		if(action.equals(ACTION_ASSIGN_MAR)) {
			if (urgency.equals("")) {
				result="Urgency is a required field";
			}
			 else {
				result = "";
			}
		} 
//		default
		else {
			result = "action not recognized";
		}
		return result;
	}
	
	private String validateAssignedTo(String action, String assignedTo) {
		String result;
//		assign mar
		if(action.equals(ACTION_ASSIGN_MAR)) {
			if (assignedTo.equals("")) {
				result="Select a Repairer to assign";
			}
			 else {
				result = "";
			}
		} 
//		default
		else {
			result = "action not recognized";
		}
		return result;
	}
}
