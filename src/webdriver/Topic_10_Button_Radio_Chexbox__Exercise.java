package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Chexbox__Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		//Step 01: Truy cập vào trang 
		driver.get("https://www.fahasa.com/customer/account/create");
		//Step 02: Navigate qua tab Đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
		
		//Step 03: Verify "Đăng nhập " button là disabled
		By buttonLogin = By.cssSelector("button.fhs-btn-login");
		Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());
		
		//Step 04: Input dữ liệu hợp lệ vào Email/Mật khẩu textbox
		driver.findElement(By.id("login_username")).sendKeys("jimin123@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(2);
		
		//Step 05: Verify "Đagw nhập " button là enabled
		Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());
		//Step 06: Verify "Đagw nhập " button có background color là màu đỏ
		String buttonLoginBC = driver.findElement(buttonLogin).getCssValue("background-color");
		//rgb(201, 33, 39) trong Selenium trả về rgba -> convert sang hexa
		String buttonLoginBCHexa = Color.fromString(buttonLoginBC).asHex().toLowerCase();
		Assert.assertEquals(buttonLoginBCHexa, "#c92127");
		
	}

	
	/*
	 * Hàm isSelected() chỉ work với radiobutton/ checkbox là thẻ Input
	 *  => DEFAULT RADIONBUTTON/CHECKBOX
	 */
	@Test
	public void TC_02_Default_Radio_or_Checkbox() {
//		//Step 01: Truy cập vào trang
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
//		//Step 02: Click vào checkbox:Dual-zone air conditioning
		jsExecutor.executeScript("window.scrollBy(0,500)");
		By checkbox = By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input");
		driver.findElement(checkbox).click();
		sleepInSecond(5);
		//Step 03: Kiểm tra checkbox đó đã chọn chưa
		Assert.assertTrue(driver.findElement(checkbox).isSelected());
		//Step 04: Sau khi checkbox đã được chọn - > bỏ chọn nó và kiểm tra nó chưa được chọn
		driver.findElement(checkbox).click();
		Assert.assertFalse(driver.findElement(checkbox).isSelected());
		
		//Step 05: Truy cập vào trang	
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		//Step 06:Click vào radio button: 2.0 Petrol, 125kW
		jsExecutor.executeScript("window.scrollBy(0,500)");
		By radioButton = By.xpath("//label[text() = '2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.findElement(radioButton).click();
		//Step 07: Kiểm tra radio button đó đã được chọn hay chưa/nếu chưa chọn lại
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		if(!driver.findElement(radioButton).isSelected()) {
			driver.findElement(radioButton).click();
		}
		
	}

	@Test
	public void TC_03_Select_All_Chexbox() {
	//	driver.get("https://automationfc.github.io/multiple-fields/");
	
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
