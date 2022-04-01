package PF.ProyectoFinal_JorgeVictoria;

/**
 * clase que almacena los atributos de una entrada
 * @author Jorge Victoria Andreu
 * @version 2.0
 * @since 20jan2022 
 */

public class Entrada {
	
	//variables miembro
	private String sala;
	private String pelicula;
	private String hora;
	private int fila;
	private int asiento;
	private double precio;
	
	/**
	 * constructor de la clase Entrada
	 * @param sala: sala de proyeccion
	 * @param pelicula: titulo de la pelicula
	 * @param hora: hora de la pelicula
	 * @param fila: numero de fila
	 * @param asiento: numero de asiento
	 * @param precio
	 */
	public Entrada(String sala, String pelicula, String hora, int fila, int asiento, double precio) {
		this.sala = sala;
		this.pelicula = pelicula;
		this.hora = hora;
		this.fila = fila;
		this.asiento = asiento;
		this.precio = precio;
	}

	/**
	 * constructor vacio de la clase Entrada
	 */
	public Entrada() {
	}

	//Getters & setters
	
	/**
	 * metodo que devuelve el numero de sala
	 * @return un entero con el numero de sala
	 */
	public String getSala() {
		return sala;
	}

	/**
	 * metodo para almacenar el numero de sala
	 * @param sala: entero con el numero de sala
	 */
	public void setSala(String sala) {
		this.sala = sala;
	}


	/**
	 * metodo para obtener el titulo de la pelicula
	 * @return una cadena con el titulo de la pelicula
	 */
	public String getPelicula() {
		return pelicula;
	}

	/**
	 * metodo para almacenar el titulo de la pelicula
	 * @param pelicula: cadena con el titulo de la pelicula
	 */
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	/**
	 * metodo para obtener la hora de la sesion
	 * @return, una cadena con la hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * metodo para almacenar la hora de la sesion
	 * @param hora, una cadena con la hora de la sesion
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * metodo para obtener la fila de la sala
	 * @return, un entero con el numero de fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * metodo para almacenar el numero de fila
	 * @param fila, un entero con el numero de fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * metodo para obtener el asiento
	 * @return, un entero con el numero de asiento
	 */
	public int getAsiento() {
		return asiento;
	}

	/**
	 * metodo para almacenar el numero de asiento
	 * @param asiento, un entero con el numero de asiento
	 */
	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	/**
	 * metodo para obtener el precio de la entrada
	 * @return, un double con el precio de la entrada
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * metodo para almacenar el precio de la entrada
	 * @param precio, un double con el precio de la entrada
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	/**
	 * metodo para imprimir por pantalla los datos del objeto
	 */
	@Override
	public String toString() {
		return "Sala: " + sala + "\nPelicula: " + pelicula + "\nFecha: " + hora + "\nN� Fila=" + fila + ", N� Asiento="
				+ asiento + "\nPrecio=" + precio + " euros";
	}
	
	public String listarEntradas() {
		
		return String.format("%-35s %-35s %-35s %-35s %-35s %-35s", this.getSala(), this.getPelicula(), this.getHora(), this.getFila(), this.getAsiento(), this.getPrecio());
		
	}
	
	
	

}
