package presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * the main view of the application.
 * @author Istvan
 *
 */
public class View extends JFrame {
	private static final long serialVersionUID = -3487313837768875561L;
	private JPanel contentPane;
	
	private JButton btnInsertCustomer;
	private JButton btnListCustomers;
	private JButton btnListProducts;
	private JButton btnDeleteCustomer;
	private JButton btnEditCustomer;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnPlaceOrder;
	private JButton btnInsertProduct;
	private JButton btnPriceFilter;
	
	ViewInsert viewInsert;
	ViewOrder viewOrder;
	ViewInsertProduct viewInsertProduct;
	private JLabel lblMinimumAmount;
	private JTextField textMinAmount;
	private JButton btnUnderstockFilter;
	private JButton btnDeleteProduct;
	private JButton btnEditProduct;
	private JButton btnOrderHistory;
	private JTextField textMinPrice;
	private JTextField textMaxPrice;
	private JButton btnSearchCustomer;
	private JLabel lblName;
	private JTextField textName;
	private JButton btnCategoryFilter;
	private JLabel lblCategory;
	private JTextField textCategory;
	
	public View() {
		setTitle("Order Management Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnInsertCustomer = new JButton("Insert Customer");
		btnInsertCustomer.setActionCommand("insertcustomer");
		btnInsertCustomer.setBounds(12, 41, 134, 25);
		contentPane.add(btnInsertCustomer);
		
		btnListCustomers = new JButton("List Customers");
		btnListCustomers.setActionCommand("listcustomers");
		btnListCustomers.setBounds(12, 79, 134, 25);
		contentPane.add(btnListCustomers);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 193, 448, 115);
		contentPane.add(scrollPane);
		
		btnListProducts = new JButton("List Products");
		btnListProducts.setActionCommand("listproducts");
		btnListProducts.setBounds(158, 79, 127, 25);
		contentPane.add(btnListProducts);
		
		btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.setActionCommand("deletecustomer");
		btnDeleteCustomer.setBounds(12, 117, 134, 25);
		contentPane.add(btnDeleteCustomer);
		
		btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.setActionCommand("editcustomer");
		btnEditCustomer.setBounds(12, 155, 134, 25);
		contentPane.add(btnEditCustomer);
		
		btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBounds(297, 41, 113, 25);
		btnPlaceOrder.setActionCommand("placeorder");
		contentPane.add(btnPlaceOrder);
		
		lblMinimumAmount = new JLabel("Minimum Amount");
		lblMinimumAmount.setBounds(158, 325, 100, 16);
		contentPane.add(lblMinimumAmount);
		
		textMinAmount = new JTextField();
		textMinAmount.setBounds(283, 325, 60, 22);
		contentPane.add(textMinAmount);
		textMinAmount.setColumns(10);
		
		btnUnderstockFilter = new JButton("UnderStock Filter");
		btnUnderstockFilter.setActionCommand("understock");
		btnUnderstockFilter.setBounds(12, 321, 134, 25);
		contentPane.add(btnUnderstockFilter);
		
		btnInsertProduct = new JButton("Insert Product");
		btnInsertProduct.setActionCommand("insertproduct");
		btnInsertProduct.setBounds(158, 41, 127, 25);
		contentPane.add(btnInsertProduct);
		
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.setActionCommand("deleteproduct");
		btnDeleteProduct.setBounds(158, 117, 127, 25);
		contentPane.add(btnDeleteProduct);
		
		btnEditProduct = new JButton("Edit Product");
		btnEditProduct.setActionCommand("editproduct");
		btnEditProduct.setBounds(158, 155, 127, 25);
		contentPane.add(btnEditProduct);
		
		btnOrderHistory = new JButton("Order History");
		btnOrderHistory.setActionCommand("history");
		btnOrderHistory.setBounds(297, 79, 113, 25);
		contentPane.add(btnOrderHistory);
		
		btnPriceFilter = new JButton("Price Filter");
		btnPriceFilter.setActionCommand("pricefilter");
		btnPriceFilter.setBounds(12, 359, 134, 25);
		contentPane.add(btnPriceFilter);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(158, 363, 56, 16);
		contentPane.add(lblFrom);
		
		textMinPrice = new JTextField();
		textMinPrice.setBounds(200, 360, 42, 22);
		contentPane.add(textMinPrice);
		textMinPrice.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(254, 363, 56, 16);
		contentPane.add(lblTo);
		
		textMaxPrice = new JTextField();
		textMaxPrice.setColumns(10);
		textMaxPrice.setBounds(283, 360, 42, 22);
		contentPane.add(textMaxPrice);
		
		btnSearchCustomer = new JButton("Search Customer");
		btnSearchCustomer.setActionCommand("searchcustomer");
		btnSearchCustomer.setBounds(12, 397, 134, 25);
		contentPane.add(btnSearchCustomer);
		
		lblName = new JLabel("Name");
		lblName.setBounds(158, 401, 56, 16);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBounds(226, 398, 143, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		btnCategoryFilter = new JButton("Category Filter");
		btnCategoryFilter.setActionCommand("categoryfilter");
		btnCategoryFilter.setBounds(12, 433, 134, 25);
		contentPane.add(btnCategoryFilter);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(158, 437, 56, 16);
		contentPane.add(lblCategory);
		
		textCategory = new JTextField();
		textCategory.setBounds(227, 434, 142, 22);
		contentPane.add(textCategory);
		textCategory.setColumns(10);

		this.setVisible(true);
	}
	
	/**
	 * set each buttons action listener to the listener defined in the controller
	 * @param listener the listener defined in the controller
	 */
	public void addBtnListener(ActionListener listener) {
		btnInsertCustomer.addActionListener(listener);
		btnListCustomers.addActionListener(listener);
		btnListProducts.addActionListener(listener);
		btnDeleteCustomer.addActionListener(listener);
		btnEditCustomer.addActionListener(listener);
		btnPlaceOrder.addActionListener(listener);
		btnUnderstockFilter.addActionListener(listener);
		btnInsertProduct.addActionListener(listener);
		btnDeleteProduct.addActionListener(listener);
		btnEditProduct.addActionListener(listener);
		btnOrderHistory.addActionListener(listener);
		btnPriceFilter.addActionListener(listener);
		btnSearchCustomer.addActionListener(listener);
		btnCategoryFilter.addActionListener(listener);
	}
	
	/**
	 * change the reference of a table and update the GUI
	 * @param newTable the new table to replace the current one
	 */
	public void setTable(JTable newTable) {
		this.table = newTable;
		scrollPane.setViewportView(table);
		repaint();
		revalidate();
	}
	
	public void setNewInsertView(ActionListener listener) {
		viewInsert = new ViewInsert();
		viewInsert.addBtnListener(listener);
	}
	
	public void setNewOrderView(JTable customers, JTable products, ActionListener listener) {
		viewOrder = new ViewOrder(customers,products);
		viewOrder.addBtnListener(listener);
	}
	
	public void setNewInsertPView(ActionListener listener) {
		viewInsertProduct = new ViewInsertProduct();
		viewInsertProduct.addBtnListener(listener);
	}
	
	public JTable getTable() {
		return table;
	}
	
	/**
	 * shows an informative message
	 * @param message the message to be displayed
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public String getMinAmount() {
		return textMinAmount.getText();
	}
	
	public String getMinPrice() {
		return textMinPrice.getText();
	}
	public String getMaxPrice() {
		return textMaxPrice.getText();
	}
	public String getName() {
		return textName.getText();
	}
	public String getCategoryName() {
		return textCategory.getText();
	}
}
