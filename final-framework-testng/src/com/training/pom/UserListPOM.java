package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserListPOM {

private WebDriver driver; 
	
	public UserListPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\"search_simple_keyword\"]")
	private WebElement enterUserName;
	
	@FindBy(xpath="//*[@id=\"search_simple_submit\"]")
	private WebElement clickSearch;
	
	@FindBy(xpath="//img[@src='http://elearningm1.upskills.in/main/img/icons/22/edit.png']")
	private WebElement iconEdit;
	
	@FindBy(name="email")
	private WebElement emailTextBox;
	
	@FindBy(xpath="//*[@id=\"user_edit\"]/fieldset/div[20]/div[1]/div/label")
	private WebElement radioBtnAccountInactive;
	
	@FindBy(id="user_edit_submit")
	private WebElement enterSaveBtn;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]/div/div[2]")
	private WebElement getHeaderMessage;
	
	public void searchByUserName(String username)
	{
		this.enterUserName.sendKeys(username);
	}
	
	public void clickUserSearchBtn()
	{
		this.clickSearch.click();
	}
	
	public void clickEditUserIcon()
	{
		this.iconEdit.click();
	}
	
	public void clearEmailTextBox()
	{
		this.emailTextBox.clear();
	}
	
	public void enterEmailInTextBox()
	{
		this.emailTextBox.sendKeys("raman@gmail.com");
	}
	
	public void clickRadioBtnInactiveAccount()
	{
		this.radioBtnAccountInactive.click();
	}
	
	public void clickSaveBtn()
	{
		this.enterSaveBtn.click();
	}
	
	public String verifyHeaderMessage()
	{
		String actualHeaderMessage= this.getHeaderMessage.getText();
		System.out.println("actual header message= "+actualHeaderMessage);
		return actualHeaderMessage;
	}
}
