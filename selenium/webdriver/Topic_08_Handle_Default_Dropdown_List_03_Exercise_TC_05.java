package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Default_Dropdown_List_03_Exercise_TC_05 {
	WebDriver driver;
	Select select;
	String firstName, lastName, jobFunction,companyName, existingTestFramework, emailAddress,country;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		//Step 01: Truy cập vào trag
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		emailAddress = "ki" + generateRandomNumber() + "@hotnet.mail";
	}

	@Test
	public void TC_01_() {
		//Step 01: Điền các thông tin bắt buộc vào form dưới
		//Đây là site thực không nên điền thông tin nhảm tránh website block
			//WorkEmail
		
		//Để cuộn trang theo chiều dọc 500px
		jsExecutor.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
			//FirstName
		driver.findElement(By.id("FirstName")).sendKeys("Teahun");
		
			//LastName
		driver.findElement(By.id("LastName")).sendKeys("KIm");
		
			//JobFunction
		select = new Select(driver.findElement(By.id("Person_Role__c")));
		select.selectByVisibleText("CTO / CIO / CPO");
		
			//CompanyName
		driver.findElement(By.id("Company")).sendKeys("ABC");
		
			//ExistingTestFramework
		select = new Select(driver.findElement(By.id("Test_Framework__c")));
		select.selectByVisibleText("Selenium");
		
			//Country
		select = new Select(driver.findElement(By.id("Self_Report_Country__c")));
		select.selectByVisibleText("United States");
		
			//States/Province
		select = new Select(driver.findElement(By.id("Self_Report_State__c")));
		select.selectByVisibleText("Alabama ");
		
		
		//Verify
//		Assert.assertEquals(driver.findElement(By.id("FirstName")).getText(), "Teahun");
//		Assert.assertEquals(driver.findElement(By.id("LastName")).getText(), "Kim");
//		select = new Select(driver.findElement(By.id("Person_Role__c")));
//		Assert.assertEquals(select.getFirstSelectedOption().getText(), "CTO / CIO / CPO");
//		Assert.assertEquals(driver.findElement(By.id("Company")).getText(), "ABC");
//		select = new Select(driver.findElement(By.id("Test_Framework__c")));
//		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Selenium");
//		select = new Select(driver.findElement(By.id("Self_Report_Country__c")));
//		Assert.assertEquals(select.getFirstSelectedOption().getText(), "United States");
	}

	

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}
	// Hàm đồng bộ
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
