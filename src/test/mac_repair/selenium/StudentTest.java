package test.mac_repair.selenium;

import static org.junit.Assert.assertEquals;
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
import functions.MacRepair_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class StudentTest extends MacRepair_BusinessFunctions{

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/mac_repair/";
		
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
//	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentRegisterTestCases.csv")
	public void testRegister(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String usernameMessage, String passwordMessage, String firstnameMessage, 
			String lastnameMessage, String roleMessage, String utaidMessage, String phoneMessage, String emailMessage, 
			String streetMessage, String cityMessage, String stateMessage, String zipcodeMessage, String message, 
			String errorMessage, String description) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
//		Thread.sleep(1000);
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
		
		takeScreenshot(driver, String.format(new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
	}
	
//	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentLoginTestCases.csv")
	public void testLogin(int testCaseNumber, String username, String password, String usernameMessage, 
			String passwordMessage, String message, String errorMessage, String description) throws Exception {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_ErrorMessage"))).getAttribute("value"));
		
		assertEquals(usernameMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_UsernameError"))).getAttribute("value"));
		assertEquals(passwordMessage, driver.findElement(By.xpath(prop.getProperty("Txt_Login_PasswordError"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format(new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/StudentCreateMARTestCases.csv")
	public void testCreateMAR(int testCaseNumber, String username, String password, String facilityName,
			String description, String descriptionErrorMessage, String message, String notes) {
		
		driver.get(baseUrl);
		login(driver, "ssssss", "Yash@1");
		
		driver.findElement(By.xpath(prop.getProperty("Lnk_Student_NewMAR"))).click();
		createMAR(driver, facilityName, description);
		
//		in case of error
		try {
			assertEquals(descriptionErrorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_NewMAR_DescriptionError"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format(new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
//		in case of success
		try {
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_Message"))).getAttribute("value"));
			assertEquals(facilityName, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_FacilityName"))).getText());
			assertEquals(description, driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_Description"))).getText());
			
			takeScreenshot(driver, String.format(new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Student_Logout"))).click();
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
