package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates the connection to a database, establishes the connection and closes it if needed
 * @author Istvan
 *
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a connection to the database
	 * @return connection to the given database
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database!");
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * create a connection and return it
	 * @return connection to the database
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}
	
	/**
	 * closes the connection to the database
	 * @param connection the connection to be closed
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
				e.printStackTrace();
			}
		}
	}
	
}
