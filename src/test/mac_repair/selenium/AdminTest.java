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
		driver = new ChromeDriver();
		//baseUrl = "http://localhost:8080/mac_repair/";
		baseUrl = "http://localhost:8082/mac_repair/";
		
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/AdminRegisterTestCases.csv")
	public void test1_Register(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message, String description) throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Lnk_Register"))).click();
		register(driver, username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		
		Thread.sleep(10_000);
//		try {
//			error in registration
			/*assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_ErrorMessage"))).getAttribute("value"));
			
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
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Register_ZipError"))).getAttribute("value"));*/
			
//			takeScreenshot(driver, String.format("FacilityManager_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
//		} catch (NoSuchElementException e) {
			assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));
			
			takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
//		}
	}
	
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/AdminSearchUserTestCases.csv")
	public void test2_SearchUser(int testCaseNumber, String username, String password, int searchFilter, String searchText, 
			String searchTextMessage, String errorMessage, String text) throws Exception {
		
		driver.get(baseUrl);
		login(driver, username, password);
		
//		go to search page
		driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_SearchUsers"))).click();
		
		Thread.sleep(1_000);
		
//		select filter
		driver.findElement(By.xpath("html/body/form/p/input[" + searchFilter + "]")).click();
		
		Thread.sleep(1_000);
		
//		enter search text
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).sendKeys(searchText);
	    
	    Thread.sleep(1_000);
	    
//	    search
	    driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();

	    Thread.sleep(1_000);
	    
		assertEquals(searchTextMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchTextError"))).getAttribute("value"));
		
		assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_ErrorMessage"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Admin_Logout"))).click();
	}
	
	@Test
	@FileParameters("src/test/mac_repair/selenium/AdminChangeRoleTestCases.csv")
	public void test3_ChangeUserRole(int testCaseNumber, String username, String password, String role, String roleMessage, String errorMessage, String text) throws Exception {
		//System.out.println("In testChangeUserRole" + role + "" +roleMessage + "" +errorMessage);
		driver.get(baseUrl);
		login(driver, username, password);
		
//		go to update role page
		driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_UpdateRole"))).click();
		
//      select All Users filter
		//driver.findElement(By.xpath(prop.getProperty("Rad_SearchUser_NoFilter"))).click();
		driver.findElement(By.xpath(prop.getProperty("Rad_SearchUser_RoleFilter"))).click();
		Thread.sleep(1_000);
		
//		enter search text for role
		driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_SearchUser_SearchText"))).sendKeys(role);
		
	    //Thread.sleep(1_000);
	    
//	    search
	    driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();

	   // Thread.sleep(1_000);
	    
	  //Get all rows
		 List<WebElement> rows= driver.findElements(By.xpath("html/body/table/tbody/tr"));
		 System.out.println("rows:"+rows.size());
		  
		 for(int i = 2;i <= rows.size();i++) {
			
			 // rule check for Admin role--> Admin cannot change it's own role
			if (!(driver.findElement(By.xpath("html/body/table/tbody/tr[" + i + "]/td[2]")).getText()).equals("Admin")) {
				System.out.println("role is " +driver.findElement(By.xpath("html/body/table/tbody/tr[" + i + "]/td[2]")).getText());
				driver.findElement(By.xpath("html/body/table/tbody/tr[" + i + "]/td[6]/a")).click();
				try {
					
					new Select(driver.findElement(By.xpath(prop.getProperty("Lst_ChangeRole_Role")))).selectByVisibleText(role);
				} catch (NoSuchElementException e) {
					System.out.println(e.getMessage());
				}
//			    Change Role Update
			    driver.findElement(By.xpath(prop.getProperty("Btn_ChangeRole_Submit"))).click();
			    
			    assertEquals(roleMessage, driver.findElement(By.xpath(prop.getProperty("Txt_ChangeRole_RoleTextError"))).getAttribute("value"));
				
				assertEquals(errorMessage, driver.findElement(By.xpath(prop.getProperty("Txt_ChangeRole_ErrorMessage"))).getAttribute("value"));
				
				takeScreenshot(driver, String.format("Admin_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
				
				driver.findElement(By.xpath(prop.getProperty("Btn_Admin_Logout"))).click();
				//System.out.println("Logout from Update Role");
				break;
				
			}
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
	
}
