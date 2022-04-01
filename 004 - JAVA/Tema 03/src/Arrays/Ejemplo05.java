package Arrays;
import java.util.Scanner;

public class Ejemplo05 {

	public static void main(String[] args) {
		
		int mega[] = new int [1000000];
		boolean encontrado = false;
		int numero;
		int temp;
		int pos=0;
		Scanner stdin = new Scanner(System.in);
		String cadena ="";
		
		for (int i = 0; i < 1000000; i++) {
			mega [i] = (int)(Math.random() * 1000000 + 1);
		}
		
		/*do {
			System.out.print("Introduce un numero:");
			numero = stdin.nextInt();
			while (encontrado == false && pos < mega.length ) {
				if (numero == mega[pos]) {
					encontrado = true;
				} else {
					pos++;
				}
			}
			cadena += (encontrado == true)? "posicion " + pos: " no encontrado\n";
			System.out.print(cadena);
			cadena="";
		} while (encontrado == false);*/
		
		//vemos el mayor
		int mayor = mega[0];
		int menor = mega[0];
		for (int i = 0; i<mega.length;i++) {
			if(mayor < mega[i]) {
				mayor=mega[i];
				pos = i+1;
			}
			
		}
		
		System.out.println(mayor);
		System.out.println(pos);
		
		//ordenar un array
		for (int i=0; i < mega.length; i++) {
			for (int j = i + 1 ; j < mega.length; j++) {
				if (mega[j]>mega[i]) {
					temp=mega[i];
					mega[i]=mega[j];
					mega[j]=temp;
				}
			}
		}
		
        for (int i = 0; i < mega.length; i++) {
        	System.out.println(mega[i]);
        }
				
		
		

	}

}
