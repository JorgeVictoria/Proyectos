package ejercicio05;

import java.util.Arrays;

public class EstaUni {
	//variables de la clase
	private static double media; //media de los valores
	private static double varianza; //varianza.
	private static double varianzaM; //varianza muestral
	private static double desvEst; //desviacion estandar
	private static boolean mediaCalculada; //para saber si la media está calculada
	private static boolean datosOrdenados; //para saber si los datos estan ordenados de menor a mayor
	private static double[] aux; //para manipular arrays
	private static double[] arrayOrdenado; //para ordenar los datos
	
	//variables del objeto
	double[] notas; //notas del curso
	int decimales;  //numero de decimales de las notas
	
	//constructor 1
	public EstaUni(int cant, int dec) {
		this.notas = new double[cant];
		this.decimales = dec;
		mediaCalculada = false;
		datosOrdenados = false;
	}
	
	//constructor 2
	public EstaUni(int cant) {
		this(cant,3); //llamada al primer constructor
	}
	
											//GETTERS ZONE
	
	//getter para obtener un solo valor de las notas
	//recibe como parametro una posicion del array
	//si la posicion no es correcta, devuelve -1
	//si es correcta devuelve la nota
	public double getNota(int pos) {
		//variable local
		double devuelve;
		
		if(pos < 0 || pos > this.notas.length) devuelve = -1;
		else devuelve = this.notas[pos];
		
		return devuelve;
	}
	
	//getter para obtener la cantidad de decimales
	//no recibe ningun parametro de entrada
	public int getDecimales() {
		return this.decimales;
	}
	
										//SETTERS ZONE
	
	//setter para introducir los valores del array de notas
	//recibe como parametro de entrada una nota y una posicion
	//llama a la funcion redondeo, para poner la nota segun los decimales indicados
	public void setNota(double nota, int pos) {
		
		this.notas[pos] = nota;	
	}
	
	//setter para introducir los valores de los decimales
	//recibe como parametro de entrada un entero que indica la cantidad de decimales
	public void setDecimales(int decim) {
		this.decimales = decim;
	}
	
									//METODS DE LOS OBJETOS ZONE
	
	//metodo para cambiar el dato de una nota
	//recibe la posicion y la nota a cambiar
	//si la posicion es correcta, cambiamos la nota
	//si la posicion no es correcta, mostramos mensaje por pantalla
	public void cambiarDato(int pos, double nota) {
		
		if (pos < 0 || pos > this.notas.length) System.out.println("posicion incorrecta");
		else {
			this.notas[pos] = nota;
			System.out.println("Cambio realizado");
		}

	}
	
	//metodo para introoducir un nuevo dato
	//recibimos el la nota como parametro
	public void nuevoDato(double nota) {
		//variables locales
		int longitud = this.notas.length; //almacena la longitud del array de notas
		longitud++; //como vamos a añadir una nueva nota, incrementamos en uno la longitud
		
		//construimos un nuevo array auxiliar
		aux = new double[longitud];
		
		//al array auxiliar le pasamos los datos del array original
		aux = this.notas.clone();
		
		//creamos el nuevo array de notas con la nueva longitud
		this.notas = new double[longitud];
		
		//copiamos el array auxiliar
		this.notas = aux.clone();
		
		//finalmente pasamos la nueva nota a la ultima posicion
		this.notas[longitud-1] = nota;
	}
	
	//metodo para eliminar un dato del array
	//recibimos como parametro la posicion a borrar
	//primero comprobamos que la posicion es correcta
	//si no lo es mostramos error por pantallas
	//si es correcta, procedemos a la modificacion
	public void borrarDato(int pos) {
		//variables locales
		int j=0; 
		int longitud = this.notas.length; //almacena la longitud del array de notas
		longitud--; //como vamos a borrar un dato, la longitud del array debe disminuir
		
		//si la posicion no es correcta, mostramos mensaje por pantalla
		if (pos < 0 || pos > this.notas.length) System.out.println("posicion incorrecta");
		
		//si la posicion es corracta, procedemos a la modificacion
		else {
			
			//primero ponemos la nota de la posicion a -1, para saber la que hay que borrar
			this.notas[pos] = -1;
			
			//creamos el array auxiliar
			aux = new double[longitud];
			
			//copiamos los datos del viejo array en el array auxiliar
			//cuando llegue a la posicion que tiene como valor -1, esa posicion no la copia
			for(int i = 0; i<this.notas.length;i++) {
				if(this.notas[i] != -1) {
					aux[j] = this.notas[i];
					j++; //si el valor no es -1, pasamos a la siguiente posicion
				}
			}
			
			//creamos el nuevo array de notas con la nueva longitud
			this.notas = new double[longitud];
			
			//copiamos el array auxiliar
			this.notas = aux.clone();
			
		}
	}
	
									//METODOS DE LA CLASE ZONE
	
