package week5.day2.homeassignment.dynamicparameter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewLegalEntity extends ProjectBaseMethods{

	@DataProvider(name="getNames")
	public String[] sendName()
	{
		String name[] = {"Geetha","Mohan","Priya","Bhavani"};
		return name;
	}

	@Test(dataProvider ="getNames")
	public void createNewLegalEntity(String Name) throws InterruptedException {

		//create new legal entity
		driver.findElement(By.xpath("//div[@title='New']")).click();
		Thread.sleep(2000);

		//enter Name
		String legalName = "Salesforce Automation by " + Name;
		driver.findElement(By.xpath("//label[text()='Legal Entity Name']/following::div/input")).sendKeys(legalName);


		//click Save
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		//verify the Legal Entity name 

		String actualLegalName = driver.findElement(By.xpath("//records-entity-label[text()='Legal Entity']/following::slot/lightning-formatted-text")).getText();
		System.out.println("webpage Legal name "+actualLegalName);


		//Assert.assertEquals(actualLegalName, Name, "New Legal name not matched");
		Assert.assertEquals(legalName, Name, "New Legal name not matched");


	}


}
