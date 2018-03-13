package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * abstract class which implements the basic database access operations
 * insert, delete, list and edit
 * @author Istvan
 *
 * @param <T> customer or product
 */
public abstract class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * creates a default list mysql query
	 * @return list query
	 */
	private String createListQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append("`" + type.getSimpleName() + "`");
		return sb.toString();
	}
	
	/**
	 * creates default insert query
	 * @return insert query
	 */
	private String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append("`"+type.getSimpleName()+"`");
		sb.append("(");
		for (Field field : type.getDeclaredFields()) {
			sb.append(field.getName()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") VALUES (");
		int n = type.getDeclaredFields().length;
		for (int i=1; i<=n; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * creates default delete query
	 * @param field field to be checked
	 * @return delete query
	 */
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append("`"+type.getSimpleName()+"`");
		sb.append(" WHERE " + field + "=?");
		return sb.toString();
	}
	
	private String createEditQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for (Field f : type.getDeclaredFields()) {
			sb.append(f.getName()+"=?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" WHERE " + field + "=?");
		return sb.toString();
	}
	
	/**
	 * edits the object with id id so that it has the same value as the given parameter
	 * @param id id of the object to be updated
	 * @param object new value
	 */
	public void edit(int id, T object) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement editStatement = null;	
		try {
			editStatement = dbConnection.prepareStatement(createEditQuery(type.getDeclaredFields()[0].getName()));
			int i=1;
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(object);
				editStatement.setString(i, value.toString());
				i++;
			}
			editStatement.setInt(i, id);
			editStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(editStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * insert an object to the database
	 * @param object the object to be inserted
	 */
	public void insert(T object) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;	
		
		try {
			insertStatement = dbConnection.prepareStatement(createInsertQuery());
			int i=1;
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(object);
				insertStatement.setString(i, value.toString());
				i++;
			}
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * deletes the object with the correspnding id
	 * @param id id to be searched and deleted
	 */
	public void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;	
		try {
			deleteStatement = dbConnection.prepareStatement(createDeleteQuery(type.getDeclaredFields()[0].getName()));
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * lists every object of a given type
	 * @return a list of objects containing all entries of a given type
	 */
	public List<Object> list() {
		List<Object> list = new ArrayList<Object>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement listStatement = null;	
		ResultSet rs = null;
		try {
			listStatement = dbConnection.prepareStatement(createListQuery());
			rs = listStatement.executeQuery();
			try {
				while (rs.next()) {
					Object instance = type.newInstance();
					for (Field field : type.getDeclaredFields()) {
						Object value = rs.getObject(field.getName());
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(instance, value);
					}
					list.add(instance);
				}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error displaying all products!");
		} finally {
			ConnectionFactory.close(listStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}
	
	public T createObject(ResultSet rs) {
		T instance = null;
		try {
			instance = type.newInstance();
			for (Field field : type.getDeclaredFields()) {
				Object value = rs.getObject(field.getName());
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Method method = propertyDescriptor.getWriteMethod();
				method.invoke(instance, value);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
