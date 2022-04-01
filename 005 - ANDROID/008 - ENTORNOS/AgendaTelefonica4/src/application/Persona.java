/**
 * 
 */
package application;

/**
 * @author Jorge Vctoria
 *
 */
public class Persona {
	
	//variables miembro de la clase
	private String nombre;
	private String email;
	private String movil;
	
	/**
	 * constructor de la clase Persona que recibe los siguientes parametros
	 * @param nombre
	 * @param email
	 * @param movil
	 */
	public Persona(String nombre, String email, String movil) {
		this.nombre = nombre;
		this.email = email;
		this.movil = movil;
	}
	
	//constructor vacio de la clase
	public Persona() {}

	public String getNombre() {
		return nombre;
	}

	//getters y setters de la clase
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	@Override
	/**
	 * @name toString: metodo para construir las filas de lista de Personas
	 */
	public String toString() {
		return String.format("%-60s %-60s %-20s", this.getNombre(), this.getEmail(), this.getMovil());
		
	}
	
	
	
}
