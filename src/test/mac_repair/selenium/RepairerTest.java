package test.mac_repair.selenium;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import functions.MacRepair_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


import org.easymock.EasyMock;



@RunWith(JUnitParamsRunner.class)
public class RepairerTest extends MacRepair_BusinessFunctions{

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	DateUtils dateUtils;
	
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
		
		dateUtils = EasyMock.strictMock(DateUtils.class);
	}
	
	//Register
	@Test
	@FileParameters("src/test/mac_repair/selenium/RepairerRegisterTestCases.csv")
	public void test1_Register(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message, String text) throws Exception {		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);		

		assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));			
		takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));						
	}

	@Test
	@FileParameters("src/test/mac_repair/selenium/RepairerViewAssignedRepairsTestCases.csv")
	public void test2_AssignedRepairs(int testCaseNumber, String username, String password, String text) throws Exception {

		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Repairer_Home"))).getText().contains("Repairer"));
		
		driver.findElement(By.xpath(prop.getProperty("Lnk_View_Assigned_Repairs"))).click();
		try {

			String marId = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[1]")).getText();
			String facilityName = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[2]")).getText();
			String description = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[3]")).getText();
			String currentDate = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[4]")).getText();
			driver.findElement(By.xpath(prop.getProperty("Lnk_View_Each_Repair"))).click();

			assertEquals(marId,driver.findElement(By.xpath("html/body/table/tbody/tr[1]/td")).getText());
			assertEquals(facilityName,driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td")).getText());
			assertEquals(description,driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td")).getText());
			assertEquals(currentDate,driver.findElement(By.xpath("html/body/table/tbody/tr[4]/td")).getText());
			assertEquals(username,driver.findElement(By.xpath("html/body/table/tbody/tr[6]/td")).getText());
			
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_Logout"))).click();
	}

	@Test
	@FileParameters("src/test/mac_repair/selenium/ReserveFacility.csv")
	public void test3_reserveFacilities(int testCaseNumber,String facilityName, String username, String password, String startTime, String expectedMessage) throws Exception {
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Repairer_Home"))).getText().contains("Repairer"));
		
		driver.findElement(By.xpath(prop.getProperty("Lnk_View_Assigned_Repairs"))).click();
		if(facilityName.equals("MR1"))
		{
			driver.findElement(By.xpath(prop.getProperty("Lnk_View_Each_Repair"))).click();
		}
		else
		{
			driver.findElement(By.xpath(prop.getProperty("Lnk_View_Multi_Repair"))).click();
		}
		Thread.sleep(10_000);
		//driver.findElement(By.xpath(prop.getProperty("Lnk_View_Each_Repair"))).click();
		try {
			/*Collect Data*/
			driver.findElement(By.xpath(prop.getProperty("Txt_Start_Time"))).sendKeys(startTime);
			driver.findElement(By.xpath(prop.getProperty("Btn_Reserve_Facility_Repair"))).click();
			
			//Thread.sleep(20_000);
			/*Validate data*/
			if(expectedMessage.equals("Reservation Modified Sucessfully"))
			{
				//String data = driver.findElement(By.xpath("html/body/form/input[2]")).getAttribute("value");
				//driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_SuccessMessage"))).getAttribute("value");
				//String data = driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_TimeError"))).getAttribute("value");
				//System.out.println(data+"waste");
				assertEquals(expectedMessage,driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_SuccessMessage"))).getAttribute("value"));
			}
			else{
				//String data = driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_TimeError"))).getAttribute("value");
				//System.out.println(data);
				assertEquals(expectedMessage,driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_TimeError"))).getAttribute("value"));
				
			}
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + startTime, testCaseNumber));
		}
			//
		 catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
		driver.findElement(By.xpath("html/body/div[1]/form/input")).click();
	}	
	
	
	
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
