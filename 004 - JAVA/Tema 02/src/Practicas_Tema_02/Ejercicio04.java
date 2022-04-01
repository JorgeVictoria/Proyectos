package Practicas_Tema_02;

import java.util.Scanner;

public class Ejercicio04 {

	public static void main(String[] args) {
		
		//declaracion de constantes
		final double XC=0; //coordenadas iniciales en el eje X del cañon en el mundo
		final double YC=0; //coordenadas iniciales en el eje Y del cañon en el mundo
		final double LONGITUD_CANION = 5; //longitud del cañon
		final double G = 9.8; // gravedad
		
		//declaracion de variables
		int angulo; //angulo del cañon
		int velocidad; //velocidad del proyectil
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		double radianes;  //valor de los grados en radianes
		double radianesCuadrado;  //para el calculo de la distancia maxima
		double tiempoSubida; //tiempo de subida del proyectil
		double tiempoVuelo; //tiempo de vuelo del proyectil
		double alturaMaxima; //altura maxima del proyectil
		double distanciaMaxima; //distancia maxima que alcanza el proyectil
		double i0, i1, i2, i3, i4;  //variables para almacenar los intervalos de tiempo
		double xp,yp; //coordenadas del proyectil
		double xpos1, xpos2, xpos3, xpos4;
		double ypos1, ypos2, ypos3, ypos4;
		
		
		//peticion de datos
		System.out.print("Teclee angulo (grados): ");
		angulo = stdin.nextInt();
		System.out.print("Teclee velocidad (m/s): ");
		velocidad = stdin.nextInt();
		
		
		//cerramos la entrada de datos
		stdin.close();
		
		
		//calculos matematicos
		radianes = Math.toRadians(angulo);
		radianesCuadrado = Math.toRadians(2*angulo);
		tiempoSubida = (velocidad * Math.sin(radianes))/G;
		tiempoVuelo = 2 * tiempoSubida;
		alturaMaxima = (Math.pow(velocidad,2)* Math.pow(Math.sin(radianes), 2))/(2*G);
		distanciaMaxima = (Math.pow(velocidad,2) * Math.sin(radianesCuadrado))/G;
		
		
		//calculo intervalos de tiempo
		i0 = (0 * tiempoVuelo)/100;
		i1 = (25 * tiempoVuelo)/100;
		i2 = (50 * tiempoVuelo)/100;
		i3 = (75 * tiempoVuelo)/100;
		i4 = (100 * tiempoVuelo)/100;
		
		
		
		//calculo de posiciones en el mundo
		xp = XC + LONGITUD_CANION * Math.cos(radianes);
		yp = YC + LONGITUD_CANION * Math.sin(radianes);
		xpos1 = xp + velocidad * Math.cos(radianes) * i1;
		xpos2 = xp + velocidad * Math.cos(radianes) * i2;
		xpos3 = xp + velocidad * Math.cos(radianes) * i3;
		xpos4 = xp + velocidad * Math.cos(radianes) * i4;
		ypos1 = yp + velocidad * Math.sin(radianes) * i1 - 0.5 * G * i1 *i1;
		ypos2 = yp + velocidad * Math.sin(radianes) * i2 - 0.5 * G * i2 *i2;
		ypos3 = yp + velocidad * Math.sin(radianes) * i3 - 0.5 * G * i3 *i3;
		ypos4 = yp + velocidad * Math.sin(radianes) * i4 - 0.5 * G * i4 *i4;
		
		
		
		//salida por pantalla
		System.out.printf ("  Pos. inicial del proyectil: ( %.2f, %.2f) \n", xp,yp);
		System.out.printf ("  Tiempo de subida: %10.2f segundos y de vuelo %.2f segundos\n", tiempoSubida, tiempoVuelo);
		System.out.printf ("  Max. altura:      %10.2f metros\n", alturaMaxima);
		System.out.printf ("  Max. distancia:   %10.2f metros\n", distanciaMaxima);
		
		
		
		//Tabla
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.println("|  Tiempo     |    Posicion en el mundo    | Posicion en pantalla |");
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.printf ("| %10.2f  | (%10.2f , %10.2f ) |    (%5d,%5d)     |\n",i0,xp,yp,(int)(xp/5.0),(24-(int)(yp/5.0)));
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.printf ("| %10.2f  | (%10.2f , %10.2f ) |    (%5d,%5d)     |\n",i1,xpos1,ypos1,(int)(xpos1/5.0),(24-(int)(ypos1/5.0)));
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.printf ("| %10.2f  | (%10.2f , %10.2f ) |    (%5d,%5d)     |\n",i2,xpos2,ypos2,(int)(xpos2/5.0),(24-(int)(ypos2/5.0)));
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.printf ("| %10.2f  | (%10.2f , %10.2f ) |    (%5d,%5d)     |\n",i3,xpos3,ypos3,(int)(xpos3/5.0),(24-(int)(ypos3/5.0)));
		System.out.println("+-------------+----------------------------+----------------------+");
		System.out.printf ("| %10.2f  | (%10.2f , %10.2f ) |    (%5d,%5d)     |\n",i4,xpos4,ypos4,(int)(xpos4/5.0),(24-(int)(ypos4/5.0)));
		System.out.println("+-------------+----------------------------+----------------------+");
	}

}
