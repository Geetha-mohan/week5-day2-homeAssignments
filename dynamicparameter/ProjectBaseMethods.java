package week5.day2.homeassignment.dynamicparameter;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import week5.day2.cractivity.DataLibrary;

public class ProjectBaseMethods {

	public RemoteWebDriver driver;
	public EdgeOptions options;
	public JavascriptExecutor js;

	
	@Parameters({"URL","browser","userName","password"})
	@BeforeMethod	
	public void preConditions(String url,String browser, String uName, String passW) {
		options = new EdgeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");

		//launch browser
		if(browser.equalsIgnoreCase("edge")) 
			driver = new EdgeDriver((EdgeOptions) options);
		if(browser.equalsIgnoreCase("chrome")) 
			driver = new ChromeDriver();
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//login
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(passW);
		driver.findElement(By.id("Login")).click();

		//app Launcher and click View All
		driver.findElement(By.xpath("//button[contains(@title,'App Launcher')]/div")).click();
		driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();

		// Locate the element you want to scroll to Legal Entities
		WebElement targetElement = driver.findElement(By.xpath("//p[text()='Legal Entities']"));

		// Use JavaScriptExecutor to scroll the element into view
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
		targetElement.click();

	}
	
	@AfterMethod
	public void postConditions() {
		driver.quit();
	}

}
