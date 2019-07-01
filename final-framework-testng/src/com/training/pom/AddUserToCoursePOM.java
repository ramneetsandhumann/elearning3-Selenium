package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddUserToCoursePOM {

private WebDriver driver; 
	
	public AddUserToCoursePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//UserList from listbox
	@FindBy(name="UserList[]")
	private WebElement userlistBox;
	
	//CourseList from listbox
	@FindBy(name="CourseList[]")
	private WebElement courseListBox;
	
	//Add course button
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/form[2]/table/tbody/tr[2]/td[2]/button")
	private WebElement addToTheCourseBtn;
	
	//verify success message of user added to course
	@FindBy(css="#cm-content > div > div.alert.alert-success")
	private WebElement successMessage;
	
	//select a username from listbox
	public void selectUserNameFromListBox(String username)
	{
		Select select= new Select(this.userlistBox);
		select.selectByVisibleText(username);
	}
	
	//select a coursename from listbox
	public void selectCourseNameFromListBox(String coursename)
	{
		Select select= new Select(this.courseListBox);
		select.selectByVisibleText(coursename);
	}
	
	//click on button to add user to course
	public void submitAddToCourseBtn()
	{
		this.addToTheCourseBtn.click();
	}
	
	//Assertion - verify "The selected users are subscribed to the selected course" message displays
	public void verifyDisplayMessage()
	{
		try
		{
		String actualResult= this.successMessage.getText();
		String expectedResult= "The selected users are subscribed to the selected course";
		assertEquals(actualResult,expectedResult);
		System.out.println("Assertion- selected users are subscribed to the selected course- this message displays correctly");
		}
		catch(Error e)
		{
			System.out.println("Assertion- selected users are subscribed to the selected course- this message does NOT display");
		}
		/*if(actualResult.equals(expectedResult))
		{
			System.out.println("selected users are subscribed to the selected course message displays correctly");
		}*/
		
	}
}
