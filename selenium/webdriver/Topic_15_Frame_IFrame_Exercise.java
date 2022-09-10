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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Frame_IFrame_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	//Thư viện Actions như thư viện Select
	Actions actions;
	Select select;

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
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		//Step 02: Verify Facebook Iframe hiển thị
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		//Step 03: Verify số lượng like của Face page a=là: 
		//B
		WebElement likeNumber = driver.findElement(By.xpath("//a[text() = 'Kyna.vn']/parent::div/following-sibling::div"));
		Assert.assertEquals(likeNumber.getText(), "166K likes");
		sleepInSecond(2);
		//Step 04: Click vào chat Iframe
			//Iframe C trong A (B-> chuyển về A)
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");// id của IFrame
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInSecond(3);
		//Step 05: Nhập dữ liệu vào các field
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("JungKôk");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0912347898");
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name = 'message']")).sendKeys("Register");
		sleepInSecond(3);
		//Step 06: Sendkey với từ khóa là "Excel" và click vào Search icon
		//	Quay lại page A 
		driver.switchTo().defaultContent();
		String key = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(key);
		driver.findElement(By.cssSelector("button.search-button")).click();
		//Step 07: Verify chuyển qua page danh sách khóa học chứa từ khóa Exercise
		List<WebElement> courseTitle = driver.findElements(By.cssSelector("div.content h4"));
		Assert.assertEquals(courseTitle.size(), 9);
		for (WebElement item : courseTitle) {
			Assert.assertTrue(item.getText().contains(key));
			
		}
	
	}

	@Test
	public void TC_02_Frame() {
		//Step 01: Truy cập vào trang https://netbanking.hdfcbank.com/netbanking/
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//Step 02: Input vào Customer ID và click vào Continue
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("123456");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		//Step 03: Verify password textbox hiển thị
		WebElement passWordText =  driver.findElement(By.id("fldPasswordDispId"));
		Assert.assertTrue(passWordText.isDisplayed());
		
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
