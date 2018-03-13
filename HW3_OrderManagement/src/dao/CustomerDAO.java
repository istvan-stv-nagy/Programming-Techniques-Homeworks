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
import model.Customer;

/**
 * executes customer specific queries
 * @author Istvan
 *
 */
public class CustomerDAO extends AbstractDAO<Customer> {

	private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	
	private static final String findStatementString = "SELECT * FROM customer WHERE `name` LIKE ?";
	
	public CustomerDAO() {
		super();
	}
		
	/**
	 * finds a customer of a given name
	 * @param name name to be searched
	 * @return a list of customers of the given name
	 */
	public List<Object> findByName(String name) {
		List<Object> customers = new ArrayList<Object>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, "%" + name + "%");
			rs = findStatement.executeQuery();
			while (rs.next()) {
				customers.add(createObject(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return customers;
	}
}
