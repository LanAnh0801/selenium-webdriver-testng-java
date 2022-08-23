package webdriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_File_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String fireFoxSinglePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String chromeSinglePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String fireFoxMultiplePath = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String chromeMultiplePath = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";
	//Image name
	String imagea = "a.jpg";
	String imageb = "b.jpg";
	String imagec = "c.jpg";
	
	String upLoadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	//Image Path
	
	String aPath = upLoadFilePath + imagea;
	String bPath = upLoadFilePath + imageb;
	String cPath = upLoadFilePath + imagec;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Khai báo sau driver vì lấy giá trị của driver để khởi tao
		// EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);

		// implicitlyWait apply cho việc tìm findElement và findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Upload_One_File_Per_Time_Sendkeys() {// Upload 1 lần 1 File sd SendKeys
		//Step 01: Truy cập vào trang
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Step 02: Sử dụng sendkey để load ảnh lên
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(aPath);
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(bPath);
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(cPath);
		//Step 03: Verify ảnh load lên thành công (tên ảnh hiện thị)
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagea+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imageb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagec+"']")).isDisplayed());
		//Step 04: Click vào button để upload ảnh lên
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement item : buttonStart) {
			item.click();
			sleepInSecond(2);
		}
		//Step 05: Verify upload lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagea+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imageb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagec+"']")).isDisplayed());
		
	}

//	@Test
	public void TC_02_Up_Multiple_File_Per_Time_Sendkeys() {//Upload 1 lần nhiều file sd SendKeys
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Load 1 lúc 3 ảnh lên
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(aPath +"\n"+bPath+"\n" + cPath);
		//Verify ảnh được load lên
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagea+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imageb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagec+"']")).isDisplayed());
		//Click upload
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement item : buttonStart) {
			item.click();
			sleepInSecond(3);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagea+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imageb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagec+"']")).isDisplayed());
		
	}

	@Test
	public void TC_03_Upload_One_File_Per_Time_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { fireFoxSinglePath, aPath});
		}
		else {
			Runtime.getRuntime().exec(new String[] { chromeSinglePath, aPath});
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagea+"']")).isDisplayed());
		//Click 
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement item : buttonStart) {
			item.click();
			sleepInSecond(3);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagea+"']")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

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

}
