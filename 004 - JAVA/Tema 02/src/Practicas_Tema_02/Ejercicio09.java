package Practicas_Tema_02;

import java.math.BigInteger;

public class Ejercicio09 {

	public static void main(String[] args) {
		
	
		//definicion de variables
		long num;
		BigInteger bigNum;
		
		//obtenemos el mayor numero long para x bits
		num = (long)Math.pow(2, 63);
		
		//si el resultado de la potencia es par, hay que restar 1 para obtener el mayor impar
		num -= (num%2)==0 ? 1:0;  
		
		//mostramos por pantalla el numero
		System.out.println("El mayor long impar es:               " + num);
		    
		//pasamos el numero long a bigInteger
		bigNum = BigInteger.valueOf(num);
		
		//obtenenemos el siguiente primo y lo mostramos por pantalla
		bigNum = bigNum.nextProbablePrime();
		System.out.println("El primer primo probable mayor es:    " + bigNum);
		
		//obtenenemos el siguiente primo y lo mostramos por pantalla
		bigNum = bigNum.nextProbablePrime();
		System.out.println("El siguiente primo probable mayor es: " + bigNum);	

	}

}
