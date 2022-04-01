package Practicas;

//librerias importadas
import java.util.Scanner;
import java.util.InputMismatchException;


public class U3P03 {

	public static void main(String[] args) {
		
		//VARIABLES
		int opcion=0; //almacena la opcion del menu
		int flag=0; //mientras sea 0, no salimos del menu ni del programa
		int flag1=0; //para controlar que el numero de asignaturas sea un entero
		int cuantas=0; //almacenamos la cantidad de asignaturas que estudiamos 
		int fila=-1; //para almacenar la fila cuando busquemos una asignatura
		int mes=0;//almacena el mes que queremos buscar
		int posMaxima=0;//almacena la posicion de la asignatura con mas horas
		
		Scanner stdin = new Scanner(System.in); //entrada de datos
		
		String basura; //para eliminar basura de la entrada de datos
		String [] asignaturas = new String[1]; //array para almacenar asignaturas
		String asignatura; //almacenamos la asignatura que queremos buscar
		
		double [][] tabla=new double[1][1];
		double horas=-1; //entrada de horas de una asignatura
		double total=0; //para almacenar totales
		double maxima=0; //almacena las horas de la asignatura que mas horas tiene
		
		//MENU PRINCIPAL
		do{
					
			//Borrado de pantalla
			System.out.print( "S\033[H\033[2J");
			System.out.flush();
					
			//Cabecera
			System.out.println("          HORAS DE ESTUDIO");
			System.out.println("          ================");
			System.out.println();
					
					//menu
					System.out.println("1. Definir Tablas.");
					System.out.println("2. Modificar hora.");
					System.out.println("3. Total horas por asignatura.");
					System.out.println("4. Total horas por meses.");
					System.out.println("5. Nombre y horas de asignatura mas estudiada");
					System.out.println("6. Salir");
					System.out.println();
					System.out.print("Escoja una opcion (1-6):");
					
					//comprobamos que el dato introducido sea un entero
					//en caso contrario le damos a opcion valor 7 para que 
					//vuelva a aparecer el menu principal
					try {
						opcion = stdin.nextInt(); //lectura de datos
					} catch (InputMismatchException e) {
						opcion = 7;
					}
					
					switch(opcion) {
					case 1: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
							
							//Cabecera
							System.out.println("          HORAS DE ESTUDIO");
							System.out.println("          ================");
							System.out.println();
							
							//pedimos el numero de asignaturas
							//controlamos que el usuario introduzca un numero entero positivo
							do{
								System.out.print("Cuantas asignaturas:");
								try {
									basura = stdin.nextLine();
									cuantas = stdin.nextInt(); //lectura de datos
								} catch (InputMismatchException e) {
									cuantas = 0;
								}
								if(cuantas > 0) flag1=1;
							} while (flag1 != 1);
							
							//creamos el array de asignaturas y lo vamos rellenando
							asignaturas = new String[cuantas];
							basura = stdin.nextLine();
							for(int i=0; i<cuantas; i++) {
								System.out.print("   Asignatura " + (i+1) + " : ");
								asignaturas[i] = stdin.nextLine();
							}
							
							//creamos el array de horas 
							tabla = new double[cuantas][12];
							
							//salida de menu 
							System.out.println("\n    pulse ENTER para continuar");
							stdin.nextLine();
							break;
							
					case 2: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
						
							//Cabecera
							System.out.println("          HORAS DE ESTUDIO");
							System.out.println("          ================");
							System.out.println();
							
							//reiniciamos variables
							mes=0;
							fila=0;
							horas=0;
							
							//pedimos la asignatura
							flag1=0;
							basura = stdin.nextLine();
							System.out.print("Asignatura: ");
							asignatura=stdin.nextLine();
							
							//buscamos la asignatura en el array de asignaturas
							fila=-1;
							for(int i=0; i<asignaturas.length;i++) {
								if(asignatura.equals(asignaturas[i])) {
									fila=i;
									break;
								} 
							}
							
							//si no encontramos la asignatura, mostramos en pantalla este mensaje
							if (fila == -1)System.out.print("No encontrada,");
							
							//si hemos encontrado la asignatura,
							//pedimos el mes hasta que sea correcto
							if (fila>= 0) {
								do {
									System.out.print("Mes(1-12): ");
									try {
										mes = stdin.nextInt(); //lectura de datos
									} catch (InputMismatchException e) {
										mes = 0;
									}
								} while (mes < 1 || mes > 12);
							}
							
							//si el mes es correcto introducimos las horas
							if (mes >=1 ) {
								do {
									System.out.print("horas: ");
									try {
										horas = stdin.nextDouble(); //lectura de datos
									} catch (InputMismatchException e) {
										
										horas = -1;
									}
								}while (horas < 0);
								
								//guardamos las horas
								tabla [fila][mes-1]=horas;
								basura = stdin.nextLine();
							}
							
							//salida de menu 
							System.out.print(" pulse ENTER para continuar");
							stdin.nextLine();
							break;
							
					case 3: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
					
							//Cabecera
							basura = stdin.nextLine();
							System.out.println("          HORAS ANUALES POR ASIGNATURA");
							
							//recorremos el array de asignaturas
							//y a la vez el array de horas
							for(int i = 0; i < asignaturas.length; i++) {
								for (int j = 0; j<tabla[i].length; j++) {
									total = total + tabla[i][j];
								}
								System.out.printf("%-10s %6.2f \n", asignaturas[i],total );
								total = 0;
							}
							
							//salida de menu 
							System.out.print("\n pulse ENTER para continuar");
							stdin.nextLine();
							break;
							
					case 4: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
							
							//Cabecera
							basura = stdin.nextLine();
							System.out.println("          HORAS TOTALES MENSUALES");
							
							//recorremos el array de las horas
							for (int i = 0; i < tabla[0].length; i++) {
								for (int j = 0; j < asignaturas.length; j++) {
									total = total + tabla[j][i];
								}
								System.out.printf("%2d %6.2f\n", i+1, total);
								total = 0;
							}
							
							//salida de menu 
							System.out.print("\n pulse ENTER para continuar");
							stdin.nextLine();
							break;
							

					case 5: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
							
							//recorremos el array de las asignaturas
							for(int i = 0; i < asignaturas.length; i++) {
								for (int j = 0; j<tabla[i].length; j++) {
									total = total + tabla[i][j];
									if(maxima < total) {
										maxima = total;
										posMaxima = i;
									}
								}
								total = 0;
							}
						
							basura = stdin.nextLine();
							System.out.print("\nLa mas estudiada es " + asignaturas[posMaxima] + " con " + maxima + " horas.\n");
							maxima = 0;
							posMaxima = 0;
							
							//salida de menu 
							System.out.print("\n pulse ENTER para continuar");
							stdin.nextLine();
							break;
							
					case 6: flag=1;
						    break; 
						    
				    default: System.out.println("la opcion no existe");
			 				 basura = stdin.nextLine();
			 				 System.out.println("pulse una tecla para continuar");
			 				 stdin.nextLine();
			 				 break;
					}
		} while (flag == 0);
		
		stdin.close();
	}


}
