package model;

public class SavingAccount extends Account {
	private static final long serialVersionUID = -8241390368648816564L;
	private boolean active;
	private float interest;
	
	public SavingAccount(float sum, float interest, int number) {
		super(sum, number);
		this.interest = interest;
		this.active = true;
	}
	
	public float withdraw(int months) {
		if (!active) {
			System.out.println("Cannot perform withdraw! Account is not active anymore!");
			return -1;
		}
		float sum = balance;
		for (int i=0; i<months; i++) {
			sum += sum*interest;
		}
		balance = 0;
		active = false;
		
		this.setObjectChanged();
		this.notifyAllObservers("Withdraw was successful. The new balance on your account:");
		return sum;
	}
	
	public String toString() {
		return "saving account = " + balance + "interest =" + interest;
	}
	public float getInterest() {
		return interest;
	}
	public boolean isActive() {
		return active;
	}
	public boolean isValid() {
		if (balance >=0 && interest>=0)
			return true;
		return false;
	}
	
}
