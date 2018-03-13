package model;

/**
 * Monomial: class for polynomial processing
 * used as a component of a polynomial
 */

public class Monomial implements Comparable<Monomial> {
	
	private double coefficient;		//coefficient of the monomial
	private int degree;				//degree
	
	public Monomial() {
		this(0, 0);
	}
	
	public Monomial(double coefficient, int degree) {
		this.coefficient = coefficient;
		this.degree = degree;
	}
	
	//returns a new Monomial which is the result of the current instance
	//multiplied with the monomial passed as a parameter
	public Monomial multiply(Monomial m) {
		return new Monomial(this.coefficient * m.coefficient, this.degree + m.degree);
	}
	//returns a new Monomial which is the result of the current instance
	//divided with the monomial passed as a parameter
	public Monomial divide(Monomial m) {
		return new Monomial(this.coefficient / m.coefficient, this.degree - m.degree);
	}
	public Monomial differentiate() {
		return new Monomial(this.coefficient * this.degree, this.degree - 1);
	}
	public Monomial integrate() {
		return new Monomial(this.coefficient / (this.degree + 1), this.degree + 1);
	}
	
	//used for comparing two monomials
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monomial other = (Monomial) obj;
		if (coefficient != other.coefficient)
			return false;
		if (degree != other.degree)
			return false;
		return true;
	}
	
	//used to compare two monomials
	@Override
	public int compareTo(Monomial monomial) {
		if (degree < monomial.degree)
			return 1;
		else if (degree > monomial.degree)
			return -1;
		else
			return 0;
	}
	
	//returns a String which will be later displayed in the view
	public String toString() {
		String string = "";
		if (coefficient > 0) {
			string += "+";
		} 
		if (coefficient == -1 && degree != 0)
			string += "-";		
		else if (coefficient != 1 || degree == 0) {
			if (coefficient - (int)coefficient == 0)
				string += (int)coefficient;
			else
				string += coefficient;
		}
		if (degree > 1) {
			string += "X^" + degree + " "; 
		} else if (degree == 1) {
			string += "X" + " ";
		}
		return string;
	}
	
	public double getCoefficient() {
		return coefficient;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
}
