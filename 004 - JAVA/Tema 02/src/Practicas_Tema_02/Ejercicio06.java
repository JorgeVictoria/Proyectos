package Practicas_Tema_02;

import java.util.Scanner;

public class Ejercicio06 {

	public static void main(String[] args) {
		
		//declaracion de variabless
		String so;  //variable que almacena el sistema operativo
		int n1,n2; //variable para almacenar 2 numeros aleatorios entre el 0 y el 20
		int resultado; //variable para almacenar el valor del producto entre n1 y n2
		int dimeResultado; //variable que almacena el resusltado que introduce el usuario
		long tInicial,tFinal ; //variables que almacenan el tiempo inicial, final y la difencia entre ambos
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		String mensaje=""; //variable para almacenar si ha acertado o no
		
		//captura del Sistema Operativo
		so = System.getProperty("os.name");
		
		//Borrado de pantalla
		System.out.print( "S\033[H\033[2J");
		System.out.flush();
		
		//obtenemos 2 valores aleatorios entre 0 y 20 y calculamos su producto
		n1 = (int)(Math.random()*20);
		n2 = (int)(Math.random()*20);
		resultado = n1*n2;
		
		//mostramos por pantalla sistema operativo,  nombre y empieza el reto de la multiplicacion
		System.out.println("Me ejecuto en " + so);
		System.out.printf("Soy Jorge Victoria Andreu ¿Cuanto es %d * %d?:", n1,n2);
		
		//ponemos en marcha el crono
		tInicial = System.currentTimeMillis();
		
		//entrada de datos
		dimeResultado = stdin.nextInt();
		stdin.close();
		
		//paramos el crono
		tFinal = System.currentTimeMillis();
		
		//vemos si es correcto el resultado
		mensaje += resultado == dimeResultado ? "Has acertado": "Has fallado";
		
		//mostramos resultado y tiempo de respuesta
		System.out.printf("%d x %d = %d\n",n1,n2,resultado);
		System.out.println(mensaje);
		System.out.println("Han transcurrido " + (tFinal-tInicial) +" milisegundos");
		
	}

}
