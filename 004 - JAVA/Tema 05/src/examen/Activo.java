package examen;

public abstract class Activo implements Amenazable {
	
	//variables miembro
	private String nombre;
	private double valor;
	
	//constructor
	public Activo(String unNombre, double unValor) throws Exception{
		this.nombre = unNombre;
		
		//comprobamos que el valor sea mayor o igual que 0 y menor o igual que 1
		if(unValor < 0 || unValor > 1) throw new IllegalArgumentException("El valor es incorrecto"); 
		else this.valor = unValor;
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	//setters
	public void setNombre(String unNombre) {
		this.nombre = unNombre;
	}
	
	public void setValor(double unValor) throws Exception{
		//comprobamos que el valor sea mayor o igual que 0 y menor o igual que 1
		if(unValor < 0 || unValor > 1) throw new IllegalArgumentException("El valor es incorrecto"); 
		else this.valor = unValor;
	}
	
	//metodo abstracto
	abstract public Activo copia(Activo original) throws Exception;
	
	//metodo toString 
		@Override
		public String toString() {
			//variable local
			String cadena = ""; //variable a devolver
			
			//construimos la cadena
			cadena = cadena + "nombre: " + this.nombre + ", valor: " + this.valor;
			
			//devolvemos la cadena
			return cadena;
		}
		
		//implementacion de la interfaz, obligamos a los hijos
		public abstract String getTipoAmenazas();
		
} //fin de la clase activo

//***********************************************************************************************
interface Amenazable {
	
	//metodo de la interfaz
	public String getTipoAmenazas();
	
} //fin de la interfaz

//**********************************************************************************************

class Hardware extends Activo{
	
	//variables mimebro
	private int vidaUtil;
	private String tipoAmenaza ="AI";
	
	//constructor. El throw viene del padre.
	public Hardware(String unNombre, double unValor, int vida) throws Exception {
		
		//llamada al padre 
		super(unNombre, unValor);
		
		//completamos el objeto
		this.vidaUtil = vida;
	}
	
	//getter
	public int getVidaUtil() {
		return this.vidaUtil;
	}
	
	//setter
	public void setVidaUtil(int vida) {
		this.vidaUtil = vida;
	}
	
	//implementamos el metodo que obliga el padre
	public Activo copia(Activo original) throws Exception {
		
		//creamos la copia del objeto
		Hardware unaCopia = new Hardware(this.getNombre(), this.getValor(), this.vidaUtil);
		
		return unaCopia;
		
	}
	
	public String getTipoAmenazas() {
		
		return this.tipoAmenaza;
	}
	
	//sobreescribimos el metodo toString
	@Override
	public String toString() {
		//variable local
		String cadena = "";
		
		//construimos la cadena
		cadena = "vida: " + this.vidaUtil + ", " + this.toString();
		
		//devolvemos la cadena
		return cadena;
		
	}
	
	
} //fin de la clase hardware

//******************************************************************************************
class Software extends Activo{
	
	//variables miembro
	private String version;
	private String tipoAmenaza = "IFH";
	
	//constructor
	public Software(String unNombre, double unValor, String unaVersion) throws Exception {
		
		//llamada al padre
		super(unNombre, unValor);
		
		//acabamos de construir el objeto
		this.version = unaVersion;
	}
	
	//getter
	public String getVersion() {
		return this.version;
	}
	
	//setter
	public void setVersion(String unaVersion) {
		this.version = unaVersion;
	}
	
	//implementamos el metodo que obliga el padre
		public Activo copia(Activo original) throws Exception {
			
			//creamos la copia del objeto
			Software unaCopia = new Software(this.getNombre(), this.getValor(), this.version);
			
			return unaCopia;
			
		}
		
		public String getTipoAmenazas() {
			
			return this.tipoAmenaza;
		}
		
		//sobreescribimos el metodo toString
		@Override
		public String toString() {
			//variable local
			String cadena = "";
			
			//construimos la cadena
			cadena = "vida: " + this.version + ", " + this.toString();
			
			//devolvemos la cadena
			return cadena;
			
		}
} //fin de la clase software

//********************************************************************************
class Amenaza{
	 //variables miembro
	private String nombre;
	private char tipo;
	 
