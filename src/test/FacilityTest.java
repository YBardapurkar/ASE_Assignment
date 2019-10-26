package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Facility;
import registration.model.FacilityErrorMessages;


@RunWith(JUnitParamsRunner.class)
public class FacilityTest {

	Facility facility;
	FacilityErrorMessages facilityErrorMsg;
	
	@Before
	public void setUp() throws Exception {
	//	facility = new Facility();
		facilityErrorMsg = new FacilityErrorMessages();
	}

	
	
	@Test
	@FileParameters("src/test/facilityTest.csv")
	public void testFacility(int testCaseNumber, String facilityType, String facilityName, String facilityInterval, String facilityDuration, String facilityVenue,
			 String facilityNameError, String facilityTypeError, String facilityIntervalError, String facilityDurationError, String facilityVenueError) 

	{
		facility = new Facility();
		facility = new Facility(facilityName, facilityType, facilityInterval, facilityDuration, facilityVenue);
		String ten = "";
		facilityErrorMsg = facility.validateFacility();
	
		
		facility.setFacilityName(facilityName);
		facility.setFacilityVenue(facilityVenue);
		facility.setFacilityDuration(facilityDuration);
		facility.setFacilityInterval(facilityInterval);
		facility.setFacilityType(facilityType);
		
//		facilityErrorMsg = facility.validateFacility();
//		System.out.println(facilityErrorMsg.getFacilityTypeError());
		
		assertEquals(facilityName, facility.getFacilityName());
		assertEquals(facilityType, facility.getFacilityType());
		assertEquals(facilityInterval, facility.getFacilityInterval());
		assertEquals(facilityDuration, facility.getFacilityDuration());
		assertEquals(facilityVenue, facility.getFacilityVenue());
		//assertEquals(showFacilityMessage, facility.getState());
		
		
		assertEquals(facilityNameError, facilityErrorMsg.getFacilityNameError());
		assertEquals(facilityTypeError, facilityErrorMsg.getFacilityTypeError());
		assertEquals(facilityIntervalError, facilityErrorMsg.getFacilityIntervalError());
		assertEquals(facilityDurationError, facilityErrorMsg.getFacilityDurationError());
		assertEquals(facilityVenueError, facilityErrorMsg.getFacilityVenueError());
		
		
//		facilityErrorMsg.setErrorMsg();
//		assertEquals(errorMsg, facilityErrorMsg.getErrorMsg());
		
		
			}

}
