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

public class Topic_09_Handle_Custom_Dropdown_Part_I_II {
	//Driver và jsExecutor là INTERFACE -> nên có thể dùng cách ép kiểu
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
		//Luôn khởi tạo sau driver -> nó cần giá trị driver để khởi tạo expliciWait lên.
		//Wait cho các element theo điều kiện có sẵn: visible (hiển thị)/ invisible/ presence/ clickable........
		expliciWait = new WebDriverWait(driver, 15);
		
		//Ep kiểu tường mình trong Java và đây là cách khởi tạo luôn
		jsExecutor = (JavascriptExecutor) driver;
		
		//Wait cho việc "tìm" element: findElement/ findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_JQuery_01() {
		
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		/*Chọn ITEM 10 */
		selectItemInCustomDropDown("span#number-button", "ul#number-menu>li>div", "11");
		//Verify cái đã chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "11");
		/*Chọn ITEM 5 */
//		selectItemInCustomDropDown("span#number-button", "ul#number-menu>li>div", "11");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "11");
		//Item đổ dữ liệu vào dropdown -> Verify thành công
		/*Chọn ITEM 19 */
		selectItemInCustomDropDown("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
		
		
		
		
		
		
		
		
		//CHỌN ITEM 10
		//STEP 01: Click vào Dropdown cho xổ hết tất cả các item con bên trong ra => CLICK
//		driver.findElement(By.cssSelector("span#number-button")).click();
		
		//STEP 02: Chờ cho tất cả các item con bên trong được load ra => Thư viện WEBDRIVERWAIT
		//By Locator = đại diện cho "tất cả các item con bên trong"
		//Lấy cái locator đến cái thẻ chứa text item
	//	expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
		/*
		 * Khai báo thư viện WEBDRIVERWAIT
		 * expliciWait khác hoàn toàn với impliciWait
		 */
		
		//STEP 03: Tìm item mong muốn (nếu như ko hiển thị thì cần cuộn chuột xuống để tìm) => VÒNG LẶP để lấy tất cả các 
		//item rồi duyệt qua
		//STEP 04: Thấy item cần chọn thì click vào => SO SÁNH VỚI ITEM mong muốn sau đó Click vào
	//	List<WebElement> allDropdownItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		//Khai báo từng element thì rất là nhiều => code nhiều => LẤY HẾT CÁC ELEMENT CHO VÀO 1 LIST WebDriver
		//Duyệt từng Item theo thủ công
//		allDropdownItems.get(0).getText();
//		allDropdownItems.get(1).getText();
//		allDropdownItems.get(2).getText();
//		allDropdownItems.get(3).getText();
//		allDropdownItems.get(4).getText();
		//Duyệt qua vòng lặp => ngắn gọn hơn
//		for(int i = 0; i < allDropdownItems.size(); i++) {
//			
//		}
//		for(WebElement item : allDropdownItems) {
//			String actualTextItem = item.getText();
//			if(actualTextItem.equals("5")) {
//				item.click();
//				//Chưa thoáy khỏi vòng lặp => các item sau vẫn duyệt = > cần lệnh BREAK
//				break;
//				//Nếu không có break thì item.getText = ""
//			}
//		}
		//Nếu chọn ITEM khác "5" thì viết lại như các bước trên => mất time => nên viết 1 hàm truyền tham số vào
		
		
		
		
		
		
		//STEP 05:  Item này sẽ đổ dữ liệu vào DropDown => VERIFY  chọn thành công
		
	
	}
	public void selectItemInCustomDropDown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(1);
		//Vẫn dùng sleepSecond cứng với time ngắn vì End User cần time để chọn item 
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		allDropdownItems.get(0).getText();
		allDropdownItems.get(1).getText();
		allDropdownItems.get(2).getText();
		allDropdownItems.get(3).getText();
		allDropdownItems.get(4).getText();
		for(WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			if(actualTextItem.equals(expectedTextItem)) {
				
				//arguments[0].scrollIntoView(true) -> Mép trên cùng của Element
				//arguments[0].scrollIntoView(false) -> Mép dưới của Element
				jsExecutor.executeScript("arguments[0].scrollIntoView(t)", item);
				sleepInSecond(12);
				item.click();
				sleepInSecond(1);
				break;
				
			}
		}
	}
	@Test
	public void TC_JQuery_02() {
		//Case ngoại lệ -> Đến bài Javascript 
	}
	@Test
	public void ReactJS_03() {
		
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
