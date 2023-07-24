package JDBC1;

import java.io.Serializable;

//value object

public class Product implements Serializable{
	
	private String ID;
	private String description;
	private double price;
	private int quantity;
	
	private static final long serialVersionUID = 1L;
		
	public Product()
	{

	}
	
	public Product(String iD, String description, double price, int quantity) {
		ID = iD;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
