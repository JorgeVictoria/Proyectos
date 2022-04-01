package jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculadoraTest.class, CalculadoraTest2.class, CalculadoraTest3.class, CalculadoraTest4.class })
public class AllTests {

}
