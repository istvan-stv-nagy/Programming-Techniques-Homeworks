package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class View extends JFrame {
	private static final long serialVersionUID = 4788248752205265238L;
	private JPanel contentPane;
	private JTextField txtFirstPolynomial;
	private JTextField txtSecondPolynomial;
	private JButton btnAdd;
	private JButton btnSubstract;
	private JButton btnMultiply;
	private JButton btnDivide;
	private JButton btnDifferentiate;
	private JButton btnIntegrate;
	private JButton btnCalcValueIn;
	private JTextArea textArea;
	private JTextField txtPointValue;
	private JTextField txtValueFrom;
	private JTextField txtValueTo;

	//creates the view
	public View() {
		setTitle("Polynomial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstPolynomial = new JLabel("First Polynomial");
		lblFirstPolynomial.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstPolynomial.setBounds(12, 43, 112, 16);
		contentPane.add(lblFirstPolynomial);
		
		txtFirstPolynomial = new JTextField();
		txtFirstPolynomial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFirstPolynomial.setBounds(144, 38, 371, 22);
		contentPane.add(txtFirstPolynomial);
		txtFirstPolynomial.setColumns(10);
		
		JLabel lblSecondPolynomial = new JLabel("Second Polynomial");
		lblSecondPolynomial.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSecondPolynomial.setBounds(12, 116, 120, 16);
		contentPane.add(lblSecondPolynomial);
		
		txtSecondPolynomial = new JTextField();
		txtSecondPolynomial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSecondPolynomial.setBounds(144, 111, 371, 22);
		contentPane.add(txtSecondPolynomial);
		txtSecondPolynomial.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand("add");
		btnAdd.setBounds(12, 188, 112, 25);
		contentPane.add(btnAdd);
		
		btnSubstract = new JButton("Substract");
		btnSubstract.setActionCommand("sub");
		btnSubstract.setBounds(144, 188, 117, 25);
		contentPane.add(btnSubstract);
		
		btnMultiply = new JButton("Multiply");
		btnMultiply.setActionCommand("mul");
		btnMultiply.setBounds(280, 188, 118, 25);
		contentPane.add(btnMultiply);
		
		btnDivide = new JButton("Divide");
		btnDivide.setActionCommand("div");
		btnDivide.setBounds(418, 188, 97, 25);
		contentPane.add(btnDivide);
		
		btnDifferentiate = new JButton("Differentiate");
		btnDifferentiate.setActionCommand("dif");
		btnDifferentiate.setBounds(12, 234, 112, 25);
		contentPane.add(btnDifferentiate);
		
		btnIntegrate = new JButton("Integrate");
		btnIntegrate.setActionCommand("int");
		btnIntegrate.setBounds(12, 280, 112, 25);
		contentPane.add(btnIntegrate);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea.setBounds(12, 364, 564, 67);
		contentPane.add(textArea);
		
		btnCalcValueIn = new JButton("CalcValueIn");
		btnCalcValueIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCalcValueIn.setActionCommand("calc");
		btnCalcValueIn.setBounds(12, 326, 112, 25);
		contentPane.add(btnCalcValueIn);
		
		txtPointValue = new JTextField();
		txtPointValue.setBounds(144, 329, 116, 22);
		contentPane.add(txtPointValue);
		txtPointValue.setColumns(10);
		
		txtValueFrom = new JTextField();
		txtValueFrom.setBounds(193, 281, 116, 22);
		contentPane.add(txtValueFrom);
		txtValueFrom.setColumns(10);
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(136, 284, 56, 16);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(321, 284, 56, 16);
		contentPane.add(lblTo);
		
		txtValueTo = new JTextField();
		txtValueTo.setBounds(356, 281, 116, 22);
		contentPane.add(txtValueTo);
		txtValueTo.setColumns(10);
		this.setVisible(true);
	}
	
	//returns the user input for the first polynomial as a string
	public String getFirstPolynomial() {
		return txtFirstPolynomial.getText();
	}
	
	//returns the user input for the second polynomial as a string
	public String getSecondPolynomial() {
		return txtSecondPolynomial.getText();
	}
	
	//gets the value from the JTextField, which will be later used in calculation 
	public String getTxtPointValue() {
		return txtPointValue.getText();
	}
	
	public String getTxtValueFrom() {
		return txtValueFrom.getText();
	}
	
	public String getTxtValueTo() {
		return txtValueTo.getText();
	}
	
	//set the text area content to the result of the operations performed
	public void setTextArea(String data) {
		textArea.setText(data);
	}
	
	//used to display error messages
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	//set each button's actionlistener
	public void setBtnAddListener(ActionListener actionListener) {
		btnAdd.addActionListener(actionListener);
	}
	
	public void setBtnSubstractListener(ActionListener actionListener) {
		btnSubstract.addActionListener(actionListener);
	}
	
	public void setBtnMultiplyListener(ActionListener actionListener) {
		btnMultiply.addActionListener(actionListener);
	}
	
	public void setBtnDivideListener(ActionListener actionListener) {
		btnDivide.addActionListener(actionListener);
	}
	
	public void setBtnDifferentiateListener(ActionListener actionListener) {
		btnDifferentiate.addActionListener(actionListener);
	}
	
	public void setBtnIntegrateListener(ActionListener actionListener) {
		btnIntegrate.addActionListener(actionListener);
	}
	
	public void setBtnCalcValueInListener(ActionListener actionListener) {
		btnCalcValueIn.addActionListener(actionListener);
	}
}
