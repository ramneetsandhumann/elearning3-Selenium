package com.training.pom;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPOM {
private WebDriver driver; 
	
	public AdminPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Click on Administration Link from Menu
	@FindBy(xpath="//*[@id=\"navbar\"]/ul[1]/li[7]/a") 
	private WebElement adminLink;
		
	//Click on Course List link on Administration page
	//@FindBy(linkText="course_list.php")
	@FindBy(xpath="//*[@id=\"tabs-2\"]/div/div[2]/div[2]/ul/li[1]/a")
	private WebElement courseListLink;
	
	//list of courses in course list page should get displayed
	@FindBy(id="course-list")
	private WebElement checkCourseList; 
		
	//click on Create-a-course link
	@FindBy(xpath="//*[@id=\"tabs-2\"]/div/div[2]/div[2]/ul/li[2]/a")
	private WebElement addNewCourse;
	
	@FindBy(xpath="//*[@id=\"tabs-2\"]/div/div[2]/div[2]/ul/li[6]/a")
	private WebElement addUserToCourselink;
	
	@FindBy(xpath="//*[@id=\"tabs-2\"]/div/div[2]/div[2]/ul/li[5]/a")
	private WebElement linkCoursesCategories;
	
	public void clickAdminLink()
	{
		this.adminLink.click();
	}
	
	//Click on Course List link on Administration page
	public void clickLinkCourseList()
	{
		this.courseListLink.click();
	}
	
	//list of courses in course list page should get displayed
	public boolean checkCourseListExists()
	{
		boolean check= this.checkCourseList.isDisplayed();
		return check;
	}
	
	//click on delete icon of the course to delete
	public void deleteCourseFromList(String code)
	{
			
			//match data of each row cell of 3rd column to find a match
			List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"course-list\"]/tbody/tr/td[3]"));
			String celldata= null;
			for(int i=2;i<rows.size() || celldata.equalsIgnoreCase(code);i++)
			{
				//verify course name added displays in table list view- column 3
				celldata= driver.findElement(By.xpath("//*[@id=\"course-list\"]/tbody/tr[" +i+ "]/td[3]")).getText();
				if(celldata.equalsIgnoreCase(code))
				{
					try
					{
					
					//find the xpath of the "X" element to click delete for the selected Row
					driver.findElement(By.xpath("//*[@id=\"course-list\"]/tbody/tr[" +i+ "]/td[8]/a[6]/img")).click();
					System.out.println("Table Row with given course "+code+" deleted");
					break;
					}
					catch(Error e)
					{
						System.out.println("There was an error in deleteRow");
					}
				}
			}
		
	}
	
	//verify course deleted from the table and does not display anymore
	boolean flag= true;
	public void verifyCourseDeletedFromTable(String code)
	{
			
		//match data of each row cell of 3rd column to find a match
		List <WebElement> rows= driver.findElements(By.xpath("//*[@id=\"course-list\"]/tbody/tr/td[3]"));
		String celldata= null;
		int i;
		for(i=2;i<rows.size() || celldata.equalsIgnoreCase(code);i++)
		{
			//verify course deleted from table list view- column 3
			celldata= driver.findElement(By.xpath("//*[@id=\"course-list\"]/tbody/tr[" +i+ "]/td[3]")).getText();
			if(celldata.equalsIgnoreCase(code))
			{
				flag=false;
				System.out.println("Course "+code+" still not deleted from the table");
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("Course "+code+" deleted from the table");
		}
	}
	
	//Assertion- verify New Course added shows in header
	public void verifyCourseDeletedInHeader()
	{
		try
		{ 
			assertTrue(driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]/div/div[2]")).getText().matches("Deleted"));
			System.out.println("Assertion- Message displays in header- Deleted");
		}
		catch(Error e)
		{
			System.out.println("Assertion- Header message does not match");
		}
	}
	
	//click on Create Course link to create a new course
	public void createNewCourse()
	{
		this.addNewCourse.click();
	}
	
	//click on Add Users To course link
	public void linkAddUserToCourse()
	{
		this.addUserToCourselink.click();
	}
	
	//click on Courses categories link of admin page
	public void linkCoursesCategories()
	{
		this.linkCoursesCategories.click();
	}
		
}
