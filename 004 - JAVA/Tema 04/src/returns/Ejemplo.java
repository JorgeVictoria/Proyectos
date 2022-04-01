package returns;

import java.util.Scanner;

public class Ejemplo {
	
	//variables globales
	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {

		//variables globales
		double cat1,cat2,hipotenusa;
		
		//entrada de datos
		System.out.print("cateto 1: ");
		cat1 = stdin.nextDouble();
		System.out.print("cateto 2: ");
		cat2 = stdin.nextDouble();
		
		//llamada a funcion
		hipotenusa = 17 + pitagoras(cat1,cat2);
		
		//salida por pantalla
		System.out.println("hipotenusa = " + hipotenusa);

	}
	
	private static double pitagoras(double c1, double c2) {
		
		return Math.sqrt(c1 * c1 + c2 * c2);
		
	}

}
