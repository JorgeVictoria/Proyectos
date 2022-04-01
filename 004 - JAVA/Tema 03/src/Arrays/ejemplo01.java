package Arrays;

public class ejemplo01 {

	public static void main(String[] args) {
		
		double[] miArray; //definicion de la referencia al array
		miArray=new double[7];  //creamos un array con 7 posiciones
		
		//ver el espacio del array
		int posiciones = miArray.length;
		
		//rellenar un array
		for (int i = 0; i <= posiciones-1; i++) {
			miArray[i] = Math.random();
		}
		
		//leer un array
		for (int i = 0; i <= posiciones-1; i++) {
			System.out.printf("%.2f",miArray[i]);
			if (i < posiciones-1) System.out.print("    ");
		}
	}

}
