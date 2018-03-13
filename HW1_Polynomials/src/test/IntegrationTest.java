package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Monomial;
import model.Polynomial;

public class IntegrationTest {

	@Test
	public void testSimpleIntegral() {
		Polynomial p = new Polynomial();
		p.add(new Monomial(3,2));
		p.add(new Monomial(4,1));
		p.add(new Monomial(7,0));
		
		Polynomial result = p.integrate();
		
		Polynomial real = new Polynomial();
		real.add(new Monomial(1,3));
		real.add(new Monomial(2,2));
		real.add(new Monomial(7,1));
		
		assertEquals(real, result);
	}
	
	@Test
	public void testIntegrationWithLimits() {
		Polynomial p = new Polynomial();
		p.add(new Monomial(3,2));
		p.add(new Monomial(4,1));
		p.add(new Monomial(7,0));
		
		int startValue = 1;
		int endValue = 2;
		double value = p.integrate(startValue, endValue);
			
		assertEquals(20.0, value, 0.0001);
	}

}
