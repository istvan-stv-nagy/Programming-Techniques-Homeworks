package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class AdditionTest {

	@Test
	public void testSimpleAddition() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(2,2));
		p.add(new Monomial(3, 1));
		p.add(new Monomial(1, 0));
		
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(3,2));
		q.add(new Monomial(5, 1));
		q.add(new Monomial(5, 0));
		
		//calling the method
		Polynomial r = p.add(q);
		
		//the real result
		Polynomial real = new Polynomial();
		real.add(new Monomial(5,2));
		real.add(new Monomial(8,1));
		real.add(new Monomial(6,0));
		
		assertEquals(real, r);
	}
	
	@Test
	public void testComplexAddition() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(5,25));
		p.add(new Monomial(35, 12));
		p.add(new Monomial(4, 4));
		
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(5,27));
		q.add(new Monomial(5, 12));
		q.add(new Monomial(125, 0));
		
		//calling the method
		Polynomial r = p.add(q);
		
		//the real result
		Polynomial real = new Polynomial();
		real.add(new Monomial(5,27));
		real.add(new Monomial(5,25));
		real.add(new Monomial(40,12));
		real.add(new Monomial(4,4));
		real.add(new Monomial(125,0));
		
		assertEquals(real, r);
	}

}
