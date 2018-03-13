package start;

import controller.Controller;
import model.Bank;
import view.ViewAccount;
import view.ViewPerson;

public class Main {
	
	public static void main(String args[]) {
		Bank bank = new Bank();
		ViewPerson viewPerson = new ViewPerson();
		ViewAccount viewAccount = new ViewAccount();
		new Controller(bank, viewPerson, viewAccount);
	}
}
