package tech;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter_Multiple_Server {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id = 'email']");
	By passTextbox = By.xpath("//*[@id = 'pass']");
	By loginButton = By.xpath("//*[@id = 'send2']");
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Browser name is not valid");
			break;
		}
	
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	//Server - lquan đến URL
	@Parameters("server")
	@Test
	public void TC_01_Login(@Optional("LIVE")  String serverName) {//Optional nếu như quên ko truyên value bên xml
		String serverURL = getServerName(serverName);
		driver.get("http://"+serverURL+"/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id = 'header']//span[text() = 'Account']")).click();
//		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		
	}
	private String getServerName(String serverName) {
		switch (serverName) {
		case "DEV":
			serverName = "dev.techpanda.org";
			break;
		case "TESTING":
			serverName = "testing.techpanda.org";
			break;
		case "STAGING":
			serverName = "staging.techpanda.org";
			break;
		case "LIVE":
			serverName = "live.techpanda.org";
			break;
		default:
			System.out.println("Server name is not valid");
			break;
		}
		return serverName;
	}
	
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
