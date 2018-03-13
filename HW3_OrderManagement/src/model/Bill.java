package model;

import java.util.Date;
import java.util.List;

import dao.OrderDAO;

public class Bill {
	
	private Customer customer;
	private List<Product> product;
	private Date date;
	
	public Bill(Customer customer, List<Product> product, Date date) {
		super();
		this.customer = customer;
		this.product = product;
		this.date = date;
	}
	
	public String generateBill() {
		String content = "BILL\n";
		content += "Date of the order" + "\t" + ": " + date.toString() + "\n";
		content += "Customer details" + "\t" + ": " + customer.toString() + "\n";
		
		float total = 0;
		int amount;
		for (Product p : product) {
			amount = OrderDAO.getAmount(p.getIdproduct(), customer.getIdcustomer());
			content += p.toString() + "\t" + p.getPrice()*amount + "$\n";
			total += p.getPrice()*amount;
		}
		
		content += "Total price" + "\t\t" + ": " + total + "\n";	
		return content;
	}
	
}
