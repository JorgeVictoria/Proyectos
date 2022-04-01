package fibonacci;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		//pedimos un numero entero
		int numero = pedirNumero();
		
		Fibonacci fibo = new Fibonacci(numero);
		
		fibo.start();

	}

	/****
	 * metodo para pedir un numero y controlar que sea valido
	 * @return numero: devuelve un numero entero positivo, mayor que 0
	 */
	private static int pedirNumero() {
		
		//variables locales
		int numero=-1; 							//numero entero que pedimos y devolvemos
		boolean salir = false;					//para controlar que el numero introducido sea correcto
		Scanner stdin = new Scanner(System.in); //para la entrada de datos por teclado
		
		//creamos un bucle para pedir un numero entero y comprobar que sea correcto
		while(!salir) {
			System.out.print("Introduce un numero entero: ");
			try {
				//suponemos que el usuario va a introducir correctamente los datos
				salir = true;
				//leemos por teclado
				numero = stdin.nextInt();
			} catch (Exception e) {
				stdin.next();
				salir = false;
				System.out.println("el dato introducido no es correcto");
			}
			//si el numero es entero, comprobamos que no sea menor que 0
			if(salir && numero <= 0) {
				salir=false;
				System.out.println("el numero no puede ser negativo o 0");
			}
		}
		
		//devolvemos el numero
		return numero;
	}

}
