package practica05;

import java.io.Serializable;

public class Persona implements Serializable {
	
	
	//añadimos un numero serialVersionUID generado por eclipse para la clase
	private static final long serialVersionUID = 31031976L;
	
	//variables miembro
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int edad;
	private transient String dni; //esta caracteristica no será serializable
	
	//constructores
	public Persona() {}

	public Persona(String nombre, String apellido1, String apellido2, int edad) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
		this.dni = "53093967P";
	}
	
	public Persona(String nombre, String apellido1, String apellido2, int edad, String dni) {
		this(nombre,apellido1,apellido2,edad);
		this.dni = dni;
	}

	//getters
	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public String getDni() {
		return dni;
	}

	//setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	protected static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", edad=" + edad
				+ ", dni=" + dni + "]";
	}

	
	
	
	
	
	
	

}
