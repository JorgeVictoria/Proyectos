/**
 * Practica de ejecucion de sentencias mediante PreparedStatement
 */
package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

/**
 * @author jovian(Jorge Victoria Andreu)
 * @version 1
 * @since 08nov2021
 *
 */
public class StarWars {

	//variables globales
		private static Connection connection = null;		   					//conexion a la bbdd
		private static String url = "jdbc:mariadb://localhost:3306/starwars";	//ruta de la BBDD
		private static String user = "star";									//usuario de acceso a la BBDD
		private static String password = "wars";								//password de acceso a la BBDD
		private static Scanner stdin = new Scanner(System.in);					//entrada de datos
		private static int[] tamanyo = new int[6];                              //almacena el tamaño de los planetas

		/**
		 * @param args
		 * @throws SQLException 
		 */
		public static void main(String[] args) throws SQLException {
			
			//variables locales
			boolean conectado = false;	//para controlar que estemos conectados a la BBDD
			
			//intentamos la conexion a la BBDD
			try {
				//llamada a la funcion de conexion
				conectado = conectarBBDD();
				//si hay exito en la conexion,  lo indicamos y vamos llamando a las distintas funciones
				if(conectado) {
					System.out.println("Se ha conectado correctamente a la BBDD.");
					//Ejercicio1
					listarPlanetas();
					//Ejercicio2
					insertarPlanetas("Jakku", 31, 13, 1976, "tropical", "1 standard", "grasslands", 40, 4);
					insertarPersonajes("Rey", 170, 54, "black", "white", "brown", "15DBY", "female","Jakku");
					insertarPersonajes("Finn", 178, 73,"black","dark","dark","11DBY","male", "Kamino");
					insertarPersonajes("Kylo Ren", 189, 89,"black","white","brown","5DBY","male", "Chandrila");
					//Ejercicio3
					verMuertes();
				}
				//control de excepciones en caso de error durante la conexion.
			} catch (SQLException e) {
				System.out.println("No se ha conectado a la BBDD.");
				e.printStackTrace();
			}
			
			//cerramos la conexion
			connection.close();

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
		 * metodo para listar los planetas cuyo diametro esta comprendido entre diferentes rangos
		 * @throws SQLException
		 */
		private static void listarPlanetas() throws SQLException {
			
			System.out.println();
			System.out.println("EJERCICIO 1");
			System.out.println();
			
			//variables locales
			String sql = "SELECT name FROM planets WHERE diameter between ? AND ?";	//consulta sql
			
			//pedimos los valores de los tamaños de los planetas.
			for(int i = 0; i < 6; i++) {
				
				//banner para saber que rango de valores estamos introduciendo
				if(i % 2 == 0) {
					System.out.println("\n***************");
					if(i == 0 || i == 1) System.out.println( "Primer rango");
					else if(i == 2 || i == 3) System.out.println(" Segundo rango");
					else System.out.println(" Tercer rango");
					System.out.println("***************\n");
				}
				
				//pedimos el valor del diametro
				//como almacenamos los valores en un array, los rangos van por pares de posiciones
				//para cada rango, el valor par será el minimo y el impar el maximo
				//o sea, en el array las pos 0 y 1 son de un intervalo, donde la pos 0 es el minimo y pos 1 el maximo
				if(i % 2 == 0) tamanyo[i] = pedirNumero("valor minimo",0);
				else tamanyo[i] = pedirNumero("valor maximo", tamanyo[i-1]);
			}
			
			//preparamos la sentencia
			PreparedStatement sentencia= connection.prepareStatement(sql);
			
			//mostramos los listado de los planetas
			for(int i = 1; i < 6; i = i+2) {
				//banner para identificar los intervalos
				System.out.println("\n*****************************************************************");
				System.out.printf("  Planetas con un diametro comprendido entre %d y %d\n", tamanyo[i-1], tamanyo[i]);
				System.out.println("*****************************************************************\n");
				//paso de parametros para sentencia sql
				sentencia.setInt(1, tamanyo[i-1]);
				sentencia.setInt(2, tamanyo[i]);
				//cogemos los resultados
				ResultSet rs = sentencia.executeQuery();
				//mostramos los datos por pantalla
				while (rs.next()) System.out.println(rs.getString(1));
				
			}
			
			//ceramos el Statement
			sentencia.close();
			
		}


		/**
		 * metodo para validar que un numero sea entero y no menor que 0
		 * @param string: la cadena que mostramos al pedir el dato
		 * @param valor:  valor con el que debemos comparar el numero. 
		 * @return
		 */
		private static int pedirNumero(String string, int valor) {
			
			//variables locales
			int numero=0; 							//numero entero que pedimos y devolvemos
			boolean salir = false;					//para controlar que el numero introducido sea correcto
		
			
			//creamos un bucle para pedir un numero entero y comprobar que sea correcto
			while(!salir) {
				System.out.print("Introduce el " + string + " del tamanyo del planeta: ");
				try {
					//suponemos que el usuario va a introducir correctamente los datos
					salir = true;
					//leemos por teclado
					numero = stdin.nextInt();
				} catch (Exception e) {
					stdin.next();
					salir = false;
					System.out.println("el dato introducido no es correcto");
				}
				//si el numero es entero, comprobamos que no sea menor que 0 o el tamaño minimo introducido anteriormente
				//ya que habremos pasado como parametro el valor minimo del rango 
				if(salir && numero < valor) {
					salir=false;
					System.out.println("el numero no puede ser negativo o menor que 0 el tamaño minimo");
				}
			}
			
			//devolvemos el numero
			return numero;
		}
		
		/**
		 * metodo para insertar planetas en la BBDD
		 * @throws SQLException 
		 * @param planeta
		 */
		private static void insertarPlanetas(String name, int rotation, int orbital, int diameter, String climate, String gravity, String terrain, int surface, int population) throws SQLException {
			
			//Variables locales
			int LastIDPlanet = 0;			//para almacenar la id del ultimo planeta almacenado en la BBDD
			String sql = "";				//aqui iremos almacenando las lineaa de consulta y manipulacion de datos
			
			System.out.println();
			System.out.println("EJERCICIO 2");
			System.out.println();
			
			//vamos a recoger el ultimo id de la tabla planetas
			sql = "SELECT id, name FROM planets;";
			//preparamos la sentencia
			PreparedStatement sentencia= connection.prepareStatement(sql);
			//cogemos los resultados
			ResultSet rs = sentencia.executeQuery();
			//corremos la coleccion hasta encontrar el ultimo registro
			boolean existe = false; 						//para comprobar que el planeta no existe
			while (rs.next()) {
				LastIDPlanet = rs.getInt(1);
				if (rs.getString(2).toLowerCase().equals(name.toLowerCase())) existe = true;
			}
			
			//si el planeta existe, lo mostramos por pantalla y sino, inyectamos la informacion
			if (existe) System.out.println("El planeta " + name + " ya existe");
			else {
				//aumentamos en uno la variable LastId... para pasar 
				LastIDPlanet++;
				//Ahora debemos cargar la info de un nuevo planeta
				//cogemos la fecha y la pasamos a formato timestamp, para las columnas created, update
				Date fecha = new Date();
				Timestamp timestamp = new Timestamp(fecha.getTime());
				//creamos el insert y pasamos los datos
				sql = "INSERT INTO planets VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				sentencia= connection.prepareStatement(sql);
				sentencia.setInt(1, LastIDPlanet);
				sentencia.setString(2,name);
				sentencia.setInt(3,rotation);
				sentencia.setInt(4, orbital);
				sentencia.setInt(5, diameter);
				sentencia.setString(6, climate);
				sentencia.setString(7, gravity);
				sentencia.setString(8, terrain);
				sentencia.setInt(9, surface);
				sentencia.setInt(10, population);
				sentencia.setTimestamp(11, timestamp);
				sentencia.setTimestamp(12, timestamp);
				String url = "https://swapi.co/api/planets/" + LastIDPlanet + "/";
				sentencia.setString(13, url);
				sentencia.executeUpdate();
				System.out.println("Datos Actualizados");
			}
			
		}
		
		private static void insertarPersonajes(String nombre, int altura, int peso, String pelo, String piel, String ojos, String nacim, String genero, String planeta) throws SQLException {
			
			//Variables locales
			int LastIDCharacter = 0;			//para almacenar la id del ultimo personaje almacenado en la BBDD
			int idPlanet = 0;					//id del planeta
			String sql = "";				    //aqui iremos almacenando las lineaa de consulta y manipulacion de datos
			
			//vamos a recoger el ultimo id de la tabla characters
			sql = "SELECT id, name FROM characters;";
			//preparamos la sentencia
			PreparedStatement sentencia= connection.prepareStatement(sql);
			//cogemos los resultados
			ResultSet rs = sentencia.executeQuery();
			//corremos la coleccion hasta encontrar el ultimo registro
			boolean existe = false; 						//para comprobar que el personaje no existe
			while (rs.next()) {
				LastIDCharacter = rs.getInt(1);
				if (rs.getString(2).toLowerCase().equals(nombre.toLowerCase())) existe = true;
			}
			
			//si el personaje existe, lo mostramos por pantalla y sino, inyectamos la informacion
			if (existe) System.out.println("El personaje " + nombre + " ya existe");
			else {
				//aumentamos en uno la variable LastId... para pasar 
				LastIDCharacter++;
				
				//buscamos el id del planeta del personaje
				sql = "SELECT id FROM planets where name = ?";
				
				sentencia= connection.prepareStatement(sql);
				sentencia.setString(1, planeta);
				
				//cogemos los resultados
				rs = sentencia.executeQuery();
				
		        boolean existePlaneta = false; 						//para comprobar que el planeta no existe
				while (rs.next()) {
					if (rs.getInt(1) > 0) {
						existePlaneta = true;
						idPlanet = rs.getInt(1);
					}
				}
				
				if(!existePlaneta) System.out.println("El planeta " + planeta + " no existe");
				else {
					//Ahora debemos cargar la info de un nuevo planeta
					//cogemos la fecha y la pasamos a formato timestamp, para las columnas created, update
					Date fecha = new Date();
					Timestamp timestamp = new Timestamp(fecha.getTime());
					//creamos el insert y pasamos los datos
					sql = "INSERT INTO characters VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					sentencia= connection.prepareStatement(sql);
					sentencia.setInt(1, LastIDCharacter);
					sentencia.setString(2,nombre);
					sentencia.setInt(3, altura);
					sentencia.setInt(4, peso);
					sentencia.setString(5, pelo);
					sentencia.setString(6, piel);
					sentencia.setString(7, ojos);
					sentencia.setString(8, nacim);
					sentencia.setString(9, genero);
					sentencia.setInt(10, idPlanet);
					sentencia.setTimestamp(11, timestamp);
					sentencia.setTimestamp(12, timestamp);
					String url = "https://swapi.co/api/planets/" + LastIDCharacter + "/";
					sentencia.setString(13, url);
					sentencia.executeUpdate();
					System.out.println("Datos Actualizados");
				}
			}
			
		}
		
		/**
		 * metodo para obtener los muertos y sus verdugos en cada pelicula de Star Wars
		 * @throws SQLException
		 */
		private static void verMuertes() throws SQLException {
			
			//primero obtenemos el listado de peliculas
			String[] peliculas = new String[9];
			String sql = "Select episode, title from films where id = ?;";
			//preparamos la sentencia
			PreparedStatement sentencia= connection.prepareStatement(sql);
			for(int i = 1; i <= 9; i++ ) {
				sentencia.setInt(1, i);
				//cogemos los resultados
				ResultSet rs = sentencia.executeQuery();
				//guardamos los datos en el array
				while (rs.next()) peliculas[i-1] = rs.getString(1) + ". " + rs.getString(2) ;
			}
			
			
			//ahora cogemos los personajes muertos y sus killers
			sql="select characters.name, ch.name from characters join deaths on "
					+ "characters.id = deaths.id_character, characters ch join deaths de on "
					+ "ch.id = de.id_killer where deaths.id = de.id and deaths.id_film = ?;";
			
			System.out.println();
			System.out.println("EJERCICIO 3");
			System.out.println();
		
			sentencia= connection.prepareStatement(sql);
			for(int i = 1; i <=9; i++) {
				sentencia.setInt(1, i);
				//cogemos los resultados
				ResultSet rs = sentencia.executeQuery();
				//mostramos los datos por pantalla
				System.out.println("************************************");
				System.out.println(" " + peliculas[i-1]);
				System.out.println("************************************");
				//creamos una cadena formateada para mostrar la info en modo columna
				System.out.printf(" %-25s %-20s\n","CHARACTER","KILLER");
				while (rs.next()) 
					System.out.printf(" %-24s  %-20s  \n", rs.getString(1), rs.getString(2));
			}
		   
		
		
		}

}
