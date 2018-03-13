package bll;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import model.Product;
import validator.AmountValidator;
import validator.PriceValidator;
import validator.RatingValidator;
import validator.Validator;

/**
 * Business Logic Class which uses the ProductDAO class to access the database and
 * the validators to validate the user input. It contains customer specific operations
 * @author Istvan
 *
 */
public class ProductBLL {

	private List<Validator<Product>> validators;
	private ProductDAO productDAO;
	
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new PriceValidator());
		validators.add(new AmountValidator());
		validators.add(new RatingValidator());
		productDAO = new ProductDAO();
	}
	
	/**
	 * inserts a new product
	 * @param product product to be inserted
	 */
	public void insert(Product product) {
		for (Validator<Product> v : validators) {
			if (!v.isValid(product)) {
				throw new IllegalArgumentException("Invalid argument identified by " + v.getClass().getName());
			}
		}
		productDAO.insert(product);
	}
	
	/**
	 * uodates a product
	 * @param id id of the product
	 * @param product new product
	 */
	public void edit(int id, Product product) {
		for (Validator<Product> v : validators) {
			if (!v.isValid(product)) {
				throw new IllegalArgumentException("Invalid argument identified by " + v.getClass().getName());
			}
		}
		productDAO.edit(id, product);
	}
	
	/**
	 * removes a product
	 * @param id id of the product to be deleted
	 */
	public void delete(int id) {
		productDAO.delete(id);
	}
	
	/**
	 * list all the products
	 * @return list of all the products
	 */
	public List<Object> list() {
		return productDAO.list();
	}
	
	/**
	 * lists all the products which have amount less than minimumAmount
	 * @param minimumAmount the minimum amount
	 * @return a list of products
	 */
	public List<Object> listUnderstock(int minimumAmount) {
		if (minimumAmount < 0)
			throw new IllegalArgumentException("Invalid amount");
		return productDAO.listUnderstock(minimumAmount);
	}
	
	/**
	 * lists all the products of a given range
	 * @param minPrice lower bound
	 * @param maxPrice upper bound
	 * @return list of products within the given price interval
	 */
	public List<Object> listPriceFiltering(float minPrice, float maxPrice) {
		if (minPrice<0 || maxPrice<0 || minPrice>maxPrice)
			throw new IllegalArgumentException("Invalid interval");
		return productDAO.listPriceFiltering(minPrice, maxPrice);
	}
	
	/**
	 * find every product of a given category
	 * @param categoryName the name of the category
	 * @return list of products
	 */
	public List<Object> findCategory(String categoryName) {
		return productDAO.findCategory(categoryName);
	}
}
