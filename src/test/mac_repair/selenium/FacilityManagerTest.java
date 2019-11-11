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
public class FacilityManagerTest extends MacRepair_BusinessFunctions{

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
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/FacilityManagerRegisterTestCases.csv")
	public void testRegister(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message, String description) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
		Thread.sleep(10_000);
		try {
//			error in registration
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_ErrorMessage"))).getAttribute("value"));
			
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_UsernameError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_PasswordError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_FisrtnameError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastnameError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_RoleError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_UtaIdError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_PhoneError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_EmailError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_StreetError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_CityError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_StateError"))).getAttribute("value"));
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_ZipError"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
		} catch (NoSuchElementException e) {
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
		}
	}
	
	@Test
	public void testSearchMAR() {
		
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
