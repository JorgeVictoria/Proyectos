/**
 * Practica 03 de Conectores de BBDD
 * En esta usaremos diferentes sentencias SQL para consulta e insercion de datos
 * Nos conectaremos a la BBDD "Star Wars" creada en la practica anterior 
 * mediante el sistema gestor de BBDD MariaDB
 * OJO. Añadir libreria de MARIADB para la conexion
 */
package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author jovian (Jorge Victoria Andreu)
 * @since 05Nov2021
 * @version 1.0
 */
public class StarWars {
	
	//variables globales
	private static Connection connection = null;		   					//conexion a la bbdd
	private static String url = "jdbc:mariadb://localhost:3306/starwars";	//ruta de la BBDD
	private static String user = "star";									//usuario de acceso a la BBDD
	private static String password = "wars";								//password de acceso a la BBDD

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//variables locales
		boolean conectado = false;	//para controlar que estemos conectados a la BBDD
		
		//intentamos la conexion a la BBDD
		try {
			//llamada a la funcion de conexion
			conectado = conectarBBDD();
			//si hay exito en la conexion,  lo indicamos y vamos llamando a las distintas funciones
			if(conectado) {
				System.out.println("Se ha conectado correctamente a la BBDD.");
				verPlanetas();
				insertarPeliculas();
				consultarJedis();
				deathsInEpisodeIII();
			}
			//control de excepciones en caso de error durante la conexion.
		} catch (SQLException e) {
			System.out.println("No se ha conectado a la BBDD.");
			e.printStackTrace();
		}

	}


	/**
	 * metodo que realiza la conexion a la BBDD
	 * @return true/false : en funcion de si hemos conectado o no
	 * @throws SQLException
	 */
	private static boolean conectarBBDD() throws SQLException {
		//intentamos la conexion
		connection = DriverManager.getConnection(url, user, password);
		//en funcion de si conectamos o no, devolvemos true or false
		if(connection!=null) return true;
		else return false;
	}
	
	/**
	 * metodo de consulta de los planetas de Star Wars
	 * @throws SQLException
	 */
	private static void verPlanetas() throws SQLException {
		
		//variables locales
		String sql="SELECT name FROM planets";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		//corremos toda la coleccion y mostramos el conjunto de todos los planetas
		System.out.println();
		System.out.println("***************************");
		System.out.println("**** STAR WARS PLANETS ****");
		System.out.println("***************************");
		while (rs.next()) System.out.println(rs.getString(1));
		
		//cerramos el statement
		sentencia.close();
	}
	
	/**
	 * metodo para la insercion de peliculas
	 * @throws SQLException
	 */
	private static void insertarPeliculas() throws SQLException {
		
		//variables locales
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		String sql = "";									//para poner 
		
		System.out.println();
		System.out.println("*******************************");
		System.out.println("**** NEW FILMS ON DATABASE ****");
		System.out.println("*******************************");
		
		//insertamos las peliculas
		//para controlar el duplicado de la primary key, lo encerramos en un try catch
		try {
		//pelicuula 7
		sql="INSERT INTO films " +  "VALUES (7, 'Episode VII', 'The Force Awakens')";				//sentencia que queremos ejecutar
		sentencia.executeUpdate(sql);			//cogemos la informacion de la consulta
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("No se ha podido ejecutar la linea: " + sql);
		}
		
		try {
		//pelicaula 8
		sql="INSERT INTO films " +  "VALUES (8, 'Episode VIII', 'The Last Jedi')";				//sentencia que queremos ejecutar
		sentencia.executeUpdate(sql);			//cogemos la informacion de la consulta
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("No se ha podido ejecutar la linea: " + sql);
		}
		
		try {
		//pelicaula 9
		sql="INSERT INTO films " +  "VALUES (9, 'Episode IX', 'The Rise of Skywalker')";				//sentencia que queremos ejecutar
		sentencia.executeUpdate(sql);			//cogemos la informacion de la consulta
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("No se ha podido ejecutar la linea: " + sql);
		}
		
		//cerramos el statement
		sentencia.close();
		
	}
	
	/**
	 * metodo para consultar los personajes Jedi
	 * @throws SQLException
	 */
	private static void consultarJedis() throws SQLException {
		
		String sql="";										//para almacenar la instruccion de consulta
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		
		
		sql= "SELECT * FROM `characters` JOIN character_affiliations ";
		sql= sql + "ON characters.id = character_affiliations.id_character ";
		sql= sql + "where character_affiliations.id_affiliation = ";
		sql= sql + "( SELECT affiliations.id from affiliations where affiliations.affiliation LIKE 'Jedi Order');";
		
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		//corremos toda la coleccion y mostramos el conjunto de los personajes Jedi
		System.out.println();
		System.out.println("*******************************");
		System.out.println("**** JEDI ORDER CHARACTERS ****");
		System.out.println("*******************************");
		
		//cadena formateada para mostrar la info en modo columna
		System.out.printf(" %-4s %-20s %-8s %-6s %-20s %-20s %-20s %-12s %-12s\n", "id","name","heigth","mass","hair_color","skin_color","eye_color","birth_year","gender");
		while (rs.next()) 
			System.out.printf(" %-4d %-20s %-8d %-6d %-20s %-20s %-20s %-12s %-12s \n", rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			
		//cerramos el statement
		sentencia.close();
	}
	
	/**
	 * metodo para la consulta de personajes muertos en el episodio III y sus verdugos
	 * @throws SQLException
	 */
	private static void deathsInEpisodeIII() throws SQLException {
		
		String sql="select characters.name, ch.name from characters join deaths on "
				+ "characters.id = deaths.id_character, characters ch join deaths de on "
				+ "ch.id = de.id_killer where deaths.id = de.id and deaths.id_film = 3;";
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		//corremos toda la coleccion y mostramos el conjunto de todos los planetas
		System.out.println();
		System.out.println("*****************************************");
		System.out.println("**** CHARACTERS DEATH IN EPISODE III ****");
		System.out.println("*****************************************");
		
		//creamos una cadena formateada para mostrar la info en modo columna
		System.out.printf(" %-21s %-20s\n", "character","killer");
		
		while (rs.next()) 
			System.out.printf(" %-20s  %-20s  \n", rs.getString(1), rs.getString(2));
		
		//cerramos el statement
		sentencia.close();
		
	}



}
