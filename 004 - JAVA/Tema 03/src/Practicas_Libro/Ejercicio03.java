package Practicas_Libro;

public class Ejercicio03 {

	public static void main(String[] args) {
		
		//definicion de variables
		long tinicial,tfinal,tcalculo;
		int numero=0;
		int divisores=0;
		int temp=0;
		
		tinicial = System.currentTimeMillis();
		for(int i = 1; i <= 10000; i++) {
			for (int j = 1; j <= i; j++){
				if(i%j==0) temp++;
			}
			if (temp > divisores) {
				divisores = temp;
				numero = i;
			}
			temp=0;
		}
		tfinal = System.currentTimeMillis();
		tcalculo = tfinal - tinicial;
		
		
		System.out.println("Primer numero con mayor numero de divisores, entre 0 a 10000"
				+ ": " + numero);
		System.out.println("Numero de divisores: " + divisores);
		System.out.println(tcalculo + "ms");

	}

}
