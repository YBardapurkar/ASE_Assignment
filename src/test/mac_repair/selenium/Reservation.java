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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import functions.MacRepair_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Reservation extends MacRepair_BusinessFunctions{

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
		baseUrl = "http://localhost:8080/mac_repair/";
		
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

  @Test
  @FileParameters("src/test/mac_repair/selenium/RepairerViewAssignedRepairsTestCases.csv")
  public void testReservation(int testCaseNumber, String username, String password, String firstname,
			String lastname, String role, String utaid, String phone, String email, String street, String city, 
			String state, String zipcode, String message) throws Exception {
	try {
		driver.get(baseUrl);
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("rrrrrr");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Yash@1");
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
		driver.findElement(By.xpath(prop.getProperty("Lnk_View_Assigned_Repairs"))).click();
		/*Collect Data*/
		String marId = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[1]")).getText();
		String facilityName = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[2]")).getText();
		String description = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[3]")).getText();
		String currentDate = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[4]")).getText();
		driver.findElement(By.xpath(prop.getProperty("Lnk_View_Each_Repair"))).click();
		/*Validate data*/
		assertEquals(marId,driver.findElement(By.xpath("html/body/table/tbody/tr[1]/td")).getText());
		assertEquals(facilityName,driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td")).getText());
		assertEquals(description,driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td")).getText());
		assertEquals(currentDate,driver.findElement(By.xpath("html/body/table/tbody/tr[4]/td")).getText());
		assertEquals(username,driver.findElement(By.xpath("html/body/table/tbody/tr[6]/td")).getText());
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_Logout"))).click();
		
		takeScreenshot(driver, String.format("Repairer_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
	} catch (NoSuchElementException e) {
//		success in registration
	//	assertEquals(message, driver.findElement(By.xpath(prop.getProperty("Txt_Register_SuccessMessage"))).getAttribute("value"));
		
		takeScreenshot(driver, String.format("Repairer_" + new Throwable().getStackTrace()[0].getMethodName() + "_%02d", testCaseNumber));
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  

 
  }

