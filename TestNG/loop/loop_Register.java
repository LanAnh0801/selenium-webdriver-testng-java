package loop;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loop_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id = 'email']");
	By passTextbox = By.xpath("//*[@id = 'pass']");
	By loginButton = By.xpath("//*[@id = 'send2']");
	
	String emailAddress, loginUserId, userID, password;
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		loginUserId = driver.getCurrentUrl();
		emailAddress = "hoony" + generateRandomNumber() + "@hotmail.net";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test(invocationCount = 3)
	public void TC_Register() {
		driver.findElement(By.xpath("//a[text() = 'here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();
		System.out.println(userID);
		System.out.println(password);
	}
	
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// Tạo hàm Random
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}

	
	
}
