package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

public class Topic_14_Random_Popup_Exercise {
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

	@Test
	public void TC_01_Random_Popup_In_Dom() {
		//Step 01: truy cập vào trang
		driver.get("https://blog.testproject.io/");
		//Step 02: Kiểm tra popup trong hai trường hợp
			//Có xuất hiện - đóng popup đi - chuyển qua Step 03
			//Không xuất hiện - chuyển qua Step 03 
		sleepInSecond(5);
		WebElement popuMailchWrap = driver.findElement(By.cssSelector("div.mailch-wrap"));
		if (popuMailchWrap.isDisplayed()) {//Đóng Popup
			driver.findElement(By.cssSelector("div.close-mailch")).click();
			sleepInSecond(5);
		}
		//Step 03: Nhập dữ liệu vào Search textbox với từ khóa Selenium
		driver.findElement(By.xpath("//aside[@id = 'secondary']//input[@class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//aside[@id = 'secondary']//span[@class = 'glass']")).click();
		//Step 04: Kiểm tra các title có xuất hiện đều chưa từ khóa Selenium
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title"));
		for (WebElement item : postTitle) {
			sleepInSecond(2);
			Assert.assertTrue(item.getText().contains("Selenium"));
			
		}
	}

	@Test
	public void TC_02_Random_Popup_In_DOM() {
	//Step 01: truy cập vào trang
	driver.get("https://vnk.edu.vn/");
	//Step 02: kiểm tra Popup trong 2 Trường hợp
	WebElement popupColums = driver.findElement(By.cssSelector("div.thrv-columns"));
	if(popupColums.isDisplayed()) {
		driver.findElement(By.cssSelector("div.thrv_icon")).click();
		sleepInSecond(3);
	}
	//Step tiếp theo
	driver.findElement(By.xpath("//a[text() = 'Khóa học OFFLINE ']")).click();
	driver.findElement(By.xpath("//li[@id = 'menu-item-26916']//a[text() = 'Thiết kế hệ thống Điện']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/ky-su-me-chuyen-nghiep/");
	
		
	}

	@Test
	public void TC_03_Random_IN_DOM() {
		driver.get("https://kynaforkids.vn/");
		WebElement popupBanner = driver.findElement(By.xpath("//div[@id = 'popup-banner']//div[@class = 'modal-body']"));
		if(popupBanner.isDisplayed()) {
			driver.findElement(By.xpath("//div[@id = 'popup-banner']//img[@class = 'close-popup']")).click();
			sleepInSecond(5);
		}
		//Step tiếp theo
		driver.findElement(By.xpath("//div[@class= 'wrapper-top-header']//a[@class = 'btn-login']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://learning.kynaforkids.vn/guest/login?redirect_url=https://kynaforkids.vn");
		
	}
	@Test
	public void TC_04_Random_IN_DOM() {
		driver.get("https://www.kmplayer.com/home");
		WebElement popupContainer = driver.findElement(By.cssSelector("div.pop-container"));
		if(popupContainer.isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click()",driver.findElement(By.cssSelector("area#btn-r")));
	
			sleepInSecond(5);
		}
		//Step tiếp theo
		driver.findElement(By.xpath("//ul[@class= 'gnb']//a[text() = 'KMP']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.kmplayer.com/kmp");
		
	}
	@Test
	public void TC_05_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		List<WebElement> popContent  = driver.findElements(By.cssSelector("div.popup-content"));
		if(popContent.size() > 0 && popContent.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(5);
		}
		driver.findElement(By.xpath("//a[text() = 'Về VNK EDU']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/p/gioi-thieu");
		
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
