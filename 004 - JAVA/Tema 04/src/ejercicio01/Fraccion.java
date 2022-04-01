package ejercicio01;

public class Fraccion {
	
	//variables globales
	private static int unNumerador;
	private static int unDenominador;
	
	//variables de objetos
	private int numerador;
	private int denominador;
	private double decimal; //para almacenar el valor decimal de la fraccion. No necesario en el ejercicio

	//CONSTRUCTORES
	
	//constructor por defecto
	public Fraccion() {
		this.numerador = 1;
		this.denominador = 1;
	}
	
	//segundo constructor
	public Fraccion(int numer, int denom) {

		//comprobaciones
		compruebaDenominador(denom);
	    numer = valorAbsoluto(numer);
	    denom = valorAbsoluto(denom);
		
		this.numerador = numer;
		this.denominador = denom;
		
		//llamada a funcion para simplificar la fraccion
		simplifica (this.numerador, this.denominador);
	}
	
	//tercer constructor
	public Fraccion(double numero) {
		
		this.decimal = numero;
		//conertimos a fraccion
		pasarDecimal(numero);
		
		//comprobaciones
		compruebaDenominador(unDenominador);
		unNumerador = valorAbsoluto(unNumerador);
		unDenominador = valorAbsoluto(unDenominador);
		
		this.numerador = unNumerador;
		this.denominador = unDenominador;
		
		//llamada a funcion para simplificar la fraccion
		simplifica (this.numerador, this.denominador);
		
	}
	
	//GETTERS
	
	//para obtener el numerador
	public int getNumerador() {
		return this.numerador;
	}
	
	//para obtener el denominador
	public int getDenominador() {
		return this.denominador;
	}
	
	//para obtener el valor decimal
	public double getDecimal() {
		return this.decimal;
	}
	
	//SETTERS
	
	//para darle un valor al numerador
	public void setNumerador(int numero) {
		
		//comprobaciones
		numero = valorAbsoluto(numero);
		
		//asignamos el valor
		this.numerador = numero;
	}
	
	//para darle un valor al denominador
	public void setDenominador(int numero) {
		
		//comprobaciones
		compruebaDenominador(numero);
		numero = valorAbsoluto(numero);
		
		//asignamos valor
		this.denominador = numero;
	}
	
	//SUBRUTINAS
	
	//subrutina a la que se le pasa dos valores, numerador y denominador
	//y simplifica la fraccion
	private void simplifica(int num, int den) {
		
		//variables locales
		int menor; //numero mayor
		
		//vemos que numero es menor para empezar a obtener el divisor comun
		if(num <= den) menor=num;
		else menor=den;
		
		//vamos corriendo todos los numeros desde el menor hasta el 2 para obtener
		//posibles divisores comunes
		for (int i = menor; i > 1; i--) {
			if (num % i == 0 && den % i== 0) { //si i divide a ambos, es comun divisor
				num = num/i;
				den = den/i;
			}
		}
		
		//le damos los valores a los parametros del objeto
		this.numerador = num;
		this.denominador=den;
	}
	
	//subrutina que asegura que el valor del denominador no sea 0
	//hay que activar las aserciones con -ea en run configurations
	public void compruebaDenominador(int den) {
		
		assert den != 0 : "denominador incorrecto. Es 0";
	}
	
	//subrutina que elimina los valores negativos
	//devuelve el valor absoluto
	public int valorAbsoluto(int numero) {
		//comprobamos que numerador y/o denominador son positivos
		if (numero < 0) numero = Math.abs(numero);
		return numero;
	}
	
	//subrutina que recoge el elemento decimal de un objeto
	//y transforma ese decimal en una fraccion que 
	//que devolveremos al objeto para completar el resto 
	public void pasarDecimal(double decimal) {
		
		//variables locales
		String str = String.valueOf(decimal); //cadena para convertir el numero que pasamos
		String cadena; //cadena del numerador tras eliminar decimales
		String cadenaDenom="1"; //cadena para construir el numerador
		int longitud; //obtenemos la longitud de la parte decimal

		//construccion del numerador
		int intNumber = Integer.parseInt(str.substring(0, str.indexOf('.'))); //obtenemos la parte entera
		int decNumberInt = Integer.parseInt(str.substring(str.indexOf('.') + 1)); //obtenemos la parte decimal
		cadena = String.valueOf(intNumber) + String.valueOf(decNumberInt); //pasamos la parte entera y decimal a cadena
		unNumerador = Integer.parseInt(cadena); //convertimos la cadena a entero
		
		//construccion del denominador
		longitud = String.valueOf(decNumberInt).length(); //obtenemos la longitud de la parte decimal
		for(int i = 1; i <= longitud; i++) cadenaDenom = cadenaDenom + "0"; //construimos la cadena del denominador
		unDenominador = Integer.parseInt(cadenaDenom); //convertimos la cadena a entero
		
	}
	
	//subrutina que calcula la suma de dos fracciones
	public Fraccion suma(Fraccion f) {
		
		int numerator; //para almacenar el numerador
		int denominator; //para almacenar el denominador
		
		//numerador
		numerator = (this.numerador * f.denominador) + (this.denominador * f.numerador);
		
		//denominador
		denominator = this.denominador * f.denominador;
		
		//creamos el nuevo objeto
		Fraccion f4 = new Fraccion(numerator, denominator);
		
		return f4;
	}
	
	//subrutina que calcula la suma de una fraccion con un entero
	public Fraccion suma(int f) {
		
		int numerator; //para almacenar el numerador
		int denominator; //para almacenar el denominador
		
		//numerador
		numerator = (this.numerador * 1) + (this.denominador * f);
		
		//denominador
		denominator = this.denominador * f;
		
		//creamos el nuevo objeto
		Fraccion f4 = new Fraccion(numerator, denominator);
		
		return f4;
	}
	
	//metodo toString para crear una cadena
	@Override
	public String toString() {
		
		String cadena;
		
		if(this.denominador == 1) cadena = "1";
		else cadena = this.getNumerador() + "/" + this.getDenominador();
		
		return cadena;
		
	}
	
	//metodo equals para comprobar si dos fracciones son iguales
	@Override
	public boolean equals(Object obj) {
		
		Fraccion igual = (Fraccion)obj;
		
		if (this.numerador == igual.numerador && this.denominador == igual.denominador) return true;
		else return false;
				}

}
