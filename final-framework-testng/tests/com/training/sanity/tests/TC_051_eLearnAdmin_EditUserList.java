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
import com.training.pom.AdminPOM;
import com.training.pom.UserListPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC_051_eLearnAdmin_EditUserList {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private AdminPOM adminPOM;
	private UserListPOM userListPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	String username;
	String usercode;	
	
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
		userListPOM = new UserListPOM(driver);
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
  public void editUserDetails() {
	  
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
					
		username= "Ram Singh";
		usercode= "ramsingh";
		
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
	  
		//Step 2- click on User List link
		adminPOM.clickLinkUserList();
		
		//Step 3- enter user name in search box and click search
		userListPOM.searchByUserName(username);
		userListPOM.clickUserSearchBtn();
		
		//step 4- click on edit icon of the user
		userListPOM.clickEditUserIcon();
		
		//step5- clear email textbox
		userListPOM.clearEmailTextBox();
		
		//step 6- enter valid credentials in email textbox
		userListPOM.enterEmailInTextBox();
		
		//step 7- click on inactive radio button of Account section
		userListPOM.clickRadioBtnInactiveAccount();
		
		//step 8- click on Save button
		userListPOM.clickSaveBtn();
		
		//Result Step 8- user updated message should get displayed
		String expectedHeaderMessage ="User updated: "+username+" ("+usercode+")";
		System.out.println("Expected header message= "+expectedHeaderMessage);
		String actualHeaderMessage= userListPOM.verifyHeaderMessage();
		try
		{
			assertEquals(actualHeaderMessage,expectedHeaderMessage);
			System.out.println("Assertion- Actual Header Message matches the Expected header message");
		}
		catch(Error e)
		{
			System.out.println("Assertion- Actual and expected messages did NOT match");
		}
  }	
}
