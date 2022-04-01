package Practicas_Tema_02;

import java.util.Scanner;
import java.util.Calendar;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		//declaracion de variables
		int anyo,mes,dia,diaSemana; //variables para introducir las fechas
		int plazo;
		Calendar fecha = Calendar.getInstance();
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		String cadena="";
		
		//entrada de datos
		System.out.print("Dia del mes (1-31): ");
		dia = stdin.nextInt();
		System.out.print("Mes del año (1-12): ");
		mes = stdin.nextInt();
		System.out.print("Año: ");
		anyo = stdin.nextInt();
		System.out.print("Plazo (dias naturales): ");
		plazo = stdin.nextInt();
		
		//cerramos la entrada de datos
		stdin.close();
		
		//establecemos la fecha
		fecha.set(Calendar.YEAR, anyo);
		fecha.set(Calendar.MONTH, mes-1);
		fecha.set(Calendar.DATE, dia);
		
		//sumamos los dias
		fecha.add(Calendar.DATE, plazo);
		
		//obtenemos el dia de la semana del plazo de entrega
		diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
		
		//si el plazo es sabado o domingo, debemos adelatar el plazo
	    plazo = diaSemana==1 ? -2: diaSemana == 7 ? -1:0;
	    
	    //restamos dias en caso de ser necesario
	    fecha.add(Calendar.DATE, plazo);
	    diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
	    
	    //extraemos el dia
	    dia = fecha.get(Calendar.DATE);
	    mes = fecha.get(Calendar.MONTH);
	    anyo = fecha.get(Calendar.YEAR);
	    
	    //montamos la cadena
	    cadena = "Debe entregarlo hasta el ";
	    
	    cadena += diaSemana==2? "Lunes ": diaSemana==3? "Martes ":diaSemana==4? "Miercoles ":diaSemana==5? "Jueves ":"Viernes ";
	    
	    cadena += dia + " de ";
	    
	    cadena += mes==0? "Enero ":mes==1? "Febrero ":mes==2? "Marzo ":mes==3? "Abril ":mes==4? "Mayo ":mes==5? "Junio ":
	    	      mes==6? "Julio ":mes==7? "Agosto ":mes==8? "Septiembre ":mes==9? "Octubre ":mes==10? "Noviembre ":"Diciembre ";
	    
	    cadena += "de " + anyo;
	    
	    System.out.println(cadena);
	    
	}

}
