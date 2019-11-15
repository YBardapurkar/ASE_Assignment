package test.mac_repair.selenium;

import static org.junit.Assert.*;

import java.io.FileInputStream;
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

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminTest extends MacRepair_BusinessFunctions {

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
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
	@FileParameters("src/test/mac_repair/selenium/AdminRegisterTestCases.csv")
	public void test1_Register(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message, String text) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
		assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
	}
	
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/AdminSearchUserTestCases.csv")
	public void test2_SearchUser(int testCaseNumber, String username, String password, int searchFilter, String searchText, 
			String searchTextMessage, String errorMessage, String text) throws Exception {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Home"))).getText().contains("Admin"));
		
//		go to search page
		driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_SearchUsers"))).click();
		
//		select filter
		driver.findElement(By.xpath("html/body/form/p/input[" + searchFilter + "]")).click();
		
//		enter search text
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).sendKeys(searchText);
	    
//	    search
	    driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();

		assertEquals(searchTextMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchTextError"))).getAttribute("value"));
		
		assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_ErrorMessage"))).getAttribute("value"));
		
		if (errorMessage.isEmpty()) {
//			Get all rows
			List<WebElement> rows = driver.findElements(By.xpath("html/body/table/tbody/tr"));
			assertTrue(rows.size() >= 1);
		}
		
		takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Admin_Logout"))).click();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/AdminChangeRoleTestCases.csv")
	public void test3_ChangeUserRole(int testCaseNumber, String username, String password, String roleToSearch, 
			String newRole, String roleMessage, String errorMessage, String text) throws Exception {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Home"))).getText().contains("Admin"));

//		go to update role page
		driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_UpdateRole"))).click();

//		select All Users filter
		driver.findElement(By.xpath(prop.getProperty("Rad_SearchUser_RoleFilter"))).click();

//		enter search text for role
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).sendKeys(roleToSearch);

//		search
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();

//		Get all rows
		List<WebElement> rows= driver.findElements(By.xpath("html/body/table/tbody/tr"));

		driver.findElement(By.xpath("html/body/table/tbody/tr[last()]/td[6]/a")).click();
		
//		rule check for Admin role--> Admin cannot change it's own role
		if (roleToSearch.equals("Admin")) {
			takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		} else {
			try {
				new Select(driver.findElement(By.xpath(prop.getProperty("Lst_ChangeRole_Role")))).selectByVisibleText(newRole);
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
//			Change Role Update
			driver.findElement(By.xpath(prop.getProperty("Btn_ChangeRole_Submit"))).click();

			assertEquals(roleMessage, driver.findElement(By.xpath(prop.getProperty("Txt_ChangeRole_RoleTextError"))).getAttribute("value"));
			assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_ChangeRole_ErrorMessage"))).getAttribute("value"));

			takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d_" + text, testCaseNumber));
		}
		
//		logout
		driver.findElement(By.xpath(prop.getProperty("Btn_Admin_Logout"))).click();
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
