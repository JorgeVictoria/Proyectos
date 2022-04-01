package biblioteca;

public class Revista extends Obra {
	
	//variables miembro
	private int mes; //almacena el mes en el que se publica el num de la revista

	//****************************************CONSTRUCTOR ZONE**********************************************************
	
	//metodo constructor que recibe como parametro de entrada un mes y un titulo de revista
	public Revista(String titulo, int mes ) throws Exception {
		
		//llamada al constructor padre al que le pasamos el titulo de la revista
		super(titulo);
		
		this.setMes(mes); //cosntruimos el mes, que tendrá expcepciones
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo que devuelve el mes de la revista
	public int getMes() {
		
		return this.mes;
	}
	
	//metodo que devuelve el numero de alquileres, pero como no se pueden alquilar devolvemos 0
	//ya que es un metodo heredado del padre que nos obliga a implementarlo
	public int getNumPrestamos() {
		
		return 0;
		
	}
	
	//****************************************SETTERS ZONE**************************************************************
	 
	//metodo para definir el mes de la revista
	public void setMes(int unMes) throws Exception {
		
		//controlamos que el mes sea un valor entre 1 y 12
		if (unMes < 1 || unMes > 12) throw new IllegalArgumentException ("el mes debe ser un valor entre 1 y 12");
		else this.mes = unMes;
		
	}
	
	//como no se pueden prestar revistas, creamos un setter vacio
	public void setNumPrestamos() {
		System.out.println("no se pueden prestar revistas");
	}
	
	//****************************************METODOS VARIOS**************************************************************
	
		//metodo para imprimir la info de la revista. Viene del padre.
		public String toString() {
			
			//variables locales
			String cadena = "Titulo: " + this.getTitulo() + "\nMes: " + this.mes;
			
			//devolvemos la cadena
			return cadena;
		}

}
