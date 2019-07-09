package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GenerateReportStudentPOM {

	private WebDriver driver; 
	
	public GenerateReportStudentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\"navbar\"]/ul[1]/li[4]/a")
	private WebElement tabReporting;
	
	@FindBy(linkText="Followed students") 
	private WebElement linkFollowedStudents;
	
	@FindBy(name="keyword")
	private WebElement textboxKeyword;
	
	@FindBy(name="submit")
	private WebElement btnSearch;
	  
	@FindBy(xpath="//*[@id=\"details_raman\"]/img")
	private WebElement arrowDetails;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[10]/table/tbody/tr[1]/td[7]/a/img")
	private WebElement arrowCourseDetails;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[8]/table/tbody/tr/td[5]/a/img")
	private WebElement iconQuiz;
	
	@FindBy(name="send_notification")
	private WebElement checkboxEmail;
	
	@FindBy(id="form-email_submit")
	private WebElement btnCorrectTest;
		
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[3]")
	private WebElement alertHeaderMessageSent;
	
	//click on reporting Tab
	public void clickReportingTab()
	{
		this.tabReporting.click();
	}
	
	//click on Followed Students link at the bottom of page
	public void clickFollowedStudentsLink()
	{
		this.linkFollowedStudents.click();
	}
	
	//enter firstname in the Keyword textbox
	public void textboxKeyword(String firstname)
	{
		this.textboxKeyword.sendKeys(firstname);
		System.out.println("Keyword search with "+firstname+" successful.");
	}
	
	//click Search button
	public void clickSearchBtn()
	{
		this.btnSearch.click();
	}
	
	//go to details of user
	public void clickArrowDetails()
	{
		this.arrowDetails.click();
		System.out.println("clicked on User details arrow");
	}
	
	//go to details of Course taken by user
	public void clickArrowCourseDetails()
	{
		this.arrowCourseDetails.click();
		System.out.println("clicked on course details arrow");
	}
	
	public void clickQuizIcon()
	{
		this.iconQuiz.click();
	}
	
	public void clickSendEmailCheckbox()
	{
		this.checkboxEmail.click();
	}
	
	public void clickCorrectTestBtn()
	{
		this.btnCorrectTest.click();
	}
	
	public String verifyAlertMessageSent()
	{
		String actualmsg= this.alertHeaderMessageSent.getText();
		return actualmsg;
	}
}
