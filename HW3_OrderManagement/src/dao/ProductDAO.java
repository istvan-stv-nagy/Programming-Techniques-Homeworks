package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

/**
 * product specific queries
 * @author Istvan
 *
 */
public class ProductDAO extends AbstractDAO<Product> {

	private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	
	private static final String underStockStatementString = "SELECT * FROM product where amount<=?";
	private static final String priceFilterStatementString = "SELECT * FROM product WHERE price>=? AND price<=?";
	private static final String findCategoryStatementString = "SELECT * FROM product JOIN category ON product.category_idcategory = category.idcategory where category.category=?";
	
	/**
	 * lists every product which has amount smaller or equal than the minimumAmount
	 * @param minimumAmount the min amount of product
	 * @return a list of products
	 */
	public List<Object> listUnderstock(int minimumAmount) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement underStatement = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<Object>();
		
		try {
			underStatement = dbConnection.prepareStatement(underStockStatementString);
			underStatement.setInt(1, minimumAmount); 
			rs = underStatement.executeQuery();
			while (rs.next()) {
				list.add(createObject(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		return list;
	}
	
	/**
	 * lists all products of a given price interva;
	 * @param minPrice lower bound
	 * @param maxPrice upper bound
	 * @return a list of products which have their prices in the given interval
	 */
	public List<Object> listPriceFiltering(float minPrice, float maxPrice) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement filterStatement = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<Object>();
		
		try {
			filterStatement = dbConnection.prepareStatement(priceFilterStatementString);
			filterStatement.setFloat(1, minPrice); 
			filterStatement.setFloat(2, maxPrice); 
			rs = filterStatement.executeQuery();
			while (rs.next()) {
				list.add(createObject(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		return list;
	}
	
	/**
	 * finds every product of a given category
	 * @param categoryName category of the product
	 * @return list of products of category categoryName
	 */
	public List<Object> findCategory(String categoryName) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement filterStatement = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<Object>();
		
		try {
			filterStatement = dbConnection.prepareStatement(findCategoryStatementString);
			filterStatement.setString(1, categoryName); 
			rs = filterStatement.executeQuery();
			while (rs.next()) {
				list.add(createObject(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		return list;
	}
}
