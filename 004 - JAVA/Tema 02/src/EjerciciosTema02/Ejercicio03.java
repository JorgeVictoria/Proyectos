package EjerciciosTema02;

public class Ejercicio03 {

	public static void main(String[] args) {
		
		//definicion de variables
		int dado1; //primer dado
		int dado2; //segundo dado
		int total; //tercer dado
		
		//tirada de dados
		dado1 = (int)(Math.random()*6+1);
		dado2 = (int)(Math.random()*6+1);
		
		//calculo del total de la tirada
		total = dado1 + dado2;
		
		//mostramos resultado por pantalla
		System.out.println ("El primer dado saca un " + dado1);
		System.out.println ("El segundo dado saca un " + dado2);
		System.out.println ("Tu tirada suma " + total);

	}

}
