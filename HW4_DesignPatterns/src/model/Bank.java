package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Bank implements BankProc {

	private static final String FILENAME = "bank_data.ser";
	
	private HashMap<Person,LinkedList<Account>> accounts;
	
	public Bank() {
		this.accounts = new HashMap<Person,LinkedList<Account>>();
		this.read();
	}
	
	/**
	 * checks if the hashmap is well-formed by checking each key and value
	 * @return true if it is well-formed, false otherwise
	 */
	public boolean isWellFormed() {
		for (Person p : accounts.keySet()) {
			if (!p.isWellFormed())
				return false;
			for (Account a : getAccounts(p)) {
				if (!a.isValid()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds a new person to the bank
	 * @param person person to be added
	 * @precondition person!=null
	 * @precondition person.isWellFormed() - the person must have valid parameters
	 * @precondition bank.isWellFormed() - the hashmap must be a valid one
	 * @post accounts.containsKey(person) - after insertion, the hashmap must contain the given person as a key
	 */
	public void add(Person person) {
		assert (person!=null);
		assert person.isWellFormed() : "Invalid person";
		assert isWellFormed();
		accounts.put(person, new LinkedList<Account>());
		assert accounts.containsKey(person);
		assert isWellFormed();
	}
	
	/**
	 * Adds a new account to a person
	 * @param person person who will get a new account
	 * @precondition person is not null
	 * @precondition account is not null
	 * @precondition the invariant of the bank holds
	 * @post getAccounts(person).contains(account)
	 */
	public void add(Person person, Account account) {
		assert person!=null;
		assert account!=null;
		assert isWellFormed();
		assert accounts.containsKey(person) : "The bank does not contain the given person!";
		account.setObserver(person);
		getAccounts(person).add(account);
		assert getAccounts(person).contains(account);
		assert isWellFormed() : "The bank is not well formed!";
	}

	/**
	 * Removes a person from the bank
	 * @param person person to be removed
	 * @precondition person!=null
	 * @precondition accounts.containsKey(person)
	 * @post !accounts.containsKey(person)
	 */
	public void remove(Person person) {
		assert person!=null;
		assert accounts.containsKey(person) : "The given person is not in the bank!";
		assert isWellFormed();
		accounts.remove(person, getAccounts(person));
		assert !accounts.containsKey(person);
		assert isWellFormed();
	}
	
	/**
	 * Removes an account of a given person
	 * @param person the person whose account will be removed
	 * @param account the account that will be removed
	 * @precondition person!=null
	 * @precondition account!=null
	 * @precondition accounts.containsKey(person)
	 * @precondition getAccounts(person).contains(account)
	 * @post getAccounts(person).contains(account) == false
	 */
	public void remove(Person person, Account account) {
		assert person != null;
		assert account != null;
		assert accounts.containsKey(person);
		assert getAccounts(person).contains(account) : "The person does not have the given account";
		assert isWellFormed();
		getAccounts(person).remove(account);
		assert !getAccounts(person).contains(account);
		assert isWellFormed();
	}
	
	public void update(Person person, Person newPerson) {
		accounts.put(newPerson, getAccounts(person));
		remove(person);
	}
	
	public void update(Person person, int number, float newBalance) {
		getAccounts(person).get(number).setBalance(newBalance);
	}
	
	public void write() {
		try {
			FileOutputStream file = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(accounts);
			
			out.close();
			file.close();
			System.out.print("Serialized data saved in " + FILENAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void print() {
		for (Person p : accounts.keySet()) {
			if (p!=null) {
				System.out.println(p.toString());
				for (Account a : getAccounts(p)) {
					System.out.println(a.toString());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void read() {
		HashMap<Person,LinkedList<Account>> data = null;
		try {
			FileInputStream file = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(file);
			
			data = (HashMap<Person,LinkedList<Account>>)in.readObject();
			
			in.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		accounts = data;
		for (Person p : accounts.keySet()) {
			for (Account a : getAccounts(p)) {
				a.setObserver(p);
			}
		}
	}
	
	public LinkedList<Account> getAccounts(Person person) {
		return accounts.get(person);
	}
	
	public List<Object> listPersons() {
		List<Object> list = new ArrayList<Object>();
		for (Person p : accounts.keySet())
			list.add(p);
		return list;
	}
	
	public List<Object> listAllAccounts() {
		List<Object> list = new ArrayList<Object>();
		for (Person p : accounts.keySet()) {
			for (Account a : getAccounts(p)) {
				if (a.getClass().getName().equals("model.SavingAccount")) {
					SavingAccount acc = (SavingAccount) a;
					list.add(new AccountDetail(p, a.getNumber(), "saving", a.getBalance(), acc.getInterest(), acc.isActive()));
				}
				else if (a.getClass().getName().equals("model.SpendingAccount")) {
					list.add(new AccountDetail(p, a.getNumber(), "spending", a.getBalance(), 0, true));
				}
			}
		}
		return list;
	}
	
	public List<Object> listAccounts(Person p) {
		List<Object> list = new ArrayList<Object>();
		for (Account a : getAccounts(p)) {
			if (a.getClass().getName().equals("model.SavingAccount")) {
				SavingAccount acc = (SavingAccount) a;
				list.add(new AccountDetail(p, a.getNumber(), "saving", a.getBalance(), acc.getInterest(), acc.isActive()));
			}
			else if (a.getClass().getName().equals("model.SpendingAccount")) {
				list.add(new AccountDetail(p, a.getNumber(), "spending", a.getBalance(), 0, true));
			}
		}
		return list;
	}
	
	public void refresh(Person person) {
		int num = 1;
		for (Account a : getAccounts(person)) {
			a.setNumber(num);
			num++;
		}
	}

	public HashMap<Person, LinkedList<Account>> getAccounts() {
		return accounts;
	}
	
	public boolean containsID(int id) {
		for (Person p : accounts.keySet()) {
			if (p.getID() == id)
				return true;
		}
		return false;
	}
}
