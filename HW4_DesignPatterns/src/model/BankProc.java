package model;

public interface BankProc {
	
	public void add(Person person);
	
	public void add(Person person, Account account);
	
	public void remove(Person person);
	
	public void remove(Person person, Account account);
	
	public void update(Person person, Person newPerson);
	
	public void read();
	
	public void write();
	
}
