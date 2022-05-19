package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, fullName, lastName, emailAddress, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//tại sao lại khởi tạo trong before class -> vì trong before class nó chạy trước
		firstName = "Automation";
		
		lastName= "FC";
		fullName = firstName + " " + lastName;
		emailAddress = "afc"+generateRandomNumber() +"@hotmail.com";
		password = "12345678";
		
	}

	@Test
	public void Login_01_With_Empty_Email_and_Password() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		/*
		 * TRONG CSS ko dùng Text 
		 * div.footer a[title = 'My Account'] khoảng trống đi nhiều node 
		 * div.footer a[title = 'My Account'] > đi 1 node
		 */
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 - Để trống UserName/Password
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		
		//Step 04 - Click Login button	
		driver.findElement(By.id("send2")).click();
		//Step 05 - Verify error message xuất hiện tại 2 field: This is a required field
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	@Test
	public void Login_02_With_Invalid_Email() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 - Nhập email  invalid: 123434234@12312.123123/password valid: 123456
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//Step 04 -Click vào button "Login"
		driver.findElement(By.id("send2")).click();
		//Step 05 - Kiểm tra các error message xuất hiện: Please enter a valid email address. For example doan@domain.com
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_With_Password_Less_Than_6_chars() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 -Nhập email correct and pass invalid: automation@gmail.com/123
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		//Step 04 -Click vào button "Login"
		driver.findElement(By.id("send2")).click();
	//Step 05 -Kiểm tra các error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void Login_04_With_Incorrect_Email_Password() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 -Nhập email correct and pass incorrect : automation@gmail.com/123123123
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		//Step 04 -Click vào button "Login"
		driver.findElement(By.id("send2")).click();
		//Step 05 - Invalid login or pass
		Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(), "Invalid login or password.");
	}

	@Test
	public void Login_05_With_Create_New_Account() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 - Click vào button "Create an Account"
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		//Step 04 -Nhập thông tin hợp lệ vào tất cả field và tạo RANDOM cho dl EmailAddress
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.name("email")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		//Step 05 - Click "Register" button		
		driver.findElement(By.xpath("//button[@title = 'Register']")).click();
		//Step 06 -Verify message xuấy hiên khi đk thành cong
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		//Step 07 -Verify User được tạo mới với thông tin
		/*
		 * Khi thoonng tin không có định thì dùng cái CỐ ĐỊNH để lấy ra
		 */
		String contactInfText = driver.findElement(By.xpath("//h3[text() = 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		
		//Step 08 - Logout khỏi hệ thống
		driver.findElement(By.xpath("//header[@id = 'header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		//Step 09- Ktra hệ thống navigate về Home page sau khi logout thành công
		//BÀI SAU SẼ HỌC
	}

	@Test
	public void Login_06_With_Valid_Email_And_Password() {
		//Step 01- Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02 -Click vao link "My Account" để tới trang đăng nhập
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		//Step 03 -Login thành công với thông tin o TC05
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		//Step 04 -Click vào button "Login"
		driver.findElement(By.cssSelector("button[title = 'Login']")).click();
		//Step 05 - Verify các thông tin sau được hiển thị
		String contactInfText =  driver.findElement(By.xpath("//h3[text() = 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfText.contains(fullName));
		Assert.assertTrue(contactInfText.contains(emailAddress));
	}
	//Tạo hàm Random
	public  int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
		
	}


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
