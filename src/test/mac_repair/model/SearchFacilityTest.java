package test.mac_repair.model;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import mac_repair.model.SearchFacility;
import mac_repair.model.SearchFacilityError;


@RunWith(JUnitParamsRunner.class)
public class SearchFacilityTest {

	SearchFacility searchFacility;
	SearchFacilityError searchFacilityError;
	DateUtils dateUtils;
	
	@Before
	public void setUp() throws Exception {
		searchFacilityError = new SearchFacilityError();
		
		dateUtils = EasyMock.strictMock(DateUtils.class);
	}

	
	@Test
	@FileParameters("src/test/mac_repair/model/searchFacilityTestCase.csv")
	public void testSearchFacility(int testCaseNumber, String facilityType, String searchDate, 
			String searchTime, String currentTimeStamp, String showFacilityMessage,
			String searchDateError, String facilityTypeError, String searchTimeError, String errorMsg) {
		EasyMock.expect(dateUtils.nowTimeStamp()).andReturn(currentTimeStamp);
		EasyMock.replay(dateUtils);

		searchFacility = new SearchFacility();
	
		searchFacility.setSearchDate(searchDate);
		searchFacility.setSearchTime(searchTime);
		searchFacility.setFacilityType(facilityType);
			
		searchFacilityError = searchFacility.validateSearchFacility(searchDate + " " + searchTime, currentTimeStamp);

		assertEquals(searchTime, searchFacility.getSearchTime());
		assertEquals(facilityType, searchFacility.getFacilityType());
		assertEquals(searchDate, searchFacility.getSearchDate());
		
		assertEquals(showFacilityMessage, searchFacilityError.getShowFacilityMessage());
		assertEquals(facilityTypeError, searchFacilityError.getFacilityTypeError());
		assertEquals(searchDateError, searchFacilityError.getSearchDateError());
		assertEquals(searchTimeError,searchFacilityError.getSearchTimeError());
		
		assertEquals(errorMsg, searchFacilityError.getErrorMsg());
	}
}
