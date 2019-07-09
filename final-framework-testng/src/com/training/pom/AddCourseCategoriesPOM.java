package com.training.pom;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCourseCategoriesPOM {

	private WebDriver driver; 
	
	public AddCourseCategoriesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="code")
	private WebElement categoryCode;
	
	@FindBy(name="name")
	private WebElement categoryName;
	
	@FindBy(name="auth_course_child")
	private WebElement radioButtonAllow;
	
	@FindBy(name="submit")
	private WebElement addNewCategoryBtn;
		
	public void clickAddCategoriesIcon()
	{
		Actions action= new Actions(driver);
		WebElement addCategoryIcon= driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]/a/img"));
		action.moveToElement(addCategoryIcon).click().build().perform();
	}
	
	public void enterCategoryCode(String code)
	{
		this.categoryCode.sendKeys(code);
	}
	
	public void enterCategoryName(String name)
	{
		this.categoryName.sendKeys(name);
	}
	
	public void radioBtnAllowAddCategory(String valRadio)
	{
		this.radioButtonAllow.click();
	}
	
	public void clickAddCategoriesBtn()
	{
		this.addNewCategoryBtn.click();
	}
	
	boolean flag= false;
	//verify New Category added displays in Category List/ OR Category Deleted does not display in the list
	public void verifyCategoryInTableList(String name)
	{
		//match data of each row cell of 1st column to find a match
		//*[@id="cm-content"]/div/table/tbody
	 	List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"cm-content\"]/div/table/tbody/tr"));
		String celldata= null;
		System.out.println("total rows= "+rows.size());
		try
		{
		for(int i=2;i<=rows.size() || celldata.startsWith(name);i++)
		{
			//verify Category name added displays in table list view- column 1
			celldata= driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/table/tbody/tr[" +i+ "]/td[a]")).getText();
			//System.out.println("row#= "+i);
			//System.out.println("verify Celldata= "+celldata);
			//System.out.println("starts with= "+celldata.startsWith(name));
			if(celldata.startsWith(name))
			{
				flag= true;
				System.out.println("Verified new Category "+name+" added, displays in the list");
				break;
			}
		}

		if(flag==false)
		{
			System.out.println("Verified category "+name+" does not display in the list");
		}
		}
		catch(Error e)
		{
			System.out.println("There was an error finding the match: "+e);
		}
	}
	
	boolean delFlag= false;		
	//Delete Category from table
	public void deletecategoryFromList(String name)
	{
		//match data of each row cell of 1st column to find a match
	   List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"cm-content\"]/div/table/tbody/tr"));
	   String celldata= null;
	   System.out.println("total rows= "+rows.size());
	   try
	   {
		for(int i=2;i<=rows.size() || celldata.startsWith(name);i++)
		{
			//Delete matched category from the table
			celldata= driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/table/tbody/tr[" +i+ "]/td[a]")).getText();
			if(celldata.startsWith(name))
			{	
					delFlag= true; //*[@id="cm-content"]/div/table/tbody/tr[13]/td[4]/a[3]/img
					driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/table/tbody/tr[" +i+ "]/td[4]/a[3]/img")).click();
					System.out.println("Category "+name+" has been deleted from the list");
					break;
			}
		}
		if(delFlag=false)
		{
			System.out.println("Category "+name+" could not be found");
		}
	   }
	   catch(Error e)
	   {
		   System.out.println("There was an error in deletion: "+e.toString());
	   }
			
	}
	
	public void alertAddedCategory()
	{
		try
		{ 
			assertTrue(driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]")).getText().matches("Created"));
			System.out.println("Assertion- Created message shows in the alert bar at the top of tableview");
		}
		catch(Error e)
		{
			System.out.println("Assertion- There was an error in creation: "+e.toString());
		}
	}
	
	public void alertDeletedCategory()
	{
		try
		{
			assertTrue(driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]")).getText().matches("Deleted"));
			System.out.println("Assertion- Deleted message shows in the alert bar at the top of tableview");
		}
		catch(Error e)
		{
			System.out.println("Assertion- There was an error in deletion: "+e.toString());
		}
	}
}
