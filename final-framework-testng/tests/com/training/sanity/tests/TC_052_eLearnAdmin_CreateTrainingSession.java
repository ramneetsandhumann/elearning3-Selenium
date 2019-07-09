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
import com.training.pom.AddSessionsCategoriesPOM;
import com.training.pom.AdminPOM;
import com.training.pom.UserListPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC_052_eLearnAdmin_CreateTrainingSession {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private AdminPOM adminPOM;
	private AddSessionsCategoriesPOM addSessionPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	String categoryname;
	String sessionname;
	
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
		
		//initialize AddSessionsCategoriesPOM class
		addSessionPOM= new AddSessionsCategoriesPOM(driver);
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
  public void createTrainingSession() {
	  
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
		
		categoryname= "Selenium cat001";
		sessionname= "Selenium session001";
		
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
	  
		//Step 2- click on Session categories list
		adminPOM.clickLinkSessionsCategories();
		
		//Step 3- click on Add category icon
		addSessionPOM.clickAddCategoriesIcon();
		
		//Step 4- enter Category name in textbox
		addSessionPOM.textBoxCategoryName(categoryname);
		
		//Step 5- click Add category button
		addSessionPOM.clickAddCategoryBtn();
		
		//Result Step 5- verify header displays message "the category has been added"
		addSessionPOM.alertCategoryAddedMessageDisplays();
		
		//Step 6- click on Session List link
		addSessionPOM.clickLinkSessionList();
		
		//Step 7- click on Add a Training Session icon
		addSessionPOM.clickIconAddTrainingSession();
		
		//Step 8- Enter session Name in textbox
		addSessionPOM.enterSessionName(sessionname);
		
		//Step 9- click on Advanced Settings button
		addSessionPOM.clickAdvancedSettingsBtn();
		
		//Step 10- select above created Category from Sessions-Categories list box
		addSessionPOM.selectSessionsCategories(categoryname);
		
		//Step 11- click on Next Step button
		addSessionPOM.clickNextStepBtn();
		
		//Step 12- select course in course list window
		addSessionPOM.selectCourseFromListbox();
		
		//Step 13- click on > button
		addSessionPOM.clickRightArrowBtn();
		
		//Step 14- click on Next step button
		addSessionPOM.clickNextBtn();
		
		//verify step 14- alert displays message U[date successful
		addSessionPOM.alertSessionAddedMessageDisplays();
				
		//Step 15- click on Finish session creation button
		addSessionPOM.clickFinishSessionCreationBtn();
		
		//Assertion- verify Step 15
		String expectedAlert= "Update successful";
		String actualAlert= addSessionPOM.alertSessionAddedMessageDisplays();
		try
		{
			assertEquals(actualAlert,expectedAlert);
			System.out.println("Assertion- Update successful message displays in header correctly");
		}
		catch(Error e)
		{
			System.out.println("Assertion- There was an error adding the session");
		}
		
  }		
}
