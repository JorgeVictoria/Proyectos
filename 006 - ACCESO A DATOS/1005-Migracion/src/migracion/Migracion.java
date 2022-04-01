/**
 * Practica de migracion de una BBDD creada en SQLITE AL SGBD MariaDB
 */
package migracion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * @author jovian(Jorge Victoria Andreu)
 * @version 1
 * @since 12nov2021
 *
 */
public class Migracion {
	
	//variables globales
	
	//para la conexion a la BBDD
	private static Connection connection = null;			
	//creamos un arrayList donde vamos a cosntruir los inserts que vamos a utilizar en el ejercicio2
	private static ArrayList<String> createTables = new ArrayList<String>();
	//creamos un arrayList donde guardaremos los nombres de las tablas
	private static ArrayList<String> storeTables = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		ejercicio1(); 									   //llamada al metodo para iniciar el primer ejercicio
		ejercicio2();									   //llamada al metodo para iniciar el segundo ejercicio
		ejercicio3();									   //llamada al metodo para iniciar el tercer ejercicio

	}


	private static void ejercicio1() throws SQLException {
		
		//variables locales
		
		
		String cadena = "";							//en esta variable vamos a construir los createTables cogiendo metadatos de veterinaria.db
		String tabla = "";							//almacena el nombre de la tabla
		String catalogo = "";						//almacena el nombre del catalago. Util para primary keys
		String esquema = "";						//almacena el nombre del esquema. Util para primary keys
		ResultSet resul;							//para la lectura de metadatos
		ResultSet columnas;							//para coger metadatos de las columnas
		ResultSet primaryKeys;						//para coger metadatos de las claves primarias
		ResultSet foreignKeys;						//para coger metadatos de las claves ajenas
		int contador;
		
		System.out.println();
		System.out.println("****************");
		System.out.println("**EJERCICIO 01**");
		System.out.println("****************");
		System.out.println();
		
		//intentamos la conexion a la BBDD veterinaria.db
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:veterinaria.db");
			if (connection != null) System.out.println("Conexion a veterinaria");
			
			//obtenemos los metadatos de la BBDD veterinaria
			DatabaseMetaData dbmd = connection.getMetaData();
			
			//cogemos los metadatos de la tabla
			resul = dbmd.getTables(null, "veterinaria.db",null , null);
		    
			
			//mientras haya datos, vamos leyendo. 
			while(resul.next()) {
				//cogemos metadatos de la tabla. Primero el nombre de la tabla y lo almacenamos en la cadena.
				//tambien almacenamos el nombre de la cadena en el arrayList storeTables
				cadena = "CREATE TABLE ";
				tabla = resul.getString("TABLE_NAME");
				catalogo = resul.getString("TABLE_CAT");
				esquema = resul.getString("TABLE_SCHEM");
				storeTables.add(tabla);
				cadena = cadena + resul.getString("TABLE_NAME") + " ( ";
				
				//cogemos metadatos de la columna para siguiendo con la construccion de la cadena
				columnas = dbmd.getColumns(null, "veterinaria.db", tabla, null);
				
				//mientras haya datos vamos leyendo y construyendo la tabla
				while(columnas.next()) {
					cadena = cadena + " " + columnas.getString("COLUMN_NAME"); 
					cadena = cadena + " " + columnas.getString("TYPE_NAME");
					if( columnas.getString("IS_NULLABLE").equals("NO")) cadena = cadena + " NOT NULL";
					cadena = cadena + " , ";
				}
				
				//pasamos las primary key
				primaryKeys = dbmd.getPrimaryKeys(catalogo, esquema, tabla);
				cadena = cadena + " CONSTRAINT" + " PK_" + tabla + " PRIMARY KEY (";
				//mientras haya datos vamos leyendo y construyendo la tabla
				while(primaryKeys.next()) {
					contador=0;
					if (contador == 0) cadena = cadena + primaryKeys.getString("COLUMN_NAME");
					else cadena = cadena + ", " + primaryKeys.getString("COLUMN_NAME");
					contador++;
				}
				cadena = cadena + " ) ";
				
				//cogemos las foreign keys
				foreignKeys = dbmd.getImportedKeys(catalogo, esquema, tabla);
				
				while(foreignKeys.next()) {
					
					//cogemos los distintos componentes que forman la foreign key
					String primaryKeyColumnName = foreignKeys.getString("PKCOLUMN_NAME");
					String primaryKeyTableName = foreignKeys.getString("PKTABLE_NAME");
		            String foreignKeyTableName = foreignKeys.getString("FKTABLE_NAME");
		            String foreignKeyColumnName = foreignKeys.getString("FKCOLUMN_NAME");
		            
		            //tenemos que ver si al menos uno de los strings no está vacio, lo que significa que hay una clave foranea
		            if (primaryKeyColumnName.length() > 0) cadena = cadena + ", CONSTRAINT FK_" + foreignKeyTableName + 
		            		" FOREIGN KEY (" + foreignKeyColumnName + ") REFERENCES " + primaryKeyTableName + "(" + primaryKeyColumnName + ")";
				}
				
				//cerramos la cadena del create table
				cadena = cadena + ");";
				
				//añadimos la cadena al arrayList de createTables
				createTables.add(cadena);
			}
		
			//si ha fallado la conexion a la BBDD, lo indic
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido realizar la conexion a veterinaria"); 
		}
		
		//cerramos la conexion
		connection.close();
	}
	
	
	private static void ejercicio2() throws SQLException {
		
		//variables locales
		//ruta de la BBDD y credencialea para la conexion
		String url = "jdbc:mariadb://localhost:3306/";
		String user = "star";
		String password = "wars";
		
		//preparedStatement para poder realizar 
		PreparedStatement sentencia;
		boolean borrar = true;
		
		System.out.println();
		System.out.println("****************");
		System.out.println("**EJERCICIO 02**");
		System.out.println("****************");
		System.out.println();
		
		//intentamos la conexion
		try {
			connection = DriverManager.getConnection(url, user, password);
			//si la conexion es correcta, lo indicamos y seguimos con el ejercicio
			if(connection != null){
				connection.setAutoCommit(false);
				System.out.println("Conexion con la BBDD MariaDB realizada con exito.");
				//vemos si hay elementos en el arrayList de storeTables y empezamos con la inyeccion de datos
				if(storeTables.size() > 0) {
					
					
					System.out.println("+++++++++++++++++++++++++++++++++");
					System.out.println("++Borrando la BBDD veterinaria.++");
					System.out.println("+++++++++++++++++++++++++++++++++");
					
					sentencia= connection.prepareStatement("drop database if exists veterinaria");
					sentencia.executeUpdate();
					
					System.out.println("+++++++++++++++++++++++++++++++++");
					System.out.println("++Creando la BBDD veterinaria.+++");
					System.out.println("+++++++++++++++++++++++++++++++++");
					
					sentencia= connection.prepareStatement("create database veterinaria");
					sentencia.executeUpdate();
					
					sentencia= connection.prepareStatement("use veterinaria");
					sentencia.executeUpdate();
					
					//TODO esta parte esta pendiente de una revision y analisis con mas tranquilidad
					//es probable que al almacenar los create tables, estos se hagan en orden alfabetico de las tablas
					//y no en el orden en el que fueron creadas.
					//Problema, que al crear tablas en orden alfabetico, seguramente habra tablas con foreign keys que no esten creadas
					//y dará error al crearlas, por falta de referencias.
					//Como solucion temporal, voy leyendo el arraylist createTables, eliminando las lineas que son creadas, y las que de error
					//las dejo hasta que se puedan construir. Es decir leo en bucle el arraylist hasta que no queden tablas que crear.
					while(createTables.size() > 0){
						for(int i = 0; i < createTables.size(); i++) {
							
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							System.out.println("  Borrando la tabla " + storeTables.get(i).toString());
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							
							sentencia= connection.prepareStatement("drop database if exists " + storeTables.get(i).toString());
							sentencia.executeUpdate();
				
							
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							System.out.println("  Creando la tabla " + storeTables.get(i).toString());
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							
							System.out.println(createTables.get(i).toString());
			                try {
			                	 sentencia= connection.prepareStatement(createTables.get(i).toString());
			                	 sentencia.executeUpdate();
			                }catch (SQLException e) {
			                	System.out.println(e);
			                	borrar = false;
			                }
			                if (borrar == true)createTables.remove(i);
			                else borrar = true;
			                
							
						}
					}
				
			     //hemos finalizado la inyeccion de tablas. Mandamos un commit
				 connection.commit();
				 
				 //activamos el autocommit
				 connection.setAutoCommit(true);
					
				}
				
			}
		//la conexion no es correcta, lo indicamos
		} catch (Exception e) {
			System.out.println("Conexion erronea");
		}
		
		//cerramos la conexion
		connection.close();
	}
	
	private static void ejercicio3() throws SQLException {
		
		//variables locales
		String cadena; 											//para generar los insert
		String tabla;											//almacena el nombre de la tabla
		ArrayList<String> inserts = new ArrayList<String>();      //almacena las cadenas con los inserts
		ArrayList<String> tipoDatos = new ArrayList<String>();    //almacena los tipos de dato de cada columna
		String subCadena = null;
		//ruta de la BBDD de mariaDB y credencialea para la conexion
		String url = "jdbc:mariadb://localhost:3306/veterinaria";
		String user = "star";
		String password = "wars";
		
		System.out.println();
		System.out.println("****************");
		System.out.println("**EJERCICIO 03**");
		System.out.println("****************");
		System.out.println();
		
		//intentamos la conexion a la BBDD veterinaria.db
				try {
					connection = DriverManager.getConnection("jdbc:sqlite:veterinaria.db");
					if (connection != null) System.out.println("Conectado a veterinaria");
					
					//obtenemos los metadatos de la BBDD veterinaria
					DatabaseMetaData dbmd = connection.getMetaData();
					
					//cogemos los metadatos de la tabla
					ResultSet resul = dbmd.getTables(null, "veterinaria.db",null , null);
				    
					//mientras haya datos, vamos leyendo. 
					while(resul.next()) {
						//cogemos metadatos de la tabla. Cogemos el nombre de la tabla y lo almacenamos en la cadena.
						cadena = "INSERT INTO ";
						tabla = resul.getString("TABLE_NAME");
						cadena = cadena + resul.getString("TABLE_NAME") + " ( ";
						
						//cogemos metadatos de la columna para siguiendo con la construccion de la cadena
						ResultSet columnas = dbmd.getColumns(null, "veterinaria.db", tabla, null);
						
						//como en sqlite no hay un metodo para acceder al numero de registros obtenidos
						//corremos el resulset contando el numero de registros
						//lo hacemos porque debemos saber cuando debemos poner coma o no al construir los insert
						int totalRegistros = 0;
						while (columnas.next()) {
							totalRegistros++;
						}
						
						//volvemos a construir columnas, ya que no podemos usar metodos como first(), last() con sqlite
						columnas = dbmd.getColumns(null, "veterinaria.db", tabla, null);
						
						//para contar los registros que leemos y vemos que no es el ultimo para poner una coma
						int contador = 1;
						
						//mientras haya datos vamos leyendo y construyendo la tabla
						while(columnas.next()) {
							cadena = cadena + " " + columnas.getString("COLUMN_NAME");
							tipoDatos.add(columnas.getString("TYPE_NAME"));
							if(contador != totalRegistros) cadena = cadena + " , ";
							contador++;
					
						}
						
						//cerramos la linea de las tablas
						cadena = cadena + ") VALUES ( ";
						
						//ahora debemos obtener los registros de la tabla
						//creamos el statement para poder realizar la consulta
						Statement sentencia = connection.createStatement();
						
						//lanzamos la consulta
						ResultSet consulta = sentencia.executeQuery("Select * from " + tabla);
						
						//TODO Revisar esto para automatizar la construccion. Hay que coger los tipos de cada columna 
						//En funcion del tipo de dato de la columna, construimos la cadena
						//He cogido los tres tipos de datos que se utilzaron al construir la BBDD en sqlite
						while(consulta.next()) {
							subCadena = cadena;
							for(int i = 1; i <= totalRegistros; i++) {
								if (tipoDatos.get(i-1).equals("INTEGER")) subCadena = subCadena + consulta.getInt(i);
								else if (tipoDatos.get(i-1).equals("DATE")) subCadena = subCadena + "STR_TO_DATE('" + consulta.getString(i) + "','%Y-%m-%d')"; 
								else subCadena = subCadena + "\"" + consulta.getString(i) + "\"";
								if(i < totalRegistros) subCadena = subCadena + (",");
							}
							subCadena = subCadena + ");";
						    inserts.add(subCadena);
						}
						tipoDatos.clear();
						
					}
				
					//si ha fallado la conexion a la BBDD, lo indic
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("No se ha podido realizar la conexion a veterinaria"); 
				}
				
				//cerramos la conexion
				connection.close();
				
				//Ahora debemos insertar las filas en MariaDB
				
				
				//preparedStatement para poder realizar las ordenes
				PreparedStatement sentencia;
				boolean borrar = true;
				
				//intentamos la conexion
				try {
					connection = DriverManager.getConnection(url, user, password);
					//si la conexion es correcta, lo indicamos y seguimos con el ejercicio
					if(connection != null){
						connection.setAutoCommit(false);
						System.out.println("Conexion con la BBDD MariaDB realizada con exito.");
						
						//corremos el arrayList de inserts
						//las lineas correctas las borramos y las que den error las mantenemos y las volvemos a leer
						while(inserts.size() > 0) {
							for(int i = 0; i < inserts.size(); i++) {
								sentencia= connection.prepareStatement(inserts.get(i));
								System.out.println(inserts.get(i));
								try {
									sentencia.executeUpdate();
								} catch (SQLException e) {
				                	System.out.println(e);
				                	borrar = false;
								}
				                if (borrar == true)inserts.remove(i);
				                else borrar = true;
								}
								connection.commit();
							}
						}
						
					
						 //activamos el autocommit
						 connection.setAutoCommit(true);
							
					
				//la conexion no es correcta, lo indicamos
				} catch (Exception e) {
					System.out.println("Conexion erronea");
				}
				
				//cerramos la conexion
				connection.close();
			}
			
	}


	

