package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Fluent_Wait_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	// Thư viện Actions như thư viện Select
	Actions actions;
	FluentWait<WebDriver> fluentWait;
	FluentWait<WebElement> fluentElement;
	long allTime = 15;// Second
	long pollingTime = 100;// MillisSecond

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Khai báo sau driver vì lấy giá trị của driver để khởi tao
		// EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		// expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		// Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		// implicitlyWait apply cho việc tìm findElement và findElements
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		findElement("//div[@id = 'start']//button").click();
		Assert.assertEquals(findElement("//div[@id = 'finish']//h4").getText(), "Hello World!");

	}
	//FluentWait 
		//Nếu set pollingtime = 500s thì tương đương với Implicitwait vì cứ 0.5s lại tìm element 1 lần
	public WebElement findElement(String xPathLocator) {
		// MỤC ĐÍCH Của đoạn FluentWait : Wait cho Start Button xuất hiện
		fluentWait = new FluentWait<WebDriver>(driver);
		// Set tổng time và tần số
		fluentWait.withTimeout(Duration.ofSeconds(allTime))
				// 1/3 s check lại 1 lần
				.pollingEvery(Duration.ofMillis(pollingTime))

				.ignoring(NoSuchElementException.class);

		// Apply điều kiện
		WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {

				return driver.findElement(By.xpath(xPathLocator));
			}
		});
		return element;

	}

	@Test
	public void TC_02_Fluent_Wait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement coutDownTime = findElement("//div[@id = 'javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement>(coutDownTime);
		fluentElement.withTimeout(Duration.ofSeconds(allTime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
		

	}

	//@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}

	// Hàm đồng bộ
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
