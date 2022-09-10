package javaBasic;

public class Topic_04_Operator {
	public static void main(String[] args) {
		int number = 19;
		int x = number++ + 10;
		System.out.println(x);
		//In number ra trước : 19
		//rùi cộng sau ++ = 20
		System.out.println(++number);
		//++ trước và in luôn: 21
		
		for(int i =0; i<=4; i++) {
			System.out.println(i);
		}
	}

}
