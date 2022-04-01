package examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Ejercicio01 {

	public static void main(String[] args) throws IOException {
		
		int altaMujeres=0;
		int bajaMujeres=0;
		int altaHombres=0;
		int bajaHombres=0;
		
		
		BufferedReader br = null;	
		File fichero = leerFichero();
		
		try {
			//nos creamos un filereader envuelto en un buffer de lectura
			br = new BufferedReader(new FileReader(fichero));
			
			//leemos la primera linea
			String linea = br.readLine();
			
			//mientras hayan lineas que leer
			while (linea != null) {
				
				//construimos un array con los campos de la linea. Separamos los campos con split
				String [] fields = linea.split(";"); 
				
				//contamos los datos
				altaMujeres = altaMujeres + Integer.parseInt(fields[3]);
				bajaMujeres = bajaMujeres + Integer.parseInt(fields[5]);
				altaHombres = altaHombres + Integer.parseInt(fields[2]);
				bajaHombres = bajaHombres + Integer.parseInt(fields[4]);
				
				//leemos la siguiente linea
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		finally {  //cerramos cosas
           br.close();
         }
		
		//mostramos datos
		System.out.println("Alta mujeres: " + altaMujeres);
		System.out.println("Baja mujeres: " + bajaMujeres);
		System.out.println("Crecimiento mujeres: " + (altaMujeres-bajaMujeres));
		System.out.println("Alta hombres: " + altaHombres);
		System.out.println("Baja hombres: " + bajaHombres);
		System.out.println("Crecimiento hombres: " + (altaHombres-bajaHombres));
		System.out.println("Crecimiento global:" + ((altaMujeres+altaHombres) - (bajaMujeres+bajaHombres)));
		
		
		
	}

	public static File leerFichero() {

        JFileChooser fd = new JFileChooser();

        fd.setDialogTitle("Selecciona el fichero a leer");
        fd.setSelectedFile(null);
        int opcion = fd.showOpenDialog(null);
        

        if (opcion != JFileChooser.APPROVE_OPTION) return null;
        
        File f = fd.getSelectedFile();
        
        String titulo = f.getName();
        
       if(titulo.contains(".csv") && titulo.startsWith("altasYbajas")) return f;
        
       else System.out.println("Archivo incorrecto");
       
      return null;
        
    }

}
