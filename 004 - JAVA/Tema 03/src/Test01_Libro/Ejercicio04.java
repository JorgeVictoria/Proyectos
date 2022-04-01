package Test01_Libro;

public class Ejercicio04 {

	public static void main(String[] args) {
		
		int num = 3;
		
		while (num <= 36) {
			System.out.print(num + " ");
			num = num + 3;
		}
		
		System.out.println();
		
		num = 3;
		
		do {
			System.out.print(num + " ");
			num = num + 3;
		} while (num <= 36 );
		
		System.out.println();
		
		for (int i = 3; i <= 36; i++) {
			if (i%3==0) System.out.print(i + " ");
		}
	}

}
