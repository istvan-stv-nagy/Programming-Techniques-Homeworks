package model;

@SuppressWarnings("unused")
public class AccountDetail {
	private static final long serialVersionUID = 1L;
	private Person person;
	private int number;
	private String type;
	private float balance;
	private float interest;
	private boolean active;
	
	public AccountDetail(Person person, int number, String type, float balance, float interest, boolean active) {
		super();
		this.person = person;
		this.type = type;
		this.balance = balance;
		this.interest = interest;
		this.active = active;
		this.number = number;
	}	
}
