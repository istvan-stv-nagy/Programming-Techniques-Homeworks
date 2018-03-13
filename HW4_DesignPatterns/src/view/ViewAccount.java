package view;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewAccount extends JFrame {
	private static final long serialVersionUID = 8900620581371134253L;
	private JPanel contentPane;
	private JRadioButton rdbtnSpendingAccount;
	private JRadioButton rdbtnSavingAccount;
	private JTextField textBalance;
	private JTextField textInterest;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnList;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblAmount;
	private JTextField textAmount;
	private JButton btnWithdraw;
	private JButton btnDeposit;
	private JLabel lblMonthsPassed;
	private JTextField textMonths;

	public ViewAccount() {
		setTitle("Account Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnSavingAccount = new JRadioButton("Saving Account");
		rdbtnSavingAccount.setBounds(8, 38, 127, 25);
		contentPane.add(rdbtnSavingAccount);
		
		rdbtnSpendingAccount = new JRadioButton("Spending Account");
		rdbtnSpendingAccount.setBounds(8, 80, 141, 25);
		contentPane.add(rdbtnSpendingAccount);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSavingAccount);
		group.add(rdbtnSpendingAccount);
		
		JLabel lblInitialBalance = new JLabel("Balance ");
		lblInitialBalance.setBounds(173, 42, 88, 16);
		contentPane.add(lblInitialBalance);
		
		textBalance = new JTextField();
		textBalance.setBounds(269, 39, 64, 22);
		contentPane.add(textBalance);
		textBalance.setColumns(10);
		
		JLabel lblInterest = new JLabel("Interest");
		lblInterest.setBounds(173, 84, 56, 16);
		contentPane.add(lblInterest);
		
		textInterest = new JTextField();
		textInterest.setBounds(269, 81, 64, 22);
		contentPane.add(textInterest);
		textInterest.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand("addacc");
		btnAdd.setBounds(12, 127, 97, 25);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("delacc");
		btnDelete.setBounds(121, 127, 97, 25);
		contentPane.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand("editacc");
		btnEdit.setBounds(236, 127, 97, 25);
		contentPane.add(btnEdit);
		
		btnList = new JButton("List");
		btnList.setActionCommand("listacc");
		btnList.setBounds(345, 127, 97, 25);
		contentPane.add(btnList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 274, 446, 166);
		contentPane.add(scrollPane);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setBounds(8, 179, 56, 16);
		contentPane.add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setBounds(76, 176, 70, 22);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setActionCommand("withdraw");
		btnWithdraw.setBounds(158, 175, 97, 25);
		contentPane.add(btnWithdraw);
		
		btnDeposit = new JButton("Deposit");
		btnDeposit.setActionCommand("deposit");
		btnDeposit.setBounds(269, 175, 97, 25);
		contentPane.add(btnDeposit);
		
		lblMonthsPassed = new JLabel("Months");
		lblMonthsPassed.setBounds(8, 216, 56, 16);
		contentPane.add(lblMonthsPassed);
		
		textMonths = new JTextField();
		textMonths.setBounds(76, 213, 70, 22);
		contentPane.add(textMonths);
		textMonths.setColumns(10);
		this.setVisible(true);
	}
	
	public void setTable(JTable newTable) {
		this.table = newTable;
		scrollPane.setViewportView(table);
		repaint();
		revalidate();
	}
	
	public JRadioButton getRdBtnSavingAccount() {
		return rdbtnSavingAccount;
	}
	public JRadioButton getRdBtnSpendingAccount() {
		return rdbtnSpendingAccount;
	}
	public void setBtnListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnEdit.addActionListener(listener);
		btnList.addActionListener(listener);
		btnDeposit.addActionListener(listener);
		btnWithdraw.addActionListener(listener);
	}
	public String getBalance() {
		return textBalance.getText();
	}
	public String getInterest() {
		return textInterest.getText();
	}
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	public JTable getTable() {
		return table;
	}
	public String getAmount() {
		return textAmount.getText();
	}
	public String getMonths() {
		return textMonths.getText();
	}
}
