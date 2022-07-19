package webdriver;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String autoITFirefox = projectPath + "\\AutoITAuthentication\\authen_firefox.exe";
	JavascriptExecutor jsExecutor;
	//tất cả những gì liên quan đến Alert đều nằm trong thư viện Alert
	Alert alert;

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

	public void TC_01_Accept_Alert() {
		//Step 01: Truy cập vào trang 
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Step 02: Click vào button : Click for JS Alert
		driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
		sleepInSecond(3);
			//Nên chỉ khởi tạo khi element/alert  đó xuất hiện
			//Muốn thao tác được với Alert đang bật lên đó cần phải switch vào nó
		
		     //Switch vào Alert sau lúc Alert này bật lên
		alert  =  driver.switchTo().alert();
		
		//Step 03: Verify message hiển thị trong Alert là: I am a JS Alert
			//Verify title của Alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		//Step 04: Accept alert và verify message hiển thị tại Result là: You clicked an alert successfully
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	
	public void TC_02_Confirm_Alert() {
		//Step 01: Truy cập vào trang trên <Test>
		driver.navigate().refresh();
		//Step 02: Click vào button: Click for JS Alert
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		sleepInSecond(4);
		//Step 03: Verify message hiển thị trong alert là: I am a JS Confirm
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//Step 04: Cancel alert và verify message hiển thị tại Result là: You clicked: Cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}

	
	public void TC_03_Prompt_Alert() {
		//Step 01: Truy cập vào trang trên <Test>
		driver.navigate().refresh();
		//Step 02: Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
		sleepInSecond(4);
		//Step 03: Verify message hiển thị trong alert là: I am a JS Prompt
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		//Step 04: Nhập vào text bất kì và verify message hiển thị result là: You entered: lananh
		String text = "lananh";
		alert.sendKeys(text);
		sleepInSecond(3);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " +text);
		
	}
	//CÁCH 1
	public void TC_04_Authentication_Alert_Trick() {

	//Sử dụng by pass qua link
	//Selenium nó cho mình pass cái User/Pass trực tiếp vài Url trước khi open nó ra
	//Format: http://Username:Password@domain
	//Không bật Alert lên -> Tự động điền url , ko yêu cầu bật Alert lên nữa-> tương thích mọi Browser
		
		
		//Step 01: Truy cập vào trang http://the-internet.herokuapp.com/basic_auth
		//Step 02: Handle authentication alert với user/pass: admin/admin
		String username = "admin";
		String password = "admin";
		String url = "http://" + username +":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		//Step 03: Verify message hiển thị sau khi  login thành công: Congratulations!You must have the proper credetials
			//So sánh tuyệt đối 2 giá trị bằng nhau => Ko sử dụng cách này vì có khoảng trắng
		//Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")), "Congratulations! You must have the proper credentials.");
			//So sánh tương đối -> dùng contains
		String messageText = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(messageText.contains("Congratulations! You must have the proper credentials."));
		
	}
	//CÁCH 2
	@Test
	public void TC_05_Authentication_Alert_Trick_Navigate_From_Other_Page() {
		//Từ 1 trang khác đến trang cần thao tác
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		//Không nên Click vào link để cho nó show Dialog ra
		//Nên get Url của link đó
		String basicAuthenLink = driver.findElement(By.xpath("//a[text() = 'Basic Auth']")).getAttribute("href");
		//http://the-internet.herokuapp.com/basic_auth
		
		//1 -  Tách link thành nhiều chuỗi
		String[] authenLinkArray = basicAuthenLink.split("//");//gặp // thì tách
		//http:
		//the-internet.herokuapp.com/basic_auth
		
		//2 - Lấy chuỗi cộng vào
		String newAuthenLinkUrl = authenLinkArray[0] + "//" + username + ":" + password + "@" + authenLinkArray[1];
		driver.get(newAuthenLinkUrl);
	
		String messageText = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(messageText.contains("Congratulations! You must have the proper credentials."));
		
		
	}
	//CÁCH 3
	public void TC_06_Authentication_Alert_Auto_IT() throws IOException {//Nếu ko tìm thấy đường dẫn file
	//Sử dụng AutoIT - Chỉ demo cho Window
		String username = "admin";
		String password = "admin";
		Runtime.getRuntime().exec(new String[] { autoITFirefox, username, password});
		
	//Trước khi mở Url lên
	//Cho nó execute cái file exe đó -> để chờ Alert bật lên trước 
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		String messageText = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(messageText.contains("Congratulations! You must have the proper credentials."));
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
