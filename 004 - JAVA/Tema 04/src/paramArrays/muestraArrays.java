package paramArrays;

public class muestraArrays {
	
	//variables globales
	private static int[] n = new int[3];  //array

	public static void main(String[] args) {
		
		//rellenamos el array
		for (int i = 0; i < n.length; i++) {
			n[i] = (int)(10 * Math.random()) + 1;
		}
		
		muestraListaInt(n);

	}
	
	private static void muestraListaInt(int[] num) {
		
		System.out.print( "n = [");
		for (int i = 0; i < num.length;  i++) {
			if (i > 0) System.out.print(", ");
			System.out.print( num[i]);
		}
		System.out.println(']');
	}

}
