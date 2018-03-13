package model;

import java.util.Observable;

public class Account extends Observable implements java.io.Serializable  {
	private static final long serialVersionUID = -3187918017562249289L;
	protected float balance;
	protected int number;
	
	public Account(float balance, int number) {
		this.balance = balance;
		this.number = number;
	}
	
	protected void notifyAllObservers(String info) {
		this.notifyObservers(info + " " + new Float(balance));
	}
	
	protected void setObjectChanged() {
		this.setChanged();
	}
	
	public float withdraw(float sum) {
		return -1;
	}
	
	public boolean deposit(float sum) {
		return false;
	}
	
	public float withdraw(int months) {
		return -1;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public boolean isValid() {
		return balance >= 0;
	}
	
	public void setObserver(Person p) {
		this.addObserver(p);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int num) {
		this.number = num;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
}
