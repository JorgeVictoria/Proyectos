package Practicas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class U3P04 {

	public static void main(String[] args) {
		
		//Variables
		Scanner stdin = new Scanner(System.in); //entrada de datos
		
		String basura; //para recoger basura de la entrada de datos
		String nombre; //para introducir el nombre de los contactos
		String borrar; //para almacenar si borramos o no 
		String operacion=""; //para recoger la opcion de las direcciones
		String insertar; // para recoger la opcion sobre las direcciones: borrar, insertar o salir
		String contiene=""; //almacena el contenido de una cadena a buscar
		String comienza=""; //almacena el inicio de una cadena a buscar
		
		
		int opcion; //opcion del menu
		int flag = 0; //para controlar que debemos permanecer dentro del programa
		int celdas = 0; //almacena las celdas del array
		int cantidad=0;  //almacena la cantidad de celdas del array contactos, para crear un array nuevo
		int pos = 0; //para almacenar la posicion del null
		int borrado=0; //para almacenar la direccion que queremos borrar
		
		
		boolean arraysExisten = false; //para comprobar si los arrays existen
		boolean hayNulls = false; //para comprobar si hay valores null en los arrays
		boolean ultima = false;// indica si estamos en la ultima posicion de cualquiera de las filas de direcciones
		
		//Algunos arrays los he tenido que inicializar aqui porque si lo hacia despues me daba error y no podia compilar
		String [] contactos = new String[0]; //array de contactos
		String [][] direcciones = new String[0][0]; //array de direcciones
		String [] copiaContactos = new String[0]; //para copiar el array de contactos
		String [][] copiaDirecciones = new String[0][0]; //para copiar todo el array de direcciones
		String [] copiaDireccion; //para copiar cada fila del array direcciones, una a una
		
		
		//MENU PRINCIPAL
		do{
							
			//Borrado de pantalla
			System.out.print( "S\033[H\033[2J");
			System.out.flush();
								
			//Cabecera
			System.out.println("          AGENDA DE CONTACTOS");
			System.out.println("          ===================");
			System.out.println();
								
			//menu
			System.out.println("1. Nuevo Contacto.");
			System.out.println("2. Borrar Contacto.");
			System.out.println("3. Modifica direcciones de un contacto.");
			System.out.println("4. Busca contacto.");
			System.out.println("5. Salir");
			System.out.println();
			System.out.print("Escoja una opcion (1-5):");
													
			//comprobamos que el dato introducido sea un entero
			//en caso contrario le damos a opcion valor 7 para que 
			//vuelva a aparecer el menu principal
			try {
				opcion = stdin.nextInt(); //lectura de datos
			} catch (InputMismatchException e) {
				opcion = 7;
			}
								
			switch(opcion) {
			
			//opcion 1: nuevo contato
			case 1: basura = stdin.nextLine();
			
					//inicializamos variables
					hayNulls = false;
					pos = 0;
					
					//comprobamos si los arrays existen
					//si no existen los creamos
					//realmente estaban creados en la declaracion de variables
					//pero siguiendo las instrcciones del ejercicio los vuelvo a crear, con una celda
					//es cierto que si no los inicializaba en la zona de variables aquí me daba error
					if(arraysExisten == false)
					{
						contactos = new String[1];
					    direcciones = new String[1][1];
						arraysExisten = true;
					}
					
					//comprobamos si hay null y almacenamos la posicion
					//con comprobarlo en el array de contactos suficiente
					//la fila correspondiente en el array de direcciones deberia estar vacia
					if(arraysExisten == true) {
						for (int i = 0; i < contactos.length; i++) {
							if (contactos[i] == null) {
								hayNulls = true;
								pos = i;
							}
						}
					}
					
					//como no hay null debemos copiar el array y crear uno nuevo
					//de esta forma creamos una nueva celda en el array de contactos
					//y una nueva fila en el array de direcciones
					if(hayNulls == false) {
						//primero el array de contactos
						copiaContactos = new String[contactos.length];  //creamos copia del array de contactos
						System.arraycopy(contactos, 0, copiaContactos, 0, contactos.length); //copiamos contactos en su copia
						cantidad = contactos.length + 1; // el +1 es para indicar que debemos añadir 1 celda al nuevo array que creemos
						contactos = new String [cantidad]; //creamos un nuevo array de contactos
						//copiamos de la copia de contactos al nuevo array de contactos
						for(int i = 0; i < copiaContactos.length; i++) {
							contactos[i]=copiaContactos[i];
						}
						pos = contactos.length - 1; //nos vamos a la ultima posicion para almacenar el nuevo contacto
						
				
						//segundo, el array de direcciones
						copiaDirecciones = direcciones.clone(); //clonamos al array de direcciones entero
						
						//creamos un nuevo array de direcciones, con un numero de filas equivalente al numero de contactos
						//como minimo una celda por fila, o casca el programa.Why?
						direcciones = new String [contactos.length][1]; 
						
						//copia del clon de direcciones al nuevo array de direcciones
						for(int i = 0; i < copiaDirecciones.length; i++) {
							direcciones[i] = new String [copiaDirecciones[i].length];
								for(int j = 0; j<copiaDirecciones[i].length; j++) {
									direcciones[i][j] = copiaDirecciones[i][j];
								}
						}
					}
					
					//introducimos el contacto
					System.out.print("Nombre: ");
					contactos[pos] = stdin.nextLine();
					System.out.println("        posicion " + pos);
					
					
					//salida de menu 
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
			//opcion 2:borrado de usuario		
			case 2: 
					System.out.println(); //espacio en blanco
					pos = 0; //inicializmos variables
					basura = stdin.nextLine();
					
					//preguntamos la posicion a borrar
					//insistimos mientras no se introduzca una pos correcta

					do {
						System.out.print("Posicion a borrar [0-" + (contactos.length-1) + "]: "); 
						try { 
							//basura = stdin.nextLine();
							pos = stdin.nextInt();
							basura = stdin.nextLine();
						} catch (InputMismatchException e) {
							pos = contactos.length;
							basura = stdin.nextLine();
						}
					} while (pos < 0 || pos >= contactos.length);
					
					//borrar usuario
					//si la celda tiene dato, se pone a null
					//y pondremos a null las celda de su fila correspondiente en el array direcciones
					if (contactos[pos] != null) {
						System.out.print("  Quieres borrar el usuario " + contactos[pos] + " (s/n)?");
						borrar = stdin.nextLine();
						if (borrar.equals("s") || borrar.equals("S")) {
							contactos[pos] = null;
							for(int i=0; i < direcciones[pos].length; i++) {
								direcciones[pos][i] = null;
							}
							System.out.println("");
							System.out.println("     Contacto y direcciones borradas");
						}
					
					//si el valor de la celda es null, no hay nada que borrar
					} else System.out.println("no hay ningun usuario en esa posicion");
						
					//salida de menu 
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
			
			//opcion 3: direcciones de los usuarios
			case 3: System.out.println(); //borrado de pantalla
					pos = 0; //inicializamos variable
					
					//preguntamos que usuario modificar
					//insistimos hasta que se introduzca una posicion correcta
					do {
						System.out.print("Posicion a modificar [0-" + (contactos.length-1) + "]: "); 
						try {
							basura = stdin.nextLine();
							pos = stdin.nextInt();
						} catch (InputMismatchException e) {
							pos = contactos.length;
						}
						//hemos introducido la posicion correta, pero su contenido es null
					 if( pos<contactos.length && contactos[pos] == null) { 
						 System.out.print("no existe el contacto.\n");
						 pos = contactos.length;
						 //hemos introducido una posicion incorrecta
					 } else if(pos == contactos.length) System.out.print("no existe el contacto.\n"); 
					} while (pos < 0 || pos >= contactos.length);
					
					basura = stdin.nextLine();
					
					//una vez tenemos un usuario que modificar, vamos a hacerlo
					//mostramos usuario
					do {System.out.println("\nDIRECCIONES de " + contactos[pos]);
						for (int i = 0; i < direcciones[pos].length; i++){
							//si el usuario tiene direcciones, las mostramos por pantalla
							if (direcciones[pos][i] != null) System.out.print( i + "   " + direcciones[pos][i] + "\n");
						}
						System.out.print("\n(I) Insertar (B)Borrar (S)Salir  Operacion: "); //opciones
						operacion = stdin.nextLine();
						operacion = operacion.toUpperCase();
						switch(operacion) {

							case "I": System.out.print("Insertar: ");
									  insertar=stdin.nextLine();
									  //si hay celda en null, rellenamos la primera que encontremos libre con la direccion
									  for (int i = 0; i < direcciones[pos].length; i++){
											if (direcciones[pos][i] == null) {
												direcciones[pos][i]=insertar;
												if (i == direcciones[pos].length -1 ) ultima = true;
												break;
											}
											
										}
									  
									  //si no hay celdas null, debemos crear una nueva celda en la fila
									  //con el procedimiento de crear un array para la almacenar copia
									  //copiar la fila en la copia
									  //crear una nueva fila, con una celda mas
									  //y pasar la info de la copia a la nueva fila
									  if(ultima) {
										   copiaDireccion = new String[direcciones[pos].length];
										   System.arraycopy(direcciones[pos], 0, copiaDireccion, 0, direcciones[pos].length);
											cantidad = direcciones[pos].length + 1;
											direcciones[pos] = new String [cantidad];
											for(int i = 0; i < copiaDireccion.length; i++) {
												direcciones[pos][i]=copiaDireccion[i];
											}
											ultima = false;
									  }
									  break;
									  
									  //para el borrado, poner las celdas de la fila a null
							case "B": System.out.print("Borrar: ");
							  		  borrado=stdin.nextInt();
							  		  direcciones[pos][borrado] = null;
							  		  basura = stdin.nextLine();
							  		  break;
						}
					 } while (operacion.equals("s")==false && operacion.equals("S")==false);
					
					//salida de menu 
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
			case 4: basura = stdin.nextLine();
					System.out.println();
					System.out.print("Comienza por la frase: ");
					comienza = stdin.nextLine();
					System.out.print("Contiene la frase:" );
					contiene = stdin.nextLine();
					System.out.println();
					
					//esta parte se podría hacer con un if-else
					//pero dejo abierta la posibilidad de que el usuario rellene los dos campos
					
					
					//si rellenamos la primera opcion
					//recorremos el array de contactos, buscando un contacto que empieze por la cadena
					//si lo localizamos, mostramos sus direcciones
					//hay que tener precaucion porque habran celdas con vallor null que no hay que mostrar
					if(comienza.length()>0) {
						for (int i = 0; i < contactos.length; i++) {
							if (contactos[i] != null && contactos[i].indexOf(comienza) == 0) {
								if (contactos [i] != null) System.out.println("   (" + (i) + ") Direcciones de " + contactos[i]);
								for(int j = 0; j < direcciones[i].length; j++) {
									if (direcciones[i][j]!=null) System.out.println("   " + j + " " + direcciones[i][j]);
								}
							}
							System.out.println();
						}
					    
					}
					
					//si rellenamos la segunda opcion
					//recorremos el array de contactos, buscando un contacto que contenga la cadena, pero no en el inicio
					//si lo localizamos, mostramos sus direcciones
					//hay que tener precaucion porque habran celdas con vallor null que no hay que mostrar
					if(contiene.length()>0) {
						for (int i = 0; i < contactos.length; i++) {
							if (contactos[i] != null && contactos[i].indexOf(contiene) > 0) {
								if (contactos [i] != null) System.out.println("   (" + (i) + ") Direcciones de " + contactos[i]);
								for(int j = 0; j < direcciones[i].length; j++) {
									if (direcciones[i][j]!=null) System.out.println("   " + j + " " + direcciones[i][j]);
								}
							}
							System.out.println();
						}
						
					}
					
					//si dejamos ambas opciones en blanco
					//recorremos el array de contactos
					//y mostramos todas las direcciones
					//hay que tener precaucion porque habran celdas con vallor null que no hay que mostrar
					if(contiene.length()==0 && comienza.length()==0) {
						for(int i = 0; i < contactos.length; i++) {
							if (contactos [i] != null)System.out.println("   (" + (i) + ") Direcciones de " + contactos[i]);
							for(int j = 0; j < direcciones[i].length; j++) {
								if (direcciones[i][j]!=null) System.out.println("   " + j + " " + direcciones[i][j]);
							}
							System.out.println();
						}
						
					}
					
					//salida de menu
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
			case 5: flag = 1;
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
