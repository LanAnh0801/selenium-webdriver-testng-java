package javaTester;

import org.testng.annotations.Test;

public class Topic_05_Param {
	//Parameter: Tham số
	// Hàm không có tham số
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
