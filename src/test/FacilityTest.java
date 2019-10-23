package test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Facility;
import registration.model.FacilityMessage;
import registration.model.User;
import registration.model.UserError;


@RunWith(JUnitParamsRunner.class)
public class FacilityTest {
	Facility facility;
	FacilityMessage facilityMessage;
	@Before
	public void setUp() throws Exception {
		facility = new Facility();
		facilityMessage = new FacilityMessage();
		
	}
	@Test
	@FileParameters("src/test/FacilityAddTestCases.csv")
	public void testAdd(int testCaseNumber, String facilityName, String facilityType, String facilityInterval, String facilityDuration, String facilityVenue,
			String facilityNameMessage, String facilityTypeMessage, String facilityIntervalMessage, String facilityDurationMessage, String facilityVenueMessage, String errorMessage){
		facility.setFacilityName(facilityName);
		facility.setFacilityType(facilityType);
		facility.setFacilityInterval(facilityInterval);
		facility.setFacilityDuration(facilityDuration);
		facility.setFacilityVenue(facilityVenue);
		
		facilityMessage = facility.validateFacility();
		
		assertEquals(facilityNameMessage, facilityMessage.getFacilityNameError());
		assertEquals(facilityTypeMessage, facilityMessage.getFacilityTypeError());
		assertEquals(facilityIntervalMessage, facilityMessage.getFacilityIntervalError());
		assertEquals(facilityDurationMessage, facilityMessage.getFacilityDurationError());
		assertEquals(facilityVenueMessage, facilityMessage.getFacilityVenueError());
		
		assertEquals(errorMessage, facilityMessage.getErrorMsg());
		
				
	}
	
	
	

}
