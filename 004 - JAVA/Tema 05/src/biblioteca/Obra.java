package biblioteca;

public abstract class Obra {
	
	//variables miembro
	private String titulo; //almacena el titulo de la obra

	//****************************************CONSTRUCTOR ZONE**********************************************************
	
	//metodo constructor para construir un objeto libro, aunque no se puede
	public Obra(String titulo) {
		this.titulo = titulo;
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo que devuelve el titulo de la obra
	public String getTitulo() {
		return this.titulo;
	}
	
	//****************************************SETTERS ZONE**************************************************************
	
	//metodo para definir el nombre de la obra
	public void setTitulo(String titol) {
		this.titulo = titol;
	}
	
	//****************************************ABSTRACT ZONE*************************************************************
	
	//metodo que sobreescribe el metodo toString y sirve para mostrar info de los libros por pantalla
	@Override
	abstract public String toString();
	
	
	//metodo para obtener el numero de prestamos de una obra
	abstract public int getNumPrestamos();
	
	//metodo para definir el numero de prestamos de una obra
	abstract public void setNumPrestamos();

}
