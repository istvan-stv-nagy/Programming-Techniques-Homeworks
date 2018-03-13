package model;

public class OrderDetail {
	private int idcustomer;
	private String cname;
	private int idproduct;
	private String pname;
	private int amount;
	
	public OrderDetail(int idcustomer, String cname, int idproduct, String pname, int amount) {
		super();
		this.idcustomer = idcustomer;
		this.cname = cname;
		this.idproduct = idproduct;
		this.pname = pname;
		this.amount = amount;
	}

	public int getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
