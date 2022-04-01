package Tema03_Libro;

import java.util.Scanner;

public class Ejercicio03 {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner (System.in);
		double valor;
		String unidad;
		double pulgadas, pies, yardas, millas, metros;
		boolean correcta = true;
		
		
		//entrada de datos
		System.out.print("Teclee valor: ");
		valor = stdin.nextDouble();
		stdin.nextLine();
		System.out.print("Teclee unidad: ");
		unidad = stdin.nextLine();
		
		//cerramos teclado
		stdin.close();
		
		//pasamos unidad a minusculas
		unidad = unidad.toLowerCase();
		
		//convertimos el valor introducido a pulgadas
		if (unidad.equals("pulgadas") || unidad.equals("pulgada")) {
			pulgadas = valor;
		} else if (unidad.equals("pies") || unidad.equals("pie")) {
			pulgadas = valor * 12 ;
		} else if (unidad.equals("yardas") || unidad.equals("yarda")) {
			pulgadas = valor * 36 ;
		} else if (unidad.equals("millas") || unidad.equals("milla")) {
			pulgadas = valor * 12 * 5280 ;
		} else if (unidad.equals("metros") || unidad.equals("metro")) {
			pulgadas = valor * 39.37;
		} else { // si el valor de la medida es desconocido o mal tecleado
			System.out.println("No comprendo \"" + unidad + "\".");
			correcta=false;
			pulgadas= -1;
		}
		
		//covertimos desde pulgadas al resto de unidades de medida
		if (correcta) {
			pies = pulgadas / 12;
			yardas = pulgadas /36;
			millas = pulgadas / (12 * 5280);
			metros = pulgadas /39.37;
			System.out.printf("%12.5g pulgadas", pulgadas);
			System.out.printf("%12.5g pies", pies);
			System.out.printf("%12.5g yardas", yardas);
			System.out.printf("%12.5g millas", millas);
			System.out.printf("%12.5g metros", metros);
		}
		
		
	}

}
