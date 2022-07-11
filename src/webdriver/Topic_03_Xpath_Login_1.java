package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Login_1 {
	WebDriver driver;//tương tác với element
	String projectPath = System.getProperty("user.dir");
	String firstname = "", lastname = "", email_Address="", pass ="", fullName = "";

	@BeforeClass
	public void beforeClass() {
		//gecko tương tác với giữa brower và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstname ="Automation";
		lastname = "Fc";
		fullName = firstname + " " + lastname;
		email_Address = "Afc" +random_Email()+ "@gmail.com";
		pass = "123456";
		
	}

	@Test
	public void Login_01_With_Empty_Email_And_Pass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	@Test
	public void Login_02_With_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123456@123445");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_With_Pass_Less_6_Chars() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("auto@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void Login_04_With_Incorrect_Email_Pass() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("auto@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(), "Invalid login or password.");
	}
	@Test
	public void Login_05_Create_New_Account() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title = 'Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email_Address);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("confirmation")).sendKeys(pass);
		driver.findElement(By.cssSelector("button[title  = 'Register']")).click();
	
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInf =  driver.findElement(By.xpath("//h3[text() = 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInf.contains(fullName));
		Assert.assertTrue(contactInf.contains(email_Address));
		//Logout khỏi hệ thống
		driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		
	}
	
	public int random_Email() {
		Random random = new Random();
		return random.nextInt(9999);
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
