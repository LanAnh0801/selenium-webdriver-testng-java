package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Display() {
		// Disabled = Read Only
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age Under 18 Radio is displayed");
		} else {
			System.out.println("Age Under 18 Radio is not displayed");
		}
		// Field Education
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Testing");
			System.out.println("Education TextArea is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}
		// Name : User 5
		WebElement name5Text = driver.findElement(By.xpath("//h5[text() = 'Name: User5']"));
		if (name5Text.isDisplayed()) {

			System.out.println("Name Text is displayed");
		} else {
			System.out.println("Name Text is not displayed");
		}

	}

	//@Test
	public void TC_02_Enabled() {
		//*********Enabled***********
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		// Field Education
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		if (educationTextArea.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		// Job 01
		WebElement selectJob01 = driver.findElement(By.id("job1"));
		if (selectJob01.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		// Job 02
		WebElement selectJob02 = driver.findElement(By.id("job2"));
		if (selectJob02.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		WebElement check_Developmennt = driver.findElement(By.id("development"));
		if (check_Developmennt.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		WebElement slider_01 = driver.findElement(By.id("slider-1"));
		if (slider_01.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//**********DISABLED************
		//Password
		WebElement passwordText = driver.findElement(By.id("disable_password"));
		if (passwordText.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//Age (Radio button is disabled)
		WebElement ageRadioButton = driver.findElement(By.id("radio-disabled"));
		if (ageRadioButton.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//Biography
		WebElement bioTextArea = driver.findElement(By.id("bio"));
		if (bioTextArea.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//Job Role 3
		WebElement job03DropDown = driver.findElement(By.id("job3"));
		if (job03DropDown.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//Inserts (Checkbox is disabled)
		WebElement insertCheck = driver.findElement(By.id("check-disbaled"));
		if (insertCheck.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		//Slider 02
		WebElement slider02 = driver.findElement(By.id("slider-2"));
		if (slider02.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is not enabled");
		}
		

	}

	//@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		WebElement javaCheck = driver.findElement(By.id("java"));
		ageUnder18.click();
		javaCheck.click();
		Assert.assertTrue(ageUnder18.isSelected());
		Assert.assertTrue(javaCheck.isSelected());
		ageUnder18.click();
		javaCheck.click();
		Assert.assertTrue(ageUnder18.isSelected());//click tiếp vẫn chọn => true
		Assert.assertFalse(javaCheck.isSelected());
	}

	@Test
	public void TC_04_Mail_Chimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("afc2345@gmail.com");
		driver.findElement(By.cssSelector("input#new_username")).sendKeys("automation");
		WebElement passwordTextbox =  driver.findElement(By.id("new_password"));
		WebElement buttonCreateAcc =  driver.findElement(By.id("create-account"));
		
		//lowercase 
		passwordTextbox.sendKeys("auto");
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char completed' and text() = 'One lowercase character']")).isDisplayed());
		Assert.assertFalse(buttonCreateAcc.isEnabled());
		//uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char completed' and text() = 'One uppercase character']")).isDisplayed());
		Assert.assertFalse(buttonCreateAcc.isEnabled());
		//number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("123");
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")).isDisplayed());
		Assert.assertFalse(buttonCreateAcc.isEnabled());
		//special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#");
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char completed' and text() = 'One special character']")).isDisplayed());
		Assert.assertFalse(buttonCreateAcc.isEnabled());
		//8 characters minimum
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char completed' and text() = '8 characters minimum']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")).isDisplayed());
		Assert.assertFalse(buttonCreateAcc.isEnabled());
		//combine
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Boot123!");
		
		sleepInSecond(1);//điền thông tin xong rồi hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text() = \"Your password is secure and you're all set!\"]")).isDisplayed());
		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
		Assert.assertTrue(buttonCreateAcc.isEnabled());
		

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
