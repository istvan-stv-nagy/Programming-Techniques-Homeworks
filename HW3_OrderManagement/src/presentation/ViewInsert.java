package presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * the view which appears when the user inserts a customer
 * @author Istvan
 *
 */
public class ViewInsert extends JFrame {
	private static final long serialVersionUID = 2149487961282322411L;
	private JPanel contentPane;
	JTextField textId;
	JTextField textName;
	JTextField textTel;
	
	private JButton btnInsert;

	public ViewInsert() {
		setTitle("Insert Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(132, 38, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(12, 38, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblTel = new JLabel("tel");
		lblTel.setBounds(282, 38, 56, 16);
		contentPane.add(lblTel);
		
		textId = new JTextField();
		textId.setBounds(12, 67, 56, 22);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(132, 67, 116, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(282, 67, 116, 22);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("insertcustomerconfirm");
		btnInsert.setBounds(132, 173, 97, 25);
		contentPane.add(btnInsert);
		
		this.setVisible(true);
	}
	
	public JButton getBtnInsert() {
		return btnInsert;
	}
	
	public void addBtnListener(ActionListener listener) {
		btnInsert.addActionListener(listener);
	}
}
