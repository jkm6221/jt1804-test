package people.boss;

import java.util.Scanner;

import people.worker.People;

public class Boss2 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("qingshu yi ge lie ming");
		String s = new Scanner(System.in).nextLine();
		Class<?> c = Class.forName(s);
		//shu chu baomin 
		System.out.println(c.getPackage().getName());
		//shuchu baoming he lei ming
		System.out.println(c.getName());
		//shu chu lei ming 
		System.out.println(c.getSimpleName());
		People people = (People)c.newInstance();
		
	}
}
