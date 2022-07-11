package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Custom_DropDown_Exercise_VueJS_04 {
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
	public void TC_01_() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
											//Lấy đến cái getText  
		selectItemCustomDropDown("div.btn-group", "ul.dropdown-menu>li>a" , "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
	}

	public void selectItemCustomDropDown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> listE = driver.findElements(By.cssSelector(childLocator));
		for(WebElement item : listE) {
			String actualTextItem = item.getText();
			if(actualTextItem.equals(expectedTextItem)) {
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
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
