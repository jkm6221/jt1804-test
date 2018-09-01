package people.boss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import people.worker.People;

public class bigBoss {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("conf.miss"));
		String s = prop.getProperty("ClassPath");
		Class<?> c = Class.forName(s);
		//shu chu baomin 
		System.out.println(c.getPackage().getName());
		//shuchu baoming he lei ming
		System.out.println(c.getName());
		//shu chu lei ming 
		System.out.println(c.getSimpleName());
		People people = (People)c.newInstance();
		people.work();
		String priceString = prop.getProperty("price");
		int price = Integer.parseInt(String.valueOf(priceString));
		people.setPrice(price);
		people.work();
	}	
}
