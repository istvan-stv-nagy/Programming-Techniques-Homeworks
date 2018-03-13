package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class MultiplicationTest {

	@Test
	public void testScalarMultiplication() {
		Polynomial p = new Polynomial();
		p.addMonomial(new Monomial(1,5));
		p.addMonomial(new Monomial(7,3));
		int scalar = 5;
		Polynomial q = new Polynomial();
		q.addMonomial(new Monomial(scalar, 0));
		Polynomial result = p.multiply(q);
		
		Polynomial real = new Polynomial();
		real.addMonomial(new Monomial(5,5));
		real.addMonomial(new Monomial(35,3));
		
		assertEquals(real, result);
	}
	
	@Test
	public void testComplexMultiplication() {
		Polynomial p = new Polynomial();
		p.addMonomial(new Monomial(1,2));
		p.addMonomial(new Monomial(2,1));
		p.addMonomial(new Monomial(7,0));
		
		Polynomial q = new Polynomial();
		q.addMonomial(new Monomial(1, 2));
		q.addMonomial(new Monomial(1, 0));
		Polynomial result = p.multiply(q);
		
		Polynomial real = new Polynomial();
		real.addMonomial(new Monomial(1,4));
		real.addMonomial(new Monomial(2,3));
		real.addMonomial(new Monomial(8,2));
		real.addMonomial(new Monomial(2,1));
		real.addMonomial(new Monomial(7,0));
		
		assertEquals(real, result);
	}

}
