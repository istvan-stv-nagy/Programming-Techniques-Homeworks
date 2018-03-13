package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
	
	private List<Monomial> monomials;	//list of monomials
	private int degree;						//degree of a polynomial(max between the degrees of the monomials)
	
	//default constructor, creates an empty constructor
	public Polynomial() {
		this.monomials = new ArrayList<Monomial>();
		degree = 0;
	}
	
	//constructor which is used to copy a content of one polynomial to another
	public Polynomial(List<Monomial> monomialList) {
		this.monomials = new ArrayList<Monomial>();
		degree = 0;
		for (Monomial m : monomialList) {
			//this.monomials.add(new Monomial(m.getCoefficient(), m.getDegree()));
			this.addMonomial(new Monomial(m.getCoefficient(), m.getDegree()));
		}
	}
	
	//adds a Monomial to the list of monomials
	public void addMonomial(Monomial monomial) {
		monomials.add(monomial);
		if (monomial.getDegree() > degree) {
			degree = monomial.getDegree();
		}
	}
	
	//adds a monomial to the polynomial
	public void add(Monomial monomial) {
		for (Monomial m : monomials) {
			//if two monomials have the same degree, add them and return
			if (m.getDegree() == monomial.getDegree()) {
				m.setCoefficient(m.getCoefficient() + monomial.getCoefficient());
				return;
			}
		}
		//otherwise, there is no monomial of such degree
		//add the monomial to the list of monomials
		monomials.add(new Monomial(monomial.getCoefficient(), monomial.getDegree()));
	}
	
	//substracts a monomial from a polynomial(same as addition)
	public void substract(Monomial monomial) {
		for (Monomial m : monomials) {
			if (m.getDegree() == monomial.getDegree()) {
				m.setCoefficient(m.getCoefficient() - monomial.getCoefficient());
				return;
			}
		}
		monomials.add(new Monomial(-1 * monomial.getCoefficient(), monomial.getDegree()));
	}
	
	//adds a polynomial to the current instance
	//return a new polynomial as a sum
	public Polynomial add(Polynomial p) {
		Polynomial result = new Polynomial(this.monomials); //copy the current instance in the result
		for (Monomial m : p.monomials) {
			result.add(m);	//add each monomial to the result
		}
		result.normalize();
		return result;
	}
	
	//subtracts a polynomial with the current instance
	//return a new polynomial as a difference
	public Polynomial substract(Polynomial p) {
		Polynomial result = new Polynomial(this.monomials);
		for (Monomial m : p.monomials) {
			result.substract(m);
		}
		result.normalize();
		return result;
	}
	
	//multiplies a polynomial with the current instance
	//return a new polynomial as a product
	public Polynomial multiply(Polynomial p) {
		Polynomial result = new Polynomial();
		for (Monomial m : monomials) {
			for (Monomial n : p.monomials) {
				result.add(n.multiply(m));
			}
		}
		result.normalize();
		return result;
	}
	
	//calculates the value of the polynomial in a given point
	public double getValueInPoint(double value) {
		double result = 0;
		for (Monomial m : monomials) {
			result += m.getCoefficient() * Math.pow(value, m.getDegree());
		}
		return result;
	}
	
	//differentiate the current istance of polynomial
	//returns a new polynomial as the derivative
	public Polynomial differentiate() {
		Polynomial result = new Polynomial();
		for (Monomial m : monomials) {
			if (m.getDegree() > 0)
				result.addMonomial(m.differentiate());
		}
		result.normalize();
		return result;
	}
	
	//integrate the current istance of polynomial
	//returns a new polynomial as the integral
	//INDEFINITE integration
	public Polynomial integrate() {
		Polynomial result = new Polynomial();
		for (Monomial m : monomials) {
			result.addMonomial(m.integrate());
		}
		result.normalize();
		return result;
	}
	
	//DEFINITE integration
	//from startValue to endValue
	public double integrate(double startValue, double endValue) {
		return this.integrate().getValueInPoint(endValue) - this.integrate().getValueInPoint(startValue);
	}
	
	//divides the current instance with the polynomial passed as a parameter
	//returns an array of polynomials
	//the first element is the quotient, the second is the remainder
	public Polynomial[] divide(Polynomial p) {
		this.normalize();
		p.normalize();
		Polynomial[] result = new Polynomial[2];
		Polynomial quatient = new Polynomial();
		Polynomial remainder = new Polynomial(this.monomials);
		if (p.isNull()) {
			return null;
		} 
		else if (this.isNull()) {
			result[0] = this;
			result[1] = p;
			return result;
		}
		else if (this.degree() < p.degree()) {
			result[0] = new Polynomial();
			result[0].addMonomial(new Monomial(0,0));
			result[1] = this;
			return result;
		}
		while (!remainder.isNull() && remainder.degree() >= p.degree()) {
			Monomial dividingMonomial = remainder.monomials.get(0).divide(p.monomials.get(0));
			quatient.add(dividingMonomial);
			Polynomial dividingPolynomial = new Polynomial();
			dividingPolynomial.addMonomial(dividingMonomial);
			remainder = remainder.substract(p.multiply(dividingPolynomial));
			remainder.normalize();
		}
		result[0] = quatient;
		result[1] = remainder;
		return result;
	}
	
	//returns true if the polynomial is empty(no monomials) or zero
	public boolean isNull() {
		if ((monomials.size() == 0) || (monomials.size() == 1 && monomials.get(0).getCoefficient() == 0))
			return true;
		return false;
	}
	
	public int degree() {
		return monomials.get(0).getDegree();
	}
	
	//returns the String version of a polynomial, which will be later displayed
	public String toString() {
		String result = "";
		for (Monomial m : monomials) {
			result += m.toString();
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		other.normalize();
		if (this.monomials.size() != other.monomials.size())
			return false;
		else {
			for (int i = 0; i<this.monomials.size(); i++) {
				if ((this.monomials.get(i).getCoefficient() != other.monomials.get(i).getCoefficient()) || (this.monomials.get(i).getDegree() != other.monomials.get(i).getDegree()))
					return false;
			}
		}
		return true;
	}

	//brings the polynomial to a normal form(no zero coefficients, only if the polynomial is zero, monomials in decreasing order of degrees)
	public void normalize() {
		for (int i=monomials.size()-1; i>=0; i--) {
			if (monomials.get(i).getCoefficient() == 0) {
				monomials.remove(i);
			}
		}
		if (monomials.size() == 0)
			monomials.add(new Monomial(0, 0));
		else
			Collections.sort(monomials);	//sort the polynomial in descending order of the monomials' degree
	}
}
