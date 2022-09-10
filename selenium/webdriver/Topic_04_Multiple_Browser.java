package webdriver;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

public class Topic_04_Multiple_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");//Lấy ra đường dẫn tương đối của project

//	@BeforeClass
//	public void beforeClass() {
//		
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		driver.get("");
//	}

	@Test
	public void TC_01_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//webdriver.gecko.driver => do SELENIUM yêu cầu
		//\\browserDrivers\\geckodriver.exe => Trỏ đường dẫn đến geckdriver trên Window đang dùng Window
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	
	}

	@Test//là 1 cái test method trong TestNG
	public void TC_02_Firefox() {
		//geckdrive -> Setting để cho HĐH hiểu được geckdrive => Giao tiếp với Browser
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//webdriver.gecko.driver => do SELENIUM yêu cầu
		//\\browserDrivers\\geckodriver.exe => Trỏ đường dẫn đến geckdriver trên Window đang dùng Window
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

	@Test
	public void TC_03_Egde_Chromium() {
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//webdriver.gecko.driver => do SELENIUM yêu cầu
		//\\browserDrivers\\geckodriver.exe => Trỏ đường dẫn đến geckdriver trên Window đang dùng Window
		driver = new EdgeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
		
	}

	@AfterClass
	public void afterClass() {
		
	}

}
