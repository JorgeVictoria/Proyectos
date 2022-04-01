package datojavaTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;


import org.hamcrest.CoreMatchers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AssumptionViolatedException;
import org.junit.jupiter.api.Test;








public class DatojavaJUnitTest {
	DatojavaJUnit datojavaJUnit = new DatojavaJUnit();

	@Test
	public void testSumar() {
		assertEquals("el resultado está mal: ",datojavaJUnit.sumar(3,8), 11);
	}

	@Test
	public void testRestar() {
		assertEquals(datojavaJUnit.restar(5,4),1);
	}
	
	@Test
	public void testAssertArrayEquals() {
		String[] nombresEsperados = { "java", "junit", "jboss" };
		String[] nombresActuales = { "java", "junit", "jboss" };
		assertArrayEquals("Fallo-No son los mismos arreglos",nombresEsperados,nombresActuales);
	}
	
	/*@Test
	public void testAssertArrayEquals2() {
		String[] nombresEsperados = { "java", "junit", "jboss" };
		String[] nombresActuales = { "java", "junit", "jon server" };
		assertArrayEquals("Fallo-No son los mismos arreglos",nombresEsperados,nombresActuales);
	}*/
	
	@Test
	public void testValidar() {
		assertFalse("Esta variable no es false: ", datojavaJUnit.validarMax(3));
	}
	
	@Test
	public void testAssertNotNull() {
		List<String> nombres = new ArrayList<String>();
		assertNotNull("La lista 'nombres' no esta null:", nombres);
		/*for(int i = 0; i < 3; i++) {
			if(i==2) {
				nombres = null;
			}
		}*/
		assertNotNull("La lista 'nombres' esta null:", nombres);
	}
	
	@Test
	public void testAssertNotSame() {
		boolean esMaximo = datojavaJUnit.validarMax(4); //el retorno es true
		boolean noEsMaximo = datojavaJUnit.validarMax(3); //el retorno es falso
		assertNotSame("Fallo - No son iguales los dos objetos:",esMaximo, noEsMaximo);
	}
	
	@Test
	public void testAssertNull() {
		String nombre = null;
		List<String>nombres=new ArrayList<String>();
		nombres.add("Java");
		nombres.add("JUnit");
		nombres.add("JBoss");
		for(String n:nombres) {
			if(n.equals("Pepe")) {
				nombre = "Practica JUnit Assertions"; //Asignamos un valor a la variable
				break;
			}
		}
		assertNull("No esta nulo el objeto", nombre);
	}
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testAssertThatArray() {
		String[] nombresEsperados = {"java", "junit" , "jboss" };
		String[] nombresActuales = {"java", "junit", "jon nieves" };
		assertThat("Fallo-No son los mismos arreglos", nombresActuales, is(nombresEsperados));
	}

	private Matcher is(String[] nombresEsperados) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}*/
	
	@Test
	public void testAssertTrue() {
		assertTrue("Esta variable no es false: ", datojavaJUnit.validarMax(4));
	}
	
	@Test
	public void testAssume() {
		CallableStatement callableStatement = null;
		Connection connection = null;
		connection = getConnection();
		assumeNotNull(connection);
	}
}
