package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Topic_05_Param {
	
	
	public void clickElement() {

	}

	// Hàm có 1 tham số
	public void clickElement(String elementName) {

	}
	//Hàm có 2 tham số
	public void clickElement(String elementName, String locatorName) {

	}
	@Test
	public void TC_01_Login() {
		clickElement("Label");
	}

}
