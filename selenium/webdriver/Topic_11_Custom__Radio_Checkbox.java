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

public class Topic_11_Custom__Radio_Checkbox {
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
		jsExecutor  =(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		//Case 1
		// - Thẻ input không click được -> Failed
		// - Thẻ input dùng Verify -> Passed
		//driver.findElement(By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input")).click();
		
		//Case 2
		// - Không dùng thẻ input để click -> dùng thẻ span chứa text -> Passed
		// - Không dùng thẻ  input để Verify được -> Failed 
//		By checkedCheckBox = By.xpath("//span[text() = 'Checked']");
//		driver.findElement(checkedCheckBox).click();
//		sleepInSecond(5);
//		Assert.assertTrue(driver.findElement(checkedCheckBox).isSelected());
//		
		//Case 3: Thỏa mãn điều kiện (vừa Click/ vừa Verify được)
		// - Không dùng thẻ input để click -> dùng thẻ span chứa text -> Passed
		// - Thẻ input dùng Verify -> Passed
		//VẤN ĐỀ XẢY RA: 1 element mà phải define 2 locator / by
		//Maintain sinh ra rất nhiều phụ thuộc 
//		By checkedCheckBoxText = By.xpath("//span[text() = 'Checked']");
//		driver.findElement(checkedCheckBoxText).click();
//		sleepInSecond(5);
//		
//		By checkedCheckbox = By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input");
//		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
//		
		
		
		//Case 4: Thỏa mãn điều kiện (vừa Click/ vừa Verify được)
		// - Thẻ input để click -> Passed (JavascriptExecutor)
		// - Thẻ input dùng Verify -> Passed
		// JavascriptExecutor : Không quan tâm element bị che hay không (Vẫn click được)
		By checkedCheckbox = By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedCheckbox));
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	
	}

	@Test
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By beforeRadio = By.xpath("//span[text() = 'Before']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(beforeRadio));
		Assert.assertTrue(driver.findElement(beforeRadio).isSelected());
	}

	@Test
	public void TC_03_Custom_Checkbox_Radio_No_Input() {//Không có thẻ input trong các radio và checkbox
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		//Radio
		By hanoiRadio = By.xpath("//div[@aria-label = 'Hà Nội']");
		driver.findElement(hanoiRadio).click();
		sleepInSecond(2);
				//Verify -> Lấy ra giá trị thuộc tính aria-checked để verify
		Assert.assertEquals(driver.findElement(hanoiRadio).getAttribute("aria-checked"), "true");
			
		//Checkbox
//		driver.findElement(By.xpath("//div[@aria-label = 'Quảng Nam']/parent::div")).click();
//		sleepInSecond(3);
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label = 'Quảng Nam']")).getAttribute("aria-checked"), "true");
			//Cách 2 dùng jsexecutor
		By quangNamCheckbox = By.xpath("//div[@aria-label = 'Quảng Nam']");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(quangNamCheckbox));
		Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"), "true");
		
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
