package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Chexbox {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		//Step 01: Truy cập vào trang 
		driver.get("https://www.fahasa.com/customer/account/create");
		
		//Step 02: Navigate qua tab Đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		//Step 03: Verify "Đăng nhập " button là disabled
			//Dùng lại nhiều lanf button này nên khai báo chhung
		By loginbutton = By.cssSelector("button.fhs-btn-login");
		
		Assert.assertFalse(driver.findElement(loginbutton).isEnabled());
		
		//Step 04: Input dữ liệu hợp lệ vào Email/Mật khẩu textbox
		driver.findElement(By.id("login_username")).sendKeys("teahunhj@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		//Step 04: nhanh quá => chưa kịp đổi màu mà đã lấy => cho sleep 5 s
		/*
		 * Apply WEBDRIVERWAIT
		 */
		sleepInSecond(5);
		//Step 05: Verify "Đăng  nhập" button là enabled
		Assert.assertTrue(driver.findElement(loginbutton).isEnabled());
		//Step 06: Verify "Đăng  nhập" button có background color là màu đỏ
		String loginButtonBackgroundColorRgba = driver.findElement(loginbutton).getCssValue("background-color");
		//rgb(201, 33, 33) nhưng trong Selenium thì trả về rgba(0, 255, 0, 1) => phải convert Hexa
		String loginButtonBackgroundColorHecxa = Color.fromString(loginButtonBackgroundColorRgba).asHex().toUpperCase();
		Assert.assertEquals(loginButtonBackgroundColorHecxa, "#C92127");
	}

	
	/*
	 * Hàm isSelected() chỉ work với radiobutton/ checkbox là thẻ Input
	 *  => DEFAULT RADIONBUTTON/CHECKBOX
	 */
	@Test
	public void TC_02_Default_Radio_Checkbox() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		//1 - Chọn (Choice)
		
		
		
		//Checkbox value = Ulcerative Colitis , Liver Disease, Tuberculosis 
		driver.findElement(By.xpath("//input[@value = 'Ulcerative Colitis']")).click();
		driver.findElement(By.xpath("//input[@value = 'Liver Disease']")).click();
		driver.findElement(By.xpath("//input[@value = 'Tuberculosis']")).click();
		
		//Radio value = 3-4 days , I have a strict diet, 3-4 glasses/day 
			//I don't have a diet plan -> 3 dâu '
		driver.findElement(By.xpath("//input[@value = '3-4 days']")).click();
		//driver.findElement(By.xpath("//input[@value = 'I have a strict diet']")).click();
		driver.findElement(By.xpath("//input[@value = '3-4 glasses/day']")).click();
		driver.findElement(By.xpath("//input[@value = \"I don't have a diet plan\"]")).click();
		
		
		
		//2 - Verify(chọn rồi hay chưa chọn thành công)
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Ulcerative Colitis']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Liver Disease']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Tuberculosis']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '3-4 days']")).isSelected());
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'I have a strict diet']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '3-4 glasses/day']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = \"I don't have a diet plan\"]")).isSelected());
		
		//3 -  Bỏ chọn (Radiobutton -> Click nó vẫn thế ko bỏ chọn || Checkbox -> Click x2 là bỏ chọn)
		driver.findElement(By.xpath("//input[@value = 'Ulcerative Colitis']")).click();
		driver.findElement(By.xpath("//input[@value = 'Liver Disease']")).click();
		driver.findElement(By.xpath("//input[@value = 'Tuberculosis']")).click();
		driver.findElement(By.xpath("//input[@value = '3-4 days']")).click();
		//driver.findElement(By.xpath("//input[@value = 'I have a strict diet']")).click();
		driver.findElement(By.xpath("//input[@value = '3-4 glasses/day']")).click();
		driver.findElement(By.xpath("//input[@value = \"I don't have a diet plan\"]")).click();
		
		//4 - Verify(chọn rồi hay chưa chọn thành công)
			//Checkbox
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value = 'Ulcerative Colitis']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value = 'Liver Disease']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value = 'Tuberculosis']")).isSelected());
			//RadioButton
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '3-4 days']")).isSelected());
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'I have a strict diet']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '3-4 glasses/day']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = \"I don't have a diet plan\"]")).isSelected());
		
	}

	@Test
	public void TC_03_Select_All_Chexbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Lấy 1 đại diện cho tât cả
		
		List<WebElement> listAllCheckbox = driver.findElements(By.xpath("//input[@type = 'checkbox']"));
		
		
		//Select all checkbox
		for(WebElement item:listAllCheckbox) {
			//Xảy ra rủi ro nếu click luôn nhỡ đâu web có tích sẵn rùi => If
			if(!item.isSelected()) {
				item.click();
				sleepInSecond(1);
			}
			//Verify all checkbox
			Assert.assertTrue(item.isSelected());
		}
		//Verify all checkbox
//		for(WebElement item:listAllCheckbox) {
//			Assert.assertTrue(item.isSelected());
//		}
		
		//Deselect -> Bỏ chọn
		for(WebElement item:listAllCheckbox) {
			//nếu chọn rùi -> Click chọn tiếp để bỏ chọn
			if(item.isSelected()) {
				item.click();
				sleepInSecond(1);
			}
			//Verify all checkbox
			Assert.assertFalse(item.isSelected());
		}
		//Verify all checkbox
//		for(WebElement item:listAllCheckbox) {
//			Assert.assertFalse(item.isSelected());
//		}
		
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
