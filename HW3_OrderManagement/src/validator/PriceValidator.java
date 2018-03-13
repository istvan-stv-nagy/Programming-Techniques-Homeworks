package validator;

import model.Product;

/**
 * check if the price is a positive real number
 * @author Istvan
 *
 */
public class PriceValidator implements Validator<Product> {

	@Override
	public boolean isValid(Product t) {
		if (t.getPrice()>0)
			return true;
		return false;
	}
	
}
