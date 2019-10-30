package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.data.FacilityDAO;
import registration.model.Facility;
import registration.model.FacilityErrorMessages;
import registration.model.SearchFacility;
import registration.model.SearchFacilityError;
import registration.util.DateUtils;

@RunWith(JUnitParamsRunner.class)
public class searchFacilityTest {

	SearchFacility searchFacility;
	SearchFacilityError searchFacilityError;
	
	@Before
	public void setUp() throws Exception {
		searchFacilityError = new SearchFacilityError();
	}

	
	@Test
	@FileParameters("src/test/searchFacilityTestCase.csv")
		
		public void testSearchFacility(int testCaseNumber, String facilityType, String searchDate, String searchTime, String showFacilityMessage,
				String searchDateError, String facilityTypeError, String errorMsg) 

		{
			searchFacility = new SearchFacility();
		
			if(searchTime.equals("current"))
			{
				searchTime = DateUtils.nowTimeStamp().split(" ")[1];
				
			}
		
			
			searchFacility.setSearchDate(searchDate);
			//System.out.println(searchFacility.getSearchDate());
			
			searchFacility.setSearchTime(searchTime);
			searchFacility.setFacilityType(facilityType);
			
			searchFacilityError = searchFacility.validateSearchFacility(searchDate + " " + searchTime);
			
//			facilityErrorMsg = facility.validateFacility();
//			System.out.println(facilityErrorMsg.getFacilityTypeError());
			
			assertEquals(searchTime, searchFacility.getSearchTime());
			assertEquals(facilityType, searchFacility.getFacilityType());
			assertEquals(searchDate, searchFacility.getSearchDate());
			
			
			assertEquals(showFacilityMessage, searchFacilityError.getShowFacilityMessage());
			assertEquals(facilityTypeError, searchFacilityError.getFacilityTypeError());
			assertEquals(searchDateError, searchFacilityError.getSearchDateError());
			assertEquals(errorMsg, searchFacilityError.getErrorMsg());
			
			
			//System.out.println(FacilityDAO.getFacilitiesByFacilityType(facilityType).size());	
//			facilityErrorMsg.setErrorMsg();
//			assertEquals(errorMsg, facilityErrorMsg.getErrorMsg());
			
			
				}


}
