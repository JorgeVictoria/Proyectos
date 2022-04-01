package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculadoraTest {

	@Test
	public void testSuma() {
		Calculadora calcu = new Calculadora(20,10);
		int resultado = calcu.suma();
		assertEquals(30, resultado);
		//fail("No implementado aun");
	}

	@Test
	public void testResta() {
		Calculadora calcu = new Calculadora(20,10);
		int resultado = calcu.resta();
		assertEquals(10, resultado);
		//fail("No implementado aun");
	}

	@Test
	public void testMultiplica() {
		Calculadora calcu = new Calculadora(20,10);
		int resultado = calcu.multiplica();
		assertEquals("Fallo en la multiplicacio",200, resultado);
		//fail("No implementado aun");
	}

	@Test
	public void testDivide() {
		Calculadora calcu = new Calculadora(20,10);
		int resultado = calcu.divide();
		assertEquals(2, resultado);
		//fail("No implementado aun");
	}
	
	@Test
	public void testException() {
		try {
			Calculadora calcu = new Calculadora(20,0);
			int resultado = calcu.divide();
			fail ("Fallo, deberia haber lanzado la excepcion");
		}
		catch (ArithmeticException e) {
			//prueba satisfactoria
		}
	}
	
	@Test(expected = java.lang.ArithmeticException.class)
	public void testDivide0() {
		Calculadora calcu = new Calculadora(20,0);
		int resultado = calcu.divide();
	}

}
