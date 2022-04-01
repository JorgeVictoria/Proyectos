package Practicas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class U3PEX2 {

	public static void main(String[] args) {
		
		//variables 
		int filas=0; //almacena el numero de filas del array
		int columnas=0; //almacena el numero de columnas del array
		int opcion; //para almacenar la opcion del menu principal
		int flag=0; //para controlar si se puede o no salir del menu
		int contador=0;
		
		Scanner stdin = new Scanner(System.in); //entrada 
		
		String basura; //para almacenar basura de la entrada de datos
		String [][] escitalo = new String[0][0]; //array donde se van colocando las letras de la cadena
		String mensaje=""; //mensaje original
		String cifra=""; //mensaje cifrado
		
		
		
		do{
			
			//Borrado de pantalla
			System.out.print( "S\033[H\033[2J");
			System.out.flush();
			
			//Cabecera
			System.out.println("     ESCITALO");
			System.out.println("     ========");
			System.out.println();
			
			//menu
			System.out.println("1. Configurar Clave.");
			System.out.println("2. Encriptar.");
			System.out.println("3. Desencriptar");
			System.out.println("4. Salir");
			System.out.println();
			System.out.print("Escoja una opcion (1-4):");
			
			//comprobamos que el dato introducido sea un entero
			//en caso contrario le damos a opcion valor 5 para que muestre
			//por pantalla que el dato introducido no es valido
			try {
				opcion = stdin.nextInt(); //lectura de datos
			} catch (InputMismatchException e) {
				opcion = 5;
			}
			
			switch(opcion) {
			case 1://Borrado de pantalla
					System.out.print( "S\033[H\033[2J");
					System.out.flush();
				
					//Cabecera
					System.out.println("     CONFIGURAR CLAVE");
					System.out.println("     ================");
					System.out.println();
					
					//pedimos las filas
					do {
						System.out.print(" Filas: ");
						try {
							basura = stdin.nextLine();
							filas = stdin.nextInt(); //lectura de datos
						} catch (InputMismatchException e) {
							filas = 0;
						}
					} while (filas < 1);
					
					//pedimos las columnas
					do {
						System.out.print(" Columnas: ");
						try {
							basura = stdin.nextLine();
							columnas = stdin.nextInt(); //lectura de datos
						} catch (InputMismatchException e) {
							columnas = 0;
						}
					} while (columnas < 1);
					
					//creamos el array
					escitalo = new String [filas][columnas];
					
					//salida de menu
					basura = stdin.nextLine();
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
					
			case 2: //Borrado de pantalla
					System.out.print( "S\033[H\033[2J");
					System.out.flush();
			
					//Cabecera
					System.out.println("     CIFRADO");
					System.out.println("     =======");
					System.out.println();
					
					//inicializar variables
					contador = 0;
					cifra = "";
					mensaje = "";
					
					//entrada de mensaje original
					basura = stdin.nextLine();
					System.out.print("Mensaje: ");
					mensaje = stdin.nextLine();
					
					//rellenamos el array fila a fila, de izda a derecha
					//cuando esta relleno, volvemos a rellenarlo
					//si nos sobran celdas en el ultimo relleno, rellenamos con espacios
					do {
						
						for(int i = 0; i < filas; i++) {
							for(int j = 0; j < columnas; j++) {
								if (contador < mensaje.length()) {
									escitalo[i][j] = Character.toString(mensaje.charAt(contador));
									contador++;
								} else escitalo[i][j] = " ";
							}
						}
						
						//para crear el mensaje cifrado, leemos el array columna a columna, de arriba a abajo
						for(int j = 0; j < columnas; j++) {
							for (int i = 0; i < filas; i++) {
								cifra = cifra + escitalo[i][j];
							}
						}
						
					} while (contador < mensaje.length());
					
					System.out.println("\ncifra: " + cifra);
					
					//salida de menu
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
			case 3: //Borrado de pantalla
					System.out.print( "S\033[H\033[2J");
					System.out.flush();
			
					//Cabecera
					System.out.println("     DESCIFRADO");
					System.out.println("     ==========");
					System.out.println();
					
					//inicializar variables
					contador = 0;
					cifra = "";
					mensaje = "";
					
					//entrada de mensaje cifrado
					basura = stdin.nextLine();
					System.out.print("Cifra: ");
					cifra = stdin.nextLine();
					
					//rellenamos el array columna a columna, de arriba a abajo
					//cuando esta relleno, volvemos a rellenarlo
					//si nos sobran celdas en el ultimo relleno, rellenamos con espacios
					do {
						
						for(int i = 0; i < columnas; i++) {
							for(int j = 0; j < filas; j++) {
								if (contador < cifra.length()) {
									escitalo[j][i] = Character.toString(cifra.charAt(contador));
									contador++;
								} else escitalo[j][i] = " ";
							}
						}
						
						//para formar el mensaje en claro, leemos el array fila a fila
						for(int i = 0; i < filas; i++) {
							for (int j = 0; j < columnas; j++) {
								mensaje = mensaje + escitalo[i][j];
							}
						}
						
					} while (contador < cifra.length());
					
					System.out.println("\nMensaje: " + mensaje);
					
					//salida de menu
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
			case 4: flag=1;
					break;
					
			default: System.out.println("la opcion no existe");
			 		 basura = stdin.nextLine();
			 		 System.out.println("pulse una tecla para continuar");
			 		 stdin.nextLine();
			 		 break;
			}
		} while (flag == 0);

	}

}
