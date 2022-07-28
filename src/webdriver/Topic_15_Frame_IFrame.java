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

public class Topic_15_Frame_IFrame {
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
	//Switch To có 3 loại: Alert/ Frame/IFramme/Window
	//@Test
	public void TC_01_IFrame() {
		//A
		driver.get("https://kyna.vn/");
//		driver.switchTo().frame(0);//C1: Dùng index -> Frame thứ mấy -> Xóa/Update thì bị Fail -> KO DÙNG
//		driver.switchTo().frame("");//C2: Dùng id, name, ...
		//A -> B
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));//C3: Dùng element -> NÊN DÙNG
		//B (Element thuộc B)
		String likeNumber = driver.findElement(By.xpath("//a[text() = 'Kyna.vn']/parent::div//following-sibling::div")).getText();
		Assert.assertEquals(likeNumber, "166K likes");
		//A -> C
			//Nhảy từ B -> A 
		driver.switchTo().defaultContent();
			//Rùi mới A -> C
		driver.switchTo().frame("cs_chat_iframe");
		//C (Element thuộc C)
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("JungKook");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456");
		new  Select (driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.xpath("//textarea[@name = 'message']")).sendKeys("Register");
		sleepInSecond(5);
		
		//C -> A
		driver.switchTo().defaultContent();
		//A (Element thuộc A)
		String key = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(key);
		driver.findElement(By.cssSelector("button.search-button")).click();
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		//Verify số lượng
		Assert.assertEquals(courseName.size(), 9);
		for (WebElement item : courseName) {
			//Verify chứa text Excel
			Assert.assertTrue(item.getText().contains(key));
		}
	}
	

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//Step 02:Input vào Customer ID và click vào Continue
		//Step 03: Verify Password textbox hiển thị
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.btn-primary")).click();
		sleepInSecond(3);
		WebElement passWordText =  driver.findElement(By.id("fldPasswordDispId"));
		Assert.assertTrue(passWordText.isDisplayed());
		passWordText.sendKeys("autofc");
		
	}

	@Test
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
