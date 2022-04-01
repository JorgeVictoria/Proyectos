package Practicas;

import java.util.Scanner;

import java.util.InputMismatchException;

public class U3PEX1 {

	public static void main(String[] args) {
		
		//variables
		int opcion; //para controlar la opcion del menu
		int flag=0; //para controlar que no salimos del menu
		int flagMenu = 0; //variable flag para controlar la entrada de datos en el menu
		int pertenece = 0; //para ver si un caraacter esta en el alfabeto
		
		Scanner stdin = new Scanner(System.in);
		
		String basura; //almacena la basura de la entrada de datos
		String alfabeto= "ABCDEFGHIJKLMNOPQRSTUVWXY Z"; //alfabeto inicial
		String nuevoAlfabeto =""; //alfabeto temporal
		String mensaje= "" ; //mensaje original
		String mensajeLimpio = ""; //mensaje original limpio
		String mensajeCifrado = ""; //mensaje cifrado
		String clave = ""; //clave para formar el alfabeto de encriptado
		String claveLimpia = ""; //clave limpia con caracateres del alfabeto
		String permuta = ""; //alfabeto formado con la clave
		
		boolean repetido = false; //variable para ver si hay letras repetidas en el nuevo alfabeto
		
		
		//MENU PRINCIPAL
				do{
					
					//Borrado de pantalla
					System.out.print( "S\033[H\033[2J");
					System.out.flush();
					
					//Cabecera
					System.out.println("     CRIPTOGRAFIA CESAR");
					System.out.println();
					
					//menu
					System.out.println("1. Configurar.");
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
					case 1: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
							basura = stdin.nextLine();
						
							//Cabecera
							System.out.println("     CRIPTOGRAFIA CESAR");
							System.out.println();
						 
							//mostramos informacion
						
							//mostramos alfabeto actual y su longitud
							System.out.println("Alfabeto actual: " + alfabeto);
							System.out.println("Modulo actual:   " + alfabeto.length());
							System.out.println("");
						
							//solicitamos un alfabeto nuevo
							do {
							//inicializamos variables
							repetido = false;
							flagMenu = 0;
						
							//tecleamos diferentes caracteres. 
							//Si solo pulsamos enter, nos quedamos con el alfabeto almacenado
							System.out.println("  Nuevo alfabeto (Enter para no cambiar)"); 
							nuevoAlfabeto = stdin.nextLine();
						
							//si pulsamos enter, longitud de la cadena temporal es 0,
							//por lo tanto no se cambia el alfabeto
							//podremos salir de este menu
							if(nuevoAlfabeto.length()==0) flagMenu = 1;
							else {
								//si llegamos a este paso, es porque hemos introducido un nuevo alfabeto
								//debemos comprobar que el nuevo alfabeto no tenga letras repetidas
								for(int i=0; i < nuevoAlfabeto.length() -1; i++) { //-1 para no llegar a la ultima letra
									for (int j=i+1; j < nuevoAlfabeto.length(); j++) {
										if(nuevoAlfabeto.charAt(i) == nuevoAlfabeto.charAt(j)) {
											repetido = true;
											System.out.println("  " + nuevoAlfabeto);
											System.out.println("    Tiene letras repetidas");
										} if (repetido == true) break;  //una vez encuentra una repeticion, para
									}
								}
							}
						
							//si el alfabeto temporal no tiene letras repetidas y su longitud es mayor que 0
							//pasamos su contenido al alfabeto original
							if (repetido == false && nuevoAlfabeto.length() !=0) {
								alfabeto = nuevoAlfabeto;
								System.out.println(alfabeto);
								System.out.println("Modulo actual: " + alfabeto.length());
								flagMenu = 1;
								
							//si no hemos cambiado el alfabeto mostramos el que hay
							} else if (repetido == false) {
								System.out.println(alfabeto);
								System.out.println("Modulo actual: " + alfabeto.length());
							}
							//mientras flagMenu sea 0 no salimos de la condicion
							//para salir, o no hemos cambiado el alfabeto
							//o el nuevo alfabeto no tiene letras repetidas
							}while(flagMenu == 0);
						
						//salida de menu 
						System.out.println("\n    pulse ENTER para continuar");
						stdin.nextLine();
				        break;
				        
					case 2: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
						
							//Cabecera
							System.out.println("     CIFRAR MENSAJE");
							System.out.println();
							
							//inicializamos variables
							mensaje = "";
							mensajeCifrado = "";
							mensajeLimpio = "";
							
							//Lectura del mensaje original
							basura = stdin.nextLine();
							System.out.print("Mensaje: ");
							mensaje = stdin.nextLine();

							//limpiamos el mensaje
							for (int i = 0; i < mensaje.length(); i++) {
								pertenece = alfabeto.indexOf(mensaje.charAt(i));
								if (pertenece != -1) mensajeLimpio = mensajeLimpio + (mensaje.charAt(i));
							}
							
							System.out.println("   Eliminando letras fuera del alfabeto...");
							
							//introducimos la clave
							System.out.print("Clave (una frase): ");
							clave = stdin.nextLine();
							
							System.out.println("   Generando clave");
							
							System.out.println("   alfabeto: " + alfabeto);
							
							//limpiamos la clave
							for (int i = 0; i < clave.length(); i++) {
								pertenece = alfabeto.indexOf(clave.charAt(i));
								if (pertenece != -1) claveLimpia = claveLimpia + (clave.charAt(i));
							}
							
							//vamos a crear la permuta
							//primero con la clave
							//primero la primera letra para que la cadena permuta tenga un tamaño
							permuta="";
							permuta = Character.toString(claveLimpia.charAt(0));
							
							for (int i = 0; i < claveLimpia.length(); i++) {
								pertenece = permuta.indexOf(claveLimpia.charAt(i));
								if (pertenece == -1) permuta = permuta + (claveLimpia.charAt(i));
							}
							
							//y ahora rellenamos la permuta con el resto del alfabeto
							for (int i = 0; i < alfabeto.length(); i++) {
								pertenece = permuta.indexOf(alfabeto.charAt(i));
								if (pertenece == -1) permuta = permuta + (alfabeto.charAt(i));
							}
							
							System.out.println("   Permuta: " + permuta);
							
							//y ahora vamos a cifrar el mensaje original
							for (int i = 0; i < mensaje.length(); i++) {
								pertenece = alfabeto.indexOf(mensaje.charAt(i));
								mensajeCifrado = mensajeCifrado + permuta.charAt(pertenece);
							}
						
							System.out.print("\ncifra: " + mensajeCifrado);
							
							//salida de menu
							System.out.println("\n\n    pulse ENTER para continuar");
							stdin.nextLine();
							break;
							
					case 3: //Borrado de pantalla
							System.out.print( "S\033[H\033[2J");
							System.out.flush();
					
							//Cabecera
							System.out.println("     CIFRAR MENSAJE");
							System.out.println();
							
							//inicializamos variables
							mensaje = "";
							mensajeCifrado = "";
							mensajeLimpio = "";
							
						
						//Lectura del mensaje cifrado
							basura = stdin.nextLine();
							System.out.print("Cifra: ");
							mensajeCifrado = stdin.nextLine();

						//limpiamos el mensaje
						for (int i = 0; i < mensajeCifrado.length(); i++) {
							pertenece = alfabeto.indexOf(mensajeCifrado.charAt(i));
							if (pertenece != -1) mensajeLimpio = mensajeLimpio + (mensajeCifrado.charAt(i));
						}
						
						System.out.println("   Eliminando letras fuera del alfabeto...");
						
						//introducimos la clave
						System.out.print("Clave (una frase): ");
						clave = stdin.nextLine();
						
						System.out.println("   Generando clave");
						
						System.out.println("   alfabeto: " + alfabeto);
						
						//limpiamos la clave
						for (int i = 0; i < clave.length(); i++) {
							pertenece = alfabeto.indexOf(clave.charAt(i));
							if (pertenece != -1) claveLimpia = claveLimpia + (clave.charAt(i));
						}
						
						//vamos a crear la permuta
						//primero con la clave
						//primero la primera letra para que la cadena permuta tenga un tamaño
						permuta="";
						permuta = Character.toString(claveLimpia.charAt(0));
						
						for (int i = 0; i < claveLimpia.length(); i++) {
							pertenece = permuta.indexOf(claveLimpia.charAt(i));
							if (pertenece == -1) permuta = permuta + (claveLimpia.charAt(i));
						}
						
						//y ahora rellenamos la permuta con el resto del alfabeto
						for (int i = 0; i < alfabeto.length(); i++) {
							pertenece = permuta.indexOf(alfabeto.charAt(i));
							if (pertenece == -1) permuta = permuta + (alfabeto.charAt(i));
						}
						
						System.out.println("   Permuta: " + permuta);
						
						//y ahora vamos a descifrar el mensaje original
						
						for (int i = 0; i < mensajeLimpio.length(); i++) {
							pertenece = permuta.indexOf(mensajeLimpio.charAt(i));
							mensaje = mensaje + alfabeto.charAt(pertenece);
						}
					
						System.out.print("\n Mensaje: " + mensaje);
						
						//salida de menu
						System.out.println("\n    pulse ENTER para continuar");
						stdin.nextLine();
						break;
						
					case 4: flag = 1;
							break;
					default: System.out.println("la opcion no existe");
			 				 basura = stdin.nextLine();
			 				 System.out.println("pulse una tecla para continuar");
			 				 stdin.nextLine();
			 				 break;
					}
				}while (flag == 0 );
			
		stdin.close(); //cerramos la variable de lectura de datos

	}

}
