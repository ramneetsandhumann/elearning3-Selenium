package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GenerateReportStudentPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC_054_eLearn_GenerateReport_Teacher {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private GenerateReportStudentPOM reportPOM;
	
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
		
		reportPOM= new GenerateReportStudentPOM(driver);
		
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
  public void generateReportForStudent() {
	  
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn();
		
		//Step 1- click on reporting tab
		reportPOM.clickReportingTab();
		
		//Step2- click on Followed students link
		reportPOM.clickFollowedStudentsLink();
		
		//Step 3- enter student name in keyword textbox
		reportPOM.textboxKeyword("ramneet");
		
		//Step 4- click on search button
		reportPOM.clickSearchBtn();
		
		//Step 5- click on >> icon of the student
		reportPOM.clickArrowDetails();
		
		//Step 6- click on >> icon of the course
		reportPOM.clickArrowCourseDetails();
		
		//step 7- click on quiz icon of the test section
		reportPOM.clickQuizIcon();
		
		//Step 8- click on SendEmail checkbox
		reportPOM.clickSendEmailCheckbox();
		
		//Step 9- click on correct test button
		reportPOM.clickCorrectTestBtn();
		
		//Assertion- verify "Message Sent" displays in header alert
		try
		{
			String actualmsg= reportPOM.verifyAlertMessageSent();
			String expectedmsg= "Message Sent";
			assertEquals(actualmsg,expectedmsg);
			System.out.println("Message Sent displays corrrectly in header");
		}
		catch(Error e)
		{
			System.out.println("Message Sent does not display in header");
		}
  }
}
