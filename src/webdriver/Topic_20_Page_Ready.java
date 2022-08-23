package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Page_Ready {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		//implicitlyWait apply cho việc tìm findElement và findElements 
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}

	//@Test
	public void TC_01_() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		
	
		//Mỗi lần chuyển trang thì gọi hàm isPagesuccess
		driver.findElement(By.xpath("//label[text() = 'Employee Name']//parent::div//following-sibling::div//child::input")).sendKeys("Aaliyah  Haq");
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		//Load lại 1 phần trang
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'Aaliyah ']")).isDisplayed());
		
	
	}

	@Test
	public void TC_02_() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(5);
		WebElement popuMailchWrap = driver.findElement(By.cssSelector("div.mailch-wrap"));
		if (popuMailchWrap.isDisplayed()) {//Đóng Popup
			driver.findElement(By.cssSelector("div.close-mailch")).click();
			sleepInSecond(5);
		}
		//Hover chuột vào field Search
		actions.moveToElement(driver.findElement(By.xpath("//aside[@id = 'secondary']//input[@class='search-field']"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.xpath("//aside[@id = 'secondary']//input[@class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//aside[@id = 'secondary']//span[@class = 'glass']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement item : postTitle) {
			//String postTitleText = item.getText();
			sleepInSecond(2);
			Assert.assertTrue(item.getText().contains("Selenium"));
			
		}
	}

	
	
	public boolean isPageLoadedSuccess() {
		expliciWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		ExpectedCondition<Boolean>jsLoad = new ExpectedCondition<Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
			
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return expliciWait.until(jQueryLoad) && expliciWait.until(jsLoad);
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}
	//Hàm đồng bộ
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
