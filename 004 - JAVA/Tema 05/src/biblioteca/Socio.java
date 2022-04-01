package biblioteca;

public class Socio implements Prestable {
	//variables globales
	private static int numeroSocio = 1; //contador que da un numero de socio, por seguir un orden
	
	//variables del objeto
		private int carnet; //almacena el carnet del socio
		private String nombre; //almacena el nombre del socio
		private boolean bloqueado; //si el socio tiene un prestamo sin devolver, se bloquea

		//****************************************CONSTRUCTOR ZONE**********************************************************
		
		//metodo constructor del socio, no recibe ningun parametro
		
	public Socio() {
		// TODO Esbozo de constructor generado automáticamente
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo que devuelve el carnet del socio
	public int getCarnet() {
		return this.carnet;
	}
	
	//metodo que devuelve el nombre del socio
	public String getNombre() {
		return this.nombre;
	}
	
	//metodo que devuelve si el socio esta bloqueado o no
	public boolean getBloqueado() {
		return this.bloqueado;
	}
	
	//****************************************SETTERS ZONE**************************************************************
	
	//metodo que asigna un numero de socio
	public void setCarnet() {
		this.carnet = numeroSocio;
		numeroSocio++; //incrementamos en uno para el siguiente socio
	}
	
	//metodo que asigna un nombre a un socio
	public void setNombre(String unNombre) {
		this.nombre = unNombre;
	}
	
	//metodo que indica si el usuario ha sido bloqueado o no
	public void setBloqueado(boolean esMoroso) {
		this.bloqueado = esMoroso;
	}
	
	//****************************************METODOS VARIOS**************************************************************
	
	public Prestamo solicitaPrestamo(Ejemplar e)throws Exception {
		
		//si es una revista no se presta
		if (e.obra.getClass() == Revista.class) throw new IllegalArgumentException("Las revistas no se prestan");
		
		//si el socio esta bloqueado no se presta
		if (this.bloqueado == true) throw new IllegalArgumentException("El socio no puede alquilar");
		
		//creamos una copia del socio
		Socio copiaSocio = new Socio();
		copiaSocio.carnet = this.carnet;
		copiaSocio.nombre = this.nombre;
		copiaSocio.bloqueado = this.bloqueado;
		
		//creamos un nuevo prestamo
		Prestamo p = new Prestamo (e, copiaSocio);
		e.obra.setNumPrestamos();
		e.setPrestadoA(copiaSocio);
		
		//devolvemos el prestamo
		return p;
	}
	
	public void devolverPrestamos(Prestamo p) {
		
		//llamada al metodo para la devolucion
		p.setDevuelto();
		
	}
	
	public void pagaSancion(int importe) {
		
	}
}
