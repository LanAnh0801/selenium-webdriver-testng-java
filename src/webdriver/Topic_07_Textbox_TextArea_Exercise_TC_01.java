package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Exercise_TC_01 {
	WebDriver driver;
	JavascriptExecutor jsEcExecutor;
	String emailAddress, loginUserId, userID, password;
	String customerName, gender, city, state, pin, mobileNumber;
	String dateofBirthInput, dateofBirthOutput;
	String addressInput, addressOutput;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsEcExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		loginUserId = driver.getCurrentUrl();
		emailAddress = "hoony" + generateRandomNumber() + "@hotmail.net";
		customerName = "Daddyyy";
		gender = "male";
		city = "Los Angeles";
		state = "California";
		pin = "123456";
		mobileNumber = "098765423";
		dateofBirthInput = "09/12/2001";
		dateofBirthOutput = "2001-09-12";
		addressInput = "134 BA Leng\n Los Angeles\nAmerican";
		addressOutput = "134 BA Leng Los Angeles American";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text() = 'here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginUserId);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3 td")).getText(), "Manger Id : " + userID);

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
		// Tránh làm từng field
		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value = 'm']")).click();
		WebElement dateofBirthTextBox = driver.findElement(By.name("dob"));
		jsEcExecutor.executeScript("arguments[0].removeAttribute('type')", dateofBirthTextBox);
		sleepInSecond(3);
		dateofBirthTextBox.sendKeys(dateofBirthInput);
	
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(),
				"Customer Registered Successfully!!!");
		
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText(),
				dateofBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText(),
				addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText(),
				city);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(),
				pin);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText(),
				emailAddress);

		
	
		
		

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
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

	// Tạo hàm Random
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}

}
