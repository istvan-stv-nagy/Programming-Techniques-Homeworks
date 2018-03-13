package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ViewPerson extends JFrame {
	private static final long serialVersionUID = -1129962073180133081L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnList;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAdd;
	
	public ViewPerson() {
		setTitle("Person Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersonId = new JLabel("Person ID");
		lblPersonId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPersonId.setBounds(12, 13, 87, 29);
		contentPane.add(lblPersonId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(12, 55, 87, 29);
		contentPane.add(lblName);
		
		textId = new JTextField();
		textId.setBounds(111, 17, 116, 22);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(111, 59, 228, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand("addperson");
		btnAdd.setBounds(12, 97, 97, 25);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("delperson");
		btnDelete.setBounds(121, 97, 97, 25);
		contentPane.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand("editperson");
		btnEdit.setBounds(230, 97, 97, 25);
		contentPane.add(btnEdit);
		
		btnList = new JButton("List");
		btnList.setActionCommand("listperson");
		btnList.setBounds(339, 97, 97, 25);
		contentPane.add(btnList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 164, 442, 167);
		contentPane.add(scrollPane);
		table = new JTable();
		
		this.setVisible(true);
	}
	
	public void setTable(JTable newTable) {
		this.table = newTable;
		scrollPane.setViewportView(table);
		repaint();
		revalidate();
	}
	
	public void setBtnListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnEdit.addActionListener(listener);
		btnList.addActionListener(listener);
	}
	
	public String getTextId() {
		return textId.getText();
	}
	public String getTextName() {
		return textName.getText();
	}
	public JTable getTable() {
		return table;
	}
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
