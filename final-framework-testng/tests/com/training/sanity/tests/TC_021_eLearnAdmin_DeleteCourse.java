package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TC_021_eLearnAdmin_DeleteCourse {
  
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private AdminPOM adminPOM;
	private static Properties properties;
	private ScreenShot screenShot;
		
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//initialize LoginPOM class
		elearnloginPOM = new eLearnLoginPOM(driver); 
		
		//initialize AdminPOM class
		adminPOM= new AdminPOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	} 
	
	@Test
	public void adminCourseDelete() throws InterruptedException {
		elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
	//	screenShot.captureScreenShot("C:\\Selenium Screenshots capture");
				
		String code= "CODE002";
		
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
		
		// Step 2- click on Course List link
		adminPOM.clickLinkCourseList();
			
		Thread.sleep(10000);
		
			//Step 3- click on delete button to delete a course
			adminPOM.deleteCourseFromList(code);
			
			//get text of the alert message and confirm message
			String alertDeleteCourse= driver.switchTo().alert().getText();		
			System.out.println("Alert displays message= "+alertDeleteCourse);
				
			//Step 4- click OK button on the alert
			driver.switchTo().alert().accept();
			System.out.println("Ok clicked on Alert");
			
			//Result Step 4- Check if course deleted from the list
			adminPOM.verifyCourseDeletedFromTable(code);
			
			//Assertion- verify course deleted message in header
			adminPOM.verifyCourseDeletedInHeader();
	}
		
}
	
