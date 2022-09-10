package tech;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {
	
	
	@Test(priority = 1)
	public void Order_01_View_Product() {
		
	}
	@Test
	public void Order_02_Add_to_cart() {
		
	}
	@Test(description = "User lựa chọn sp và lc hình thức thanh toán")
	public void Order_03_Add_Payment_method() {
		
	}
	@Test(enabled = false)//bỏ testcase
	public void Order_04_Checkout() {
		
	}

}
