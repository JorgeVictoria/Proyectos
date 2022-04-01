package Practicas_Libro;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Ejercicio04 {

	public static void main(String[] args) {
		
		// definicion de variables
		double num1=0;  //primer numero
		double num2=0;  //segundo numero
		char operacion=43; //operacion a realizar
		int flag=0;
		int flagTry=0;
		String basura = "";
		Scanner stdin = new Scanner (System.in);
		
		do {
		
			//entrada de datos
			System.out.print("Introduce la expresion (numero operacion numero):");
			
			try {
				num1 = stdin.nextDouble();
				if (num1 == 0.0) {
					System.exit(0);
					System.out.print("fin del programa");
				}
				operacion = stdin.next().charAt(0);
				num2 = stdin.nextDouble();
			
			} catch (InputMismatchException e) {
				System.out.println("datos incorrectos");
				basura = stdin.nextLine();
				num1 = 0;
				flagTry = 1;
			}
			
			if(num1 != 0) {
				switch (operacion) {
				case 43:
					System.out.printf("%.2f + %.2f = %.2f\n",num1,num2, num1+num2);
					break;
				case 45:
					System.out.printf("%.2f - %.2f = %.2f\n",num1,num2, num1-num2);
					break;
				case 42:
					System.out.printf("%.2f x %.2f = %.2f\n",num1,num2, num1*num2);
					break;
				case 47:
					System.out.printf("%.2f : %.2f = %.2f\n",num1,num2, num1/num2);
					break;
				default: System.out.print("operacion no reconocida\n");
				}
			} else if (flagTry != 1) flag = 1;
			
			flagTry = 0;
		
		}while (flag == 0);
		
		stdin.close();
		

	}

}
