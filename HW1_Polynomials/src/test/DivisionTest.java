package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class DivisionTest {

	@Test
	public void testNoRemainder() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(1,2));
		p.add(new Monomial(2, 1));
		p.add(new Monomial(1, 0));
		
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(1,1));
		q.add(new Monomial(1, 0));
		
		//calling the method
		Polynomial[] r = new Polynomial[2];
		r = p.divide(q);
		
		//the real result
		Polynomial realQuatient = new Polynomial();
		realQuatient.add(new Monomial(1,1));
		realQuatient.add(new Monomial(1,0));
		Polynomial realRemainder = new Polynomial();
		realRemainder.add(new Monomial(0, 0));
		
		assertEquals(realQuatient, r[0]);
		assertEquals(realRemainder, r[1]);
	}
	
	@Test
	public void testWithRemainder() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(1,3));
		p.add(new Monomial(2, 1));
		p.add(new Monomial(7, 0));
		
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(1,1));
		q.add(new Monomial(1, 0));
		
		//calling the method
		Polynomial[] r = new Polynomial[2];
		r = p.divide(q);
		
		//the real result
		Polynomial realQuatient = new Polynomial();
		realQuatient.add(new Monomial(1,2));
		realQuatient.add(new Monomial(-1,1));
		realQuatient.add(new Monomial(3,0));
		Polynomial realRemainder = new Polynomial();
		realRemainder.add(new Monomial(4, 0));
		
		assertEquals(realQuatient, r[0]);
		assertEquals(realRemainder, r[1]);
	}
	
	@Test
	public void testSmallerDegree() {
		//first polynomial
		Polynomial p = new Polynomial();
		p.add(new Monomial(1,3));
		p.add(new Monomial(2, 1));
		p.add(new Monomial(7, 0));
		
		//second polynomial
		Polynomial q = new Polynomial();
		q.add(new Monomial(1,1));
		q.add(new Monomial(1, 0));
		
		//calling the method
		Polynomial[] r = new Polynomial[2];
		r = q.divide(p);
		
		Polynomial realQuatient = new Polynomial();
		realQuatient.add(new Monomial(0, 0));
		
		assertEquals(realQuatient, r[0]);
		assertEquals(q, r[1]);
	}

}
