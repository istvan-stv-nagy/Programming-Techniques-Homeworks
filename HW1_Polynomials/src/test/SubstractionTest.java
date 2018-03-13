package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class SubstractionTest {

	@Test
	public void testSimpleSubstraction() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(5,4));
		p.add(new Monomial(3, 2));
		p.add(new Monomial(1, 0));
				
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(-2,2));
		q.add(new Monomial(5, 0));
		
		//calling the method
		Polynomial r = p.substract(q);
		
		//the real result
		Polynomial real = new Polynomial();
		real.add(new Monomial(5,4));
		real.add(new Monomial(5,2));
		real.add(new Monomial(-4,0));
		
		assertEquals(real, r);
	}
	
	@Test
	public void testZeroSubstraction() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(5,4));
		p.add(new Monomial(3, 2));
		p.add(new Monomial(1, 0));
				
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(5,4));
		q.add(new Monomial(3, 2));
		q.add(new Monomial(1, 0));
		
		//calling the method
		Polynomial r = p.substract(q);
		
		//the real result
		Polynomial real = new Polynomial();
		real.add(new Monomial(0,0));
		
		assertEquals(real, r); 
	}

}
