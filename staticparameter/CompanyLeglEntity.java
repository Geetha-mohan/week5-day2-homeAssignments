package week5.day2.homeassignment.staticparameter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CompanyLeglEntity extends ProjectBaseMethods{
		
		@Test
		public void createCompanyEntity() throws InterruptedException {
		
		 //create new legal entity
	    driver.findElement(By.xpath("//div[@title='New']")).click();
	    Thread.sleep(2000);
		
	    //enter companyname, description
	    driver.findElement(By.xpath("//div[normalize-space(@class='slds-form-element__control slds-grow')]/input[@name='CompanyName']")).sendKeys("TestLeaf");
	    driver.findElement(By.xpath("//label[text()='Description']/following::div/textarea")).sendKeys("Salesforces");
	    
	    //select Active Status dropdown
	    WebElement Activestatus = driver.findElement(By.xpath("//button[@aria-label='Status']"));
	    //js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", Activestatus);
	    Activestatus.click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Active')]")).click();
	    
	    //click Save
	    driver.findElement(By.xpath("//button[text()='Save']")).click();
	    
	    //verify the alert for Legal Entity field 
	    String legalName = driver.findElement(By.xpath("//h2[text()='We hit a snag.']")).getText();
	    System.out.println("Legal name "+legalName);
	    String nameText = driver.findElement(By.xpath("//h2[text()='We hit a snag.']/following::a")).getText();
	    System.out.println(nameText);
	    if(nameText.contains("Entity Name"))
	    	System.out.println("legal name is mandatory");
	    else
	    	System.out.println("no alert message");
		
	}
	

}
