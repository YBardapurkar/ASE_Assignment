package test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.SearchFacility;
import registration.model.SearchFacilityError;


@RunWith(JUnitParamsRunner.class)
public class searchFacilityTest {

	SearchFacility searchFacility;
	SearchFacilityError searchFacilityError;
	DateUtils dateUtils;
//	DateUtils dateUtils2;
	
	@Before
	public void setUp() throws Exception {
		searchFacilityError = new SearchFacilityError();
		
//		dateUtils2 = new DateUtils();
		dateUtils = EasyMock.strictMock(DateUtils.class);
	}

	
	@Test
	@FileParameters("src/test/searchFacilityTestCase.csv")
		
		public void testSearchFacility(int testCaseNumber, String facilityType, String searchDate, String searchTime, String currentTimeStamp, String showFacilityMessage,
				String searchDateError, String facilityTypeError, String searchTimeError, String errorMsg) 

		{

			//EasyMock.expect(dateUtils.nowTimeStamp()).andReturn(DateUtils.nowTimeStamp());
			
			EasyMock.expect(dateUtils.nowTimeStamp()).andReturn(currentTimeStamp);
			EasyMock.replay(dateUtils);

			//System.out.println(dateUtils.nowTimeStamp()+"inter");
			
			searchFacility = new SearchFacility();
		
						
			searchFacility.setSearchDate(searchDate);
			//System.out.println(searchFacility.getSearchDate());
			
			searchFacility.setSearchTime(searchTime);
			searchFacility.setFacilityType(facilityType);
			
			assertEquals(searchTime, searchFacility.getSearchTime());
			assertEquals(facilityType, searchFacility.getFacilityType());
			assertEquals(searchDate, searchFacility.getSearchDate());
			
			
			searchFacilityError = searchFacility.validateSearchFacility(searchDate + " " + searchTime, currentTimeStamp);
			
			
			//System.out.println((searchDate + " " + searchTime).length());
			
//			facilityErrorMsg = facility.validateFacility();
//			System.out.println(facilityErrorMsg.getFacilityTypeError());
			
			
			
			assertEquals(showFacilityMessage, searchFacilityError.getShowFacilityMessage());
			assertEquals(facilityTypeError, searchFacilityError.getFacilityTypeError());
			assertEquals(searchDateError, searchFacilityError.getSearchDateError());
			String h = searchFacilityError.getSearchTimeError();
			System.out.println("!!!!!!");
			System.out.println(h);
			assertEquals(searchTimeError,searchFacilityError.getSearchTimeError());
			assertEquals(errorMsg, searchFacilityError.getErrorMsg());
			//assertEquals(errorMsg, searchFacilityError.getErrorMsg());
			
			
			//System.out.println(FacilityDAO.getFacilitiesByFacilityType(facilityType).size());	
//			facilityErrorMsg.setErrorMsg();
//			assertEquals(errorMsg, facilityErrorMsg.getErrorMsg());
			
			
				}


}
