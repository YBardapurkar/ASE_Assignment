package test.mac_repair.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import functions.MacRepair_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import mac_repair.model.Facility;
import mac_repair.model.MAR;
import mac_repair.util.DropdownUtils;
import mac_repair.util.SQLConnection;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacilityManagerTest extends MacRepair_BusinessFunctions{

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
		
		prop = new Properties();
//		load configuration file
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		
//		load base url, shared ui map
		baseUrl = prop.getProperty("sAppURL");
		prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerRegisterTestCases.csv")
	public void test1_Register(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message, String description) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
		
//		success in registration
		assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerSearchMARTestCases.csv")
	public void test2_SearchMAR(int testCaseNumber, String username, String password, int searchFilter, String searchText, 
			String searchTextMessage, String errorMessage, String text) {
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_FacilityManager_Home"))).getText().contains("Facility Manager"));
		
//		go to search page
		driver.findElement(By.xpath(prop.getProperty("Lnk_FacilityManager_SearchMARs"))).click();
//		select filter
		driver.findElement(By.xpath("html/body/form/p/input[" + searchFilter + "]")).click();
//		enter search text
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchMAR_SearchText"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchMAR_SearchText"))).sendKeys(searchText);
//		search
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchMAR_Submit"))).click();
		
		assertEquals(searchTextMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchMAR_SearchTextError"))).getAttribute("value"));
		assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchMAR_ErrorMessage"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		
		driver.findElement(By.xpath(prop.getProperty("Btn_FacilityManager_Logout"))).click();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerAssignMARTestCases.csv")
	public void test3_AssignMAR(int testCaseNumber, String username, String password, String repairer, 
			String urgency, String estimate, String repairerError, String errorMessage, 
			String successMessage, String text) throws Exception {
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_FacilityManager_Home"))).getText().contains("Facility Manager"));
		
//		go to search page
		driver.findElement(By.xpath(prop.getProperty("Lnk_FacilityManager_SearchMARs"))).click();
//		select filter
		driver.findElement(By.xpath(prop.getProperty("Rad_SearchMAR_UnassignedFilter"))).click();
//		search
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchMAR_Submit"))).click();

//		select repair
		String marId = driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td[1]")).getText();
		String facilityName = driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td[2]")).getText();
		String description = driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td[3]")).getText();
		String creationDate = driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td[4]")).getText();
		
		driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td/a")).click();
		
//		assert details
		assertEquals(marId, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_MARId"))).getText());
		assertEquals(facilityName, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_FacilityName"))).getText());
		assertEquals(description, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_Description"))).getText());
		assertEquals(creationDate, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_CreationDate"))).getText());
		
		Thread.sleep(500);
		
//		assign repair
		try {
//			select repairer
	    	new Select(driver.findElement(By.xpath(prop.getProperty("Lst_AssignMAR_Repairer")))).selectByVisibleText(repairer);
//	    	select urgency
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_AssignMAR_Urgency")))).selectByVisibleText(urgency);
//			select estimate
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_AssignMAR_Estimate")))).selectByVisibleText(estimate);
			
			takeScreenshot(driver, String.format("FacilityManager_TestSelections_%02d_" + text, testCaseNumber));
		} catch (NoSuchElementException e) {
	    	System.out.println(e.getMessage());
	    }
		driver.findElement(By.xpath(prop.getProperty("Btn_AssignMAR_Submit"))).click();
		if (!errorMessage.isEmpty()) {
//			not assigned
			assertEquals(repairerError, driver.findElement(By.xpath(prop.getProperty("Txt_AssignMAR_RepairerError"))).getText());
			assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_AssignMar_Error"))).getText());
			
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		} else {
//			assigned
			assertEquals(successMessage, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_Message"))).getAttribute("value"));
			
			assertEquals(urgency, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_Urgency"))).getText());
			assertEquals(repairer, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetailsFull_AssignedTo"))).getText());
			
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		}
		
		driver.findElement(By.xpath(prop.getProperty("Btn_FacilityManager_Logout"))).click();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerSearchFacilityTestCases.csv")
	public void test4_SearchFacility(int testCaseNumber, String username, String password, String facilityType, 
			String date, String time, String message, String text) {
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_FacilityManager_Home"))).getText().contains("Facility Manager"));
//		Go to search facility page
		driver.findElement(By.xpath(prop.getProperty("Lnk_FacilityManager_SearchFacilities"))).click();
//		select facility type
		try {
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_SearchFacility_FacilityType")))).selectByVisibleText(facilityType);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
//		submit
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchFacility_Search"))).click();
		
//		Search Facility Next
//		verify selected facility type
		String selectedFacilityType = new Select(driver.findElement(By.xpath(prop.getProperty("Lst_SearchFacilityNext_FacilityType")))).getFirstSelectedOption().getText();
		assertEquals(facilityType, selectedFacilityType);
//		Select date and time
		try {
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_SearchFacilityNext_Date")))).selectByVisibleText(date);
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_SearchFacilityNext_Time")))).selectByVisibleText(time);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
//		submit
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchFacilityNext_Search"))).click();
		
		if (!message.isEmpty()) {
//			error
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityNext_ErrorMessage"))).getAttribute("value"));
		} else {
//			no error
			List<WebElement> rows = driver.findElements(By.xpath("html/body/table/tbody/tr"));
			
			try {
//				select last
				String fName = driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityResult_FacilityName_Last"))).getText();
				String fType = driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityResult_FacilityType_Last"))).getText();
				String fInterval = driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityResult_Interval_Last"))).getText();
				String fDuration = driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityResult_Duration_Last"))).getText();
				String fVenue = driver.findElement(By.xpath(prop.getProperty("Txt_SearchFacilityResult_Venue_Last"))).getText();
				
				driver.findElement(By.xpath(prop.getProperty("Lnk_SearchFacilityResult_View_Last"))).click();
				
//				facility details			
				assertEquals(fName, driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_FacilityName"))).getText());
				assertEquals(fType, driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_FacilityType"))).getText());
				assertEquals(fInterval, driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Interval"))).getText());
				assertEquals(fDuration, driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Duration"))).getText());
				assertEquals(fVenue, driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Venue"))).getText());
			} catch (NoSuchElementException e) {
				
			}
		}
		
//		logout
		driver.findElement(By.xpath(prop.getProperty("Btn_FacilityManager_Logout"))).click();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerAddFacilityTestCases.csv")
	public void test5_AddFacility(int testCaseNumber, String username, String password, String facilityType, String text) {
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_FacilityManager_Home"))).getText().contains("Facility Manager"));
//		Go to search facility page
		driver.findElement(By.xpath(prop.getProperty("Lnk_FacilityManager_NewFacility"))).click();
		
		try {
			new Select(driver.findElement(By.xpath(prop.getProperty("Lst_AddFacility_FacilityType")))).selectByVisibleText(facilityType);
			
			driver.findElement(By.xpath(prop.getProperty("Btn_AddFacility_Submit"))).click();
			
			Facility selectedFacility = DropdownUtils.getFacilityTypeDropdown().stream().filter(x -> x.getFacilityType().equals(facilityType)).findFirst().get();
			
//			facility details			
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_FacilityName"))).getText().contains(selectedFacility.getFacilityName()));
			assertEquals(selectedFacility.getFacilityType(), driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_FacilityType"))).getText());
			assertEquals(selectedFacility.getFacilityInterval(), driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Interval"))).getText());
			assertEquals(selectedFacility.getFacilityDuration(), driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Duration"))).getText());
			assertEquals(selectedFacility.getFacilityVenue(), driver.findElement(By.xpath(prop.getProperty("Txt_FacilityDetails_Venue"))).getText());
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	private static List<MAR> searchMARByFilter(int filter, String text) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String query = "";
		if (filter == 1) {
			query = "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
					+ "from mar "
					+ " left outer join assignment on mar.mar_id = assignment.mar_id "
					+ "where mar.facility_name like '%" + text + "%' "
					+ "order by mar.mar_id;";
		} else if (filter == 2) {
			query = "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
					+ "from mar "
					+ " left outer join assignment on mar.mar_id = assignment.mar_id "
					+ "where assignment.assigned_to like '%" + text + "%' "
					+ "order by mar.mar_id;";
		} else if (filter == 3) {
			query = "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
					+ "from mar "
					+ " left outer join assignment on mar.mar_id = assignment.mar_id "
					+ "where assignment.assigned_to is null "
					+ "order by mar.mar_id;";
		} else if (filter == 4) {
			query = "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
					+ "from mar "
					+ " left outer join assignment on mar.mar_id = assignment.mar_id "
					+ "where date(mar.creation_date) = '" + text + "' "
					+ "order by mar.mar_id;";
		} else {
			query = "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
					+ "from mar "
					+ " left outer join assignment on mar.mar_id = assignment.mar_id "
					+ "order by mar.mar_id;";
		}
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				MAR mar = new MAR();
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setDate(result.getString("creation_date"));
				mar.setReportedBy(result.getString("reported_by"));
				marList.add(mar);	
			}
		} catch (SQLException e) {}
		return marList;
	}
}
