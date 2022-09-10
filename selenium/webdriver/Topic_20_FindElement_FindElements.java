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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_FindElement_FindElements {
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
		//implicitlyWait apply cho việc tìm findElement và findElements 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_FindElement() {
		// - Tìm thấy duy nhất 1 element/node
			//Thao tác trực tiếp lên nút đó
			//Vì nó tìm thấy nên ko cần phải chờ hêt timeout là 15s
		driver.findElement(By.id("email"));
		// - Tìm thấy nhiều hơn 1 element/node
			//Thao tác với element đầu tiên
			//Ko quan tâm các element còn lại
			//Trong trườn hợp bắt loactor sai - > tìm sai 
		driver.findElement(By.cssSelector("input[@type = 'email']")).sendKeys("admm@gmail.com");
		
		// - Không tìm thấy element/node nào
			//Có cơ chế tìm lại cứ 0.5s sẽ tìm lại 1 lần
			//Nếu trong time tìm mà thấy element thì thỏa mãn đk -> Pass
			//Nếu hết time mà ko tìm thấy element
				//+ Đánh fail testcase này tại step này 
				//+ Throw ra 1 cái ngoại lệ: NosuchElemenntException
	
	}

	@Test
	public void TC_02_FindElements() {
		// - Tìm thấy duy nhất 1 element/node
			//Tìm thấy và lưu vào 1 list  = 1 element
			//Vì nó tìm thấy nên ko cần phải chờ hêt timeout là 15s
		List<WebElement> elements = driver.findElements(By.id("email"));
		// - Tìm thấy nhiều hơn 1 element/node
			//Tìm thấy và lưu nó vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input[@type = 'email']"));
		// - Không tìm thấy element/node nào
			//Có cơ chế tìm lại cứ 0.5s sẽ tìm lại 1 lần
			//Nếu trong time tìm mà thấy element thì thỏa mãn đk -> Pass
			//Nếu hết time mà ko tìm thấy element
				//+ Không đáng fail testcase này tại step này -> Vẫn chạy step tiếp 
				//+ Trả về 1 list rỗng = 0

		
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
