package functions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MacRepair_BusinessFunctions {
	public static WebDriver driver;
	public static Properties prop;
	
	public void takeScreenshot(WebDriver driver, String screenshotName) {
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotName + ".png"));
		} catch (IOException e) {}
		try {
			Thread.sleep(1_000);
//			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(WebDriver driver, String username, String password, String firstName, String lastName, 
			String role, String utaId, String phone, String email, String street, String city, String state,
			String zip) {
//		username
		driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).sendKeys(username);
//	    password
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
//	    first name
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Fisrtname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Fisrtname"))).sendKeys(firstName);
//	    last name
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Lastname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Lastname"))).sendKeys(lastName);
//	    role
	    try {
	    	new Select(driver.findElement(By.xpath(prop.getProperty("Lst_Register_Role")))).selectByVisibleText(role);
	    } catch(NoSuchElementException e) {
	    	System.out.println(e.getMessage());
	    }
//	    uta id
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Utaid"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Utaid"))).sendKeys(utaId);
//	    phone
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).sendKeys(phone);
//	    email
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).sendKeys(email);
//	    street
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Street"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Street"))).sendKeys(street);
//	    city
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).sendKeys(city);
//	    state
	    try {
	    	new Select(driver.findElement(By.xpath(prop.getProperty("Lst_Register_State")))).selectByVisibleText(state);
	    } catch (NoSuchElementException e) {
	    	System.out.println(e.getMessage());
	    }
//	    zip
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zip"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zip"))).sendKeys(zip);
//	    register
	    driver.findElement(By.xpath(prop.getProperty("Btn_Register_Register"))).click();
	}
	
	public void login(WebDriver driver, String username, String password) {
//		username
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
//	    password
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
//	    login
	    driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
	}
	
	public void createMAR(WebDriver driver, String facilityName, String description) {
//		facility name
		try {
	    	new Select(driver.findElement(By.xpath(prop.getProperty("Lst_NewMAR_FacilityName")))).selectByVisibleText(facilityName);
	    } catch(NoSuchElementException e) {
	    	System.out.println(e.getMessage());
	    }
//	    description
	    driver.findElement(By.xpath(prop.getProperty("Txt_NewMAR_Description"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_NewMAR_Description"))).sendKeys(description);
//	    submit
	    driver.findElement(By.xpath(prop.getProperty("Btn_NewMAR_Submit"))).click();
	}
}
