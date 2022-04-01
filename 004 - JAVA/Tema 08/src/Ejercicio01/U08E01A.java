package Ejercicio01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class U08E01A {
	
	public static ArrayList <Palabras> palabras = new ArrayList();
	private static Palabras aux;
	private static Palabras temp;
	private static boolean flag = true;
	private static String st = "";
	private static ListIterator li;
	

	public static void main(String[] args) throws FileNotFoundException {
		int contador = 0;
		Pattern pat = Pattern.compile(".*[^\\.\\,\\-,\\n,\\'\\?\\�\\!\\�\\;\\(\\)\\[\\]\\#\\*\\:]$");
		Matcher mat;
		
		
		try {
	    	// inicializamos el objeto scaner 
	        Scanner scan = new Scanner(new File("C:\\Users\\jvand\\OneDrive\\Estudios\\1º DAM\\02-PROGRAMACION\\Tema 08\\quijote_cervantes\\quijote_cervantes.txt")); 
	        //vamos cogiendo los tokens
	        while(scan.hasNext()){
	        	//quitamos los espacios
	        	st = scan.next();
	        	//pasamos a minusculas
	        	st = st.toLowerCase();
	        	//vemos si encontramos algun signo.
	        	mat = pat.matcher(st);
	        	//si tenemos signos
	        	while(!mat.matches()) {
	        		st = st.substring(0, st.length()-1);
	        		mat = pat.matcher(st);
	        	}
	        	
	        	//primero vemos si la lista esta vacia y a�adimos el primer elemento
	        	if (palabras.size() == 0) {
	        		palabras.add(new Palabras(st,0));
	        		
	        	}
	        	//nos creamos un objeto temporal con la cadena que hemos creado
	        	temp  = new Palabras(st,0);
	        	
	        	 //corremos el arrayList
	        	ListIterator<Palabras> li = palabras.listIterator();
	        	while ( li.hasNext()){
	        		flag = true;
	        		Palabras aux = (Palabras)li.next();
	        		//en este caso usamos el metodo equals de String
	        		if(aux.getPalabra().equals(temp.getPalabra())) {
	        			aux.setContador(1);
	        			flag = false;	//lo pongo a false porque no hay que crear nada nuevo
	        			break;
	        		}
	        	}
	        
	        	if(flag == true) {
	        		palabras.add(new Palabras(st,1));
	        		flag = true;
	        	}
	        }
	        	
	        	
	        scan.close(); //cerramos la lectura
	      
	        //tratamiento de excepciones
    	} catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
		
		for(Palabras n: palabras) {
			System.out.println(n.getPalabra() + ":" + n.getContador());
		}
		
	}
	
}
