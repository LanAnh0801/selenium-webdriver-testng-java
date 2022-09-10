package javaBasic;

import org.testng.annotations.Test;

public class Topic_01_Variable {
	//Biến toàn cục_Global/Instance
	//Không truy cập trong static method
	//Truy cập từ class bên ngoài theo c/p: tendoituong_tenbien
	//Co giá trị mặc định
	String studentName = "Doan Lan Anh";
	//Biến tĩnh_Static
	//Là biến class
	//Truy cập thông qua class
	static int studentNumber;
	
	
	public static void main(String[] args) {
		
		//Biến cục bộ___LOCAL
		//Không có giá trị mặc đinh => Phải gán giá trị
		int price = 5;
		System.out.println(price);
		Topic_01_Variable topic_01 =  new Topic_01_Variable();
		System.out.println(topic_01.studentName);
		
		System.out.println(Topic_01_Variable.studentNumber);
	}
	
	
}
