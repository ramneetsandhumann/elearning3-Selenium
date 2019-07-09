package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddUserToCoursePOM;
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

public class TC_023_eLearnAdmin_AddUserToCourse {
		  
		private WebDriver driver;
		private String baseUrl;
		private eLearnLoginPOM elearnloginPOM;
		private AdminPOM adminPOM;
		private AddUserToCoursePOM addUserPOM;
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
			addUserPOM = new AddUserToCoursePOM(driver);
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
  public void addUserToCourse() throws InterruptedException {
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
					
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
		
		//Step 2- click on Add users to Course link
		adminPOM.linkAddUserToCourse();
		
		//step 3- select a user to add from list box
		addUserPOM.selectUserNameFromListBox("kohli virat (virat)");
		
		//Step 4- select a course to add from list box
		addUserPOM.selectCourseNameFromListBox("(CD41) TestNG");
		
		//Step 5- click on Add to the course button
		addUserPOM.submitAddToCourseBtn();
		
		//result Step 5- verify user added to course message displays
		addUserPOM.verifyDisplayMessage();
  }
}
 