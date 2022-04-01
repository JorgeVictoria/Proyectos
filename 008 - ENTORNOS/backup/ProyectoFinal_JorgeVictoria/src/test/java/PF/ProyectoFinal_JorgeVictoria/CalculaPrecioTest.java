package PF.ProyectoFinal_JorgeVictoria;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Comentado por Gras
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

//Comentado por Gras
//import junit.framework.Assert;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;

//import static org.junit.Assert.assertEquals;


public class CalculaPrecioTest {

	static TaquillaControler calc1;
	static TaquillaControler calc2;
	static TaquillaControler calc3;
	
	

	@BeforeAll
	public static void inicio() {
		
		calc1 = new TaquillaControler();
		calc2 = new TaquillaControler();
		calc3 = new TaquillaControler();
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculaPrecioTest() {
		
		//Comentado por Gras
		//Assert.assertEquals(9.0,calc1.calcularPrecio(0));
		
		//Añadido por Gras
		calc2 = new TaquillaControler();
		
		
		assertEquals(8.1,calc2.calcularPrecio(10));
		//Comentado por Gras
		//Assert.assertEquals(4.5,calc3.calcularPrecio(50));
		
	}

}
