package model;

public class Customer {
	private int idcustomer;
	private String name;
	private String tel;
	
	public Customer() {
		this(0,"","");
	}
	
	public Customer(int idcustomer, String name, String tel) {
		super();
		this.idcustomer = idcustomer;
		this.name = name;
		this.tel = tel;
	}

	public int getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Customer [idcustomer=" + idcustomer + ", name=" + name + ", tel=" + tel + "]";
	}
	
}
