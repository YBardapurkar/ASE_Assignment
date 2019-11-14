package mac_repair.model;

import java.sql.Date;
import java.util.ArrayList;

import mac_repair.data.AssignmentDAO;
import mac_repair.data.UserDAO;
import mac_repair.util.DropdownUtils;

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
	
	public void validateAssignment (String action, AssignmentMessage assignmentMessage, Date currentDate, String duration) {
		assignmentMessage.setUrgencyMessage(validateUrgency(action, this.getUrgency()));
		assignmentMessage.setAssignedToMessage(validateAssignedTo(action, this.getAssignedTo()));
		assignmentMessage.setEstimateMessage(validateEstimate(action,duration, this.getEstimate()));
		assignmentMessage.setMarIdMessage(validateMarId(action, this.getMarId()));
		assignmentMessage.setDateErrorMessage(validateDate(this.assignedDate, currentDate));
		assignmentMessage.setErrorMessage();
	}
	 
	private String validateDate(Date assignedDate, Date currentDate)
	{ 
		//setDate(DateUtils.nowTimeStamp());
		String result;
		if(currentDate.equals(assignedDate)) {
			result = "";
		} else {
			result = "Date not correct";
		}
		return result;
	}
	
	private String validateUrgency(String action, String urgency) {
		String result;
//		assign mar
	//	if(action.equals(ACTION_ASSIGN_MAR)) {
		

		
		if(urgency.equals(""))
		{
			result = "Urgency is a required field";
		}
		else if(urgency.equals("Minor")|| urgency.equals("Major") || urgency.equals("Unusable"))
		{
			result= "";
		}
		else
		{
			result = "Urgency data is not valid";
		}
		
	return result;
		
		
		
		
		
		

			
	//	} 
//		default
		/*else {
			result = "action not recognized";
		}*/
	}
	
	private String validateEstimate(String action,String duration, int estimate) {
		String result;	
		
		ArrayList<String[]> estimateDropDown = DropdownUtils.getRepairTimeDropdown(duration);
		if(estimateDropDown.stream().anyMatch(estimateArray -> Integer.parseInt(estimateArray[1]) == estimate))
		{
				
			result = "";
			
		}
		else
		{
			result="Estimate is not valid";
		}
//		
	//	} 
//		default
		/*else {
			result = "action not recognized";
		}*/
		return result;
	}
	
	private String validateMarId(String action, int marId) {
		String result;
//		assign mar
	//	if(action.equals(ACTION_ASSIGN_MAR)) {
			if (marId <= 0) {
				result="MarId cannot be 0 or negative";
			}
			 else {
				result = "";
			}
		//} 
//		default
		/*else {
			result = "action not recognized";
		}*/
		return result;
	}
	
	private String validateAssignedTo(String action, String assignedTo) {
		String result = "";
		ArrayList<User> repairers = UserDAO.getUsersByRole("Repairer");
//		assign mar
	//	if(action.equals(ACTION_ASSIGN_MAR)) {
			if (assignedTo.equals("")) {
				result="Select a Repairer to assign";
			} else if(!repairers.stream().anyMatch(listOfRepairers -> listOfRepairers.getUsername().contains(assignedTo))) {
				result = "Repairer name is not in list of repairers";
			} else if (AssignmentDAO.getAssignmentCountByDay(assignedTo, new Date(System.currentTimeMillis())) >= 5) {
//				More than 5 in a day, cannot assign
				result = "Cannot assign more than 5 MARs to this repairer today";
			} else if (AssignmentDAO.getAssignmentCountByWeek(assignedTo) >= 10) {
//				More than 10 in a week, cannot assign
				result = "Cannot assign more than 10 MARs to this repairer in this week";
			} else {
				result = "";
			}
	//	} 
//		default
		/*else {
			result = "action not recognized";
		}*/
		return result;
	}
}
