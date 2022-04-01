package ejercicio03;

import java.time.LocalDateTime;

public class Gna {
	
	//variables de la clase
	private static LocalDateTime tiempo;
	private static long any;
	private static long mes;
	private static long dia;
	private static long hora;
	private static long min;
	private static long seg;
	private static long u,v,w;
	
	//variables del objeto
	public long semilla;
	
	public Gna() {
		//Construimos el objeto pasando el conjunto fecha/hora
		this(dia * seg + any * min + mes * hora);
		
	}
	
	public Gna(long largo) {
		
		this.semilla = largo;
		
		w = 1;
		v = 4101842887655102017L;
		u = this.semilla ^ v;
		int64();
		v = u;
		int64();
		w = v;
		int64();
	}
	
	
	//metodo para recoger el conjunto fecha hora a partir del cual 
	//construimos la GNA1
	public static void cogerHora() {
		tiempo = LocalDateTime.now();
		any = (long)tiempo.getYear();
		mes = (long)tiempo.getMonthValue();
		dia = (long)tiempo.getDayOfMonth();
		hora = (long)tiempo.getHour();
		min = (long)tiempo.getMinute();
		seg = (long)tiempo.getSecond();
	}
	
	private static long int64() {
		long x;
		long num;
		u = u * 2862933555777941757L + 7046029254386353087L;
		v = v ^ (v >> 17);
		v = v >> 31;
		v = v ^ v >> 8;
		w = 4294957665L * (w & 0x7FFFFFFFFFFFFFFFL) + (w >> 32);
		x = u ^ (u << 21);
		x = x ^ (x >> 35);
		x = x ^ (x << 4);
		num = (x + v) ^ w;
		
		// para evitar negativos, pon el bit + significativo a 0
		if(num < 0) num = ~(num)+1;
		
		return num;
	}
	
	public static double random() {
		
		//System.out.println("hola");
		
		double r;
		
		r = 5.42101086242752217E-20 * int64();
		
		return r;
		
	}
	
	public static int valor(int a,int b) {
		
		//al parametro b le multiplico 2 para que coja el rango completo
		//si b vale 20, coge el rango de 0 a 10, si vale 40 coge el rango de 0 al 20
		double numero = a + random() * (b*2);
		int entero = (int)numero;
		return(entero);
	}
	
	public static void valor(long a, long b) {
		
		//variables locales
		double numA = (double)a;
		double numB = (double)b;
		double numero= numA + random() * (numB*2);
		long largo =(long)numero;
		
		System.out.print(largo);
	
	}
	
	public static String valor(int n, String alfabeto) {
		//variables locales
		String retorno ="";
		boolean flag=false;
		int pos;
		 
		//comprobamos el parametro entero es mayor o igual que 0
		if(n <= 0) flag=true;	
		
		//comprobamos que el parametro alfabeto no este vacio
		//si esta vacio, le ponemos un alfabeto
		if(alfabeto.length()==0) alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		//creamos la cadena
		if(flag == false) {
			for(int i = 0; i < n; i++) {
				pos = valor(0,alfabeto.length());
				retorno = retorno + alfabeto.charAt(pos);
			}
		}
		return retorno;
	}
}
