package Arrays;

public class ProblemaDelCumple {

	public static void main(String[] args) {
		
		boolean[] usado;
		int contador;
		int total=0;
		usado = new boolean[365];
		
		
		for (int i = 0 ; i <= 99; i++) {
		contador = 0;
		while (true) {
			int cumple;
			cumple = (int)(Math.random() * 365);
			contador++;
			System.out.printf("Persona %d nace %d ", contador, cumple);
			System.out.println();
			if ( usado[cumple]) {
				for (int j = 0; j<usado.length;j++) {
					usado[j]=false;
				}
				break;
			}
			usado[cumple] = true;
		}
		total=total+contador;
		}
		System.out.println(total);
		System.out.println("media:" + (total/100));
	}

}
