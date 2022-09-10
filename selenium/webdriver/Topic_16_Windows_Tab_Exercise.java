package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_16_Windows_Tab_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	//Thư viện Actions như thư viện Select
	Actions actions;
	Alert alert;

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

//	@Test
	public void TC_10_Windows_Tab() {
		//Driver đang ở trang Moblie
		driver.get("http://live.techpanda.org/");
		//Step 02: Click vào Moblie Tab
		driver.findElement(By.xpath("//a[text() = 'Mobile']")).click();
		//Step 03: Add sp Sony Xperia vào để Compare (Add to Compare)
			//Verify text hiển thi: The product Sony Xperia has been added to comparison list
		driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		//Step 04:  Add sp Samsung Galaxy vào để Compare (Add to Compare)
			//Verify text hiển thi: The product Samsung Galaxy has been added to comparison list
		driver.findElement(By.xpath("//a[text() = 'Sony Xperia']/parent::h2/following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		//Step 05: Click to Compare button
		driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
		sleepInSecond(3);
		//Step 06: Switch qua cửa sổ mới (chứa 2 sp đã được Add vào để Compare)
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		//Step 07: Verify title của cửa sổ bằng: Products Comparison List - Mangento Commerce
		String titleProductsComparison = driver.getTitle();
		Assert.assertEquals(titleProductsComparison, "Products Comparison List - Magento Commerce");
		//Step 08: Close Tab và chuyển về Parent Window
		driver.findElement(By.xpath("//button[@title = 'Close Window']")).click();
		sleepInSecond(3);
		//DRIVER ĐANG Ở PAGE PRODUCTS COMPARISON -> VỀ TRANG MOBLIE
		switchToWindowByTitle("Mobile");
		//Step 09: Click 'Clear All' link và accept alert
		driver.findElement(By.xpath("//a[text() = 'Clear All']")).click();
		sleepInSecond(3);
		alert = driver.switchTo().alert();
		alert.accept();
		sleepInSecond(2);
		//Step 10: Verify message xuất hiện: The comparison list was cleared
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
		//Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/mobile.html");
	}

	@Test
	public void TC_02_Window_Tab() {
		//Step 01: Truy cập trang
		driver.get("https://dictionary.cambridge.org/vi/");
		//Step 02: Click vào Đăng nhập link
		driver.findElement(By.cssSelector("span.cdo-login-button span")).click();
		//Step 03: Switch qua cửa sổ mới
		switchToWindowByTitle("Login");
		sleepInSecond(3);
		//Step 04: Click vào Login button
		driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
		//Step 05: Verify message This field is required xuất hiện tại 2 field Email và Pass
		WebElement emailText = driver.findElement(By.xpath("//input[@placeholder= 'Email *']/following-sibling::span"));
		Assert.assertEquals(emailText.getText(), "This field is required");
		WebElement passText = driver.findElement(By.xpath("//input[@placeholder= 'Password *']/following-sibling::span"));
		Assert.assertEquals(emailText.getText(), "This field is required");
		sleepInSecond(3);
		//Step 06: Nhập dữ liệu vào 2 field trên
		driver.findElement(By.xpath("//input[@placeholder= 'Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder= 'Password *']")).sendKeys("Automation000***");
		sleepInSecond(3);
		//Step 07: Click LoginIn Button
		driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		//Step 08: Verify đã thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");
	}

//	@Test
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
