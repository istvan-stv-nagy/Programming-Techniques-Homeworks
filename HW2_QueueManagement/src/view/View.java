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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {
	private static final long serialVersionUID = 7911439213138383723L;
	
	private JPanel contentPane;
	private JTextField textMinArrival;
	private JTextField textMaxArrival;
	private JTextField textMinService;
	private JTextField textMaxService;
	private JTextField textMaxQueues;
	private JTextField textSimulationTime;
	private JTextField textTolerance;
	
	private JTextField textQueueNumber;
	
	private JButton btnInfo;
	private JButton btnStart;
	
	private JTextArea textQueueInfo;
	private JTextArea textArea;
	private JTextField textHour;
	private JTextField textMinute;
	private JTextField textSec;

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("SHOP Simulator 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntervalOfArriving = new JLabel("INTERVAL OF ARRIVING TIME BETWEEN CUSTOMERS");
		lblIntervalOfArriving.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntervalOfArriving.setBounds(12, 41, 315, 39);
		contentPane.add(lblIntervalOfArriving);
		
		textMinArrival = new JTextField();
		textMinArrival.setBounds(339, 49, 70, 22);
		contentPane.add(textMinArrival);
		textMinArrival.setColumns(10);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(339, 24, 56, 16);
		contentPane.add(lblMin);
		
		textMaxArrival = new JTextField();
		textMaxArrival.setColumns(10);
		textMaxArrival.setBounds(456, 49, 70, 22);
		contentPane.add(textMaxArrival);
		
		JLabel lblMax = new JLabel("max");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setBounds(456, 24, 56, 16);
		contentPane.add(lblMax);
		
		JLabel lblServiceTimeDuration = new JLabel("SERVICE TIME DURATION");
		lblServiceTimeDuration.setBounds(12, 109, 187, 16);
		contentPane.add(lblServiceTimeDuration);
		
		textMinService = new JTextField();
		textMinService.setColumns(10);
		textMinService.setBounds(339, 106, 70, 22);
		contentPane.add(textMinService);
		
		textMaxService = new JTextField();
		textMaxService.setColumns(10);
		textMaxService.setBounds(456, 106, 70, 22);
		contentPane.add(textMaxService);
		
		JLabel lblMaxNumberOf = new JLabel("MAX NUMBER OF QUEUES");
		lblMaxNumberOf.setBounds(12, 162, 159, 16);
		contentPane.add(lblMaxNumberOf);
		
		textMaxQueues = new JTextField();
		textMaxQueues.setBounds(191, 159, 105, 22);
		contentPane.add(textMaxQueues);
		textMaxQueues.setColumns(10);
		
		JLabel lblSimulationTime = new JLabel("SIMULATION TIME");
		lblSimulationTime.setBounds(12, 212, 137, 16);
		contentPane.add(lblSimulationTime);
		
		textSimulationTime = new JTextField();
		textSimulationTime.setBounds(191, 209, 105, 22);
		contentPane.add(textSimulationTime);
		textSimulationTime.setColumns(10);
		
		JLabel lblWaitingTimeTolerance = new JLabel("WAITING TIME TOLERANCE");
		lblWaitingTimeTolerance.setBounds(12, 261, 159, 16);
		contentPane.add(lblWaitingTimeTolerance);
		
		textTolerance = new JTextField();
		textTolerance.setBounds(191, 258, 105, 22);
		contentPane.add(textTolerance);
		textTolerance.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 25));
		textArea.setBounds(12, 292, 946, 153);
		contentPane.add(textArea);
		
		textQueueInfo = new JTextArea();
		textQueueInfo.setBounds(772, 21, 186, 254);
		contentPane.add(textQueueInfo);
		
		btnStart = new JButton("Start");
		btnStart.setActionCommand("start");
		btnStart.setBounds(339, 158, 97, 25);
		contentPane.add(btnStart);
		
		btnInfo = new JButton("Info");
		btnInfo.setActionCommand("info");
		btnInfo.setBounds(651, 48, 97, 25);
		contentPane.add(btnInfo);
		
		JLabel lblQueue = new JLabel("Queue#");
		lblQueue.setBounds(591, 109, 56, 16);
		contentPane.add(lblQueue);
		
		textQueueNumber = new JTextField();
		textQueueNumber.setBounds(651, 106, 97, 22);
		contentPane.add(textQueueNumber);
		textQueueNumber.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(339, 209, 86, 22);
		contentPane.add(lblTime);
		
		textHour = new JTextField();
		textHour.setBounds(409, 209, 31, 22);
		contentPane.add(textHour);
		textHour.setColumns(10);
		
		textMinute = new JTextField();
		textMinute.setColumns(10);
		textMinute.setBounds(456, 209, 31, 22);
		contentPane.add(textMinute);
		
		textSec = new JTextField();
		textSec.setColumns(10);
		textSec.setBounds(499, 209, 31, 22);
		contentPane.add(textSec);
		this.setVisible(true);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void setTextQueueInfo(String content) {
		textQueueInfo.setText(content);
	}
	
	public void setTextArea(String content) {
		textArea.setText(content);
	}
	
	public String getTextQueueNumber() {
		return textQueueNumber.getText();
	}
	
	public void setBtnInfoListener(ActionListener listener) {
		btnInfo.addActionListener(listener);
	}
	
	public void setBtnStartListener(ActionListener listener) {
		btnStart.addActionListener(listener);
	}
	
	public String getTextMinArrival() {
		return textMinArrival.getText();
	}
	
	public String getTextMaxArrival() {
		return textMaxArrival.getText();
	}
	
	public String getTextMinService() {
		return textMinService.getText();
	}
	
	public String getTextMaxService() {
		return textMaxService.getText();
	}
	
	public String getTextMaxQueues() {
		return textMaxQueues.getText();
	}
	
	public String getTextSimulation() {
		return textSimulationTime.getText();
	}
	
	public String getTolerance() {
		return textTolerance.getText();
	}
	
	public String getHour() {
		return textHour.getText();
	}
	
	public String getMinute() {
		return textMinute.getText();
	}
	
	public String getSec() {
		return textSec.getText();
	}
}
