package javaBasic;

import org.testng.annotations.Test;

public class Topic_02_Excecise {
	@Test
	public void TC_01_() {
		int a = 6;
		int b = 2;
		System.out.println("Tổng của a và b là: " + (a + b));
		System.out.println("Hiệu của a và b là: " + (a - b));
		System.out.println("Tích của a và b là: " + (a * b));
		System.out.println("Thương của a và b là: " + (a / b));
	
	}
	@Test
	public void TC_02_() {
		double x =  7.5;
		double y =  3.8;
		System.out.println("Area của hcn là: " + (x*y));
	
	
	}
	@Test
	public void TC_03_() {
		
		String name = "Automation Testing";
		System.out.println("Hello " + name);
	
	
	}

}
