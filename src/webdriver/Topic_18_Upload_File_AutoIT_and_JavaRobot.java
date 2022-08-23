package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_File_AutoIT_and_JavaRobot {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String fireFoxSinglePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String chromeSinglePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String fireFoxMultiplePath = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String chromeMultiplePath = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";
	//Thư viện Actions như thư viện Select
	Actions actions;
	//Image Name
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
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		//Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		//implicitlyWait apply cho việc tìm findElement và findElements 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Upload_One_File_Per_Time_Auto_IT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		//Upload File bằng AutoIT
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { fireFoxSinglePath, aPath});
		}
		else {
			Runtime.getRuntime().exec(new String[] { chromeSinglePath, aPath});
		}
		//Verify thành công
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

	@Test
	public void TC_02_pload_One_File_Per_Time_JavaRobot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Cách thức
				//Gỉa lập hành vi COPY của 1 file -> Java Support
				//Gỉa lập hành vi PASTE và ENTER vào Open File Dialog  -> Java Support
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		loadFileByRobot(aPath);
		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+imagea+"']")).isDisplayed());
//		//Click 
//		
//		List<WebElement> buttonStart = driver.findElements(By.cssSelector("table button.start"));
//		for (WebElement item : buttonStart) {
//			item.click();
//			sleepInSecond(3);
//		}
//		//Verify upload thành công
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '"+imagea+"']")).isDisplayed());
		
	}

	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}
	public void loadFileByRobot(String filePath) throws AWTException {
		StringSelection select = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		//Load File
		Robot robot = new Robot();
		sleepInSecond(1);
		
		//Nhấn phim Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		//Nhả Ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);
		//Nhấn phím ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);
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
