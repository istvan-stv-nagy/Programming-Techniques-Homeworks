package model;

import java.util.Observable;
import java.util.Observer;

public class Person extends Observable implements java.io.Serializable, Observer{
	private static final long serialVersionUID = -3631621693117288553L;
	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return "["+id+"] " + name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public boolean isWellFormed() {
		if (id <= 0)
			return false;
		if (name.length() > 100)
			return false;
		String aux = name.toLowerCase();
		for (int i=0; i<name.length(); i++)
			if (aux.charAt(i) < 'a' || aux.charAt(i) > 'z')
				return false;
		return true;
	}

	@Override
	public void update(Observable account, Object obj) {
		if (obj instanceof String) {
			this.setChanged();
			this.notifyObservers("Dear client " + toString() + "! Your account has been changed!" + obj.toString());
		}
	}
	
	public int getID() {
		return id;
	}
}


