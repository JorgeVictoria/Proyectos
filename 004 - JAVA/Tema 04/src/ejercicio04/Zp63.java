package ejercicio04;

import java.util.Arrays;
import java.util.Random;

public class Zp63 {
	//variables locales
	
	//array con los primeros numeros primos
	final private static long[] primosBajos =  {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
												71, 73, 79, 83, 89, 97, 101 ,103, 107, 109, 113, 127, 131, 137, 139, 149,
												151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
												233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
												317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
												419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
												503, 509};
	
	//variables de objeto
	private long modulo; //el modulo indica el rango de numeros desde 0 hasta el valor del modulo
	private boolean esPrimo; //indica si el valor de modulo es primo o no
	 
	//constructor
	public Zp63(long p) {
		
		//vemos si es correcto
		assert p > 1L : "modulo debe ser mayor que 1L";
		
		//si es correcto
		this.modulo = p;
		
		//vemos si es primo
		this.esPrimo = esPrimoProbable(this.modulo, 99);
		
	}
	
	//getters
	public long getModulo() {
		
		return this.modulo;
		
	}
	
	public boolean getPrimo() {
		
		return this.esPrimo;
	}
	
	//metodo para realizar la suma aritmetica de dos numeros
	public long suma(long a,long b) {
		//variables locales
		long resul=0;
		
		//comprobamos que los 2 valores esten en el conjunto Zp
		assert (a < this.modulo && b < this.modulo) : "Los numeros no pueden ser mayores de " + this.getModulo();
		assert (a >= 0 && b >= 0): "Los numeros deben ser mayores o iguales que 0";
		
		if((a+b) < this.modulo) resul = a+b;
		else resul = (a+b) - this.modulo;
		
		return resul;
		
	}
	
	//metodo para calcular la resta aritmetica de dos numeros
		public long resta(long a,long b) {
			//variables locales
			long resul=0;
			
			//comprobamos que los 2 valores esten en el conjunto Zp
			assert (a < this.modulo && b < this.modulo) : "Los numeros no pueden ser mayores de " + this.getModulo();
			assert (a >= 0 && b >= 0): "Los numeros deben ser mayores o iguales que 0";
			
			if((0 <= (a-b)) && (a-b < this.modulo)) resul = a-b;
			else if ((a-b) < 0) resul = this.modulo + (a-b);
			
			return resul;
			
		}
	
	//metodo del objeto que se utiliza para ver si un numero es primo
	//entradas: n--> numero a probar, p-->probabilidad de que sea primo (0-1)
	//salidas: false-->seguro que no es primo, true-->es primo con probabilidad p
	public boolean esPrimoProbable(long n, int p) {
		//variables locales
		boolean primo=false; //valor que se devuelve, segun p es primo o no
		int pos; //posicion de p en el array, si está.
		if (n <= 1 || n == 4) return false; // 0 o menor que 1 no son primos. 4 no es primo es top 5
		else if(n <= 3) return true; //1,2 y 3 son primos
		else if (n <= primosBajos[primosBajos.length-1]){ //vemos si esta en el array primos bajos
			pos = Arrays.binarySearch(primosBajos, n);
			if (pos >= 0) return true;
			else return false;
		}
		//si el numero es par,no es primo
		else if(n%2 ==0) return false;
		
		//para el resto de impares
		int k =(int) Math.ceil(Math.log(1-0.99)/Math.log(0.5));
		long d =n-1;
		while( d%2 == 0) {
			d/=2;
		}
		for(int i=0;i<k-1;i++) {
			if (!millerTest(d,n)) return false;
		}
		return true;
	}
	
	// ENTRADAS: d (un nº par tal que d x 2^r = n-1 para algún r >= 1) y n (el nº a probar)
	// SALIDAS: true si es primo
	public static boolean millerTest(long d, long n) {
	 // Elegir un nº aleatorio en [2..n-2] los casos extremos aseguran que n > 4
	 long a =(long)(2 + Math.random() % (n - 4));
	 long x = potenciaMod(a, d, n); // a^d % n
	 if( x == 1 || x == n-1) return true;
	 // Continuar mientras no se cumple algunas de estas cosas
	 // (i) d no sea n-1 (ii) (x^2) % n no sea 1 // (iii) (x^2) % n no sea n-1
	 while (d != n-1) {
		 
		 x = (x * x) % n;
		 d *= 2;
		 if (x == 1) return false;
		 if ( x == n-1) return true;
	 }
	 return false;
	}
	
	// ALGORITMO RÁPIDO DE POTENCIAS EN Zp
	// ENTRADAS: b base, e exponente y p
	// SALIDA: b^e (mod p)
	public static long potenciaMod(long b, long e, long p) {
	 long x = 1;
	 b = b % p;
	 e = e % p;
	 while (e > 0 && b > 1) {
		 if (e % 2 == 1) {
			 x = (x * b) % p;
			 e = e - 1; // dividir sindecimales
		 }
		 b = (b * b) % p;
		 e = e / 2;
	 }
	 
	 
	 return x;
	}
	
	// ALGORITMO DE EUCLIDES:
	// ENTRADAS: 2 enteros a, b
	// SALIDA: máximo común divisor de a y b
	public static long mcd(long a, long b) {
		
	//variables locales
	long temp; //variable temporal
	long r0, r1;
	
	 if (a < b) {
		 temp = a;
		 a = b;
		 b = temp;
	 }
	 r0 = a;
	 r1 = b;
	 
	 while (r1 > 0) {
	 temp = r1;
	 r1 = r0 % r1;
	 r0 = temp;
	 }
	 return r0;
	
	}
	
	// ALGORITMO EXTENDIDO DE EUCLIDES:
	// ENTRADA: a, b números enteros con a < b.
	// SALIDA: r[0] el mcd, [1] u, [2] v
	public static long[] mcdExtendido(long a, long b) {
	
	//variables locales
	 long[] resultado = new long[3];
	 long u = 1;
	 long g = a;
	 long x = 0;
	 long y = b;
	 long v;
	 long q;
	 long t;
	 long s;
	 
	 while (true) {
		 if (y == 0) {
			 v = (g - a * u ) / b;
			 resultado[0] = g;
			 resultado[1] = u;
			 resultado[2] = v;
			 return resultado;
		 }
		 q = g / y;
		 t = g % y;
		 s = u - q * x;
		 u = x;
		 g = y;
		 x = s;
		 y = t;
	 }
	 
	}
	
}