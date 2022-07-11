package TestNG;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	public void Test_01() {
		WebElement element = driver.findElement(By.id(""));
		//3 hàm Assert hay dùng
		//Mình action mà không Verify thì đâu biết đúng hay sai
		//Kiểm tra tính đúng đắn của dữ liệu
				
		// 1 - Kiêm tra dữ liệu mình mong muốn là ĐÚNG
		//Email textbox hiển thị
		Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
		
		// 2 - Kiêm tra dữ liệu mình mong muốn là SAI
		//Email textbox ko  hiển thị
		Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());
		
		// 3 - Kiêm tra dữ liệu mình mong muốn với dữ liệu thực tế là BẰNG NHAU
		//Tuyệt đối 2 cái bằng nhau
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		//Tương đối
		String benefitsText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefitsText.contains("Faster checkout"));
		Assert.assertTrue(benefitsText.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benefitsText.contains("View and track orders and more"));
		
		
		
		
		
		
		
		
		
		
		
	}

}
