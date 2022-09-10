package Parallel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_02_Second_Class {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id = 'email']");
	By passTextbox = By.xpath("//*[@id = 'pass']");
	By loginButton = By.xpath("//*[@id = 'send2']");
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_Login_01() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passTextbox).sendKeys("111111");
//		driver.findElement(loginButton).click();
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
//		driver.findElement(By.xpath("//header[@id = 'header']//span[text() = 'Account']")).click();
//		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		
	}
	@Test
	public void TC_Login_02() {
		
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id = 'header']//span[text() = 'Account']")).click();
//		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		
	}
	
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
