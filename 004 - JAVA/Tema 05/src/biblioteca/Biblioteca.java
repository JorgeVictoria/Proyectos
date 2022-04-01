package biblioteca;

public class Biblioteca {
	
	//el Exception hay que ponerlo porque tenemos usamos excepcion en el metodo compruebaISBN()
	public static void main(String[] args) throws Exception {
		
		//creamos 2 libros
		Libro libro1 = new Libro("El codigo da Vinci","Dan Brown");
		Libro libro2 = new Libro("El Señor de los Anillos", "J.R. Tolkien");
		
		//creamos la revista
		Revista revista1 = new Revista("Hobby Consolas", 3);
		
		//creamos los ejemplares
		Ejemplar ejemplar1 = new Ejemplar("Planeta", "1234567890123", libro1);
		Ejemplar ejemplar2 = new Ejemplar("Altaya", "1122334455667", libro2);
		Ejemplar ejemplar3 = new Ejemplar("Hobby Press", "1234567891011", revista1);
		
		//creamos un socio
		Socio socio1 = new Socio();
		socio1.setCarnet();
		socio1.setNombre("Jorge");
		socio1.setBloqueado(false);
		
		//creamos los prestamos
		Prestamo prestamo1 = new Prestamo();
		Prestamo prestamo2 = new Prestamo();
		prestamo1 = socio1.solicitaPrestamo(ejemplar1);
		prestamo2 = socio1.solicitaPrestamo(ejemplar2);
		
		//devolvemos 1
		socio1.devolverPrestamos(prestamo1);
		
		//vemos la info
		System.out.println(ejemplar1.toString());
		System.out.println("---------------------------");
		System.out.println(ejemplar2.toString());
		System.out.println("---------------------------");
		System.out.println(ejemplar3.toString());
		
	}

}
