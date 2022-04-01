package examen;

public class TrabajoImpresion {
	
	//variables globales
	private static final int maxTrabajos = 5; //almacena el numero maximo de trabajos. Maximo 5.
	private static int totalTrabajos = 0; //almacena el numero de trabajos realizdos
	private static int numPaginasImpresas = 0; //contador de paginas impresas
	private static int asignaId = 1; //asigna el numero de trabajo.
	
	//variables del objeto
	private int numTrabajo; //identifica el numero de trabajo
	private int numPaginas; //almacena el numero de paginas de cada trabajo
	private boolean pendiente; //nos indica si el trabajo ha sido impreso o no
	private String impresora; //indentifica a que impresora se envia el trabajo
	
	//****************************************CONSTRUCTOR ZONE**************************************************************
	
    //metodo constructor. Recibe el numero de paginas del trabajo y si es necesario imprimir a color
	public TrabajoImpresion(int cuantasPaginas, boolean color) {
		
		//comprobamos que el numero total trabajos no ha sido alcanzado
		if(totalTrabajos == maxTrabajos) {
			throw new IllegalArgumentException ("Se ha alcanzado el maximo numero de trabajos.");
		}
		
		//comprobamos que el numero de paginas enviado sea mayor que 0
		if(cuantasPaginas <= 0) {
			throw new IllegalArgumentException ("El trabajo no tiene paginas.");
		}
		
		//definimos el numero de trabajo
		this.numTrabajo = asignaId;
		asignaId++; //incrementamos en uno el asignador de id's para asignar un numero al siguiente trabajo
		
		//ponemos el trabajo en pendiente de impresion
		this.pendiente = true;
		
		//indicamos a que impresora mandamos la impresion
		if(color == true) this.impresora = "Color";
		else this.impresora = "B/N";
		
		//indicamos cuantas paginas tiene el trabajo
		this.numPaginas = cuantasPaginas;
		
		//incrementamos en uno el numero de trabajos
		totalTrabajos++;
	}
	
	//metodo constructor. Solo recibe el numero de paginas.
	public TrabajoImpresion(int cuantasPaginas) {
		
		//llamamos al metodo constructor anterior
		//le pasamos el numero de paginas e indicamos que vamos a imprimir en B/N
		this(cuantasPaginas, false);
		
	}
	
	//****************************************GETTERS ZONE**************************************************************
	
	//metodo getter que devuelve el numero de trabajo
	public int getNumTrabajo() {
		return this.numTrabajo;
	}
	
	//metodo getter que devuelve el numero de paginas del trabajo
	public int getNumPaginas() {
		return this.numPaginas;
	}
	
	//metodo getter que devuelve si el trabajo esta pendiente de imprimir o no
	public boolean getPendiente() {
		return this.pendiente;
	}
	
	//metodo getter que devuelve la impresora donde se ha enviado el trabajo
	public String getImpresora() {
		return this.impresora;
	}
	
	//****************************************SETTERS ZONE**************************************************************
	
	//metodo setter para indicar si el trabajo esta pendiente de imprimir o no
	public void setPendiente(boolean estaPendiente) {
		this.pendiente = estaPendiente;
	}
	
	//metodo setter para indicar la impresora donde queremos imprimir
	public void setImpresora(String unaImpresora) {
		this.impresora = unaImpresora;
	}
	
	//****************************************SUBRUTINAS Y METODOS VARIOS ZONE**************************************************************
	
	//subrutina que actualiza el numero de paginas impresas y pone el estado del trabajo a impreso
	//se supone que hemos enviado el trabajo ha sido enviado e impreso
	public void imprimir() {
		
		//actualizamos el contador de paginas impresas en total
		numPaginasImpresas = numPaginasImpresas + this.getNumPaginas();
		
		//actualizamos el estado del trabajo para reflejar que ha sido impreso
		this.setPendiente(false);
		
	}
	
	//metodo que indica si un trabajo ha sido cancelado
	public boolean anular() {
		
		//comprobamos que el trabajo todavia no ha sido impreso
		if(this.getPendiente() != false) {
			
			//si no ha sido impreso, restamos uno al numero total de trabajos
			totalTrabajos--;
			
			//restamos a uno el asignador de trabajo de impresion. Así podemos volver a asignar el mismo nº de trabajo
			asignaId--;
			
			//devolvemos true como el trabajo se ha llegado a cancelar
			return true;
			
		}
		
		//si el trabajo ya se ha impreso, devolvemos false como que no hemos podido cancelarlo
		else return false;
		
	}
	
	//metodo que indica si un trabajo ha sido cancelado, pero a nivel de clase
	//le pasamos un objeto TrabajoImpresion como parametro
	public static boolean anular (TrabajoImpresion trabajo) {
		
		//comprobamos que el trabajo todavia no ha sido impreso
				if(trabajo.getPendiente() != false) {
					
					//si no ha sido impreso, restamos uno al numero total de trabajos
					totalTrabajos--;
					
					//restamos a uno el asignador de trabajo de impresion. Así podemos 
					asignaId--;
					
					//devolvemos true como el trabajo se ha llegado a cancelar
					return true;
					
				}
				
				//si el trabajo ya se ha impreso, devolvemos false como que no hemos podido cancelarlo
				else return false;
	}
	
	//*****************************************override zone****************************************************************
	
	//metodo que sobrescribe el metodo toString()
	@Override
	public String toString() {
		
		//variables locales
		String color; //para ver si hemos impreso a color o blanco y negro
		String cadena = ""; //cadena que vamos a devolver
		
		//vemos si la impresora es color o blanco y negro
		if (this.getImpresora().equals("Color")) color = "true";
		else color = "false";
		
		//construimos la cadena
		cadena = "{Trabajo: " + this.getNumTrabajo() + " Pags: " + this.getNumPaginas() + " Color: " + color + " Impreso: " + this.getPendiente();
		
		//devolvemos la cadena
		return cadena;
		
		
	}
	
	//metodo que sobresecribe el metodo equals()
	//compara 2 trabajos de impresion
	@Override
	public boolean equals(Object obj) {
		
		//Hacemos un casting del objeto que recibimos como parametro
		TrabajoImpresion miTrabajo = (TrabajoImpresion)obj;
		
		//2 trabajos son iguales si tienen el mismo numero de pagina y van a la misma impresora
		if (this.getNumPaginas() == miTrabajo.getNumPaginas() && this.getImpresora().equals(miTrabajo.getImpresora())) return true;
		else return false;
		
	}
}
