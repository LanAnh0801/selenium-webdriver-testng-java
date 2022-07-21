package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Actions_Part_II {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions actions;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_Click_And_Hold_Element_Block() {//Click và giữ chuột
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Step 02: Click and hold từ 1 -> 4
			//Store all 12 items // findElements số nhiều
		List<WebElement> allNumbers =  driver.findElements(By.cssSelector("ol#selectable>li"));
		actions.clickAndHold(allNumbers.get(0))//click và giữ chuột tại vị trí số 1
		.moveToElement(allNumbers.get(10))//Hover chuột tới sô 11 
		.release()//Nhả chuột trái ra
		.perform();//Thực thi các actions trên
		//Step 03: Sau khi chọn - kiểm tra có đúng 4 phần tử đã được chọn thành công
			//Thay đổi xpath vì trên kia là 12 item chưa chọn nhưng sau khi thì có 9 
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 9);//Verify 9 cái đã chọn
	}


	public void TC_02_Click_And_Hold_Element_Random() {
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Step 02: Click and hold từ 1, 3, 6, 11
		List<WebElement> allNumbers =  driver.findElements(By.cssSelector("ol#selectable>li"));
		//Nhấn Phím Control xuống
		actions.keyDown(Keys.CONTROL).perform();
		actions.click(allNumbers.get(0))
		.click(allNumbers.get(2))
		.click(allNumbers.get(5))
		.click(allNumbers.get(10)).perform();
		//Nhả phím Control ra
		actions.keyUp(Keys.CONTROL).perform();
		//Step 03: Sau khi chọn - kiểm tra có đúng 4 phần tử đã được chọn thành công
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 4);
	}


	public void TC_03_Double_Click() {
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Step 02: Double click vào button: Double click me
			//	Scroll to Element
		WebElement buttonDoubleClick = driver.findElement(By.xpath("//button[text() = 'Double click me']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",buttonDoubleClick );
		actions.doubleClick(buttonDoubleClick).perform();
		sleepInSecond(5);
		//Step 03: Verify text được hiển thị bên dưới: Hello Automation test
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
	}
	
	public void TC_04_Right_Click_To_Element() {//Click vào chuột phải
		//Step 01: Truy cập vào trang
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		//Step 02: Right click vào Element: Right click me
		//Step 03: Hover chuột vào Element: Quit
		//Step 04: Kiểm tra Quit menu hiển thị
		//Step 05: Verify element Quit (visible + hover) với xpath
			//Đây là giá trị trong attribute class - ko phải đang nói đến visible của Selenium
			//Gía trị visible  + hover chỉ được hiển thị khi mình hover chuột vào
		//Step 06: Click chọn Quit
		//Step 07: Kiểm tra Quit menu không hiển thị
		WebElement buttonRightClick = driver.findElement(By.xpath("//span[text() = 'right click me']"));
		actions.contextClick(buttonRightClick).perform();//Click chuột phải
		sleepInSecond(5);
		WebElement quitBefore = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
		actions.moveToElement(quitBefore).perform();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-visible")).isDisplayed());
		actions.click(quitBefore).perform();
		sleepInSecond(3);
		alert  =  driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		Assert.assertFalse(quitBefore.isDisplayed());
		
	}
	@Test
	public void TC_05_Drag_And_Drop_Element_HTML4() {//Kéo thả chuột
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		//Step 02: Kéo thả hình tròn nhỏ vào hình tròn lớn
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		actions.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(5);
		//Step 03: Verify message đã thay đổi: Yoi did greate!
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		//Step 04: Verify background color của hình tròn lớn chuyển qua màu xanh da trời

		String circleTargetBackgroundColorHecxa = Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(circleTargetBackgroundColorHecxa, "#03A9F4");
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
