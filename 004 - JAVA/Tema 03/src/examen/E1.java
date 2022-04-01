package examen;

import java.util.Scanner;
import java.util.InputMismatchException;

public class E1 {

	public static void main(String[] args) {
		
		//variables locales
		int numPuestos;
		int numPartidos;
		int votosBlanco;
		int[] votos;
		int[][] tabla;
		int total=0;
		int porcentaje;
		int posibles=0;
		int divisor = 1;
		int maximo=0;
		int pos=0;
		
		String[] partidos;
		String basura;
		
		boolean flag;
		boolean nulo=false;
		
		Scanner stdin = new Scanner(System.in);

		//pedimos datos iniciales
		System.out.print("Puestos: ");
		numPuestos = stdin.nextInt();
		System.out.print("Partidos: ");
		numPartidos = stdin.nextInt();
		System.out.print("Votos en blanco(abstenciones): ");
		votosBlanco = stdin.nextInt();
		
		//creamos los arrays para almacenar partidos y votos
		partidos = new String[numPartidos];
		votos = new int[numPartidos];
		
		//rellenamos los arrays
		for (int i = 0; i < partidos.length; i++) {
			System.out.print("Partido: ");
			partidos[i] = stdin.next();
			flag = false;
			do {
				System.out.print("  Votos: ");
				try {
					votos[i] = stdin.nextInt();
				} catch (InputMismatchException e) {
					nulo = true;
					basura = stdin.nextLine();
				}
				if (i==0 && nulo==false) flag=true;
				else if (votos[i] < 0) flag=false;
				else if(i > 0 && nulo==false && votos[i] < votos[i-1] ) flag=true;
				else  if (nulo == true || votos[i] > votos[i-1]) flag=false;
				nulo = false;
			} while (flag == false);
		}
		
		//PASO 4
		for(int i=0;i<votos.length;i++){
			total = total + votos[i];
		}
		
		total = total + votosBlanco;
		porcentaje = (total * 3)/100;
		
		System.out.println("El total de votos es " + total + " validos y en blanco. El 3% es " + porcentaje);
		
		//PASO 5
		//calculamos los posibles
		for(int i = 0; i < votos.length; i++) {
			if(votos[i] > porcentaje) posibles++;
		}
		
		tabla = new int[numPuestos][posibles];
		
		//paso 6
		for(int i = 0; i < tabla.length; i++) {
			for(int j = 0; j < tabla[i].length; j++) {
				tabla[i][j] = votos[j]/divisor;
			}
			divisor++;
		}
		
		int[] escanos = new int[posibles];
		
		//paso 7
		for(int i = 0; i < tabla.length; i++) {
			for(int j = 0; j < tabla[i].length; j++) {
				maximo = tabla[i][j];
			}
		}
		
	}

}
