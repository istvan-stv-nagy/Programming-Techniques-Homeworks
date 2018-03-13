package presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * the view which appears when the user inserts a product
 * @author Istvan
 *
 */
public class ViewInsertProduct extends JFrame {
	private static final long serialVersionUID = -3681542030306446847L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textAmount;
	private JTextField textRating;
	private JTextField textCategory;
	private JButton btnInsert;

	public ViewInsertProduct() {
		setTitle("Insert Product");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(12, 13, 56, 16);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(102, 10, 116, 22);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(12, 48, 56, 16);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBounds(102, 45, 116, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(12, 83, 56, 16);
		contentPane.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setBounds(102, 80, 116, 22);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		textAmount = new JTextField();
		textAmount.setBounds(102, 115, 116, 22);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("amount");
		lblAmount.setBounds(12, 118, 56, 16);
		contentPane.add(lblAmount);
		
		textRating = new JTextField();
		textRating.setBounds(102, 150, 116, 22);
		contentPane.add(textRating);
		textRating.setColumns(10);
		
		JLabel lblRating = new JLabel("rating");
		lblRating.setBounds(12, 153, 56, 16);
		contentPane.add(lblRating);
		
		textCategory = new JTextField();
		textCategory.setBounds(102, 185, 116, 22);
		contentPane.add(textCategory);
		textCategory.setColumns(10);
		
		JLabel lblCategoryid = new JLabel("categoryID");
		lblCategoryid.setBounds(12, 188, 66, 16);
		contentPane.add(lblCategoryid);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("confirminsertproduct");
		btnInsert.setBounds(270, 184, 97, 25);
		contentPane.add(btnInsert);
		this.setVisible(true);
	}
	
	public JButton getBtnInsert() {
		return btnInsert;
	}
	
	public void addBtnListener(ActionListener listener) {
		btnInsert.addActionListener(listener);
	}
	
	public String getTextId() {
		return textId.getText();
	}
	public String getTextName() {
		return textName.getText();
	}
	public String getTextPrice() {
		return textPrice.getText();
	}
	public String getTextAmount() {
		return textAmount.getText();
	}
	public String getTextRating() {
		return textRating.getText();
	}
	public String getTextCategory() {
		return textCategory.getText();
	}
}
