package Practicas_Tema_02;

import java.util.Scanner;

public class Ejercicio03 {

	public static void main(String[] args) {
		
		//declaracion de variables
		String username; //variable donde almacenamos el nombre de usuario o login del usuario
		String password; //variable donde almacenamos la password o contraseña del usuario
		String validaclave; //variable que va a ser una especie de log que almacena los errores a la hora de introducir los paramatros
		String mensaje; //mostraremos un mensaje por pantalla
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		
		//inicializacion de variables
		validaclave=""; //inicializamos la clave a cadena vacia
		mensaje="";
		
		//lectura de datos
		
		//preguntamos por el login
		System.out.print("Nombre de usuario: ");
		username = stdin.nextLine();
		
		//preguntamos por la password
		System.out.print("password: "); 
		password = stdin.nextLine();
		
		//cerramos la entrada de datos
		stdin.close();
		
		//comprobamos si el usuario es o no admin
		validaclave += username.equals("admin")?  "el usuario \"" + username +"\" no se puede utilizar \n" :"";
		
		//comprobamos que el nombre de usuario no empiece por numero
		validaclave += username.charAt(0) >= 48 && username.charAt(0) <= 57? "el nombre de usuario debe comenzar por letra\n":"";
		
		//comprobamos que la password tiene 8 caracteres
		validaclave += password.length() < 8? "la password debe tener un minimo de 8 caracteres\n":"";
		
		//comprobamos que la password tiene al menos una letra minuscula. Pendiente de encontrar una expresion regular
		validaclave += password.indexOf(97)>-1 || password.indexOf(98)>-1 || password.indexOf(99)>-1 ||  
					   password.indexOf(100)>-1 || password.indexOf(101)>-1 || password.indexOf(102)>-1 ||
					   password.indexOf(103)>-1 || password.indexOf(104)>-1 || password.indexOf(105)>-1 ||
					   password.indexOf(106)>-1 || password.indexOf(107)>-1 || password.indexOf(108)>-1 ||
					   password.indexOf(109)>-1 || password.indexOf(110)>-1 || password.indexOf(111)>-1 ||
					   password.indexOf(112)>-1 || password.indexOf(113)>-1 || password.indexOf(114)>-1 ||
					   password.indexOf(115)>-1 || password.indexOf(116)>-1 || password.indexOf(117)>-1 ||
					   password.indexOf(118)>-1 || password.indexOf(119)>-1 || password.indexOf(120)>-1 ||
					   password.indexOf(121)>-1 || password.indexOf(122)>-1? "":"la password debe tener una letra minuscula\n";
					   
		//comprobamos que la password tiene al menos una letra mayuscula. Pendiente de estudiar y encontrar una expresion regular
		validaclave += password.indexOf(65)>-1 || password.indexOf(66)>-1 || password.indexOf(67)>-1 ||  
					   password.indexOf(68)>-1 || password.indexOf(69)>-1 || password.indexOf(70)>-1 ||
					   password.indexOf(71)>-1 || password.indexOf(72)>-1 || password.indexOf(73)>-1 ||
					   password.indexOf(74)>-1 || password.indexOf(75)>-1 || password.indexOf(76)>-1 ||
					   password.indexOf(77)>-1 || password.indexOf(78)>-1 || password.indexOf(79)>-1 ||
					   password.indexOf(80)>-1 || password.indexOf(81)>-1 || password.indexOf(82)>-1 ||
					   password.indexOf(83)>-1 || password.indexOf(84)>-1 || password.indexOf(85)>-1 ||
					   password.indexOf(86)>-1 || password.indexOf(87)>-1 || password.indexOf(88)>-1 ||
					   password.indexOf(89)>-1 || password.indexOf(90)>-1 ? "":"la password debe tener una letra mayuscula\n";
					   
		//comprobamos que la password tiene al menos un simbolo que sea: "."   "_"   "@"
		validaclave += password.indexOf(46)>-1 || password.indexOf(64)>-1 || password.indexOf(95)>-1
				      ? "":"la password debe tener una letra \"._@\"\n";
			
		
		//salida por pantalla
		mensaje += validaclave.equals("") ? "clave correcta":validaclave;
		System.out.print(mensaje);
	}

}
