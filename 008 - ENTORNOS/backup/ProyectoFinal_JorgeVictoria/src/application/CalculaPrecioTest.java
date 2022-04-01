package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class CalculaPrecioTest {

	static TaquillaControler calc1;
	static TaquillaControler calc2;
	static TaquillaControler calc3;
	
	

	@BeforeAll
	static void inicio() {
		
		calc1 = new TaquillaControler();
		calc2 = new TaquillaControler();
		calc3 = new TaquillaControler();
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculaPrecioTest() {
		
		Assert.assertEquals(9.0,calc1.calcularPrecio(0));
		Assert.assertEquals(8.1,calc2.calcularPrecio(10));
		Assert.assertEquals(4.5,calc3.calcularPrecio(50));
		
	}

}
