package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
		firstName = "John";
		lastName = "Deepp";
		day = "12";
		month = "May";
		year = "1920";
		emailAddress = "john" +  generateRandomNumber() + "@hotnet.mail";
		companyName = "ABC";
		password = "123456";
	}

	@Test
	public void TC_01_NopCommerce() {
		//NHẬP DƯ LIỆU -> INPUT
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Thao tác với DropDown thông qua thư viện Select của Selenium
		// Khi nào dùng để thao tác vơi element thì mới khởi tạo XXX KHÔNG KHỞI TẠO như
		// driver

		// Hàm chọn
		// thứ tự của option ---Thứ tự bắt đầu từ 1
//		select.selectByIndex(1);
//		select.selectByValue("2");
//		//Nên DÙNG Text vì value, index thay đổi
//		select.selectByVisibleText("Day");
//		// Bỏ chọn -> ít khi dùng
//		select.deselectByIndex(1);
//		//Multiple DropDown hiếm khi sử dụng

		// DropDown Day
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		// Kiểm tra xem đã được chọn hay chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		// DropDown là mutiple hay single
		Assert.assertFalse(select.isMultiple());

		// DropDown Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		// DropDown Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		//VERIFY -> OUTPUT
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		driver.findElement(By.className("ico-account")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
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
