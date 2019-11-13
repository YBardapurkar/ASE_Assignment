package test.mac_repair.model;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import registration.model.MAR;
import registration.model.MARError;


@RunWith(JUnitParamsRunner.class)
public class MARTest {
	
	MAR mar;
	MARError marError;
	DateUtils dateUtils;
	
	@Before
	public void setUp() throws Exception {
		mar = new MAR();
		marError = new MARError();
		dateUtils = EasyMock.strictMock(DateUtils.class);
	}

	@Test
	@FileParameters("src/test/mac_repair/model/MARTest.csv")
	public void testMAR(String description,String expectedMessagedescription, 
			String urgency,String expectedMessageUrgency ,int id,String expectedMessageId, 
			String assignedTo, String expectedMessageAssignedto, String reportedBy, String expectedMessagereportedBy, 
			String facilityName, String expectedMessagefacilityName, String date, String expectedMessageDate, 
			String message, String currentTimestamp) {
		 
		EasyMock.expect(dateUtils.nowTimeStamp()).andReturn(currentTimestamp);
		EasyMock.replay(dateUtils);
		
		mar.setDescription(description);
		mar.setReportedBy(reportedBy);
		mar.setFacilityName(facilityName);
		mar.setId(id);
		mar.setDate(date);
		
		marError = mar.validateMar(dateUtils.nowTimeStamp());
		 
		assertEquals(description,mar.getDescription());
		assertEquals(id,mar.getId());
		assertEquals(reportedBy,mar.getReportedBy());
		assertEquals(facilityName,mar.getFacilityName());
		assertEquals(date, mar.getDate());
		 
		assertEquals(expectedMessagedescription, marError.getDescriptionError());
		assertEquals(expectedMessageId, marError.getIdError());
		assertEquals(expectedMessagereportedBy, marError.getReportedByError());
		assertEquals(expectedMessagefacilityName, marError.getFacilityNameError()); 
		
		assertEquals(expectedMessageDate, marError.getDateError());
	}
}
