package ejercicio02;
import java.util.Scanner;


public class U04E02 {
	

	public static void main(String[] args) {
		//variables
		Scanner stdin = new Scanner(System.in);
		String basura;
		
		//borrado de pantalla
		MiCo.cls();
		
		//primer cambio de color
		MiCo.setColor("textoverde","FONDOPURPURA");
		MiCo.cls();
		System.out.println("Jorge Victoria Andreu");
		
		//segundo cambio de color
		MiCo.setColor("textoverdeclaro","FONDONEGRO");
		MiCo.cls();
		System.out.println("Jorge Victoria Andreu");
		
		//cambio de posicion
		MiCo.setCursor(10,10);
		System.out.println("Jorge Victoria Andreu");
		System.out.println("pulse enter");
		basura = stdin.nextLine();
		
		//cambio de color con enteros
		//los parametros se podrian solicitar
		MiCo.setColor(46);
		MiCo.cls();
		
	}

}
