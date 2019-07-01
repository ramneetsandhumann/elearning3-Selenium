package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddCourseCategoriesPOM;
import com.training.pom.AdminPOM;
import com.training.pom.eLearnLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC_025_eLearnAdmin_DeleteCategory {
	
	private WebDriver driver;
	private String baseUrl;
	private eLearnLoginPOM elearnloginPOM;
	private AdminPOM adminPOM;
	private AddCourseCategoriesPOM addCategoryPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String code;	
	private String name;
	
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
	
		//initialize AddCourseCategoriesPOM class
		addCategoryPOM= new AddCourseCategoriesPOM(driver);
		
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
  public void deletCategory() throws InterruptedException {
	  	
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
					
		name= "Learning001";
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
		
		//Step 2- click on Courses Categories link on Admin page
		adminPOM.linkCoursesCategories();
		
		//Delete Category from table list
		addCategoryPOM.deletecategoryFromList(name);
		
		//Result Step 3- verify category deleted from list
		addCategoryPOM.verifyCategoryInTableList(name);
		
		//Assertion- verify the result
		addCategoryPOM.alertDeletedCategory();
  }
}
