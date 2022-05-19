package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void Register_01_with_Empty_Data() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Click vao button "Đăng Kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 03 - Kiểm tra các error message hiện thị tại form đăng kí
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void Register_02_With_Invalid_Email() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Nhập dữ liệu hợp lệ vào các field ngoại trừ Email và ConfirmEmail
		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		
		//Step 03 -Click vào button "Đăng kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 04 - Kiểm tra các error message hiện thị tại Email và Confirm Email
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}

	@Test
	public void Register_03_With_Incorrect_Confirm_Email() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Nhập dữ liệu hợp lệ vào các field ngoại trừ  ConfirmEmail
		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
		driver.findElement(By.id("txtEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("dlha@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		//Step 03 -Click vào button "Đăng kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 04 - Kiểm tra các error message hiện thị tại Confirm Email
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	@Test
	public void Register_04_With_Password_Less_Than_6_Chars() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Nhập dữ liệu hợp lệ vào các field ngoại trừ  PassWord và Confirm PassWord
		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
		driver.findElement(By.id("txtEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		//Step 03 -Click vào button "Đăng kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 04 - Kiểm tra các error message hiện thị tại PassWord và Confirm PassWord
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}

	@Test
	public void Register_05_With_Incorrect_Confirm_Password() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Nhập dữ liệu hợp lệ vào các field ngoại trừ  Confirm PassWord
		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
		driver.findElement(By.id("txtEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		//Step 03 -Click vào button "Đăng kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 04 - Kiểm tra các error message hiện thị tại Confirm PassWord
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}

	@Test
	public void Register_06_With_Invalid_Phone_Number() {
		//Step 01- Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Step 02 -Nhập dữ liệu hợp lệ vào các field ngoại trừ  Phone_Number
//		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
//		driver.findElement(By.id("txtEmail")).sendKeys("dla@gmail.com");
//		driver.findElement(By.id("txtCEmail")).sendKeys("dla@gmail.com");
//		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
//		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
//		driver.findElement(By.id("txtPhone")).sendKeys("09876543");
//		//Step 03 -Click vào button "Đăng kí"
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
//		//Step 04 - Kiểm tra các error message hiện thị tại Phone_Number
//		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		//Step 05-Nhập dữ liệu hợp lệ vào các field ngoại trừ  Phone_Number
		driver.findElement(By.id("txtFirstname")).sendKeys("Doan lan anh");
		driver.findElement(By.id("txtEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("dla@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("1234");
		//Step 06 -Click vào button "Đăng kí"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Step 07 - Kiểm tra các error message hiện thị tại Phone_Number
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
