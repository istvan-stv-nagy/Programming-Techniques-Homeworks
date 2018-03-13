package validator;

import model.Customer;

/**
 * validates the name of a customer
 * @author Istvan
 *
 */
public class NameValidator implements Validator<Customer> {

	@Override
	/**
	 * if the lenght is appropriate and does not contain special characters, return true, otherwise false
	 */
	public boolean isValid(Customer t) {
		String name = t.getName().toLowerCase();
		if (name.length() <3 || name.length()>100)
			return false;
		for (int i=0; i<name.length(); i++) {
			if ((name.charAt(i)<'a' || name.charAt(i)>'z') && name.charAt(i)!=' ')
				return false;
		}
		return true;
	}	
}
