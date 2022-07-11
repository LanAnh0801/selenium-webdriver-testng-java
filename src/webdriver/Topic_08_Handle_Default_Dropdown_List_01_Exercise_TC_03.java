package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Default_Dropdown_List_01_Exercise_TC_03 {
	WebDriver driver;
	Select select;
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Step 01: Truy cập vào trag
		driver.get("https://rode.com/en/support/where-to-buy");
		
	}

	@Test
	public void TC_01_() {
		//Step 02: kiểm tra dropdown không hỗ trợ thuộc tính Multiple select
		select = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select.isMultiple());
		//Step 03: Chọn giá trị Vietnam trong dropdown
		select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("Vietnam");
		//Step 04: Kiểm tra giá trị đã được chọn thành công hay chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		//Step 05: Click button Search
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		//Step 06: Kiểm tra có 32 giá trị trả về => Ko thấy xuat HIỆN
		
		//Step 07: In ra console tất cả Distributor name
		
		List<WebElement> listD = driver.findElements(By.cssSelector("div.p-1>h4"));
	
		for(WebElement item :listD) {
			String distributorName = item.getText();
			System.out.println(distributorName);
		}
		
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
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
