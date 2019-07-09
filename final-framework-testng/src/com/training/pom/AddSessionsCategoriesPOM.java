package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddSessionsCategoriesPOM {
	
private WebDriver driver; 
	
	public AddSessionsCategoriesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="name")
	private WebElement categoryName;
	
	@FindBy(xpath="//button[@value='Add category']")
	private WebElement btnAddCategory;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]")
	private WebElement alertCategoryAdded; 
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/ul/li[1]/a")
	private WebElement linkSessionList;
	
	@FindBy(xpath="//img[@title='Add a training session']") //img src="http://elearningm1.upskills.in/main/img/icons/32/new_session.png"
	private WebElement iconAddTrainingSession;
	
	@FindBy(id="add_session_name")
	private WebElement textboxSessionName;
	
/*	@FindBy(name="coach_username")
	private WebElement dropdownCoachName;*/
	
	@FindBy(xpath="//button[@id='advanced_params']")
	private WebElement btnAdvancedSettings;
	
	@FindBy(name="session_category")
	private WebElement dropdownSessionsCategories;
	
	@FindBy(id="add_session_submit")
	private WebElement btnNextStep;
	
	@FindBy(name="NoSessionCoursesList[]")
	private WebElement listboxCoursesList;
	
	@FindBy(name="add_course")
	private WebElement arrowAddCourse;
	
/*	@FindBy(name="SessionCoursesList[]")
	private WebElement listboxCourseInSession;*/
	
	@FindBy(name="next")
	private WebElement btnNext;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]")
	private WebElement alertSessionAdded;
	
	@FindBy(id="user_to_add")
	private WebElement textboxStudentName;
	
	@FindBy(name="next")
	private WebElement btnFinishSessionCreation;
	
	//click on icon Add Categories
	public void clickAddCategoriesIcon()
	{
		Actions action= new Actions(driver); 
		WebElement addCategoryIcon= driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]/div/div[1]/a[1]/img"));
		action.moveToElement(addCategoryIcon).click().build().perform();
	}
	
	//enter value in category name textbox
	public void textBoxCategoryName(String categoryname)
	{
		this.categoryName.sendKeys(categoryname);
	}
	
	//click on Add button to add category
	public void clickAddCategoryBtn()
	{
		this.btnAddCategory.click();
	}
	
	//The message should display in the header- "The category has been added"
	public void alertCategoryAddedMessageDisplays()
	{
		String actualAlert= this.alertCategoryAdded.getText();
		if(actualAlert.equals("The category has been added"))
		{
			System.out.println("The category has been added message displays in header correctly");
		}
		else
		{
			System.out.println("There was an error adding the category");
		}
	}
	
	//click on link Session List
	public void clickLinkSessionList()
	{
		this.linkSessionList.click();
	}
	
	//click on icon for Add Training Session
	public void clickIconAddTrainingSession()
	{
		this.iconAddTrainingSession.click();
	}
	
	//enter the name in Session Name textbox
	public void enterSessionName(String sessionname)
	{
		this.textboxSessionName.sendKeys(sessionname);
	}
	
	//click on Advanced Settings button
	public void clickAdvancedSettingsBtn()
	{
		this.btnAdvancedSettings.click();
	}
	
	//select any Category from the dropdown
	public void selectSessionsCategories(String categoryname)
	{
		Select category= new Select(this.dropdownSessionsCategories);
		category.selectByVisibleText(categoryname);
	}
	
	//click on Next Step button
	public void clickNextStepBtn()
	{
		this.btnNextStep.click();
	}
	
	//select any Course from listbox
	public void selectCourseFromListbox()
	{
		Select course= new Select(this.listboxCoursesList);
		course.selectByIndex(2);
	}
	
	//click on arrow button for Add course
	public void clickRightArrowBtn()
	{
		this.arrowAddCourse.click();
	}
	
	//click on Next Step button
	public void clickNextBtn()
	{
		this.btnNext.click();
	}
	
	public String alertSessionAddedMessageDisplays()
	{
		String actualAlert= this.alertSessionAdded.getText();
		System.out.println("actual alert message= "+actualAlert);
		return actualAlert;
		
	}
	
	public void clickFinishSessionCreationBtn()
	{
		this.btnFinishSessionCreation.click();
	}
}
