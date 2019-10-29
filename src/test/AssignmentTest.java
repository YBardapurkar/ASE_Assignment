package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Assignment;
import registration.model.AssignmentMessage;
import registration.model.User;

@RunWith(JUnitParamsRunner.class)
public class AssignmentTest {
	
	Assignment assignment;
	AssignmentMessage assignmentMessage;
	//User user;

	@Before
	public void setUp() throws Exception {
		assignment = new Assignment();
		assignmentMessage = new AssignmentMessage();
	}

	@Test
	@FileParameters("src/test/AssignmentTestCases.csv")
	public void testAssignment(int testCaseNumber, String action, int marId, String assignedTo, String urgency, int estimate, String marIdMessage
			, String assignedToMessage, String urgencyMessage, String estimateMessage, String errorMessage, String description) {
		
		assignment.setMarId(marId);
		assignment.setAssignedTo(assignedTo);
		assignment.setUrgency(urgency);
		assignment.setEstimate(estimate);
		
		assignment.validateAssignment(action, assignmentMessage);
		
		assertEquals(marId, assignment.getMarId());
		assertEquals(assignedTo, assignment.getAssignedTo());
		assertEquals(urgency, assignment.getUrgency());
		assertEquals(estimate, assignment.getEstimate());
		
		assertEquals(marIdMessage, assignmentMessage.getMarIdMessage());
		assertEquals(assignedToMessage, assignmentMessage.getAssignedToMessage());
		assertEquals(urgencyMessage, assignmentMessage.getUrgencyMessage());
		assertEquals(estimateMessage, assignmentMessage.getEstimateMessage());
		assertEquals(errorMessage, assignmentMessage.getErrorMessage());
		
	}

}
