package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import registration.model.MAR;
import registration.model.MARError;
import registration.util.*;


@RunWith(JUnitParamsRunner.class)
public class MARTest {
	
	MAR mar;
	MARError marError;
	
	@Before
	public void setUp() throws Exception {
		mar = new MAR();
		marError = new MARError();
	}

	@Test
	@FileParameters("src/test/MARTest.csv")
	public void validateMartest(String description,String expectedMessagedescription,String urgency,String expectedMessageUrgency
			,int id,String expectedMessageId, String assignedTo, String expectedMessageAssignedto, String reportedBy, String expectedMessagereportedBy
			,String facilityName, String expectedMessagefacilityName, String date, String expectedMessageDate ,String message) {
		 String expecteddate = DateUtils.nowTimeStamp().split(" ")[0];
		 mar.setDescription(description);
		 mar.setReportedBy(reportedBy);
		 mar.setFacilityName(facilityName);
		 mar.setId(id);

		 if(date.contentEquals("current"))
		 {
			 mar.setDate(DateUtils.nowTimeStamp());
			 date = DateUtils.nowTimeStamp().split(" ")[0];
		 }
		 else
		 {
			 mar.setDate(date);
		 }
		 
		 mar.validateMar(mar, marError);
		 
		 assertEquals(description,mar.getDescription());
		 assertEquals(id,mar.getId());
		 assertEquals(reportedBy,mar.getReportedBy());
		 assertEquals(facilityName,mar.getFacilityName());
		 assertEquals(date,mar.getDate());
		 
		 assertEquals(expectedMessagedescription, marError.getDescriptionError());
		 assertEquals(expectedMessageId, marError.getIdError());
		 assertEquals(expectedMessagereportedBy, marError.getReportedByError());
		 assertEquals(expectedMessagefacilityName, marError.getFacilityNameError()); 
		 assertEquals(expectedMessageDate, marError.getDateError()); 
	}
}
