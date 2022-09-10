package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Explicit_Wait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	//Thư viện Actions như thư viện Select
	Actions actions;
	//Image Name 
	String imagea = "a.jpg";
	String imageb = "b.jpg";
	String imagec = "c.jpg";
	String upLoadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	//Image Xpath
	String aPath = upLoadFilePath + imagea;
	String bPath = upLoadFilePath + imageb;
	String cPath = upLoadFilePath + imagec;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		
		jsExecutor = (JavascriptExecutor) driver;
		//Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		//implicitlyWait apply cho việc tìm findElement và findElements 
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Thiếu time
		expliciWait = new WebDriverWait(driver, 5);
		
		
		//Click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();
		//C2 ExplicitWait để element trưoc xuất hiện 
			//Wait cho icon loading biến mất
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	
	}
	//@Test
	public void TC_01_visible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Thiếu time
		expliciWait = new WebDriverWait(driver, 5);
		
		
		//Click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();
		//C1 ExplicitWait để element sau xuất hiện
		//expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	
	}

	//@Test
	public void TC_03_ExplicitWait_Ajax_Loading() {
		//Step 01: TRuy cập trang
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		jsExecutor.executeScript("window.scrollBy(0,300)");
		expliciWait = new WebDriverWait(driver, 15);
		
		//Step 02: Wait cho Date Time được hiển thị (sử dụng visibility)
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		//Step 03: In ra ngày đã chọn (Before Ajax call) - > hiện chưa chọn nên chưa in ra = No Selected Dates to display
		Assert.assertEquals(driver.findElement(By.cssSelector("div.RadAjaxPanel>span")).getText(), "No Selected Dates to display.");
		//Step 04: Chọn ngày hiện tại 
			//Wait cho ngày 8 được phép click 
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = '8']")));
			//Click vào ngày 8
		driver.findElement(By.xpath("//a[text() = '8']")).click();
		//Step 05: Wait cho đến khi Ajax loading icon không còn visible (sử dụng invisibility)
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*= 'RadCalendar1']>div.raDiv")));
		//Step 06: Wait cho selected date = 22 được selected (sử dụng visibility)
			//Wait cho ngày được chọn Click trở lại được
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class= 'rcSelected']/a[text() = '8']")));
		//expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.rcSelected")));
		//Step 07: Verify ngày đã chọn bằng = "............."
		Assert.assertEquals(driver.findElement(By.cssSelector("div.RadAjaxPanel>span")).getText(), "Monday, August 8, 2022");
	}
	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/?t=uploadFiles");
		expliciWait = new WebDriverWait(driver, 45);
		
		//Wait cho Add File được xuất hiện
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button")));
		
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(aPath + "\n" + bPath );
		
		//Wait cho từng icon loading biến mất
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		//Wait cho Uplaod message thành công được visible
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text() = 'Your files have been successfully uploaded']")));
		
		jsExecutor.executeScript("window.scrollBy(0,200)");
		//Verify message được hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text() = 'Your files have been successfully uploaded']")).isDisplayed());
		
		//Wait cho show File được Clickable
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		//Click button Show File
		
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		//Ko dung ImplicitWait thì phải dùng ExplicitWait
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '"+imagea+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+imagea+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")).isDisplayed());
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '"+imageb+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+imageb+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")).isDisplayed());
//		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '"+imagec+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")));
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+imagec+"' ]/parent::a/parent::div/following-sibling::div//button[@id = 'contentId-download']")).isDisplayed());
//		
		
		
		
		
		
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
