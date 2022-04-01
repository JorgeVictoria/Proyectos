package empresas;

public class Empresa {

	public static void main(String[] args) {
		//variables locales
		Empleado[] empresa = new Empleado[4]; //creamos el array de empleados
		
		//creamos 2 repartidores
		empresa[0] = new Repartidor ("Pepe","01/01/1981","01/01/1999",800);
		empresa[1] = new Repartidor ("Juan","02/02/1982","02/02/2000",800);
		
		//creamos 2 comerciales
		empresa[2] = new Comercial ("Manuel","03/03/1983","03/03/2001",800);
		empresa[3] = new Comercial ("Laura","04/04/1984","04/04/2002",850);
		
		//creamos 2 productos
		Producto prod1 = new Producto("yogur Yoplait", 1, 0.1);
		Producto prod2 = new Producto("PCX 125", 2849, 0.2);
		
		//creamos una venta, hacemos casting para poder pasar los objetos a la clase ventas
		Ventas ven1 = new Ventas((Comercial)empresa[2], (Repartidor)empresa[0], prod2, 2);
		
		//vamos a suponer que ven1 ha sido entregada
		ven1.setEntregado(true);
		
		//vemos los datos
		for(int i = 0; i < empresa.length; i++) {
			String cadena = empresa[i].toString();
			System.out.println(cadena);
		}

	}

}
