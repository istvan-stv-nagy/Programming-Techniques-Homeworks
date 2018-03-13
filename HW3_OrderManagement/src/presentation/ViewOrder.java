package presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

/**
 * the window which appears when an order is created
 * @author Istvan
 *
 */
public class ViewOrder extends JFrame {
	private static final long serialVersionUID = 3010036495749399188L;
	private JPanel contentPane;
	private JTable customers;
	private JTable products;
	private JButton btnConfirmOrder;
	private JButton btnCreateBill;
	private JTextField textAmount;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	
	public ViewOrder(JTable customers, JTable products) {
		this.customers = customers;
		this.products = products;
		setTitle("Order Placement");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 748, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 65, 267, 118);
		scrollPane1.setViewportView(customers);
		contentPane.add(scrollPane1);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(326, 65, 392, 118);
		scrollPane2.setViewportView(products);
		contentPane.add(scrollPane2);
		
		btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.setActionCommand("confirmorder");
		btnConfirmOrder.setBounds(255, 249, 132, 25);
		contentPane.add(btnConfirmOrder);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAmount.setBounds(12, 253, 79, 16);
		contentPane.add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setBounds(103, 250, 58, 22);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		btnCreateBill = new JButton("Create Bill");
		btnCreateBill.setActionCommand("createbill");
		btnCreateBill.setBounds(255, 318, 132, 25);
		contentPane.add(btnCreateBill);
		this.setVisible(true);
	}
	
	public void addBtnListener(ActionListener listener) {
		btnConfirmOrder.addActionListener(listener);
		btnCreateBill.addActionListener(listener);
	}
	
	public JTable getCustomerTable() {
		return customers;
	}
	
	public JTable getProductsTable() {
		return products;
	}
	
	public void setProductsTable(JTable table) {
		this.products = table;
		scrollPane2.setViewportView(products);
		repaint();
		revalidate();
	}
	
	public String getTextAmount() {
		return textAmount.getText();
	}
}
