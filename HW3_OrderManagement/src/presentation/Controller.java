package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import bll.CustomerBLL;
import bll.ProductBLL;
import dao.OrderDAO;
import model.Bill;
import model.Customer;
import model.Order;
import model.Product;

/**
 * part of the MVC;
 * has a view and business logic classes in order to execute the given operations and at the same time
 * communicate back and forth with the GUI
 * @author Istvan
 *
 */
public class Controller {
	
	private View view;
	private BtnListener btnListener;
	private OrderDAO orderDAO = new OrderDAO();
	private CustomerBLL customerBLL = new CustomerBLL();
	private ProductBLL productBLL = new ProductBLL();
	
	private FileWriter fw = null;
	private BufferedWriter bw = null;
	
	public Controller(View view) {
		this.view = view;
		btnListener = new BtnListener();
		view.addBtnListener(btnListener);
	}
	
	/**
	 * creates a table from a list of objects
	 * @param objects list of objects to appear in the table
	 * @return table containing the given data
	 */
	private JTable createTable(List<Object> objects) {
		if (objects.size() == 0)
			return null;
		JTable table = null;
		int numberOfColumns = objects.get(0).getClass().getDeclaredFields().length;
		String columns[] = new String[numberOfColumns];
		int i = 0;
		for (Field field : objects.get(0).getClass().getDeclaredFields()) {
			columns[i] = field.getName();
			i++;
		}
		String data[][] = new String[objects.size()][numberOfColumns];
		int row = 0;
		for (Object obj : objects) {
			int col = 0;
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(obj);
					data[row][col] = value.toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				col++;
			}
			row++;
		}
		table = new JTable(data,columns);
		return table;
	}
	
	/**
	 * gets the selected customer of a table
	 * @param table the table
	 * @return the selected customer
	 */
	private Customer getSelectedCustomer(JTable table) {
		int selectedRow = table.getSelectedRow();
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		String name = table.getValueAt(selectedRow, 1).toString();
		String tel = table.getValueAt(selectedRow, 2).toString();
		return new Customer(id,name,tel);
	}
	
	private Product getSelectedProduct(JTable table) {
		int selectedRow = table.getSelectedRow();
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		String name = table.getValueAt(selectedRow, 1).toString();
		float price = Float.parseFloat(table.getValueAt(selectedRow, 2).toString());
		int amount = Integer.parseInt(table.getValueAt(selectedRow, 3).toString());
		float rating = Float.parseFloat(table.getValueAt(selectedRow, 4).toString());
		int categoryID = Integer.parseInt(table.getValueAt(selectedRow, 5).toString());
		return new Product(id,name,price,amount,rating,categoryID);
	}
	
	class BtnListener implements ActionListener {
		/**
		 * checks the action performed by the user (the pressed button)
		 */
		public void actionPerformed(ActionEvent e) {
			String name;
			int amount;
			int id;
			switch (e.getActionCommand()) {
			case "insertcustomer" :
				view.setNewInsertView(btnListener);
				break;
			case "insertproduct" :
				view.setNewInsertPView(btnListener);
				break;
			case "confirminsertproduct" :
				id = Integer.parseInt(view.viewInsertProduct.getTextId());
				name = view.viewInsertProduct.getTextName();
				float price = Float.parseFloat(view.viewInsertProduct.getTextPrice());
				amount = Integer.parseInt(view.viewInsertProduct.getTextAmount());
				float rating = Float.parseFloat(view.viewInsertProduct.getTextRating());
				int catID = Integer.parseInt(view.viewInsertProduct.getTextCategory());
				try {
					productBLL.insert(new Product(id,name,price,amount,rating,catID));
					view.viewInsertProduct.dispose();
					view.showMessage("Product inserted successfully!");
				} catch (IllegalArgumentException ex1) {
					view.showMessage(ex1.getMessage());
				}
				break;
			case "insertcustomerconfirm" :
				id = Integer.parseInt(view.viewInsert.textId.getText());
				name = view.viewInsert.textName.getText();
				String tel = view.viewInsert.textTel.getText();
				try {
					customerBLL.insert(new Customer(id,name,tel));
					view.viewInsert.dispose();
					view.showMessage("Customer inserted successfully!");
				} catch (IllegalArgumentException e1) {
					view.showMessage(e1.getMessage());
				}
				break;
			case "listcustomers" :
				view.setTable(createTable(customerBLL.list()));
				break;
			case "deletecustomer" :
				if (view.getTable() == null || !(view.getTable().getSelectedRow()>=0))
					break;
				int selectedRow = view.getTable().getSelectedRow();
				int idDeleted = Integer.parseInt(view.getTable().getValueAt(selectedRow, 0).toString());
				customerBLL.delete((int)idDeleted);
				view.setTable(createTable(customerBLL.list()));
				view.showMessage("Customer deleted successfully!");
				break;
			case "deleteproduct" :
				if (view.getTable() == null || !(view.getTable().getSelectedRow()>=0))
					break;
				selectedRow = view.getTable().getSelectedRow();
				idDeleted = Integer.parseInt(view.getTable().getValueAt(selectedRow, 0).toString());
				productBLL.delete(idDeleted);
				view.setTable(createTable(productBLL.list()));
				view.showMessage("Product deleted successfully!");
				break;
			case "editcustomer" :
				if (view.getTable() == null || !(view.getTable().getSelectedRow()>=0))
					break;
				int selected = view.getTable().getSelectedRow();
				int idUpdate = Integer.parseInt(view.getTable().getValueAt(selected, 0).toString());
				String nameUpdate = view.getTable().getValueAt(selected, 1).toString();
				String telUpdate = view.getTable().getValueAt(selected, 2).toString();
				try {
					customerBLL.edit(idUpdate, new Customer(idUpdate,nameUpdate,telUpdate));
					view.setTable(createTable(customerBLL.list()));
					view.showMessage("Customer updated successfully!");
				} catch (IllegalArgumentException e1) {
					view.showMessage(e1.getMessage());
				}
				break;
			case "editproduct" :
				if (view.getTable() == null || !(view.getTable().getSelectedRow()>=0))
					break;
				selected = view.getTable().getSelectedRow();
				idUpdate = Integer.parseInt(view.getTable().getValueAt(selected, 0).toString());
				Product newProduct = getSelectedProduct(view.getTable());
				try {
					productBLL.edit(idUpdate, newProduct);
					view.setTable(createTable(productBLL.list()));
					view.showMessage("Product updated successfully!");
				} catch (IllegalArgumentException ex2) {
					view.showMessage(ex2.getMessage());
				}
				break;
			case "listproducts" :
				view.setTable(createTable(productBLL.list()));
				break;
			case "understock" :
				try {
					int minAmount = Integer.parseInt(view.getMinAmount());
					JTable tab = createTable(productBLL.listUnderstock(minAmount));
					view.setTable(tab);
					if (tab != null)
						view.showMessage("There are " + tab.getRowCount() + " understocked products!");
					else 
						view.showMessage("There are no understocked products!");
				} catch (NumberFormatException ex2) {
					view.showMessage("Invalid Data");
				} catch (IllegalArgumentException ex2) {
					view.showMessage(ex2.getMessage());
				}
				break;
			case "searchcustomer" :
				List<Object> list = null;
				try {
					list = customerBLL.findByName(view.getName());
					view.setTable(createTable(list));
					if (list.size() == 0) {
						view.showMessage("There are no such customers in the database!");
					} else {
						view.showMessage("Found " + list.size() + " customers with name matching with " + view.getName() + "!");
					}
				} catch (IllegalArgumentException e1) {
					view.showMessage(e1.getMessage());
				}
				break;
			case "categoryfilter" :
				String categoryName = view.getCategoryName();
				List<Object> catlist = productBLL.findCategory(categoryName);
				if (catlist.size() == 0) {
					view.showMessage("No product found having category " + categoryName);
				} else {
					view.setTable(createTable(catlist));
				}
				break;
			case "placeorder" :
				view.setNewOrderView(createTable(customerBLL.list()), createTable(productBLL.list()),btnListener);
				break;
			case "pricefilter" :
				try {
					float minPrice = Float.parseFloat(view.getMinPrice());
					float maxPrice = Float.parseFloat(view.getMaxPrice());
					view.setTable(createTable(productBLL.listPriceFiltering(minPrice, maxPrice)));
				} catch (NumberFormatException ex3) {
					view.showMessage("Invalid data");
				} catch (IllegalArgumentException ex3) {
					view.showMessage(ex3.getMessage());
				}
				break;
			case "history" :
				view.setTable(createTable(OrderDAO.history()));
				break;
			case "createbill" :
				Customer selectedCustomer = getSelectedCustomer(view.viewOrder.getCustomerTable());
				Bill bill = new Bill(selectedCustomer,OrderDAO.listOrderedProducts(selectedCustomer.getIdcustomer()), new Date());
				String generated = bill.generateBill();
				view.showMessage(generated);
				try {
					fw = new FileWriter("Bill_c" + selectedCustomer.getIdcustomer()+"_p" + ".txt");
					bw = new BufferedWriter(fw);
					for (int i=0; i<generated.length(); i++) {
						if (generated.charAt(i) == '\n')
							bw.newLine();
						else
							bw.write(generated.charAt(i));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (bw != null)
							bw.close();
						if (fw != null)
							fw.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				break;
			case "confirmorder" :
				selectedCustomer = getSelectedCustomer(view.viewOrder.getCustomerTable());
				Product selectedProduct = getSelectedProduct(view.viewOrder.getProductsTable());
				amount = Integer.parseInt(view.viewOrder.getTextAmount());
				if (amount <= selectedProduct.getAmount()) {
					Order order = new Order(amount,selectedCustomer.getIdcustomer(),selectedProduct.getIdproduct());
					orderDAO.insert(order);
					view.showMessage("Product bought!");
					//Bill bill = new Bill(selectedCustomer, selectedProduct, amount, new Date());
					//String generated = bill.generateBill();
					//view.showMessage(generated);
					selectedProduct.setAmount(selectedProduct.getAmount() - amount);
					productBLL.edit(selectedProduct.getIdproduct(), selectedProduct);
					view.setTable(createTable(productBLL.list()));
					view.viewOrder.setProductsTable(createTable(productBLL.list()));
				} else {
					view.showMessage("Order count not be completed due to UNDERSTOCK!");
				}
				break;
			}
		}
	}
}
