package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_04_Excecise {
	
	@Test
	public void TC_01_() {
		Scanner sc = new Scanner(System.in);
		int n;
		System.out.print("I : ");
		n = sc.nextInt();
		System.out.println("After 15 years I will " + (n+15) + " years");
	
	}
	@Test
	public void TC_02_() {
		Scanner sc = new Scanner(System.in);
		int a ;
		System.out.print("Nhap a = ");
		a = sc.nextInt();
		int b;
		System.out.print("Nhap b = ");
		b = sc.nextInt();
		System.out.println("After swapping then a = " + b + " b  = " + a);
		
		
	}
	@Test
	public void TC_03_() {
		Scanner sc = new Scanner(System.in);
		int a ;
		System.out.print("Nhap a = ");
		a = sc.nextInt();
		int b;
		System.out.print("Nhap b = ");
		b = sc.nextInt();
		if(a>b) {
			System.out.println("True");
		}
		else {
			System.out.println("False");
		}
		
		
	}


}
