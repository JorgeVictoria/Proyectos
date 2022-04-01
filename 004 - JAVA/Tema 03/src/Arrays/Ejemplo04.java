package Arrays;
import java.util.Scanner;

public class Ejemplo04 {

	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		double empresa[][] = new double [5][12];
		int mes;
		int tienda;
		double benef=0;
		
		for (int i=0 ; i < 5; i++) {
			for (int j=0 ; j < 12; j++ ) {
				empresa [i][j] = (int)(Math.random() * 1000 + 1);
			}
		}
		
		
		for (int i=0 ; i < 5; i++) {
			for (int j=0 ; j < 12; j++ ) {
				if (empresa[i][j] < 100) System.out.print(" ");
				System.out.printf(" %5.2f ", empresa [i][j]);
			}
			System.out.println();
		}
		
		//beneficio de un mes
		System.out.print("Introduce un mes:");
		mes= stdin.nextInt();
		
		for(int i = 0; i < 5; i++) {
			benef = benef + empresa [i][mes-1];
		}
		
		System.out.println(benef);
		benef=0;
		
		//beneficio de una tienda
		System.out.print("Introduce una tienda: ");
		tienda = stdin.nextInt();
		
		for(int i = 0; i < 12; i++ ) {
			benef = benef + empresa [tienda-1][i];
		}
		
		System.out.println(benef);
		
		
		

	}

}
