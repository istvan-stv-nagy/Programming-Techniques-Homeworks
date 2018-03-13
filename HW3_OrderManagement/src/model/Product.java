package model;

public class Product {
	private int idproduct;
	private String name;
	private float price;
	private int amount;
	private float rating;
	private int category_idcategory;
	
	public Product() {
		this(0,"",0f,0,0f,0);
	}
	
	public Product(int idproduct, String name, float price, int amount, float rating, int category_idcategory) {
		super();
		this.idproduct = idproduct;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.rating = rating;
		this.category_idcategory = category_idcategory;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCategory_idcategory() {
		return category_idcategory;
	}

	public void setCategory_idcategory(int category_idcategory) {
		this.category_idcategory = category_idcategory;
	}

	@Override
	public String toString() {
		return "Product [id=" + idproduct + ", name=" + name + ", price=" + price + ", amount=" + amount + ", rating=" + rating
				+ ", categoryID=" + category_idcategory + "]";
	}
	
}
