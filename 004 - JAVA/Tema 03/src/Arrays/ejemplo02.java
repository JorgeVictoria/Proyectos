package Arrays;

public class ejemplo02 {

	public static void main(String[] args) {
		
		int[] enteros= new int[10];
		int cuantos= (int)(Math.random() * enteros.length + 1);
		
		for(int i=0; i < cuantos; i++) {
			enteros[i] =(int)(Math.random() * 10);
			System.out.print(enteros[i] + "   ");
		}
		
		System.out.println();
		
		for(int i=cuantos -1; i >= 0; i--) {
			System.out.print(enteros[i] + "   ");
		}
		
		
		
		

	}

}
