package javaBasic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Topic_02_Data_Type {
	// trong Stack lưu trữ biến và Primitive
	
	//Primitive Type/ Value Type : Nguyên thủy
	//Ko có function đi theo
	byte bNumber;
	short sNumber;
	long lNumber;
	float fNumber;
	double dNumber;
	char cNumber;
	boolean bStatus;
	
	
	
	//trong Heap lưu trữ Reference và giá trị của biến
	//Reference Type: Tham chiếu
	//Có function đi theo
	
	/*Array
	 * Class/Interface
	 * Collection: List(ArrayList/ LinkedList)/ Set/ Queue
	 * Object
	 * String
	 */
	String address = "Ha Noi";
	String [] studentInfo = {address, "Doan Lan Anh"};
	Integer[] studentNumber = {1, 5, 6};
	Topic_02_Data_Type topic;
	Object students;
	By emailText = By.cssSelector("");
	WebDriver webDriver = new ChromeDriver();
	WebElement element = webDriver.findElement(By.cssSelector(""));
	List<WebElement> homeLinks = webDriver.findElements(By.id("email"));
	public static void main(String[] args) {
		
	}

}
