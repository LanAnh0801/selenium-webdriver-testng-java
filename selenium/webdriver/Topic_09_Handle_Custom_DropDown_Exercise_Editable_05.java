package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Custom_DropDown_Exercise_Editable_05 {
	WebDriver driver;
	WebDriverWait expliciWait;
	
	//Từ item 9 đên 19 thì k pass vì user chỉ nhìn thấy item từ 1 dến 8 
		//Muốn từ 9 trở lên thì phải dùng scroll kéo xuống -> dùng JavascriptExecutor
		//javascriptExecutor -> Chạy được câu lệnh Javascript trong code của mình
	JavascriptExecutor jsExecutor;
	
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		inputItemCustomDropDown("input.search", "div.selected.item>span" , "Afghanistan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		inputItemCustomDropDown("input.search", "div.selected.item>span" , "Benin");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");
	}

	public void selectItemCustomDropDown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> listE = driver.findElements(By.cssSelector(childLocator));
		for(WebElement item : listE) {
			String actualTextItem = item.getText();
			if(actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(false)", item);
				sleepInSecond(5);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
	}
	public void inputItemCustomDropDown(String editTableLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(editTableLocator)).sendKeys(expectedTextItem);;
		sleepInSecond(1);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> listE = driver.findElements(By.cssSelector(childLocator));
		for(WebElement item : listE) {
			String actualTextItem = item.getText();
			if(actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(false)", item);
				sleepInSecond(5);
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
