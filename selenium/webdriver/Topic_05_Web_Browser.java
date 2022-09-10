package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01_() {
		//Nếu chỉ có 1 tab thì close
		//Nếu nhiều hơn 1 tab thì đóng tab đang active
		driver.close();
		//Đóng tất cả các tab => đóng  Browser 
		driver.quit();											//(1)
		//Lấy ra URL của page hiện tại
		driver.getCurrentUrl();
		
		/*
		 * HỌC BÀI WAIT
		 */
		driver.findElement(By.id(""));                          //(1)
		driver.findElements(By.id(""));							//(1)
		//Lấy ra Source Code của page hiện tại -> Trong TH element khó bắt quá thì dùng hàm này xem có chứa nó không
		driver.getPageSource();
		//Lấy ra tieu đề của page hiện tại -> chữ trên đầu trang   (title của HTML5)
		driver.getTitle();
		
		/*
		 * WINDOW/TAB
		 */
		//Dùng để xử lí Window/Tab
		//Lấy ra ID của Window/ tab đang active
		driver.getWindowHandle();						//(1)
		//Lấy ra ID của tất cả các Window/tab đang có 
		driver.getWindowHandles();						//(1)
		
		driver.manage();//Trả về kiểu dữ liệu Options
		//********************FRAMEWORK - Cookie ****************
		//Cookie: Lưu lại phien đăng nhập/dữ liệu duyệt web
		driver.manage().deleteAllCookies();
		//********************FRAMEWORK - Log *******************
		//driver.manage().logs().get(Log);
		
		//Chờ cho findElement /findElements chạy hoặc tìm
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		//(1)
		//Chờ cho 1 Page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//********************JavaScript************************
		//Chờ cho 1 đoạn JavaScript chạy thành công
		//Java chạy theo asynchronous script --> bất đồng bộ deex bị fail
		// Synchronous script -> đồng bộ
		driver.manage().timeouts().setScriptTimeout(17, TimeUnit.SECONDS);
		//Full màn hình
		driver.manage().window().fullscreen();
		//Full cửa sổ
		driver.manage().window().maximize();				//(1)
		//Set vị trí của browser so với độ phân giải màn hình (Resolution)
		driver.manage().window().setPosition(new Point(150, 290));
		driver.manage().window().getPosition();
		//Mở browser với kích thước là bao nhiêu
		//Test Responsive
		driver.manage().window().setSize(new Dimension(100, 100));
		driver.manage().window().getSize();
		//Tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		driver.get("https://www.facebook.com/");
		//*************Alert********** 						//(1)
		driver.switchTo().alert();
		//*************Frame/IFrame**********
		driver.switchTo().frame(0);							//(1)
		//*************Window/ Tab***********
		driver.switchTo().window("");						//(1)
		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
