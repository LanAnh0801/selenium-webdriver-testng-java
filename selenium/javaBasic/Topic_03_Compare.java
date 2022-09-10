package javaBasic;

public class Topic_03_Compare {
	int number = 11;
	public static void main(String[] args) {
		int age = 10;
		int ages = age;
		System.out.println(age);
		System.out.println(ages);
		ages = 20;
		System.out.println(age);
		System.out.println(ages);
		Topic_03_Compare topic = new Topic_03_Compare();
		Topic_03_Compare topic_01 = topic;
		System.out.println(topic.number);
		System.out.println(topic_01.number);
		topic_01.number = 15;
		System.out.println(topic.number);
		System.out.println(topic_01.number);
	}
}
