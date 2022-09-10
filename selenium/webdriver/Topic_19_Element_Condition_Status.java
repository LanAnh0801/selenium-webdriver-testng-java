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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Element_Condition_Status {
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
		//implicitlyWait apply cho việc tìm findElement và findElements 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Visiable_Displayed_Visibility() {
		driver.get("https://vi-vn.facebook.com/");
		//1. Có trên UI
		//1. Có trong HTML
		//Wait cho email Address textbox hiển thị
			//Chờ cho email Addresss text box hiển thị trong vòng 10s
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("autoation@gmail.com");
	
	}

	//@Test
	public void TC_02_Invisiable_UnDisplayed_Invisibility_I() {
		//Không có trên UI (bắt buộc)
		//Có trong HTML
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.xpath("//a[text() = 'Tạo tài khoản mới']")).click();
		//Chờ cho  'reg_email_confirmation__' không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	//@Test
	public void TC_02_Invisiable_UnDisplayed_Invisibility_II() {
		//Không có trên UI
		//Không có trên HTML
		driver.get("https://vi-vn.facebook.com/");
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	//@Test
	public void TC_03_Element_Presence_I() {
		//Có trên UI
		//Có trong Html(bắt buộc)
		driver.get("https://vi-vn.facebook.com/");
		//Chờ cho email Addresss text box presence hiển thị trong vòng 10s
		
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));	
	}
	//@Test
	public void TC_03_Element_Presence_II() {
		//Không có trên UI
		//Có trong Html(bắt buộc)
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.xpath("//a[text() = 'Tạo tài khoản mới']")).click();
		//Chờ cho  'reg_email_confirmation__' presence không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		
	
	}
	@Test
	public void TC_04_Staleness() {
		//Không có trong UI
		//Không có trong HTML
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.xpath("//a[text() = 'Tạo tài khoản mới']")).click();
		//Phase 1: Element có trong DOM
		//Thao tác với element khác làm cho element 
		WebElement  retEnterEmailAddressTextbox  = driver.findElement(By.name("reg_email_confirmation__"));
		//Close popup
		driver.findElement(By.cssSelector("img._8idr")).click();
		expliciWait.until(ExpectedConditions.stalenessOf(retEnterEmailAddressTextbox));
		
		
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