	public static double redondea(double num, int dec) {
		//variables locales
		double devuelve; //valor que devuelve
		String cadena ="1" ; //para construir el numero que usaremos en math.round
		double decimal; //valor numerico a utilizar en el math random
		
		for(int i = 0; i < dec; i++) {
			cadena = cadena + "0";
		}
		
		decimal = Double.parseDouble(cadena);
		
		devuelve = Math.round(num * decimal) / decimal;
		
		return devuelve;
		
	}
	
	//metodo toString para crear una cadena
		@Override
		public String toString() {
			
			String cadena = "[ ";
			
			for(int i = 0; i < this.notas.length; i++) {
				cadena = cadena + this.redondea(this.notas[i], this.decimales);
				if (i < (this.notas.length-1)) cadena = cadena + ", ";
			}
			
			cadena = cadena + " ]";
			return cadena;
			
		}
	
								//METODOS DE LOS OBJETOS CLASE, STATISTICS ZONE
	
	public double media() {
		//variables locales
		double total=0;
		
		//corremos todo el array
		for(int i = 0; i < this.notas.length; i++) {
			total = total + this.notas[i];
		}
		
		//calculamos la media
		media = total/this.notas.length;
		
		//indicamos que la media esta calculada
		mediaCalculada = true;
		
		//devolvemos el resultado
		return media;
		
	}
	
	public double mediana() {
		//variables locales
		 double  mediana;
		 int pos1,pos2;
		//creamos un array para ordenar los datos
		arrayOrdenado = new double[this.notas.length];
		
		//le pasamos los datos
		arrayOrdenado = this.notas.clone();
		
		//ordenamos los datos
		Arrays.sort(arrayOrdenado);
		
		//calculo de la mediana
		if(arrayOrdenado.length%2 != 0) {
			pos1 = (arrayOrdenado.length-1)/2;
			mediana = arrayOrdenado[pos1];
		}
		else {
			pos1 = (arrayOrdenado.length-2)/2;
			pos2 = arrayOrdenado.length/2;
			mediana = (arrayOrdenado[pos1] + arrayOrdenado[pos1]) /2;
		}
		return mediana;
	}
	
	public double percentil(int p) {
		//variables locales
		double percentil=0;
		int pos,pos1;
		
		//calculo del percentil
		pos = (int)(this.notas.length * p)/100;
		if((this.notas.length * p)%100 != 0) percentil = arrayOrdenado[pos];
		else {
			if (pos >= 1) pos1 = pos-1;
			else pos1 = pos;
			percentil = (arrayOrdenado[pos]+arrayOrdenado[pos1])/2;
		}
	
		return percentil;
	}
	
	public double varianza() {
		//variables locales
		double numerador=0;
		
		for (int i = 0; i < this.notas.length; i++ ) {
			numerador = numerador + (Math.pow((this.notas[i] - media), 2));
		}
		
		varianza = numerador/this.notas.length;
		
		return varianza;
	}
	
	public double varianzaM() {
		//variables locales
		double numerador=0;
		
		for (int i = 0; i < this.notas.length; i++ ) {
			numerador = numerador + (Math.pow((this.notas[i] - media), 2));
		}
		
		varianzaM = numerador/(this.notas.length-1);
		
		return varianzaM;
	}
	
	public double desvEstandar() {
		desvEst = Math.sqrt(varianza);
		return desvEst;
	}
	
	public double desvEstandarM() {
		return Math.sqrt(varianzaM);
	}
	
	public double rango() {
		//variables locales
		int pos = this.notas.length-1;
		
		return arrayOrdenado[pos] - arrayOrdenado[0];
	}
	
	public double curtosis() {
		//variable local
		double curtosis;
		double numerador = 0;
		
		for(int i = 0; i < this.notas.length; i++) {
			numerador = numerador + Math.pow((this.notas[i]-media), 4);
		}
		
		curtosis = numerador/(media*Math.pow(desvEst, 4));
		
		return curtosis;
		
	}
	
	public double cafisher() {
		//variable local
		double cafisher;
		double numerador = 0;
		
		for(int i = 0; i < this.notas.length; i++) {
			numerador = numerador + Math.pow((this.notas[i]-media), 3);
		}
		
		cafisher = numerador/(media*Math.pow(desvEst, 4));
		
		return cafisher;
		
	}
	
	public void normalizar() {
		
		for (int i = 0; i < this.notas.length; i++) {
			this.notas[i] = (arrayOrdenado[i] - media)/desvEst;
		}
	}
	
	public void escalar(int a,int b) {
		//variables locales
		double min = arrayOrdenado[0];
		double max = arrayOrdenado[arrayOrdenado.length-1];
		
		for (int i = 0; i < this.notas.length; i++) {
			this.notas[i] = (a + (arrayOrdenado[i] - min) * (b - a))/max-min;
		}
	}
	
	
}
