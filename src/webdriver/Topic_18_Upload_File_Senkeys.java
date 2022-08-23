package webdriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_File_Senkeys {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	// Image Name
	String anha = "a.jpg";
	String anhb = "b.jpg";
	String anhc = "c.jpg";
	// UploadFileFolder -> Mục tiêu : tùy hđh để / \ hay phải
	String upLoadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	// Image Path
	String aPath = upLoadFileFolderPath + anha;
	String bPath = upLoadFileFolderPath + anhb;
	String cPath = upLoadFileFolderPath + anhc;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Khai báo sau driver vì lấy giá trị của driver để khởi tao
		// EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);

		// implicitlyWait apply cho việc tìm findElement và findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Upload_One_File_Per_Time() {// Upload 1 lần 1 File
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load File lên (Brower file)
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(aPath);
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(bPath);
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(cPath);
		// Verify Image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anha+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anhb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anhc+"']")).isDisplayed());
		// Chưa thực hiện Upload
		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement item : startButton) {
			item.click();
			sleepInSecond(2);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anha+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anhb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anhc+"']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load File lên (Brower file)
		driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(aPath +"\n" + bPath  +"\n" + cPath);
		
		// Verify Image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anha+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anhb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+anhc+"']")).isDisplayed());
		// Chưa thực hiện Upload
		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement item : startButton) {
			item.click();
			sleepInSecond(2);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anha+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anhb+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='"+anhc+"']")).isDisplayed());
		

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
