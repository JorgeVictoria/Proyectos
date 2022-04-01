import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	
	//variables globales
	private static Scanner stdin = new Scanner(System.in); //entrada de datos por teclado
	private static Connection connection = null;		   //coexion a la bbdd
	private static String bbdd = "starwars.db";			   //nombre de la bbdd

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		System.out.println("Hola");
		
		//variables locales
		//ruta de la BBDD y credencialea para la conexion
		String url = "jdbc:mariadb://localhost:3306/starwars";
		String user = "star";
		String password = "wars";
		
		//variable para control del menu
		int opcion = 0;
		
		
		//intentamos la conexion
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			//si la conexion es correcta, lo mostramos por pantalla y accedemos al menu
			if(connection!=null) {
				System.out.println("conexion correcta");
				System.out.println();
				System.out.println("1. Imprimir por consola");
				System.out.println("2. Salir por pantalla");
				System.out.println("3. Salir");
				
				//mientras la opcion no sea salir, pedimos opcion
				while(opcion != 3) {
					//llamamos a la funcion que controla que el numero sea entero y con un valor vslido
					opcion = pedirOpcion();
					//en funcion de la opcion elegida, llamamos a la funcion correspondiente
					switch(opcion) {
					case 1 : consola();
							 break;
					case 2 : texto(); 
							 break;
					default: stdin.close();
							 break;
				}
					
				}
				
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * metodo para pedir un dato numerico de tipo entero
	 * @return num: valor de la opcion
	 */
	private static int pedirOpcion() {
		
		//variables locales
		int num = 0;		//numero que introduce el usuario
		boolean salir = false;
		
		while(!salir) {
			//damos por hecho que el usuario a poner bien el numero
			salir = true;
			System.out.printf("Elija una opcion(1-3): ");
			try {
				num = stdin.nextInt();
			} catch(Exception e) {
				salir = false;
				System.out.println("Debe introducir un dato numerico.");
				stdin.next();
			}
			if(salir && (num <=0 || num > 3)) {
				System.out.println("El valor no es correcto");
				salir = false;
			}
		}
		return num;
	}
	
	/**
	 * metodo para imprimir por consola los metadatos de la BBDD
	 * @throws SQLException
	 */
	private static void consola() throws SQLException {
		
		//variable contador para la linea de primary keys
		int contador = 0;
		
		System.out.println();
		
		//obtenemos los metadatos
		DatabaseMetaData dbmd = connection.getMetaData();
		
		//cogemos los metadatos de la tabla
		ResultSet resul = dbmd.getTables(null, bbdd,null , null);
		
		//obtenemos distinta informacion de la tabla y mostramos su nombre. Bucle para todas las tablas
		while(resul.next()) {
			String catalogo = resul.getString("TABLE_CAT");
			String esquema = resul.getString("TABLE_SCHEM");	//util para obtener las primary keys
			String tabla =resul.getString("TABLE_NAME");        //util para obtener las primary keys
			System.out.println("Tabla: " + tabla);
			System.out.println();
			
			//obtenemos los metadatos de las columnas de la tabla
			ResultSet columnas = dbmd.getColumns(null, bbdd, tabla, null);
			
			//obtenemos las primary key de la tabla
			ResultSet primaryKeys = dbmd.getPrimaryKeys(catalogo, esquema, tabla);
			
			//mostramos las primary key, con el contador controlamos poner "-" si hay mas de una.
			System.out.print("Clave Primaria: ");
			while(primaryKeys.next()) {
				if(contador == 0) System.out.print(primaryKeys.getString("COLUMN_NAME"));
				else System.out.print(" - " + primaryKeys.getString("COLUMN_NAME"));
				contador++;
			}
		    contador = 0;
		    System.out.print("\n");
		    
		    //mostramos el nombre de columna y su tipo
		    System.out.println("Columnas:");
			while(columnas.next()) {
				System.out.println(columnas.getString("COLUMN_NAME") + " - " + columnas.getString("TYPE_NAME"));
			}
			System.out.println();
		}

	}
	
	private static void texto() throws FileNotFoundException, UnsupportedEncodingException, SQLException {
		
		//primero nos creamos la gestion del fichero donde vamos a guardar la informacion
		
		
		//creamos el filtro para indicar el tipo de fichero que guardamos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivos TXT (.txt)" , "txt");
        
        //creamos el jfileChoser
        JFileChooser guardar = new JFileChooser();
        
        //al jfilechooser le pasamos el filtro que queremos aplicar
        guardar.setFileFilter(filtro);
        
        //mostramos la ventana
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //creamos un File donde almacenamos el fichero creado/seleccionado
        File archivo = guardar.getSelectedFile();

        //puede ocurrir que el fichero se guarde sin extension. se la añadimos
        if(!archivo.getName().contains(".")) archivo = new File(archivo.toString() + ".txt");
        
        //nos creamos un objeto PrintWriter para poder escribir en el fichero
        PrintWriter writer = new PrintWriter(archivo, "UTF-8");
        
        //A continuacion realizamos la gestion de la BBdd y volcamos la info en el txt
        
        //variable contador para la linea de primary keys
      		int contador = 0;
      		
      		System.out.println();
      		
      		//obtenemos los metadatos
      		DatabaseMetaData dbmd = connection.getMetaData();
      		
      		///cogemos los metadatos de la tabla
      		ResultSet resul = dbmd.getTables(null, "starwars.db",null , null);
      		
      		//obtenemos distinta informacion de la tabla y mostramos su nombre. Bucle para todas las tablas/
      		while(resul.next()) {
      			String catalogo = resul.getString("TABLE_CAT");
      			String esquema = resul.getString("TABLE_SCHEM");
      			String tabla =resul.getString("TABLE_NAME");
      			writer.println("Tabla: " + tabla);
      			writer.println();
      			
      			//obtenemos los metadatos de las columnas de la tabla
      			ResultSet columnas = dbmd.getColumns(null, "starwars.db", tabla, null);
      			
      			//obtenemos las primary key de la tabla
      			ResultSet primaryKeys = dbmd.getPrimaryKeys(catalogo, esquema, tabla);
      			
      			//mostramos las primary key, con el contador controlamos poner "-" si hay mas de una.
      			writer.print("Clave Primaria: ");
      			while(primaryKeys.next()) {
      				if(contador == 0) writer.print(primaryKeys.getString("COLUMN_NAME"));
      				else writer.print(" - " + primaryKeys.getString("COLUMN_NAME"));
      				contador++;
      			}
      		    contador = 0;
      		    writer.print("\n");
      		    
      		    //mostramos el nombre de columna y su tipo
      		    writer.println("Columnas:");
      			while(columnas.next()) {
      				writer.println(columnas.getString("COLUMN_NAME") + " - " + columnas.getString("TYPE_NAME"));
      			}
      			writer.println();
      		}
      		
      		//limpiamos el writer y volcamos la info en el txt
      		writer.flush();
      		
      		//cerramos el writer
      		writer.close();
  
	}
	
}
