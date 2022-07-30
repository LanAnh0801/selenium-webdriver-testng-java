package webdriver;

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

public class Topic_17_JavaScript_Executor_Exercise {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	//Thư viện Actions như thư viện Select
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Khai báo sau driver vì lấy giá trị của driver để khởi tao
		//EXPLIVIWAIT ĐỂ wait các Element theo điều kiện có sẵn
		expliciWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;//Ep kiểu
		//Khai báo trong Before class vì nó khởi tạo cùng lúc với Element
		actions = new Actions(driver);
		//implicitlyWait apply cho việc tìm findElement và findElements 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_JavaScript_Executor() {//Chạy nhanh nên dễ bị Fail- > Có sleep
		//Step 01: Truy cập vào trang
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		//Step 02: Sử dụng JE để get domain của page
		String techPandaDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");
		//Step 03: Sử dụng JE để get URL của page
		String homePage = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePage, "http://live.techpanda.org/");
		//Step 04: Open MOBILE page (JE)
		hightlightElement("//a[text() = 'Mobile']");
		clickToElementByJS("//a[text() = 'Mobile']");
		sleepInSecond(5);
		
		//Step 05: Add sản phẩm SAM SUM GALAXY vào Cart (Click = JE)
		hightlightElement("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class = 'actions']/button");
		clickToElementByJS("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class = 'actions']/button");
		sleepInSecond(5);//Step 06: Verify message được hiển thị (JE - Get inner text)
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		//Step 07: Open Customer Service Page
		hightlightElement("//a[text() ='Customer Service']");
		clickToElementByJS("//a[text() ='Customer Service']");
		sleepInSecond(5);
		
		//Step 08: Scroll tới element Newsletter textbox ở cuối Page 
		scrollToElementOnTop("//input[@id = 'newsletter']");
		//Step 09: Input email hợp lệ vào Newsletter textbox
		String emailTextbox = "afc" +  generateRandomNumber() + "@hotmail.com";
		sendkeyToElementByJS("//input[@id = 'newsletter']", emailTextbox);
		sleepInSecond(3);
 		//Step 10: Click vào Subscribe button
		clickToElementByJS("//button[@title = 'Subscribe']");
		sleepInSecond(5);
		//Step 11: Verify text có hien thị
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		sleepInSecond(3);
		//Step 12: Navigate tới domain demo.gruu99.com
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		String demoGruu99  = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGruu99, "demo.guru99.com");
	
	}

	//@Test
	public void TC_02_Verify_HTML5_Validation_Message() {
		//Step 01: Truy cập vào trang
		driver.get("https://automationfc.github.io/html5/index.html");
		sleepInSecond(5);
		//Step 02: Click Submit và verify message hiển thị tại Field Name Textbox
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'fname']"), "Please fill out this field.");
		sleepInSecond(3);
		//Step 03: Input dữ liệu vào Field Name Textbox và Click Submit -> Verify message hiển thị tại Password Textbox
		sendkeyToElementByJS("//input[@id = 'fname']", "JungKookie");
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'pass']"), "Please fill out this field.");
		sleepInSecond(3);
		//Step 04: Input dữ liệu vào Field Password và Click Submit -> Verify message hiển thị tại Email Textbox
		sendkeyToElementByJS("//input[@id = 'pass']", "123456");
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//input[@id= 'em']"), "Please fill out this field.");
		sleepInSecond(3);
		//Step 05: Input dữ liệu ko hợp lệ vào field Email và Click vào Submit -> Verify message hiển thị tại Email Textbox
		sendkeyToElementByJS("//input[@id= 'em']", "123!@#$");
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//input[@id= 'em']"), "Please enter an email address.");
		sleepInSecond(3);
		//Step 06: Input dữ liệu ko hợp lệ vào field Email và Click vào Submit -> Verify message hiển thị tại Email Textbox
		sendkeyToElementByJS("//input[@id= 'em']", "123@456");
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//input[@id= 'em']"), "Please match the requested format.");
		sleepInSecond(3);
		//Step 07: Input dư liệu hợp lệ vào field Email và Click Submit -> Verify message hiển thị tại Address Textbox
		sendkeyToElementByJS("//input[@id= 'em']", "dao@gmail.com");
		clickToElementByJS("//input[@class = 'btn']");
		sleepInSecond(5);
		Assert.assertEquals(getElementValidationMessage("//b[text() = '✱ ADDRESS ']/parent::label/following-sibling::select"), "Please select an item in the list.");
		
	}

	//@Test
	public void TC_03__Verify_HTML5_Validation_Message_Page_Sieuthimaymoc() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		//LastName TextBox
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'lastName']"), "Please fill out this field.");
		//Input dữ liệu vào LastName TextBox và Verify 
		driver.findElement(By.xpath("//input[@id = 'lastName']")).sendKeys("DAM");
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'firstName']"), "Please fill out this field.");
		//Input dữ liệu vào FirstName TextBox và Verify
		driver.findElement(By.xpath("//input[@id = 'firstName']")).sendKeys("Huong");
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'email']"), "Please fill out this field.");
		//Input dữ liệu ko hợp lệ
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("123@#%$");
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'email']"), "Please enter an email address.");
		//Input dữ liệu ko hợp lệ
		driver.findElement(By.xpath("//input[@id = 'email']")).clear();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("123@456");
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'email']"), "Please match the requested format.");
		//Input dư liệu hợp lệ vào Email
		driver.findElement(By.xpath("//input[@id = 'email']")).clear();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("dam@gmail.com");
		driver.findElement(By.xpath("//button[@value = 'Đăng ký']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'password']"), "Please fill out this field.");
		
	}
	
	//@Test
	public void TC_03_Verify_HTML5_Validation_Message_Page_Warrannyty_Rode() {
		driver.get("https://warranty.rode.com/");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'firstname']"), "Please fill out this field.");
		//Input dữ liệu vào First Name
		driver.findElement(By.xpath("//input[@id = 'firstname']")).sendKeys("Jung");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'surname']"), "Please fill out this field.");
		//Input dữ liệu vào surname
		driver.findElement(By.xpath("//input[@id = 'surname']")).sendKeys("Kookie");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'email']"), "Please fill out this field.");
		//Input ko hợp lệ dữ liệu vào Email
		driver.findElement(By.xpath("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='email']")).sendKeys("123$#!%");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		//Css của textEmail  = form[action*= 'register'] input#email
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='email']"), "Please enter an email address.");
		//Input hợp lệ Email
		driver.findElement(By.xpath("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='email']")).sendKeys("lan@gmail.com");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='password']"), "Please fill out this field.");
		//Input dữ liệu vào Password
		driver.findElement(By.xpath("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[contains (text() , 'Register')]")).click();
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]//following-sibling::div//input[@id='password-confirm']"), "Please fill out this field.");
	}
	@Test
	public void TC_04_() {
		//Step 01: Truy cập vào trang
		driver.get("http://demo.guru99.com/v4");
		//Step 02: Đăng nhập với thông tin User =  	mngr428807  và Pass = ezEhUha 
		driver.findElement(By.xpath("//input[@name = 'uid']")).sendKeys("mngr428807");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("ezEhUha");
		driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();
		//Step 03: Chọn menu New Customer
		driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
		//Step 04: Nhập toàn bộ dữ liệu đung  -> Click Submit
			//Remove attribute type = date của field Date of Birth 
		driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys("JungK");
		driver.findElement(By.xpath("//input[@value = 'm']")).click();
		removeAttributeInDOM("//input[@id = 'dob']", "type");
		sleepInSecond(5);
		driver.findElement(By.xpath("//input[@id = 'dob']")).sendKeys("09/12/2001");
		driver.findElement(By.xpath("//textarea[@name= 'addr']")).sendKeys("Korea");
		driver.findElement(By.xpath("//input[@name= 'city']")).sendKeys("Bussan");
		driver.findElement(By.xpath("//input[@name= 'state']")).sendKeys("Bussan");
		driver.findElement(By.xpath("//input[@name= 'pinno']")).sendKeys("0123445");
		driver.findElement(By.xpath("//input[@name= 'telephoneno']")).sendKeys("098765432");
		String emailTextBox = "afc" + generateRandomNumber() + "ho@gmail.com";
		driver.findElement(By.xpath("//input[@name= 'emailid']")).sendKeys(emailTextBox);
		driver.findElement(By.xpath("//input[@name = 'password' ]")).sendKeys("123456");
		//Step 05: Verify tạo mới Customer thành công
		driver.findElement(By.xpath("//input[@name = 'sub']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class = 'heading3']")).getText(), "Customer Registered Successfully!!!");
		
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	//Kiểm tra 1 text có khớp với cái mình tuyền vào Hay Không
	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