	//constructor
	public Amenaza(String unNombre, char unTipo) throws Exception{
		
		this.nombre = unNombre;
		
		//comprobamos que el tipo sea correcto
		if(unTipo != 'A' || unTipo != 'I' || unTipo != 'F' || unTipo != 'H') throw new IllegalArgumentException("Tipo incorrecto");
		else this.tipo = unTipo;
		
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public char getTipo() {
		return this.tipo;
	}
	
	//setters
	public void setNombre(String unNombre) {
		this.nombre = unNombre;
	}
	
	public void setTipo(char unTipo) throws Exception {
		//comprobamos que el tipo sea correcto
		if(unTipo != 'A' || unTipo != 'I' || unTipo != 'F' || unTipo != 'H') throw new IllegalArgumentException("Tipo incorrecto");
		else this.tipo = unTipo;
	}
	
} //fin de Amenaza

//*********************************************************************************************

class Incidente {
	
	//variables miembro
	private Amenaza unaAmenaza;
	private Activo unActivo;
	private double FrecuenciaAnual;
	private double consecuencia;
	
	//cosntructor
	public Incidente(Amenaza laAmenaza, Activo elActivo, double frecuencia, double consecuencia) {
		
		this.unaAmenaza = laAmenaza;
		this.unActivo = elActivo;
		this.FrecuenciaAnual = frecuencia;
		this.consecuencia = consecuencia;
		
	}
	
	//getters
	public double getFrecuenciaAnual() {
		return this.FrecuenciaAnual;
	}
	
	public double getConsecuencia() {
		return this.consecuencia;
	}
	
	//setters
	public void setFrecuenciaAnual(double frecuencia) {
		this.FrecuenciaAnual = frecuencia;
	}
	
	public void setConsecuencia(double consecuencia) {
		this.consecuencia = consecuencia;
	}
	
	//sobreescribir el metodo toString
	@Override
	public String toString() {
		//variable local
		String cadena = "";
		
		cadena = cadena + this.unActivo.toString() + ", Amenaza: " + this.unaAmenaza.getNombre() + ", Tipo: " + this.unaAmenaza.getTipo() +
				 " , frecuencia anual: " + this.FrecuenciaAnual + " .consecuencia: " + this.consecuencia;
		
		return cadena;
	}
	
} //fin de incidente

//************************************************************************************************************
class Cliente implements Amenazable {
	
	//variable local
	private String nombre;
	private String tipoAmenaza = "A";
	
	//constructor
	public Cliente(String unNombre) {
		this.nombre = unNombre;
	}
	
	//getter
	public String getNombre() {
		return this.nombre;
	}
	
	//setter
	public void setNombre(String unNombre) {
		this.nombre = unNombre;
	}
	
	//override de to string
	@Override
	public String toString() {
		String cadena = "";
		
		return cadena;
	}
	
	//implementacion de la interfaz
		public String getTipoAmenazas() {
			return this.tipoAmenaza;
		}
} //fin Cliente

//****************************************************************************************

class puestaEnMarcha {
	
	public static void main(String[] args) throws Exception {
		//variables locales
		Activo[] incorporacion = new Activo[2]; //creamos el array
		
		//creamos los objetos dentro del array
		incorporacion[0] = new Hardware("HD Seagate", 0.8, 5);
		incorporacion[1] = new Software("SO Windows", 0.5, "10");
		//incorporacion[2] = new Cliente("Manolo"); lo dejo pendiente
		
		//recorremos el array. 
		
			System.out.print("Incorporacion 0");
			System.out.print("(nombre: " + incorporacion[0].getNombre());
			System.out.print(" , valor: " + incorporacion[0].getValor() + ") ");
			System.out.print(incorporacion[0].getClass());
			System.out.print(" " + incorporacion[0].getTipoAmenazas());
			
		
		
		
	} //fin del main
	
} //fin de la clase
