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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Fixed_Popup {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	//Thư viện Actions như thư viện Select
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		//Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Fixed_Popup_In_Dom() {
		//Step 01: truy cập vào trang
		driver.get("https://ngoaingu24h.vn/");
		//Step 02: Kiểm tra Popup Đăng nhập không hiển thị
		//Chu ý: Bắt Element
		WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		Assert.assertFalse(loginPopup.isDisplayed());
		//Step 03: Click vào button Đăng nhập
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(5);
		//Step 04: Kiểm tra Popup Đăng nhập hiển thị
		Assert.assertTrue(loginPopup.isDisplayed());
		//Step 05: Nhập thông tin username/password = automationfc
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id ='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id ='password-input']")).sendKeys("automationfc");
		//Step 06: Click Đăng nhập button 
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();
		//Step 07: Verify message hiển thị: tài khoản không tồn tại
		Assert.assertEquals(driver.findElement(By.xpath("//div[text() = 'Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
		
		//Thêm
		//Step 08: Đóng popup
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'close')]")).click();
		//Step 09: Kiểm tra Popup Đăng nhập không hiển thị
		Assert.assertFalse(loginPopup.isDisplayed());
	
	}

	@Test
	public void TC_02_Fixed_Popup_In_Dom() {
		//Step 01;: Truy cập trang
		driver.get("https://kyna.vn/");
		//Verify ko hiển thị popup
		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		Assert.assertFalse(loginPopup.isDisplayed());
		//Click button Đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		//Verify  hiển thị popup
		Assert.assertTrue(loginPopup.isDisplayed());
		// Nhập thông tin username/password = automationfc
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("1234");
		//Click Đăng nhập
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		//Verify message Sai tên đăng nhập hoặc mật khẩu
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		//Close popup
		driver.findElement(By.cssSelector("div#k-popup-account-login button.k-popup-account-close")).click();
		
	}

	@Test
	public void TC_03_() {
		
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
