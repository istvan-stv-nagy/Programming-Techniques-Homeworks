package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import view.ViewAccount;
import view.ViewPerson;

public class Controller implements Observer {

	private Bank bank;
	private ViewPerson viewPerson;
	private ViewAccount viewAccount;
	private MouseTableListener tableListener;
	
	public Controller(Bank bank, ViewPerson viewPerson, ViewAccount viewAccount) {
		this.bank = bank;
		this.viewPerson = viewPerson;
		this.viewAccount = viewAccount;
		this.tableListener = new MouseTableListener();
		BtnListener listener = new BtnListener(this);
		this.viewPerson.setBtnListener(listener);
		this.viewAccount.setBtnListener(listener);
		for (Person p : bank.getAccounts().keySet()) {
			p.addObserver(this);
		}
		
		WindowAdapter windowListener = new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(viewPerson, 
		            "Do you want to save?", "Save?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	bank.write();
		        }
		    }
		};
				
		viewPerson.addWindowListener(windowListener);
		viewAccount.addWindowListener(windowListener);
	}
	
	private Person getSelectedPerson() {
		int row = viewPerson.getTable().getSelectedRow();
		int id = Integer.parseInt(viewPerson.getTable().getValueAt(row, 0).toString());
		String name = viewPerson.getTable().getValueAt(row, 1).toString();
		return new Person(id,name);
	}
	
	public class MouseTableListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent evt) {
			int row = viewPerson.getTable().rowAtPoint(evt.getPoint());
	        int col = viewPerson.getTable().columnAtPoint(evt.getPoint());
	        if (row >= 0 && col >= 0) {
	        	int id = Integer.parseInt(viewPerson.getTable().getValueAt(row, 0).toString());
	        	String name = viewPerson.getTable().getValueAt(row, 1).toString();
	        	viewAccount.setTable(Controller.createTable(bank.listAccounts(new Person(id,name))));
	        }
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		
	}
	
	public class BtnListener implements ActionListener {
		
		private Controller controller;
		
		public BtnListener(Controller controller) {
			this.controller = controller;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id, row;
			String name;
			
			switch (e.getActionCommand()) {
			case "listperson":
				viewPerson.setTable(createTable(bank.listPersons()));
				viewPerson.getTable().addMouseListener(tableListener);
				break;
			case "addperson" :
				try {
					id = Integer.parseInt(viewPerson.getTextId());
					name = viewPerson.getTextName();
					Person p = new Person(id, name);
					if (!bank.containsID(id)) {
						bank.add(p);
						p.addObserver(controller);
						viewPerson.setTable(createTable(bank.listPersons()));
						viewPerson.getTable().addMouseListener(tableListener);
						viewPerson.showMessage("Person added successfully!");
					} else {
						viewPerson.showMessage("Person with the given id already exists!");
					}
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			case "delperson" :
				row = viewPerson.getTable().getSelectedRow();
				id = Integer.parseInt(viewPerson.getTable().getValueAt(row, 0).toString());
				name = viewPerson.getTable().getValueAt(row, 1).toString();
				bank.remove(new Person(id, name));
				viewPerson.setTable(createTable(bank.listPersons()));
				viewPerson.getTable().addMouseListener(tableListener);
				viewPerson.showMessage("Person removed successfully!");
				break;
			case "editperson" :
				try {
					row = viewPerson.getTable().getSelectedRow();
					id = Integer.parseInt(viewPerson.getTable().getValueAt(row, 0).toString());
					name = viewPerson.getTable().getValueAt(row, 1).toString();
					int newid = Integer.parseInt(viewPerson.getTextId());
					String newName = viewPerson.getTextName();
					bank.update(new Person(id, name), new Person(newid,newName));
					viewPerson.setTable(createTable(bank.listPersons()));
					viewPerson.getTable().addMouseListener(tableListener);
					viewPerson.showMessage("Person updated successfully!");
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			case "addacc" :
				try {
					if (viewAccount.getRdBtnSavingAccount().isSelected()) {
						//create a new saving account for a given person
						float sum = Float.parseFloat(viewAccount.getBalance());
						float interest = Float.parseFloat(viewAccount.getInterest());
						bank.add(getSelectedPerson(), new SavingAccount(sum,interest, bank.getAccounts(getSelectedPerson()).size() + 1));
						viewAccount.showMessage("Saving account successfully added to " + getSelectedPerson().toString() + "!");
					} else if (viewAccount.getRdBtnSpendingAccount().isSelected()) {
						bank.add(getSelectedPerson(), new SpendingAccount(bank.getAccounts(getSelectedPerson()).size() + 1));
						viewAccount.showMessage("Spending account successfully added to " + getSelectedPerson().toString() + "!");
					}
					viewAccount.setTable(createTable(bank.listAllAccounts()));
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			case "listacc" :
				viewAccount.setTable(createTable(bank.listAllAccounts()));
				break;
			case "delacc" :
				int number = Integer.parseInt(viewAccount.getTable().getValueAt(viewAccount.getTable().getSelectedRow(), 1).toString());
				bank.remove(getSelectedPerson(), bank.getAccounts(getSelectedPerson()).get(number - 1));
				bank.refresh(getSelectedPerson());
				viewAccount.showMessage("Account removed successfully!");
				viewAccount.setTable(createTable(bank.listAllAccounts()));
				break;
			case "editacc" :
				try {
					float sum = Float.parseFloat(viewAccount.getBalance());
					number = Integer.parseInt(viewAccount.getTable().getValueAt(viewAccount.getTable().getSelectedRow(), 1).toString());
					bank.update(getSelectedPerson(), number - 1, sum);
					viewAccount.showMessage("Account updated successfully!");
					viewAccount.setTable(createTable(bank.listAllAccounts()));
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			case "deposit" :
				try {
					float amount = Float.parseFloat(viewAccount.getAmount());
					number = Integer.parseInt(viewAccount.getTable().getValueAt(viewAccount.getTable().getSelectedRow(), 1).toString());
					if(bank.getAccounts(getSelectedPerson()).get(number-1).deposit(amount)) {
						viewAccount.showMessage("Deposit was completed! A total of " + amount + " was deposited!");
						viewAccount.setTable(createTable(bank.listAllAccounts()));
					}
					else {
						viewAccount.showMessage("Cannot deposit money!");
					}
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			case "withdraw" :
				try {
				number = Integer.parseInt(viewAccount.getTable().getValueAt(viewAccount.getTable().getSelectedRow(), 1).toString());
				String type = viewAccount.getTable().getValueAt(viewAccount.getTable().getSelectedRow(), 2).toString();
				if (type.equals("spending")) {
					float amount = Float.parseFloat(viewAccount.getAmount());
					float money = bank.getAccounts(getSelectedPerson()).get(number-1).withdraw(amount);
					if(money > 0) {
						viewAccount.showMessage("Withdraw was completed! A total of " + money + " was withdrawn!");
						viewAccount.setTable(createTable(bank.listAllAccounts()));
					}
					else {
						viewAccount.showMessage("Cannot withdraw money!");
					}
				}
				else if (type.equals("saving")) {
					int months = Integer.parseInt(viewAccount.getMonths());
					float money = bank.getAccounts(getSelectedPerson()).get(number-1).withdraw(months);
					if (money > 0) {
						viewAccount.showMessage("Withdraw was completed! A total of " + money + " was withdrawn and the account was deactivated!");
						viewAccount.setTable(createTable(bank.listAllAccounts()));
					}
					else {
						viewAccount.showMessage("Cannot withdraw money!");
					}
				}
				} catch (NumberFormatException ex) {
					viewPerson.showMessage("Invalid data!");
				}
				break;
			}
		}
		
	}
	
	private static JTable createTable(List<Object> objects) {
		if (objects.size() == 0)
			return null;
		JTable table = null;
		int numberOfColumns = objects.get(0).getClass().getDeclaredFields().length - 1;
		String columns[] = new String[numberOfColumns];
		int i = 0;
		for (Field field : objects.get(0).getClass().getDeclaredFields()) {
			if (!field.getName().equals("serialVersionUID")) {
				columns[i] = field.getName();
				i++;
			}
		}
		String data[][] = new String[objects.size()][numberOfColumns];
		int row = 0;
		for (Object obj : objects) {
			int col = 0;
			for (Field field : obj.getClass().getDeclaredFields()) {
				if (!field.getName().equals("serialVersionUID")) {
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
			}
			row++;
		}
		table = new JTable(data,columns);
		return table;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			viewPerson.showMessage(arg.toString());
		}
	}	
}
