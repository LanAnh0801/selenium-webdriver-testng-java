package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	@Test
	public void TC_01() {
		//Thư viện Assert : kiểm tra tính đúng đắn của dữ liệu
		
		String add = "Ha Noi";
		//Kiểm tra 1 điều kiện mong đợi là đúng
		Assert.assertTrue(add.equals("Ha Noi"));
		//Kiểm tra 1 điều kiện mong đợi là sai
		Assert.assertFalse(add.equals("Minh"));
		//Kiểm tra đầu ra 
		
	}

}
