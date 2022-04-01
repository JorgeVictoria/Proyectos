package biblioteca;

import java.util.Calendar;

public class Ejemplar {
	//variables locales
	private static int longitudISBN; //para comprobar la longitud del ISBN
	
	//variables miembro
	private String editorial; //almacena la editorial de una obra
	private long ISBN; //almacena el numero ISBN de una obra
	private int anyo; //almcena el año de publicacion de la obra
	private Socio prestadoA; //miembro que coge prestado el ejemplar
	protected Obra obra;

	//****************************************CONSTRUCTOR ZONE**********************************************************
	
	//metodo constructor del objeto ejemplar. Recibe una editorial, un ISBN y una obra
	//el Exception hay que ponerlo porque tenemos usamos excepcion en el metodo compruebaISBN()
	public Ejemplar(String editorial, String ISBN, Obra obra) throws Exception {
		
		this.editorial = editorial;
		
		//el numero ISBN debe contener 13 cifras. Llamamos al metodo para que lo compruebe y devuelva el valor.
		this.ISBN = compruebaISBN(ISBN);
		
		this.obra = obra;
		
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo que devuelve la editorial del ejemplar
	public String getEditorial() {
		
		return this.editorial;
		
	}
	
	//metodo que devuelve el ISBN del ejemplar
	public long getISBN() {
		
		return this.ISBN;
		
	}
	
	//metodo que devuelve el año de publicacion del ejemplar
	public int getAnyo() {
		
		return this.anyo;
		
	}
	
	//metodo que devuelve el socio que tiene prestado el libro
	public Socio getPrestadoA() {
		
		return this.prestadoA;
		
	}
	
	//****************************************SETTERS ZONE**************************************************************
	
	//metodo que define la editorial del ejemplar
	public void setEditorial(String unaEditorial) {
		
		this.editorial = unaEditorial;
	
	//metodo que define el ISBN del ejemplar. Se comprueba que el ISBN sea correcto
	}
	
	public void setISBN(String unISBN) throws Exception {
		
		this.ISBN = compruebaISBN(unISBN);
		
	}
	
	//metodo que define  el año de publicacion del ejemplar
	public void setAnyo(int unAnyo) throws Exception {
		
		//variables locales
		Calendar today = Calendar.getInstance(); //obtenemos la fecha de hoy.
		
		//comprobamos que el año recibido es correcto
		if(unAnyo > today.get(Calendar.YEAR)) throw new IllegalArgumentException ("El año no pueder mayor que el actual");
		else this.anyo = unAnyo;
	}
	
	//metodo que define el socio al que se le presta la publicacion
	public void setPrestadoA(Socio unSocio) {
		
		this.prestadoA = unSocio;
		
	}
	
	//****************************************METODOS VARIOS**************************************************************
	
	//metodo que comprueba si el ISBN es correcto
	public static long compruebaISBN(String unISBN) throws Exception {
		//variables locales
		long valor;
		
		//comprobamos que la longitud del ISBN es de 13 caracteres
		if(unISBN.length() != 13) throw new IllegalArgumentException ("la longitud del ISBN no es correcta");
		
		//ahora comprobamos que todos los caracteres son numeros.
		else {
			for(int i = 0; i < 13; i++) {
				if(unISBN.charAt(i) < 48 || unISBN.charAt(i) > 57 ) 
					throw new IllegalArgumentException ("un codigo ISBN no puede contener letras.");
			}
		}
		
		//si ha pasado las restricciones, devolvemos la cadena convertida a long
		valor = Long.valueOf(unISBN);
		
		return valor;
	}
	
	//metodo toString que devuelve la informacion del ejemplar
	@Override
	public String toString() {
		
		//variables locales
		String cadena;
		
		//vemos el tipo de Obra
		if (this.obra.getClass() == Libro.class) cadena = "Tipo: Libro\n";
		else cadena = "Tipo: Revista\n";
		
		//llamamos al metodo toString correspondiente
		cadena = cadena + this.obra.toString();
		
		//completamos la info con los detalles del ejemplar
		cadena = cadena + "\nISBN: " + this.ISBN + "\nEditorial: " + this.editorial;
		
		//si hemos puesto el año
		if(this.anyo != 0) cadena = cadena + "\nAnyo: " + this.anyo;
		else cadena = cadena + "\nAnyo: no definido";
		
		//por ultimo si vemos a quien esta prestado
		if (this.obra.getClass() == Libro.class) {
			if(this.prestadoA != null) {
				cadena = cadena + "\nPrestado a: " + this.prestadoA.getCarnet();
			}
			else cadena = cadena + "\nPrestado a: no prestado";
		}
		
		return cadena;
	}

}
