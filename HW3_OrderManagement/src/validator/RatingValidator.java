package validator;

import model.Product;

/**
 * checks if a rating of a product is between 0 and 10
 * @author Istvan
 *
 */
public class RatingValidator implements Validator<Product> {

	@Override
	public boolean isValid(Product t) {
		if (t.getRating()>0 && t.getRating()<=10)
			return true;
		return false;
	}

}
