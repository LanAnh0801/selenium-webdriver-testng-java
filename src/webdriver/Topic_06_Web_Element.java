package webdriver;

import java.awt.RenderingHints.Key;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Define_Element() {
		// Tìm element
		// Loại locator gì
		// Tương tác / kiếm tra

		// Muốn thao tác 1 lần thì ko dùng biến
		driver.findElement(By.id("send2")).click();
		// Muốn thao tác nhiều lần(2 lần trở lên) thì nên khai báo biến
//		driver.findElement(By.id("email")).clear();//xóa dữ liệu
//		driver.findElement(By.id("email")).sendKeys("afcauto@gmail.com");;
//		driver.findElement(By.id("email")).isDisplayed();
		// Build Framework -> build sao viêc tránh lặp
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("add");
		emailTextbox.isDisplayed();

	}

	@Test
	public void TC_02_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		// Xóa dữ liệu trong những field cho phép nhập
		// Luôn luôn clear hết dữ liệu trước khi thao tác lên field đó
		// Textbox/TextArea/Editable Dropdown
		element.clear();

		// Nhập dữ liệu trong field cho phép nhập
		element.sendKeys("");// 1 chuỗi
		element.sendKeys(Keys.ENTER);// 1 kí tự trên bàn phím rùi bấm ENTER

		// Tìm nhiều element cùng 1 lần (ko dùng)

		// **************GUI****************
		// GUI: Font Type/ Font Size/ Color/ Pixel
		element.getCssValue("background-color");
		// blue
		element.getCssValue("font-size");
		// 13
		// GUI: Position / Location/ Size of Element
		element.getLocation();
		element.getRect();
		element.getSize();

		// **************FRAMEWORK: Attach screenshot to Report HTML*************
		element.getScreenshotAs(OutputType.FILE);
		
		
		//muốn 1 Element hiển thị/ko hiển thị
		//Hiển thị: Người dùng nhìn thấy được/ có kích thước cụ thể (chiều rộng/ chiều cao)
		//Áp dụng cho tất cả các Element : TextBox/ TextArea/ Dropdown/ Checkbox/Radio/ Button
		element.isDisplayed();
		
		//Mong muốn 1 element có thể thao tác được lên hay ko
		//Ngược với display
		//Thao tác được : Enabled
		//Ko thao tác được: Disabled = Read Only
		//Áp dụng cho tất cả các Element : TextBox/ TextArea/ Dropdown/ Checkbox/Radio/ Button
		element.isEnabled();
		
		
		//Mong muốn 1 element đã được chọn hay chưa
		//Ap dụng cho 1 vài loại element : Checkbox/ Radio Button/ Dropdown (Default)
		element.isSelected();
		
		element.click();
		//Click lên Slider và giữ chuột
		//Sau đó kéo Slider này 1 tọa độ bao nhiêu pixel
		//Kéo tới 1 element khác
		actions.clickAndHold(element).moveToElement(element).perform();

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
