package test;
import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class TestBank {

	@Test
	public void testAddClient() {
		Bank bank = new Bank();
		Person p = new Person(-5, "Ad4m");
		bank.add(p);
		bank.write();
	}
	
	@Test
	public void testAddAccount() {
		Bank bank = new Bank();
		Person p = new Person(10,"Anonymus");
		bank.add(p, new SpendingAccount(5));
		bank.write();
	}
	
	@Test
	public void testRemovePerson() {
		Bank bank = new Bank();
		Person p = new Person(10,"Anonymus");
		bank.remove(p);
		bank.write();
	}
	
	@Test
	public void testRemoveAccount() {
		Bank bank = new Bank();
		Person p = new Person(2,"istvan");
		Account a = bank.getAccounts(new Person(3,"rudolf")).get(0);
		bank.remove(p,a);
		bank.write();
	}
	
	@Test
	public void testAddSavingAccount() {
		Bank bank = new Bank();
		Person p = new Person(2,"istvan");
		bank.add(p, new SavingAccount(-200, 0.1f,bank.getAccounts(p).size() +1));
		bank.write();
	}

}
