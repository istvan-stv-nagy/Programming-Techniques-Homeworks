package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 * order specific queries
 * @author Istvan
 *
 */
public class OrderDAO extends AbstractDAO<Order>{
	
	private static final String historyStatementString = 
			"SELECT customer.idcustomer,customer.name,product.idproduct,product.name,`order`.amount "
			+ "FROM product "
			+ "JOIN `order` ON product.idproduct=`order`.idproduct "
			+ "JOIN customer ON customer.idcustomer=`order`.idcustomer";
	
	private static final String customerProductStatementString =
			"SELECT * FROM product join `order` on `order`.idproduct = product.idproduct"
			+ " where idcustomer=?";
	
	private static final String getAmountStatement =
			"SELECT amount from `order` where idcustomer=? AND idproduct=?";
	
	public OrderDAO() {
		super();
	}
	
	/**
	 * history of all the orders performed
	 * @return a list of all the orders
	 */
	public static List<Object> history() {
		List<Object> list = new ArrayList<Object>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement historyStatement = null;	
		ResultSet rs = null;
		try {
			historyStatement = dbConnection.prepareStatement(historyStatementString);
			rs = historyStatement.executeQuery();
			while (rs.next()) {
				int idcustomer = rs.getInt(1);
				String cname = rs.getString(2);
				int idproduct = rs.getInt(3);
				String pname = rs.getString(4);
				int amount = rs.getInt(5);
				list.add(new OrderDetail(idcustomer,cname,idproduct,pname,amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(historyStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}
	
	public static List<Product> listOrderedProducts(int id) {
		List<Product> list = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement productsStatement = null;	
		ResultSet rs = null;
		try {
			productsStatement = dbConnection.prepareStatement(customerProductStatementString);
			productsStatement.setInt(1, id);
			rs = productsStatement.executeQuery();
			while (rs.next()) {
				int idproduct = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				int amount = rs.getInt(4);
				float rating = rs.getFloat(5);
				int catID = rs.getInt(6);
				list.add(new Product(idproduct,name,price,amount,rating,catID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(productsStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}
	
	public static int getAmount(int idproduct, int idcustomer) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getStatement = null;	
		ResultSet rs = null;
		int res = 0;
		try {
			getStatement = dbConnection.prepareStatement(getAmountStatement);
			getStatement.setInt(1, idcustomer);
			getStatement.setInt(2, idproduct);
			rs = getStatement.executeQuery();
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(getStatement);
			ConnectionFactory.close(dbConnection);
		}
		return res;
	}
	
}
