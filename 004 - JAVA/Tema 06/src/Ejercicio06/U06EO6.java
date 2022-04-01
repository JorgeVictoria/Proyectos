package Ejercicio06;

import java.util.Scanner;
import java.util.regex.*;


import java.io.File;
import java.io.FileNotFoundException;
class U06EO6 {
    public static void main(String[] args) throws FileNotFoundException{
    	
    	Pattern delimitador = Pattern.compile("<[^>\\n]+>");
    	try {
	    	// inicializamos el objeto scaner 
	        Scanner scan = new Scanner(new File("index.html"));  
	        //inicializamos el delimitador
	        scan.useDelimiter(delimitador);
	        int contador = 0;
	        //imrpimimos los tokens   
	        while(scan.hasNext()){
	        	String st = scan.next().trim();
	        	if(st.length()>0) System.out.println(st);  
	        } 
	        scan.close();
    	} catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		} 
	          
	   }    
    }

