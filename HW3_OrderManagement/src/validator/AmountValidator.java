package validator;

import model.Product;

/**
 * validates the amount of a product
 * @author Istvan
 *
 */
public class AmountValidator implements Validator<Product> {
	
	@Override
	public boolean isValid(Product t) {
		if (t.getAmount()>=0)
			return true;
		return false;
	}

}
