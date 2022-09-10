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

public class Topic_08_Handle_Default_Dropdown_List_02_Exercise_TC_04 {
	WebDriver driver;
	Select select;
	String  emailAddress, password;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Step 01: Truy cập vào trag
		driver.get("https://demo.nopcommerce.com/register");
		emailAddress =  "jk" +  generateRandomNumber() + "@hotnet.mail";
		password = "123456";
	}

	@Test
	public void TC_01_() {
		//Step 02: Click Register link trên Header
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		//Step 03: Input các thông tin hợp lệ vào form
		 //Gender
		driver.findElement(By.cssSelector("input#gender-male")).click();
		//First Name
		driver.findElement(By.id("FirstName")).sendKeys("JeiKei");
		//Last Name
		driver.findElement(By.id("LastName")).sendKeys("Jeon");
		//Date of Birth Day - Month -Year
			//Day
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		int number_Day = select.getOptions().size();
		Assert.assertEquals(32, number_Day);
		select.selectByVisibleText("1");
			//month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		int number_Month = select.getOptions().size();
		Assert.assertEquals(13, number_Month);
		select.selectByVisibleText("May");
			//year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		int number_Year= select.getOptions().size();
		Assert.assertEquals(112, number_Year);
		select.selectByVisibleText("1980");
		//Email
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		//Password
		driver.findElement(By.id("Password")).sendKeys(password);
		//Confirm password
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		
		//Step 04: Click REGISTER button
		driver.findElement(By.id("register-button")).click();
		//Step 05: Verify đã vào trang Home Page sau khi đăng kí thành công
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-body>div")).getText(), "Your registration completed");
		//Step 06: Click vào trang My Account và kiểm tra ngày/ tháng/ năm nhập vào là đúng
		driver.findElement(By.cssSelector("a.ico-account")).click();
			//Kiểm tra day
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
			//Kiểm tra month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
			//Kiểm tra year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
