package people.worker;

public class Chushi extends People{
	private int hp;
	private int length;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private String name;
	private int price;
	public void work() {
		System.out.println("Cooker is a pingdiguo");
		
	}
	public void tong() {
		System.out.println(" pingdiguo");
	}
}
