package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Radio_Checkbox_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

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

	@Test
	public void TC_01_Custom_Checkbox_or_Radio_Button() {
		//Step 01: Truy cập vào trang
		driver.get("https://material.angular.io/components/radio/examples");
		//Step 02: Click vào radio button: Summer
		By summerRadio = By.xpath("//span[text() = ' Summer ']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(summerRadio));
		//Step 03: Kiểm tra radio button đó đã được chọn hay chưa/nếu chưa chọn lại
		Assert.assertTrue(driver.findElement(summerRadio).isSelected());
		if(!driver.findElement(summerRadio).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(summerRadio));
		}
		//Step 04: Truy cập vào trang https://material.angular.io/components/checkbox/examples
		driver.get("https://material.angular.io/components/checkbox/examples");
		//Step 05: Click vào checkbox
			//Checked
			//Indeterminate
		By checkedCheckbox = By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text() = 'Indeterminate']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedCheckbox));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(indeterminateCheckbox));
		//Step 06: Kiểm tra checkbox đó đã được chọn
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		
		//Step 07: Sau khi checkbox đã được chọn-bỏ chọn nó và kiểm tra
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedCheckbox));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(indeterminateCheckbox));
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
	}

	@Test
	public void TC_02_Custom_Checkbox_or_Radio_Button() {
		//Step 01: Truy cập vào trang
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//Step 02: Kiểm tra 'Cần Thơ' radio button là chưa được chọn 
		By canThoRadio = By.xpath("//div[@aria-label = 'Cần Thơ']");
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
		
		//Step 03: Click chọn 'Cần Thơ' radio button
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(canThoRadio));
		
		//Step 04: Kiểm tra 'Cần Thơ ' radio button là đã được chọn 
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
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
