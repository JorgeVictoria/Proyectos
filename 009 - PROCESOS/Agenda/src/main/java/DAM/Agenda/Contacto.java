package DAM.Agenda;

public class Contacto {
	
	private String nombre;
	private String email;
	private String tfnoContacto;
	
	public Contacto(String nombre, String email, String tfnoContacto) {
		this.nombre = nombre;
		this.email = email;
		this.tfnoContacto = tfnoContacto;
	}
	
	public Contacto() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTfnoContacto() {
		return tfnoContacto;
	}

	public void setTfnoContacto(String tfnoContacto) {
		this.tfnoContacto = tfnoContacto;
	}
	
	
	
	

}
