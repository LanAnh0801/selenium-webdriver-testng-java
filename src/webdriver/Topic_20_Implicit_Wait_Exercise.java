package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
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

public class Topic_20_Implicit_Wait_Exercise {
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
		
		driver.manage().window().maximize();
		// 2. NGoại lê
			//ImliciWait ở đau sẽ apply từ đó trở xuống
			//Nếu bị gán lại dùng giá trị mới / ko dùng giá trị cũ
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Not_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	
	}

	@Test
	public void TC_02_Enough_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_More_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
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
