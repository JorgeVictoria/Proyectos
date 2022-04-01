package Practicas;

//librerias importadas
import java.util.Scanner;
import java.util.InputMismatchException;

public class U3P01 {

	public static void main(String[] args) {
		
		//DEFINICION DE VARIABLES
		int flag = 0; //variable flag para permanecer en el menu o salir de el
		int flagMenu = 0; //variable flag para controlar la entrada de datos en el menu
		int clave = 0; //variable para almacenar la clave
		int opcion=0; //variable que almacena la opcion seleccionada en el menu
		int pertenece = 0; //variable para ver si hay letras que no pertenecen al alfabeto
		int posicion; //para almacenar la posicion del caracter en el alfabeto
		int nuevapos; //para almacenar la nueva posicion del caracter
		
		boolean repetido = false; //variable para ver si hay letras repetidas en el nuevo alfabeto
		
		Scanner stdin = new Scanner(System.in); //variable de tipo scanner para la entrada de datos
		
		String basura; //para vaciar la entrada de datos
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //almacenamos el alfabeto original o modificado
		String nuevoAlfabeto =""; //almacenaremos el nuevo alfabeto de forma temporal
		String cadenaOriginal = "";  //cadena original
		String cadenaModificada = ""; //cadena con los elementos que tiene el alfabeto
		String cadenaEncriptada = ""; //cadena encriptada
		
		
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
		
			//diferentes opciones del programa
		switch (opcion) { 
		
		//para introducir un nuevo alfabeto
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
				System.out.println("  Nuevo alfabeto (Enter para no cambiar):"); 
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
								System.out.println("    Tiene letras repetidas");
							} if (repetido == true) break;  //una vez encuentra una repeticion, para
						}
					}
				}
				
				//si el alfabeto temporal no tiene letras repetidas y su longitud es mayor que 0
				//pasamos su contenido al alfabeto original
				if (repetido == false && nuevoAlfabeto.length() !=0) {
					alfabeto = nuevoAlfabeto;
					System.out.println("Modulo actual: " + alfabeto.length());
					flagMenu = 1;
					
				//si no hemos cambiado el alfabeto mostramos el que hay
				} else if (repetido == false) {
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
				basura = stdin.nextLine();
			
				//cabecera
				System.out.println("     CRIPTOGRAFIA CESAR-ENCRIPTAR");
				System.out.println();
				
				//variable inicializada
				cadenaModificada ="";
				cadenaEncriptada ="";
				
				//Pedimos la clave. No puede ser igual o menor quecero 
				//o igual o mayor que la longitud del alfabeto
				do {
					System.out.print("Clave (1-" + alfabeto.length() + "): ");
					//controlamos que el operador de cifra no introduzca un caracter
					try {
						clave = stdin.nextInt();
					} catch (InputMismatchException e) {
						basura = stdin.nextLine();  //si no lo pongo, cuando pongo un caracter entra en bucle
						clave = 0;
					} 
				} while (clave <= 0  || clave >= alfabeto.length());
				
				//pedimos el mensaje en claro
				basura = stdin.nextLine();
				System.out.print("Texto en claro: ");
				cadenaOriginal = stdin.nextLine();
				
				//limpiamos la cadena de caracteres no existentes
				for(int i=0; i < cadenaOriginal.length(); i++) {
					pertenece = alfabeto.indexOf(cadenaOriginal.charAt(i));
					if (pertenece != -1) cadenaModificada = cadenaModificada + (cadenaOriginal.charAt(i));
				}
				//mostramos la cadena moficada
				System.out.print(" Texto filtrado: " + cadenaModificada);
				
				//calculamos y obtenemos la nueva cadena ya encriptada
				for(int i=0; i < cadenaModificada.length(); i++) {
					posicion = alfabeto.indexOf(cadenaModificada.charAt(i));
					nuevapos = (posicion + clave)% alfabeto.length();
				    cadenaEncriptada = cadenaEncriptada + alfabeto.charAt(nuevapos);
				}
				
				System.out.print("\n Texto cifrado: " + cadenaEncriptada);
			
				//salida de menu 
				System.out.println("\n    pulse ENTER para continuar");
				stdin.nextLine();
				break;
				
		case 3: //Borrado de pantalla
				System.out.print( "S\033[H\033[2J");
				System.out.flush();
				basura = stdin.nextLine();
		
				//cabecera
				System.out.println("     CRIPTOGRAFIA CESAR-DESENCRIPTAR");
				System.out.println();
			
				//variable inicializada
				cadenaModificada ="";
				cadenaEncriptada ="";
				
				//Pedimos la cifra. No puede ser menor o igual que cero o igual
				// o mayor que la longitud del alfabeto
				do {
					System.out.print("Clave (1-" + alfabeto.length() + "): ");
					//controlamos que el operador de cifra no introduzca un caracter
					try {
						clave = stdin.nextInt();
					} catch (InputMismatchException e) {
						basura = stdin.nextLine();  //si no lo pongo, cuando pongo un caracter entra en bucle
						clave = 0;
					} 
				} while (clave <= 0  || clave >= alfabeto.length());
				
				//pedimos el mensaje encriptado
				basura = stdin.nextLine();
				System.out.print("Texto cifrado: ");
				cadenaOriginal = stdin.nextLine();
				
				//limpiamos la cadena de caracteres no existentes
				for(int i=0; i < cadenaOriginal.length(); i++) {
					pertenece = alfabeto.indexOf(cadenaOriginal.charAt(i));
					if (pertenece != -1) cadenaModificada = cadenaModificada + (cadenaOriginal.charAt(i));
				}
				//mostramos la cadena moficada
				System.out.print(" Cifra filtrada: " + cadenaModificada);
				
				//calculamos y obtenemos la nueva cadena ya desencriptada
				for(int i=0; i < cadenaModificada.length(); i++) {
					posicion = alfabeto.indexOf(cadenaModificada.charAt(i));
					nuevapos = (posicion - clave)% alfabeto.length();
					if(nuevapos < 0) nuevapos = alfabeto.length() + nuevapos;
				    cadenaEncriptada = cadenaEncriptada + alfabeto.charAt(nuevapos);
				}
				
				System.out.print("\n Texto en claro: " + cadenaEncriptada);
			
				//salida de menu 
				System.out.println("\n    pulse ENTER para continuar");
				stdin.nextLine();
				break;
			
				
		case 4: //salida del programa
		        flag=1;
		        basura = stdin.nextLine();
		        System.out.println("pulse una tecla para continuar");
		        stdin.nextLine();
		        break;
		        
		        //cuando no introducimos una opcion valida
		 default: System.out.println("la opcion no existe");
		 		basura = stdin.nextLine();
		 		System.out.println("pulse una tecla para continuar");
		 		stdin.nextLine();
		        break;
		}
		} while (flag == 0); //mientras la variable flag sea 0, nos mantenemos en el menu
		//cerramos la variable scanner
		stdin.close();
	}

}
