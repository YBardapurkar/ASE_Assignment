package test.mac_repair.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.Search;
import registration.model.SearchMessage;

@RunWith(JUnitParamsRunner.class)
public class SearchTest {
	
	Search search;
	SearchMessage searchMessage;
	
	@Before
	public void setUp() throws Exception {
		search = new Search();
		searchMessage = new SearchMessage();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/model/SearchUserTestCases.csv")
	public void testSearchUser(int testCaseNumber, String action, String searchFilter, String searchText, 
			String searchTextMessage, String searchErrorMessage, String message) {
		
		search.setSearchParam(searchText, searchFilter);
		searchMessage = search.validateSearch(action);
		
		assertEquals(searchFilter, search.getSearchFilter());
		assertEquals(searchText, search.getSearchText());
		
		assertEquals(searchTextMessage, searchMessage.getSearchTextMessage());
		assertEquals(searchErrorMessage, searchMessage.getSearchErrorMessage());
	}
	
	@Test
	@FileParameters("src/test/mac_repair/model/SearchMARTestCases.csv")
	public void testSearchMAR(int testCaseNumber, String action, String searchFilter, String searchText, 
			String searchTextMessage, String searchErrorMessage, String message) {
		
		search.setSearchFilter(searchFilter);
		search.setSearchText(searchText);
		
		searchMessage = search.validateSearch(action);
		
		assertEquals(searchFilter, search.getSearchFilter());
		assertEquals(searchText, search.getSearchText());
		
		assertEquals(searchTextMessage, searchMessage.getSearchTextMessage());
		assertEquals(searchErrorMessage, searchMessage.getSearchErrorMessage());
	}
}
