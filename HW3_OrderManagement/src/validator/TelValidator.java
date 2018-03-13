package validator;

import model.Customer;

/**
 * checks if the tel number of a customer is of lenght 10, starts with 0 and contains only numbers
 * @author Istvan
 *
 */
public class TelValidator implements Validator<Customer> {

	@Override
	public boolean isValid(Customer t) {
		String num = "0123456789";
		if (t.getTel().length() != 10)
			return false;
		if (t.getTel().charAt(0)!='0')
			return false;
		for (int i=0; i<t.getTel().length(); i++) {
			if (!num.contains(t.getTel().substring(i, i))) {
				return false;
			}
		}
		return true;
	}
	
}
