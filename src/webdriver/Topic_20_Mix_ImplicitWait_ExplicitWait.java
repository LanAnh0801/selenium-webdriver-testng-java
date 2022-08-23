package webdriver;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Mix_ImplicitWait_ExplicitWait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	// Thư viện Actions như thư viện Select
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Khai báo sau driver vì lấy giá trị của driver để khởi tao

		jsExecutor = (JavascriptExecutor) driver;
		// Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		// EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn

		// implicitlyWait apply cho việc tìm findElement và findElements

		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Element_Found() {// Tìm thấy elememnt
		// Element có xuất hiện và ko cần chơ hết timeout
		// Dù có set cả 2 loại wait cũng không ảnh hưởng
		// 2 loại wait cái nào trước cũng k a/h

		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// ExplicitWait cho emailTextbox
		System.out.println("Time bắt đầu của explicitWait" + getTimeStamp());
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("Time kết thúc của explicitWait" + getTimeStamp());
		// ImplicitWait
		System.out.println("Time bắt đầu của implicitWait" + getTimeStamp());
		driver.findElement(By.id("email"));
		System.out.println("Time kết thúc của implicitWait" + getTimeStamp());

	}

	// @Test
	public void TC_02_Element_Not_Found_ImplicitWait() {// Ko tìm thấy element
		// TH1: Chỉ dùng ImplicitWait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// ImplicitWait
		System.out.println("Time bắt đầu của implicitWait" + getTimeStamp());
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {

			System.out.println("Time kết thúc của implicitWait" + getTimeStamp());
		}

		// Output (Testcase bị fail, cứ 0.5s lại chạy lại 1 lần, chờ hết timeout, throw
		// ra 1 exception: NoSuchElement
	}

	// @Test
	public void TC_03_Element_Not_Found_ImplicitWait_Explicit() throws TimeoutException {// Ko tìm thấy element
		// 3.1 - ImplicitWait = ExplicitWait
		// 3.2 - ImplicitWait < ExplicitWait
		// 3.3 - ImplicitWait > ExplicitWait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");
		// ImplicitWait
		System.out.println("Time bắt đầu của implicitWait" + getTimeStamp());
		try {
			driver.findElement(By.id("slenium"));
		} catch (Exception e) {
			System.out.println("Time kết thúc của implicitWait" + getTimeStamp());
		}

		// ExplicitWait cho emailTextbox
		System.out.println("Time bắt đầu của explicitWait" + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Time kết thúc của explicitWait " + getTimeStamp());
		}

	}

	//@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		expliciWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");
		//Explicit -  By là tham số nhận vào của hàm explict - visibilityOfElementLocated
		//Implicit = 0
		//Tổng time = Explicit
		System.out.println("Time bắt đầu của explicitWait" + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Time kết thúc của explicitWait " + getTimeStamp());
		}

	}
	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");
		//Explicit -  By là tham số nhận vào của hàm explict - visibilityOfElementLocated
		//Implicit = 0
		//Tổng time = Explicit
		System.out.println("Time bắt đầu của explicitWait" + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Time kết thúc của explicitWait " + getTimeStamp());
		}

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}

	// Show ra time tại thời điểm gọi hàm
	// time -stamp = ngày-h-phút..
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();

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
