/**
 * 
 */
package application;

/**
 * Clase que define los distintos atributos que forman parte de cada contacto de la agenda, 
 * asi como los metodos para acceder y modificar los atributos
 * @author Jorge Vctoria Andreu
 * @since 22dic2021
 * @version 1.4
 */
public class Persona {
	
	//variables miembro de la clase
	private String nombre;
	private String email;
	private String movil;
	
	/**
	 * constructor de la clase Persona que recibe los siguientes parametros
	 * @param nombre: nombre y apellidos del contacto
	 * @param email: direccion de correo electronico del contacto
	 * @param movil: telefono movil del contacti
	 */
	public Persona(String nombre, String email, String movil) {
		this.nombre = nombre;
		this.email = email;
		this.movil = movil;
	}
	
	/**
	 * Constructor vacio para la clase
	 */
	public Persona() {}

	/**
	 * metodo para obtener el nombre del contacto
	 * @return una cadena con el nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para definir o modificar el nombre del contacto
	 * @param nombre: nombre del contacto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * metodo para obtener el email del contacto
	 * @return una cadena con el email del contacto
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo para definir o modificar el email del contacto
	 * @param email: email del contacto
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * metodo para obtener el nº de telefono del contacto
	 * @return una cadena con el nº de telefono del contacto
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Metodo para definir o modificar el nº de telefono del contacto
	 * @param movil: nº de telefono del contacto
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}

	/**
	 * Metodo para dar formato a la cadena con los datos que debemos mostrar en el listView, listado de contactos
	 * @return la cadena formateada para cada linea de la lista
	 */
	@Override
	public String toString() {
		return String.format("%-60s %-60s %-20s", this.getNombre(), this.getEmail(), this.getMovil());
		
	}
	
	
	
}
