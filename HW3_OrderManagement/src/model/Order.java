package model;

public class Order {
	int amount;
	int idcustomer;
	int idproduct;
	
	public Order(int amount, int idcustomer, int idproduct) {
		super();
		this.amount = amount;
		this.idcustomer = idcustomer;
		this.idproduct = idproduct;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

}
