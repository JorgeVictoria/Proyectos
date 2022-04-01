package Practicas;

import java.util.Scanner;
import java.util.InputMismatchException;

public class U3P02 {

	public static void main(String[] args) {
		
		//declaracion de variables
	    Scanner stdin = new Scanner(System.in); //entrada de datos

	    //array con los meses del año
	    String mes[] = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
	    double litros[] = new double[12]; //array para los litros

	    double cantidad; //para almacenar la cantidad de litros
	    double lluvioso=0; //para almacenar la mayor cantidad de lluvia
	    double media; //para almacenar la cantidad media anual de lluvia
	    double temp; //para almacenar litros en la ordenacion
	    double mediana; //para almacenar la mediana de las lluvias
	    double desviacion; //para almacenar la desviacion tipica
	    double total=0; //para calcular la desviacion
	    
	    int flag;  //para certificar que los datos introducidos son correctos
	    int posLluvioso=0; //almacenamos la posicion del primer mes mas lluvioso
	    
	    String basura; //para recoger basura de la entrada de datos 
	    String meses; //para almacenar meses en la ordenacion
	    
	  //Borrado de pantalla
		System.out.print( "S\033[H\033[2J");
		System.out.flush();

	    //cabecera
	    System.out.println("        PRECIPITACIONES ANUALES");
	    System.out.println("        =======================");
	    System.out.println("");

	    //vamos a ir introduciendo precipitaciones mes a mes
	    for(int i=0; i<mes.length;i++){
	    	flag=0;
	    	do {
	    		System.out.print(mes[i] + ": ");
	    		//pedimos datos. Si introducimos un caracter no valido
	    		//cantidad sera negativa y volveremos a pedir el dato
	    		try {
					cantidad = stdin.nextDouble(); //lectura de datos
				} catch (InputMismatchException e) {
					basura = stdin.nextLine();
					cantidad = -1;
				}
	    		//si la cantidad es 0 o positiva, almacenamos la precipitacion
	    		if (cantidad >= 0) {
	    			litros[i]=cantidad;
	    			flag=1;
	    		}
	    	}while(flag == 0);
	    	
	    }
	    
	    //calculo del primer mes mas lluvioso
	   for(int i=0; i<mes.length; i++) {
		   if(litros[i] > lluvioso) {
			   lluvioso = litros[i];
			   posLluvioso = i;
		   }
	   }
	   //mostramos el mes mas lluvioso
	   System.out.println("\nEl mes mas lluvioso es " + mes[posLluvioso] + " con " + lluvioso + " mm");
	   
	   //calculo de la media
	   //inicializamos variables
	   cantidad = 0;
	   for(int i=0; i<mes.length;i++) {
		   cantidad = cantidad + litros[i];
	   }
	   //mostramos la media
	   media = cantidad/12;
	   System.out.printf("Media: %.2f mm", media);
	   
	   //calculo de la mediana
	   
	   //ordenamos los arrays, segun el mes menos lluvioso
	   for(int i = 0; i<mes.length;i++) {
		   for(int j= 0; j<mes.length; j++) {
			   if(litros[j] > litros[i]) {
				   //ordenamos el array de litros
				   temp = litros[i];
				   litros[i]=litros[j];
				   litros[j]=temp;
				   //ordenamos el array de los meses
				   meses = mes[i];
				   mes[i] = mes[j];
				   mes[j] = meses;
			   }
		   }
	   }
	   
	   //calculamos y mostramos la mediana
	   //si la longitud del array es impar, cogemos el dato del centro
	   if (mes.length%2 != 0) mediana = Math.ceil(litros[(mes.length/2)-1]);
	   //si la longitud del array, cogemos los 2 datos del centro
	   else mediana = ((litros[(mes.length/2)-1]) + (litros[mes.length/2]))/2;
	   System.out.printf("\nMediana: %.2f mm", mediana);
	   
	   //calculo de la desviacion
	   for(int i=0; i<mes.length; i++) {
		   total = total + Math.pow(litros[i]-media, 2);
	   }
	   desviacion = Math.sqrt(total/mes.length);
	   System.out.printf("\nDesviacion: %.3f mm", desviacion);
	}
}
