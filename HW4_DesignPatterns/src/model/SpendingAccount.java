package model;

public class SpendingAccount extends Account {
	private static final long serialVersionUID = -5086682188573991628L;
	
	public SpendingAccount(int number) {
		super(0, number);
	}
	
	public boolean deposit(float sum) {
		if (sum > 0) {
			balance += sum;
			this.setObjectChanged();
			this.notifyAllObservers("Deposit was successful. The new balance on your account:");
			return true;
		}
		return false;
	}
	
	public float withdraw(float sum) {
		if (sum <= balance) {
			balance -= sum;
			this.setObjectChanged();
			this.notifyAllObservers("Withdraw was successful. The new balance on your account:");
			return sum;
		} else {
			return -1;
		}
	}
	
	public String toString() {
		return "spending account = " + balance;
	}
}
