package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Monomial;
import model.Polynomial;
import view.View;

public class Controller{
	//gets the input from the view
	//uses the model(polynomials) to perform the the requested operation
	//sets the view content according to the result
	
	private Polynomial first;	//the two operands
	private Polynomial second;
	
	private View view;			//the user interface
	
	
	public Controller(Polynomial first, Polynomial second, View view) {
		this.first = first;
		this.second = second;
		this.view = view;
		BtnListener btnListener = new BtnListener();
		view.setBtnAddListener(btnListener);
		view.setBtnSubstractListener(btnListener);
		view.setBtnMultiplyListener(btnListener);
		view.setBtnDivideListener(btnListener);
		view.setBtnDifferentiateListener(btnListener);
		view.setBtnIntegrateListener(btnListener);
		view.setBtnCalcValueInListener(btnListener);
	}
	
	//converts a string given as a parameter to a polynomial
	//returns a valid polynomial if the string has a valid format
	//if the string has an invalid format, an exception is thrown, and the method returns null
	private Polynomial stringToPolynomial(String data){
		try{
		Polynomial result = new Polynomial();
		data = data.replaceAll("X", "x");
		data = data.replaceAll("\\-", "+-");
		data = data.replaceAll("x\\^", "x");
		//splits the polynomial into monomials
		String[] monom = data.split("\\+"); //split the polynomials with respect to the + character
		for (String s : monom) {
			//for each monomial, split the monomial into coefficient and degree, if it is possible
			double coeff;
			int deg;
			if (!s.equals("")) {
				if (s.contains("x")) {
					String[] comp = s.split("x");	//split the monomial with respect to x(coefficient on the left, degree on the right)
					if (comp.length >= 1) {
						if (comp[0].equals("-")) {
							coeff = -1;
						} else if (comp[0].equals("")) {
							coeff = 1;
						} else {
							coeff = Integer.parseInt(comp[0]);
						}
						if (comp.length > 1) {
							deg = Integer.parseInt(comp[1]);
						} else {
							deg = 1;
						}
						result.add(new Monomial(coeff, deg));
					} else {
						result.add(new Monomial(1, 1));
					}
				} else {
					result.add(new Monomial(Integer.parseInt(s), 0));
				}
			}
		}
		return result;
		} catch (NumberFormatException e) {
			//the format of the input string is invalid
			view.showMessage("Invalid Polynomial!"); //show an error message in the view
			return null;
		}
	}

	class BtnListener implements ActionListener {
		//verifies which button was pressed, and performs the corresponding operations
		//set the content of the view
		public void actionPerformed(ActionEvent e) {
			first = stringToPolynomial(view.getFirstPolynomial());		//get the first polynomial(user input)
			second = stringToPolynomial(view.getSecondPolynomial());	//get the second polynomial
			Polynomial[] result;										//result
			String[] message = new String[2];							//message which describe the result
			if (first != null && second != null) {
				switch (e.getActionCommand()) {
				case "add" :
					result = new Polynomial[1];
					result[0] = first.add(second);
					message[0] = "P1(x) + P2(x) = ";
					message[1] = null;
					break;
				case "sub" :
					result = new Polynomial[2];
					result[0] = first.substract(second);
					result[1] = second.substract(first);
					message[0] = "P1(x) - P2(x) = ";
					message[1] = "P2(x) - P1(x) = ";
					break;
				case "mul" :
					result = new Polynomial[1];
					result[0] = first.multiply(second);
					message[0] = "P1(x) * P2(x) = ";
					message[1] = null;
					break;
				case "div" :
					result = new Polynomial[1];
					result = first.divide(second);
					if (result != null) {
						message[0] = "Quatient[P1(x) / P2(x)] = ";
						message[1] = "Remainder[P1(x) / P2(x)] = ";
					}
					else {
						if (second.isNull())
							view.showMessage("Cannot divide by 0!");
						else
							view.showMessage("Error in division!");
					}
					break;
				case "dif" :
					result = new Polynomial[2];
					result[0] = first.differentiate();
					result[1] = second.differentiate();
					message[0] = "P1(x)' = ";
					message[1] = "P2(x)' = ";
					break;
				case "int" :
					if (view.getTxtValueFrom().equals("") && view.getTxtValueTo().equals("")) {
						result = new Polynomial[2];
						result[0] = first.integrate();
						result[1] = second.integrate();
						message[0] = "integrate[P1(x)] = ";
						message[1] = "integrate[P2(x)] = ";
						break;
					} else {
						try{
						result = null;
						double res = first.integrate(Double.parseDouble(view.getTxtValueFrom()), Double.parseDouble(view.getTxtValueTo()));
						String msg = "integrate[P1(x)] from " + view.getTxtValueFrom() + " to " + view.getTxtValueTo() + " = " + res+"\n";
						res = second.integrate(Double.parseDouble(view.getTxtValueFrom()), Double.parseDouble(view.getTxtValueTo()));
						msg += "integrate[P1(x)] from " + view.getTxtValueFrom() + " to " + view.getTxtValueTo() + " = " + res+"\n";
						view.setTextArea(msg);
						} catch (NumberFormatException ex) {
							view.showMessage("Invalid upper/lower limit of integration!");
							result = null;
						}
					}
					break;
				case "calc" :
					result = null;
					try{
					double value = Double.parseDouble(view.getTxtPointValue());
					String msg;
					msg = "P1(" + value + ") = " + first.getValueInPoint(value)+"\n";
					msg += "P2(" + value + ") = " + second.getValueInPoint(value) + "\n";
					view.setTextArea(msg);
					} catch (NumberFormatException ex) {
						view.showMessage("Invalid value : " + view.getTxtPointValue());
					}
				default :
					result = null;
					break;
				}
				
				//compute the result as a string and set it to the text area in the view
				String resultString="";
				if (result != null) {
					for (int i=0; i<result.length; i++) {
						if (message[i] != null)
							resultString += message[i];
						resultString += result[i].toString() + "\n";
					}
					view.setTextArea(resultString);
				}
			}
		}
	}	
}
