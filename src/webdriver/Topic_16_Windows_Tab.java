package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
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

public class Topic_16_Windows_Tab {
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
	public void TC_01_() {
		//Trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Vừa mở ra chỉ có duy nhất 1 ID
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		sleepInSecond(3);//Để sleep sau Click sau khi click page chưa kịp load
		//Lúc này có 2 tab rồi
		
		switchToWindowByTitle("Google");
		
		//Step tiếp theo
		//Trang B
		//System.out.println(driver.getCurrentUrl());
		//System.out.println(driver.getTitle());
		//Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		//Trag A
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("lananh");
		//Quya về A
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		closeAllWindowWithoutParent(parent);
		sleepInSecond(3);
	}

	//@Test
	public void TC_02_() {
		
	}

	//@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	//CHỈ DÙNG CHO 2 ID (TAB)
	public void switchToWindowById(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		//Dùng vòng lặp duyệt qua từng ID đang có trong SEt
		
		for (String id : allWindows) {//id là biến tạm dùng để duyệt qua từng giá trị
			if(!id.equals(parentID)) {//CHỈ DÙNG CHO 2 ID (TAB)
				driver.switchTo().window(id);
			}
		}
	}
	//DÙNG CHO 3 TAB TRỞ LÊN
	//Có 3 tab trở lên dùng Title để Switch qua
	public void switchToWindowByTitle(String ExceptedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		//Dùng vòng lặp duyệt qua từng ID đang có trong SEt
		
		for (String id : allWindows) {//id là biến tạm dùng để duyệt qua từng giá trị
			//Switch trước
			driver.switchTo().window(id);
			//Lấy ra Title của page Switch vào
			String curentPagetTitle = driver.getTitle();
			//So sánh với Title Page mong muốn
			if(curentPagetTitle.equals(ExceptedPageTitle)) {
				//Thoát khỏi vòng lặp -> ko lặp nữa
				break;
			}
		}
	}
	public  void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		//Dùng vòng lặp duyệt qua từng ID đang có trong SEt
		
		for (String id : allWindows) {//id là biến tạm dùng để duyệt qua từng giá trị
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				//Đóng tab đang Active
				driver.close();
				//Đóng cả cái browser (ko quan tâm bao nhiêu tab/window)
				//Nếu có 1 tab thì close và quit như nhau
				//driver.quit();
			}
		}
		driver.switchTo().window(parentID);
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
