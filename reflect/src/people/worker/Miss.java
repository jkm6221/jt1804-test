package people.worker;

public class Miss extends People{
	private int price;
	private String phone;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void work() {
		System.out.println(price +"sleep");
	}
	
}
