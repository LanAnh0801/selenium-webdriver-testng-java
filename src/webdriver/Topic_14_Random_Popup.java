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

public class Topic_14_Random_Popup {
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
	public void TC_01_Random_Popup_In_Dom() {
		driver.get("https://www.kmplayer.com/home");
		WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-layer"));
		//Viết 1 hàm thỏa mãn 
		/*
		 * Phải luôn chạy được testcase dù popup có hiển thị hay không
		 * Đang trong đợt sale nó hiển thị script mình phải đóng nó đi để chạy tiếp
		 * Hết đợt sale ko hiển thị thì script chạy luôn 
		 */
		if (popupLayer.isDisplayed()) {//Hiển thị Popup thì chạy vào hàm If không thì chạy Step tiếp theo
			//Close nó đi để chạy tiếp cái Step tiếp theo
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(5);
		} 
		//Step tiếp theo
		driver.findElement(By.cssSelector("p.donate-coffee")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
		
	}

	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {//KO nên spam vào site này vì đang live
		//CASE khi màn hình phân giải thấp -> Có 1 element nào che mất Popup -> dùng Javascript 
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		//Nếu element từ 1 trở lên -> có Popup
		if(popupContent.size() > 0 && popupContent.get(0).isDisplayed()) {//Close Popup
			//Thử xem mình chạy Đ hay Ko ( 4 dòng đầu)
//			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("JungKoook");
//			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("JungKoook@gmail.com");
//			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("12345");
//			sleepInSecond(3);
			
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button#close-popup")));
			//driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(5);
			
		}
		//Step tiếp theo 
		driver.findElement(By.xpath("//a[text() = 'Kích hoạt khóa học']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/kich-hoat-khoa-hoc");
		
		
		
	}

	//@Test
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
