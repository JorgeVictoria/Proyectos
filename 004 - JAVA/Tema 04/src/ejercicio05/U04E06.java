package ejercicio05;

//import
import java.util.Scanner;

public class U04E06 { //inicio de la clase
	
	//variables de la clase
	static Scanner stdin = new Scanner(System.in); //para la entrada de datos
	
	public static void main(String[] args) { //inicio del main
		//variables locales
		int opcion=0;  //recoge la opcion del menu
		int decimales; //recoge cantidad de decimales
		int notas; //recoge cantidad de notas
		
		double nota; //para introducir la nota
		double calculo; //para hacer calculos
		
		EstaUni dam = new EstaUni(1,1); //para que no de pegas Eclipse, lo creo aqui
		EstaUni damNormalizado = new EstaUni(1); //para los datos normalizados
		EstaUni damEscalado = new EstaUni(1); //para los datos escalados
		
		String basura;
		
		//menu principal del programa
		do {
			//Borrado de pantalla
			System.out.print( "S\033[H\033[2J");
			System.out.flush();
			
			//menu principal
			System.out.println("  ESTADISTICA UNIDIMENSIONAL");
			System.out.println("  ==========================");
			System.out.println();
			System.out.println("1. Definir datos desde teclado.");
			System.out.println("2. Parametros.");
			System.out.println("3. Normalizar y escalar.");
			System.out.println("4. Salir.");
			System.out.println();
			
			//solicitamos opcion
			do {
				System.out.print("  Escoja una opcion (1-4):");
				while (!stdin.hasNextInt()) {
					System.out.print("\n  Escoja una opcion (1-4):");
					stdin.nextLine();
				}
				opcion= stdin.nextInt();
			}while(opcion < 1 || opcion > 4);
			
			switch(opcion){
			case 1: 
				
					//solicitamos nº de decimales
					do {
						System.out.print("Cuantos decimales quiere en los resultados?:");
						while (!stdin.hasNextInt()) {
							System.out.print("\nCuantos decimales quiere en los resultados?:");
							stdin.nextLine();
						}
						decimales = stdin.nextInt();
					} while (decimales < 0);
					
					//solicitamos cantidad de notas
					do {
						System.out.print("Cuantos datos quiere definir:?:");
						while (!stdin.hasNextInt()) {
							System.out.print("\nCuantos datos quiere definir:?:");
							stdin.nextLine();
						}
						notas = stdin.nextInt();
					} while (notas < 1);
					
					//creamos el objeto pasando la cantidad de datos y los decimales
					dam = new EstaUni(notas,decimales);
					
					//empezamos a meter datos
					for(int i = 0; i < dam.notas.length; i++) {
						System.out.print("dato[" + i + "]:");
						nota = stdin.nextDouble();
						dam.setNota(nota, i);
					}
					
					//salida de menu
					basura = stdin.nextLine();
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					break;
					
			case 2: 
				
					//datos centrales
					System.out.println("Parametros centrales");
					
					//media
					calculo = dam.media(); //calculo
					calculo = dam.redondea(calculo, dam.decimales); //redondeo
					System.out.print("  Media: " + calculo);
					
					//mediana
					calculo = dam.mediana(); //calculo
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print("  Mediana: " + calculo);
					System.out.println();
					
					//percentiles
					System.out.print("  Percentiles ");
					
					//percentil P1
					calculo = dam.percentil(1);
					calculo = dam.redondea(calculo,dam.decimales);
					System.out.print("P1: " + calculo);
					
					//percentil P2
					calculo = dam.percentil(25);
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" P25: " + calculo);
					
					//percentil P3
					calculo = dam.percentil(50);
					calculo = dam.redondea(calculo,dam.decimales);
					System.out.print("  P50: " + calculo);
					
					//percentil P4
					calculo = dam.percentil(75);
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" P75: " + calculo);
					
					//percentil P5
					calculo = dam.percentil(99);
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" P99: " + calculo);
					System.out.println();
					
					//parametros de dispersion
					System.out.println("Parametros de dispersion");
					
					//varianza
					calculo = dam.varianza();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print("  Varianza: " + calculo); 
					
					//varianza Muestral
					calculo = dam.varianzaM();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" Varianza Muestral: " + calculo);
					
					//varianza
					calculo = dam.desvEstandar();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print("  Desv. Estandar: " + calculo); 
					
					//varianza Muestral
					calculo = dam.desvEstandarM();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" Desv. Estandar Muestral: " + calculo);
					
					//varianza Muestral
					calculo = dam.rango();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" Rango: " + calculo);
					System.out.println();
					
					//parametros de forma
					System.out.println("Parametros de forma");
					
					//Curtosis
					calculo = dam.curtosis();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" Curtosis: " + calculo);
					if(calculo > 0) System.out.print(" --> empinada");
					else if ( calculo == 0) System.out.print(" --> normal");
					else if ( calculo < 0) System.out.print(" --> achatada");
					System.out.println();
					
					//Cafisher
					calculo = dam.cafisher();
					calculo = dam.redondea(calculo, dam.decimales);
					System.out.print(" Cafisher: " + calculo);
					if(calculo > 0) System.out.print(" --> a la derecha");
					else if ( calculo == 0) System.out.print(" --> c");
					else if ( calculo < 0) System.out.print(" --> a la izquierda");
					System.out.println();
					
					//salida de menu
					basura = stdin.nextLine();
					System.out.println("\n    pulse ENTER para continuar");
					stdin.nextLine();
					
					break;
				
			case 3: 
					//generamos los dos nuevos objetos 
					//uno para los datos normalizados
					//otro para los datos escalados
					damNormalizado = new EstaUni(dam.notas.length);
					damEscalado = new EstaUni(dam.notas.length);
					
					//llamamos a los metodos correspondientes para que haga la conversion
					damNormalizado.normalizar();
					damEscalado.escalar(0,1);
					
					System.out.println("Datos normalizados: " + damNormalizado.toString());
					System.out.println("Datos escalados: " + damEscalado.toString());
				
				break;
			case 4: System.out.println("Fin de programa");
			
			} //fin del switch
			
		} while (opcion != 4); //fin del menu principal
		
	} //fin del main

} //fin de la clase
