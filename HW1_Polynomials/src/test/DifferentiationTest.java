package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class DifferentiationTest {

	@Test
	public void testDifferentiation() {
		Polynomial p = new Polynomial();
		p.add(new Monomial(3,2));
		p.add(new Monomial(4,1));
		p.add(new Monomial(7,0));
		
		Polynomial result = p.differentiate();
		
		Polynomial real = new Polynomial();
		real.add(new Monomial(6,1));
		real.add(new Monomial(4,0));
		
		assertEquals(real, result);
	}

}
