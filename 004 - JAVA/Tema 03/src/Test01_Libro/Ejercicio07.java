package Test01_Libro;

import java.util.Scanner;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		String s1;
		String s2;
		int n1,n2;
		n1= 0;
		n2= 0;
		Scanner stdin = new Scanner (System.in);
		
		System.out.print("Inserte numero:");
		s1 = stdin.nextLine();
		
		System.out.print("Inserte numero:");
		s2 = stdin.nextLine();
		
		try {
			n1 = Integer.parseInt(s1);
			n2 = Integer.parseInt(s2);
		} catch (NumberFormatException e) {
			System.out.println("error al pasar a entero");
			System.exit(0);
		}
		
		System.out.println(n1 + " + " + n2 + " = " + (n1+n2));
		
		stdin.close();

	}

}
