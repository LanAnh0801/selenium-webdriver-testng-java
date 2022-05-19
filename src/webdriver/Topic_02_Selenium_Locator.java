package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;//tương tác với element trên brower
	String projectPath = System.getProperty("user.dir");

	@BeforeClass//annotations : chú thích giống chỉ đường để TestRuner hiểu 
	public void beforeClass() {
		/*
		 * driver đại diện cho thư viện của Selenium
		 * Tìm element để tương tác lên 
		 */
		
		//Set gecko.driver tương tác giữa brower và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//Khởi tạo trình duyệt firefox
		
		driver = new FirefoxDriver();
		//Set thời gian đi tìm  Element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Set brower to lên
		driver.manage().window().maximize();
		//Mở application lên
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		//id -> class -> name -> css -> xpath
		// cssx path kết hợp với id, class, name chạy nhanh hơn các locatior khác
		//Selenium có 8 loại locator
		/*
		 * <input type="text" class="inputtext _55r1 _6luy" name="email" id="email" 
		 * data-testid="royal_email" placeholder="Email address or phone number" autofocus="1" aria-label="Email address or phone number">
		 */
		//ID
		driver.findElement(By.id("email")).sendKeys("doanthilananh001@gmail.com");;
		//Class
		/*
		 * <img class="fb_logo _8ilh img" src="https://static.xx.fbcdn.net/rsrc.php/y8/r/dF5SId3UHWd.svg" alt="Facebook">
		 */
		driver.findElement(By.className("fb_logo"));//value class ghi đại diện
		//Name
		driver.findElement(By.name("email"));
		//TagName
		driver.findElement(By.tagName("img"));//element số it là ra cái tagname đầu tiên
		
		//Link Text : Truyền tất cả Text
		driver.findElement(By.linkText("Tiếng Việt"));
		
		//Partial Link Text" Truyền 1 phần text được nhưng ko chính xác 100%
		//driver.findElement(By.partialLinkText("English"));
		//driver.findElement(By.partialLinkText("UK"));
		//CSS
		/*/
		 * với id c1 dùng thẳng cụm từ input[id = 'value'];
		 * 		  c2 dùng #value như trong CSS HTML
		 *        c3 dùng input#value
		 */
		driver.findElement(By.cssSelector("input[id = email]"));
		driver.findElement(By.cssSelector("#email"));
		driver.findElement(By.cssSelector("input#email"));
		/*
		 * với classname c1 dùng thẳng cụm từ img[class = 'fb_logo _8ilh img'] với value ghi đầy đủ
		 * 				 c2 dùng .value (đại diện)
		 * 				 c3 dùng img.vlaue
		 */					
		driver.findElement(By.cssSelector("img[class = 'fb_logo _8ilh img']"));
		driver.findElement(By.cssSelector(".fb_logo"));
		driver.findElement(By.cssSelector("img.fb_logo"));

		/*
		 * với name như c1 của id thay id = name
		 */
		driver.findElement(By.cssSelector("input[name='email']"));
		/*
		 * với tagname truyền thẳng tagname vào 
		 * 
		 */
		driver.findElement(By.cssSelector("a"));
		/*
		 * với LinkText không làm việc trực tiếp với Text như "English (UK)" ở trên mà dùng thuộc tính khác vi
		 * 		VD: title, onclick
		 * dùng *= bao gồm
		 * 
		 */
		driver.findElement(By.cssSelector("a[title = 'Vietnamese']"));
//		driver.findElement(By.cssSelector("a[title *= 'English']"));
//		driver.findElement(By.cssSelector("a[onclick*= 'vi_VN']"));
		
		//Xpath
		/*
		 * nhớ phải có @
		 */
		//driver.findElement(By.xpath("//input[@id='email']"));
		//Lưu ý với classname phải viết đầy đủ không được đại diện
		//driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
		//Lưu ý: [] bên ngoài tất cả,contains dùng dấu , not dấu = 
//		driver.findElement(By.xpath("//img [contains (@class, 'fb_logo')]"));
//		driver.findElement(By.xpath("//img [starts-with (@class, 'fb_logo')]"));
//		driver.findElement(By.xpath("//input[@name='email']"));
//		driver.findElement(By.xpath("//a"));
//		driver.findElement(By.xpath("//a[text()= 'English (UK)'] "));
//		driver.findElement(By.xpath("//a[contains (text(), 'English'] "));
	
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
