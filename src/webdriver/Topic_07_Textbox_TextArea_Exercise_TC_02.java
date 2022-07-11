package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Exercise_TC_02 {
	WebDriver driver;
	JavascriptExecutor jsEcExecutor;
	String employeeId, firstName, lastName;
	WebElement textboxFirstName,textboxLastName, textboxEmployeeId ;
	
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsEcExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		firstName = "Jonny";
		lastName = "Head";
		
		
	}

	@Test
	public void TC_01__Login() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
	}

	@Test
	public void TC_02_Save_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		employeeId =  driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
	
	}

	@Test
	public void TC_03_Verify_Data_Add_Employee() {
		
		textboxFirstName =  driver.findElement(By.id("personal_txtEmpFirstName"));
		textboxLastName = driver.findElement(By.id("personal_txtEmpLastName"));
		textboxEmployeeId = driver.findElement(By.id("personal_txtEmployeeId"));
		
		Assert.assertEquals(textboxFirstName.getAttribute("value"), firstName);
		Assert.assertEquals(textboxLastName.getAttribute("value"), lastName);
		Assert.assertEquals(textboxEmployeeId.getAttribute("value"), employeeId);
		
		Assert.assertFalse(textboxEmployeeId.isEnabled());
		Assert.assertFalse(textboxFirstName.isEnabled());
		Assert.assertFalse(textboxLastName.isEnabled());
		driver.findElement(By.id("btnSave")).click();
	
		
		
		
		
	}
	@Test
	public void TC_04_Verify_Data_Edit_Employee() {
		
		textboxFirstName.clear();
		textboxLastName.clear();
		textboxFirstName.sendKeys("Joj");
		textboxLastName.sendKeys("deep");
		Assert.assertTrue(textboxFirstName.isEnabled());
		Assert.assertTrue(textboxLastName.isEnabled());
		Assert.assertTrue(textboxEmployeeId.isEnabled());
		driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), "Joj");
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), "deep");
		
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		
		
		
	}
	@AfterClass
	public void afterClass() {
		// driver.quit();
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

	// Tạo hàm Random
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);

	}

}
