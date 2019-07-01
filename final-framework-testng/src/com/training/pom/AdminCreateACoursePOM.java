package com.training.pom;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminCreateACoursePOM {
private WebDriver driver; 
	
	public AdminCreateACoursePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//enter valid data in title textbox on Create a Course page
		@FindBy(name="title")
		private WebElement enterTitle;
		
		//enter code data in code field on Create a course page
		@FindBy(name="visual_code")
		private WebElement enterCode;
		
		//select a Teacher from dropdown
	//	@FindBy(xpath="//*[@id=\"update_course\"]/fieldset/div[4]/div[1]/span/span[1]/span/ul")
	//	@FindBy(xpath="//*[contains(@span,'select2-selection')]")
	//	@FindBy(name="course_teachers[]")
		@FindBy(xpath="//*[@id=\"course_teachers\"]") 
		private WebElement selectTeacher;
		
		//select a Category from list box
		@FindBy(name="category_code")
		private WebElement categoryListbox;
		
		//select a Language from Listbox
		@FindBy(name="course_language")
		private WebElement selectLanguage;
		
		//click on Create a Course button
		@FindBy(name="submit")
		private WebElement createCourseButton;
		
		//enter Title of course on Create a Course page
		public void enterTextOfTitle(String title)
		{
			this.enterTitle.sendKeys(title);
		}
		
		public void enterCodeOfTitle(String code)
		{
			this.enterCode.sendKeys(code);
		}
		
		public void selectTeacherFromDropdown(String teacher)
		{
			//this.selectTeacher.clear();
			Select select= new Select(this.selectTeacher);
			this.selectTeacher.sendKeys(teacher);
			select.selectByVisibleText(teacher);
			
		}
		
		public void selectCategoryFromListbox(String category)
		{
			Select select= new Select(this.categoryListbox);
			select.selectByVisibleText(category);
		}
		
		public void selectLanguageFromDropdown(String language)
		{
			Select select= new Select(this.selectLanguage);
			select.selectByVisibleText(language);
		}
		
		public void clickCreateCourseBtn()
		{
			this.createCourseButton.click();
		}
				
		//verify course name added displays at the top of the menu bar
		@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]/div/div[2]/a")
		private WebElement verifyNewCourseDisplayed;
		
		//Assertion- verify New Course added shows in header
		public void verifyCourseNameInHeader(String title)
		{
			try
			{
				assertTrue(driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]/div/div[2]/a")).getText().contains(title));
				System.out.println("Assertion- Message displays in header- Course "+title+" added");
			}
			catch(Error e)
			{
				System.out.println("Assertion- New Course Name is not displayed in the header");
			}
		}
		boolean flag=false;
		//verify New Course displays in Course List
		public void verifyNewCourseInTableList(String code)
		{
				
			//match data of each row cell of 3rd column to find a match
		   List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"course-list\"]/tbody/tr/td[3]"));
			String celldata= null;
			System.out.println("total rows= "+rows.size());
			for(int i=2;i<=rows.size() || celldata.equalsIgnoreCase(code);i++)
			{
				//verify course name added displays in table list view- column 3
				celldata= driver.findElement(By.xpath("//*[@id=\"course-list\"]/tbody/tr[" +i+ "]/td[3]")).getText();
				System.out.println("cell data= "+celldata+" @row= "+i);
				if(celldata.equalsIgnoreCase(code))
				{
					flag= true;
					System.out.println("New Course code "+code+" is displayed in table");
					break;
				}
			}
			if(flag=false)
			{
				System.out.println("Code= "+code+" could not be found");
			}
		
		}
}
