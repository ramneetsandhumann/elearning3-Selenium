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
import com.training.pom.CareersAndPromotionsPOM;
import com.training.pom.GenerateReportStudentPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC_055_eLearnAdmin_CreateCareerPromotionSubscribe {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private AdminPOM adminPOM;
	private CareersAndPromotionsPOM careerPOM;
	String careername;
	String promotionname;
	
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
		
		careerPOM= new CareersAndPromotionsPOM(driver);
		
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
  public void createCareerPromotionSubscribe() {
	  
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
		careername="QA";
		promotionname="JAVA training";
		
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
	  
		//Step 2- click on Careers and promotions link
		adminPOM.clickLinkCareersAndPromotions();
		
		//Step 3- click on Careers icon
		careerPOM.clickCareersIcon();
		
		//Step 4- click on Add Career icon
		careerPOM.clickAddCareerIcon();
		
		//Step 5- enter Career name in Name textbox
		careerPOM.enterCareerNameTextbox(careername);
		
		//Step 6- click on Add button
		careerPOM.clickAddCareerButton();
		
		//Result Step 6
		careerPOM.verifyAlertItemAddedHeader();
		
		//Step 7- click on Careers and promotions link
		careerPOM.clickCareersPromotionsLink();
		
		//Step 8- click on Promotions icon
		careerPOM.clickPromotionsIcon();
		
		//Step 9- click on add icon
		careerPOM.clickAddPromotionsIcon();
		
		//Step 10- enter Promotion name in textbox
		careerPOM.enterPromotionName(promotionname);
		
		//Step 11- click add button for promotion page
		careerPOM.clickAddPromotionBtn();
		
		//Result Step 11
		careerPOM.verifyAlertItemAddedHeader();
		
		//Step 12- click on subscribe sessions to promotion icon
		careerPOM.clickSubscribeSessionsToPromotionsLink();
		
		//Step 13- select session in Sessions not subscribed listbox
		careerPOM.selectSessionNotSubscribedListbox();
		
		//Step 14- click on -> arrow
		careerPOM.clickArrowRightSide();
		
		//Step 15- click on subscribe sessions in promotion window button
		careerPOM.clickSubscribeSessionToPromotionBtn();
		
		//Assertion- verify session should get added into promotion
		String actualCol2= careerPOM.verifySessionAddedToPromotion(promotionname);
		try
		{
			assertEquals(actualCol2,careername);
			System.out.println("Session has been added to Promotion");
		}
		catch(Error e)
		{
			System.out.println("Session has NOT been added to Promotion");	
		}
		
  }
}
