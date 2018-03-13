package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdditionTest.class, DifferentiationTest.class, DivisionTest.class, IntegrationTest.class,
		MultiplicationTest.class, SubstractionTest.class })
public class TestAllOperations {

}
