package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddCourseCategoriesPOM;
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

public class TC_024_eLearnAdmin_CoursesCategories {
	
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
  public void addNewCategory() throws InterruptedException {
	  	
	  	elearnloginPOM.sendUserName("admin");
		elearnloginPOM.sendPassword("admin@123");
		elearnloginPOM.clickLoginBtn(); 
					
		//Step 1- click on Admin link
		adminPOM.clickAdminLink();
		
		//Step 2- click on Courses Categories link on Admin page
		adminPOM.linkCoursesCategories();
		
		//Step 3- click on Add category icon
		addCategoryPOM.clickAddCategoriesIcon();
		
		code= "learn004";
		name= "Learning004";
		//Step 4- enter code in Category Code textbox
		addCategoryPOM.enterCategoryCode(code);
		
		//step 5- enter Category name in Category name textbox
		addCategoryPOM.enterCategoryName(name);
		
		//step 6- click on Yes radio button to allow adding courses in categories
		addCategoryPOM.radioBtnAllowAddCategory("TRUE");
		
		//Step 7- click on Add Category button
		addCategoryPOM.clickAddCategoriesBtn();
		
		//Result step 7- verify added category displays in the list
		addCategoryPOM.verifyCategoryInTableList(name);
		
		//Assertion- verify result
		addCategoryPOM.alertAddedCategory();
	  
  }
 
}
