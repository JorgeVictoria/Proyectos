package Test01_Libro;

public class Test {

	public static void main(String[] args) {
		//variables
		int[] miArray = new int[5];
		double probabilidad;
		int numero;
		
		
		//para cada posicion del array, calculas la probabilidad
		//si sale 0,1 entonces en esa celda puede haber un numero entre 0 y 9
		//si es mayor entonces el valor de esa celda es 0
		for(int i = 0;i< miArray.length;i++) {
			probabilidad = ((int)(10 * Math.random()))/10.0;
			if(probabilidad == 0.1) miArray[i]=(int)(10*Math.random());
			else miArray[i]=0;
		}
		
		for (int i = 0; i < miArray.length; i++) {
			System.out.print(miArray[i] + " ");
		}
	}

}
