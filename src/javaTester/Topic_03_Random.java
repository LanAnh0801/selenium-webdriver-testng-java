package javaTester;

import java.util.Random;

public class Topic_03_Random {
	public static void main(String[] args) {
		Random random = new Random();
		//từ 1 đến 100 viết trong ngoặc là 99 vì bắt đầu từ 0 -> 99
		System.out.println(random.nextInt(99));
		System.out.println(random.nextInt(99));
		System.out.println(random.nextInt(99));
		System.out.println(random.nextInt(99));
		System.out.println(random.nextInt(99));
	}

}
