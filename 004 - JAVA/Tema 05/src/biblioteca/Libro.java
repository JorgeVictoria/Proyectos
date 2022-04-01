package biblioteca;

public class Libro extends Obra{
	
	//variables miembrp
	private String autor;  //almacena el autor del libro
	private int numPrestamos; //almacena la cantidad de prestamos del libro
	
	//****************************************CONSTRUCTOR ZONE**********************************************************
	
	//metodo constructor de un objeto libro. Recibe un titulo y el nombre del autor
	public Libro (String titulo, String autor) {
		super(titulo); //llamada al constructor del padre para pasarle el titulo
		this.autor = autor;
		this.numPrestamos=0; //ponemos a cero el contador de prestamos del libro
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo que devuelve el autor de la obra
	public String getAutor() {
		return this.autor;
	}
	
	//metodo que devuelve el numero de prestamos de la obra. Hereda de padre
	public int getNumPrestamos() {
		return numPrestamos;
	}
	
	//****************************************SETTERS ZONE**************************************************************
	
	//metodo para definir el autor
	public void setAutor(String unAutor) {
		this.autor = unAutor;
	}
	
	//metodo para definir el numero de prestamos
	public void setNumPrestamos() {
		this.numPrestamos++;
	}
	
	//****************************************METODOS VARIOS**************************************************************
	
	//metodo para imprimir la info del libro. Viene del padre.
	public String toString() {
		
		//variables locales
		String cadena = "Titulo: " + this.getTitulo() + "\nAutor: " + this.autor + "\nNumero de prestamos: " + this.numPrestamos;
		
		//devolvemos la cadena
		return cadena;
	}

}
