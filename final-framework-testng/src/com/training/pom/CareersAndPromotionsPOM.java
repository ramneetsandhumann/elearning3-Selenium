package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CareersAndPromotionsPOM {

private WebDriver driver; 
	
	public CareersAndPromotionsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='careers.php']")
	private WebElement iconCareers;
	
	@FindBy(xpath="//a[@href='/main/admin/careers.php?action=add']")
	private WebElement iconAddCareer;
	
	@FindBy(id="career_name")
	private WebElement textboxName;
	
	@FindBy(id="career_submit")
	private WebElement btnAddCareer;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]")
	private WebElement alertItemAddedHeader;
	
	@FindBy(linkText="Careers and promotions")
	private WebElement linkCareersPromotions;
	
	@FindBy(xpath="//*[@id=\"toolbar-career\"]/div/div/a[3]/img")
	private WebElement iconPromotions;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]/a[2]/img")
	private WebElement iconAddPromotions;
	
	@FindBy(id="name")
	private WebElement textboxPromotionsName;
	
	@FindBy(id="promotion_submit")
	private WebElement btnAddPromotion;
	
	//@FindBy(xpath="//*[@id=\"cm-content\"]/div/div[2]") Item Added Promotions
	
	@FindBy(xpath="//*[@id=\"7\"]/td[4]/a[1]/img")
	private WebElement iconSubscribeSessionsToPromotions;
	
	@FindBy(name="session_not_in_promotion_name")
	private WebElement listboxSessionNotSubscribed;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/form/table/tbody/tr[3]/td[2]/button[1]")
	private WebElement arrowRightClick;
	
	@FindBy(xpath="//*[@id=\"cm-content\"]/div/form/table/tbody/tr[4]/td/button")
	private WebElement btnSubscribeSessionToPromotion;
		
	//click on Career icon on Careers and Promotions page
	public void clickCareersIcon()
	{
		this.iconCareers.click();
	}
	
	//click on Add Career icon
	public void clickAddCareerIcon()
	{
		this.iconAddCareer.click();
	}
	
	//enter Career name in Name textbox
	public void enterCareerNameTextbox(String careername)
	{
		this.textboxName.sendKeys(careername);
	}
	
	//click on Add button
	public void clickAddCareerButton()
	{
		this.btnAddCareer.click();
	}
	
	public void verifyAlertItemAddedHeader()
	{
		String actualalert= this.alertItemAddedHeader.getText();
		if(actualalert.equals("Item added"))
		{
		System.out.println("Actual message matches Expected msg= "+actualalert);
		}
		else
		{
			System.out.println("Actual and expected message donot match");
		}
	}
	
	public void clickCareersPromotionsLink()
	{
		this.linkCareersPromotions.click();
	}
	
	public void clickPromotionsIcon()
	{
		this.iconPromotions.click();
	}
	
	public void clickAddPromotionsIcon()
	{
		this.iconAddPromotions.click();
	}
	
	public void enterPromotionName(String promotionname)
	{
		this.textboxPromotionsName.sendKeys(promotionname);
	}
	
	public void clickAddPromotionBtn()
	{
		this.btnAddPromotion.click();
	}
	
	public void clickSubscribeSessionsToPromotionsLink()
	{
		this.iconSubscribeSessionsToPromotions.click();
	}
	
	public void selectSessionNotSubscribedListbox()
	{	
		//<option value="8">abcde</option>
		Select session= new Select(this.listboxSessionNotSubscribed);
		session.selectByIndex(0);
	//	String sessionname= session.getFirstSelectedOption().getText();
	//	System.out.println("Session selected= "+sessionname);
		//return sessionname;
	}
	
	public void clickArrowRightSide()
	{
		this.arrowRightClick.click();
	}
	
	public void clickSubscribeSessionToPromotionBtn()
	{
		this.btnSubscribeSessionToPromotion.click();
	}
	
	//verify session added to promotion displays against its name
		boolean flag= false;
		public String verifySessionAddedToPromotion(String promotionname)
		{
			//match data of column 1 and 2
			//	List <WebElement> rows= driver.findElements(By.xpath("//*[@role='row']//following::tr"));
			List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"promotions\"]/tbody//descendant::tr"));
		//	List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"promotions\"]//child::tr"));
			System.out.println("total rows= "+rows.size());
			String actualCol1= null; 
			String actualCol2= null;
			int i;
			for(i=1;i<=rows.size() ||actualCol1.equalsIgnoreCase(promotionname);i++)
			{  
				actualCol1= driver.findElement(By.xpath("//*[@id=" +i+ "]/td[1]")).getText();
				actualCol2= driver.findElement(By.xpath("//*[@id=" +i+ "]/td[2]")).getText();
				System.out.println("col1= "+actualCol1);
				System.out.println("col2= "+actualCol2);
				if(actualCol1.equalsIgnoreCase(promotionname))
				{
					System.out.println("Match for promotion found");
					flag= true;
					break;
				}
			}
			if(flag==false)
			{
				System.out.println("Promotion not found");
			}
			return actualCol2;
		}
} 
