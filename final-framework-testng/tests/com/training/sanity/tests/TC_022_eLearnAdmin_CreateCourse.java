package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminCreateACoursePOM;
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

public class TC_022_eLearnAdmin_CreateCourse {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private AdminPOM adminPOM;
	private AdminCreateACoursePOM CreateCoursePOM; 
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
		
		//initialize AdminCreateACoursePOM
		CreateCoursePOM= new AdminCreateACoursePOM(driver);
		
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
  public void adminCreateNewCourse() throws InterruptedException
  {
	  elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
	//	screenShot.captureScreenShot("C:\\Selenium Screenshots capture");
		
		String title= "testing006";
		String code= "tes006";
		//Step 1- click on Administration tab
		adminPOM.clickAdminLink();
	//	screenShot.captureScreenShot("C:\\Selenium Screenshots capture");
		
		//Step 2- click on Create a course link
		adminPOM.createNewCourse();
				
		//Step 3- Enter valid credential in Title textbox
		CreateCoursePOM.enterTextOfTitle(title);
		
		//Step 4- Enter valid credential in  code textbox
		CreateCoursePOM.enterCodeOfTitle(code);
		
		//Select Valid credentials in Teacher list box
	//	CreateCoursePOM.selectTeacherFromDropdown("manzoor");
		
		//Select Valid credentials from Category list box
		CreateCoursePOM.selectCategoryFromListbox("Projects (PROJ)");
		
		//Select Valid credentials from Language list box
		CreateCoursePOM.selectLanguageFromDropdown("English");
	//	screenShot.captureScreenShot("C:\\Selenium Screenshots capture");
		
		//Click on Create a course button
		CreateCoursePOM.clickCreateCourseBtn();
		
		Thread.sleep(2000);
		//Verify New Course Code is displayed in Course List
		CreateCoursePOM.verifyNewCourseInTableList(code);
				
		//Assertion- Verify New Course Name is displayed in Header
		CreateCoursePOM.verifyCourseNameInHeader(title);
	//	screenShot.captureScreenShot("C:\\Selenium Screenshots capture");
		
  }
 

}
