package webdriver;

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

public class Topic_13_Actions_Part_I {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		actions =  new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Hover_to_element_Tooltip() {
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/jquery-tooltip/");
			//Hai hàm click khác nhau 
			//actions.click(driver.findElement(By.xpath("")));//có hover chuột
			//driver.findElement(By.xpath("")).click();//Ko có hover chuột
		//PERFORM RẤT QUAN TRỌNG -> KO THỂ THIẾU
		//Step 02: Hover chuột vào textbox
		WebElement ageTextbox = driver.findElement(By.id("age"));
		actions.moveToElement(ageTextbox).perform();
		sleepInSecond(5);
		
		//Làm sao để lấy đoạn text khi di chuột vào
		//Step 03: Kiểm tra tooltip xuất hiện
			//C1: Bật debugger
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
			//C2: Dùng SetTimeOut :   setTimeout(() => {debugger;}, 3000);
		
	
	}

	@Test
	public void TC_02_Hover_to_element() {
		////Step 01: Truy cập vào trang
		driver.get("https://www.myntra.com/");
		//Step 02: Hover chuột vào KIDS
		WebElement kidsMenu = driver.findElement(By.xpath("//header[@id= 'desktop-header-cnt']//a[text() = 'Kids']"));
		actions.moveToElement(kidsMenu).perform();
		sleepInSecond(5);
		//Step 03: Click chọn Home and Bath hoặc bất kì page nào => Click bằng actions hoặc driver
		driver.findElement(By.xpath("//a[text() = 'Home & Bath']")).click();
		sleepInSecond(5);
		
		//Step 04: Verify đã chuyển page sau khi click thành công
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text() = 'Kids Home Bath']")).isDisplayed());
	}

	@Test
	public void TC_03_Hover_To_Element_() {
	//Step 01: Truy cập vào trang
	driver.get("https://fptshop.com.vn/");
	//Step 02: Hover chuột vào bất kì trang nào trên Menu (trái)
	actions.moveToElement(driver.findElement(By.xpath("//a[@title = 'ĐIỆN THOẠI']"))).perform();
	sleepInSecond(3);
	driver.findElement(By.xpath("//a[@title = 'Apple (iPhone)']")).click();
	sleepInSecond(3);
	//Step 03: Kiểm tra các trang sub-menu xuất hiện khi hover thành công
	Assert.assertEquals(driver.getCurrentUrl(), "https://fptshop.com.vn/dien-thoai/apple-iphone");
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
