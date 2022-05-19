package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		//Xpath có 2 LOẠI tương đối và tuyệt đối
		//Tuyệt đối: /html/body/div[1]/section[1]/div/div/div[2]/div/div/form/div[2]/input -> dài, dev thay đổi dẫn đên sai
		//Tương đối: //*[@id="email"] -> ngắn/dễ/hiểu/ có thay đôi vẫn ko bị ảnh hưởng
		/*
		 * 1 dau/ thi đi đến 1 thẻ (node)
		 * 2 dau // thì đi qua n thẻ (n node)
		 * 
		/*
		 * Tìm (findElement / findElements)
			Thao tác (Action): click/ type(sendkey)/ select/ hover..
			Lấy dữ liệu Kiểm tra (Verify/ Assert): getText/ get Attribute/ getCss
		*/
		/*
		 * Nếu dùng webElement 1 lần ( có 1 hành đông thì viết action luôn không cần khai bao biến)
		 * Nếu WebElement này sử dùng nhiều lần (có nhiều hành động ) thì dùng bieens)
		 */
		/*LOCAL : chính là máy người đang dùng, mỗi máy có đ/c IP private khác nhhau
		 * SƯ ƯU TIÊN
		 * driver.findElement(By.xpath("//input[@id='email']"));
		 * ưu tiên id/ class/ name trươc vì chạy nhanh hơn -> Brower hỗ trợ tốt cho id/ class/ name
		 * Gía trị nào vô nghĩa thì loại bỏ hoặc nhwungx giá trị bị random sau mỗi lần brower tải lại trang
		 * nếu mà không có id/ class/name thì dug data testid
		 *Atrribute là href không nên cho vào xpath vì 
		 */
		/*
		 * TRƯỜNG HỢP NGOẠI LỆ
		 */
		driver.findElement(By.id(""));
		
		driver.findElement(By.id("")).click();//Viết Action trực tiếp
		WebElement loginbutton = driver.findElement(By.id(""));
		//So nhieu
		driver.findElements(By.id(""));
		//Khai bao kieu du lieu List<WebElement>
		List<WebElement> logincheckboxes = driver.findElements(By.id(""));
//		for (int i = 0; i < logincheckboxes.size(); i++) {
//			logincheckboxes.get(i).click();
//			
//		}
		//Thao tác với Email textbox
		
		//Thao tác với Pass textbox
		
		//Thao tác với Login
	
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
