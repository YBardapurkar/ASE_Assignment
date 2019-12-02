package test.mac_repair.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import functions.MacRepair_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import mac_repair.model.MAR;
import mac_repair.util.SQLConnection;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentTest extends MacRepair_BusinessFunctions{

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
	@FileParameters("src/test/mac_repair/selenium/StudentRegisterTestCases.csv")
	public void test1_Register(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String usernameMessage, String passwordMessage, String firstnameMessage, 
			String lastnameMessage, String roleMessage, String utaidMessage, String phoneMessage, String emailMessage, 
			String streetMessage, String cityMessage, String stateMessage, String zipcodeMessage, String message, 
			String errorMessage, String text) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
		if (message.isEmpty()) {
//			error in registration
			assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_ErrorMessage"))).getAttribute("value"));
			
			assertEquals(usernameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_UsernameError"))).getAttribute("value"));
			assertEquals(passwordMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_PasswordError"))).getAttribute("value"));
			assertEquals(firstnameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_FisrtnameError"))).getAttribute("value"));
			assertEquals(lastnameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastnameError"))).getAttribute("value"));
			assertEquals(roleMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_RoleError"))).getAttribute("value"));
			assertEquals(utaidMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_UtaIdError"))).getAttribute("value"));
			assertEquals(phoneMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_PhoneError"))).getAttribute("value"));
			assertEquals(emailMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_EmailError"))).getAttribute("value"));
			assertEquals(streetMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_StreetError"))).getAttribute("value"));
			assertEquals(cityMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_CityError"))).getAttribute("value"));
			assertEquals(stateMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_StateError"))).getAttribute("value"));
			assertEquals(zipcodeMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Register_ZipError"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		} else {
//			success in registration
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Login_SuccessMessage"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		}
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentLoginTestCases.csv")
	public void test2_Login(int testCaseNumber, String username, String password, String usernameMessage, 
			String passwordMessage, String message, String errorMessage, String text) throws Exception {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_ErrorMessage"))).getAttribute("value"));
		
		assertEquals(usernameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_UsernameError"))).getAttribute("value"));
		assertEquals(passwordMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_PasswordError"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentCreateMARTestCases.csv")
	public void test3_CreateMAR(int testCaseNumber, String username, String password, String facilityName,
			String description, String descriptionErrorMessage, String message, String text) {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Student_Home"))).getText().contains("Student"));
		
		driver.findElement(By.xpath(prop.getProperty("Lnk_Student_NewMAR"))).click();
		createMAR(driver, facilityName, description);
		
		if (message.isEmpty()) {
//			in case of error
			assertEquals(descriptionErrorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_NewMAR_DescriptionError"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		} else {
//			in case of success
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_Message"))).getAttribute("value"));
			
			int marId = Integer.parseInt(driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_MARId"))).getText());
			MAR mar = getMARByMARId(marId);
			
			assertEquals(mar.getId(), marId);
			assertEquals(mar.getFacilityName(), driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_FacilityName"))).getText());
			assertEquals(mar.getDescription(), driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_Description"))).getText());
//			precision till minutes
			assertEquals(mar.getDate().substring(0, 16), driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_CreationDate"))).getText().substring(0, 16));
			assertEquals(mar.getReportedBy(), username);
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		}
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Student_Logout"))).click();
	}
	
	//Edit profile
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentUpdateProfileTestCases.csv")
	public void test4_UpdateProfile (int testCaseNumber, String login_username, String login_password, String password, String firstname,
			String lastname, String phone, String email, String street, String city, 
			String state, String zipcode, String usernameMessage, String passwordMessage, String firstnameMessage, 
			String lastnameMessage, String roleMessage, String utaidMessage, String phoneMessage, String emailMessage, 
			String streetMessage, String cityMessage, String stateMessage, String zipcodeMessage, String message, 
			String errorMessage, String text) throws Exception {
		driver.get(baseUrl);		
		login(driver, login_username, login_password);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Student_Home"))).getText().contains("Student"));
		driver.findElement(By.xpath(prop.getProperty("Lnk_Student_Profile"))).click();
		updateProfile(driver, password, firstname, lastname, phone, email, street, city, state, zipcode);
		
		if (message.isEmpty()) {
//			error in registration
			assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_ErrorMessage"))).getAttribute("value"));			
			assertEquals(usernameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_UsernameError"))).getAttribute("value"));
			assertEquals(passwordMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_PasswordError"))).getAttribute("value"));
			assertEquals(firstnameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_FisrtnameError"))).getAttribute("value"));
			assertEquals(lastnameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_LastnameError"))).getAttribute("value"));
			assertEquals(roleMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_RoleError"))).getAttribute("value"));
			assertEquals(utaidMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_UtaIdError"))).getAttribute("value"));
			assertEquals(phoneMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_PhoneError"))).getAttribute("value"));
			assertEquals(emailMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_EmailError"))).getAttribute("value"));
			assertEquals(streetMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_StreetError"))).getAttribute("value"));
			assertEquals(cityMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_CityError"))).getAttribute("value"));
			assertEquals(stateMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_StateError"))).getAttribute("value"));
			assertEquals(zipcodeMessage, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_ZipError"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		}
		
		else {
//			success in registration
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_UpdateProfile_SuccessMessage"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Student_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
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
	
	private static MAR getMARByMARId (int marId) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		MAR mar = new MAR(); 
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(" SELECT * from mar where mar_id = '" + marId + "';");
			while (result.next()) {
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setDate(result.getString("creation_date"));
				mar.setReportedBy(result.getString("reported_by"));
				break;
			}
		} catch (SQLException e) {}
		return mar;
	}
}
