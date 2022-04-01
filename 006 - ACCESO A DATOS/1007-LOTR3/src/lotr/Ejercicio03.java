package lotr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 * Practica para pasar datos de una BBDD relacional (MariaDB) a una BBDD orientada a objetos
 * @author Jorge Victoria Andreu
 * @version 1.0
 * @since 14Jan2022
 */
public class Ejercicio03 {

	//variables locales
	private static Connection connection = null;			//conexion a la BBDD de MariaDB
	
	/**
	 * metodo principal de la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		
		//variables locales
		//ruta de la BBDD de MariaDB y credenciales para la conexion
		String url = "jdbc:mariadb://localhost:3306/lotr";
		String user = "root";
		String password = "";
		
		//intentamos la conexion
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			//si la conexion es correcta, lo mostramos por pantalla y accedemos a los distintos metodos del ejercicio
			if(connection!=null) {
				System.out.println("Conectado a LOTR en MariaDB");
				
				cargaRealm();
				cargaCharacter();
				cargarPareja();
				cargarLibro();
				cargaChapter();
				cargaMovies();
				updateBookAndMovie();
				cargaDialogo();
				consulta1();
				consulta2();
				consulta3();
				consulta4();
				consulta5();
				consulta6();
				
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * metodo para cargar los registros de la clase Realm
	 * @throws SQLException
	 */
	private static void cargaRealm() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM realms";					//sentencia de tipo query que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		//cabeera
		System.out.println();
		System.out.println("CARGA REALM");
		System.out.println();
		
		//creamos o accedemos a la BBDD de Neodatis
		ODB odb = ODBFactory.open("lotr.db");
		
		//corremos la tabla Realms en MariaDB
		while (rs.next()) {
			
			//Creamos un objeto de tipo realm
			Realm realm = new Realm();
			//almacenamos el id
			realm.setId(rs.getString(1));
			//almacenamos el nombre
			realm.setName(rs.getString(2));
			//si el campo population es null, como es de tipo entero ponemos cero para que no de error
			//en caso contrario, almacenamos el dato
			if(rs.getString(3) == null) realm.setPopulation(0);
			else realm.setPopulation(Integer.parseInt(rs.getString(3)));
			//si el campo area es null, como es de tipo entero ponemos cero para que no de error
			//en caso contrario, almacenamos el dato
			if(rs.getString(4) == null) realm.setArea(0);
			else realm.setArea(Integer.parseInt(rs.getString(4)));
			//almacenamos el regsitro en la BBDD de Neodatis
			odb.store(realm);
			
		}
		
		//cerramos la conexion a la bbdd de Neodatis
		odb.close();
		
		//cerramos el statement
		sentencia.close();
		
		//banner final de este metodo
		System.out.println("CARGA REALM FINALIZADA");
		System.out.println();
	}
	
	/**
	 * metoodo para cargar objetos de tipo Character en la BBDD de Neodatis
	 * @throws SQLException
	 */
	private static void cargaCharacter() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM characters";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
				
		//banner de cabecera
		System.out.println();
		System.out.println("CARGA CHARACTERS");
		System.out.println();
				
		//abrimos la conexion con la BBDD de Neodatis
		ODB odb = ODBFactory.open("lotr.db");
		
		//corremos los elementos de la tabla Characters de MariaDB
		while(rs.next()) {
		
			//Nos creamos un objeto de tipo Character
			Character character = new Character();
			
			//pasamos los atributos al objeto: id, nombre, raza...
			character.setId(rs.getString(1));
			character.setName(rs.getString(2));
			character.setWikiUrl(rs.getString(3));
			character.setRace(rs.getString(4));
			character.setBirth(rs.getString(5));
			character.setGender(rs.getString(6));
			character.setDeath(rs.getString(7));
			character.setHair(rs.getString(8));
			character.setHeight(rs.getString(9));
			
			//uno de los atributos de cada personaje es su pertenencia a un Realm
			//obtenemos los objetos de la clase Realm
			Objects<Realm> objetos = odb.getObjects(Realm.class);
			//recorremos la lista de objetos
			while (objetos.hasNext()) {
				//nos creamos un objeto de tipo Realm
				Realm realm = objetos.next();
				//si el id del Realm coincide con el dato del registro
				//almacenamos el personaje en la coleccion correspondiente del Realm
				//y almacenamos el Realm en el atributo correspondiente del personaje
				if (realm.getId().equals(rs.getString(10))) {
					character.setId_realm(realm);
					realm.setCharacters(character);
					//guardamos los cambios del Realm
					odb.store(realm);
				}
			}
			
			//almacenamos el personaje
			odb.store(character);
			
		}
		
		//cerramos la conexion con la bbdd de Neodatis
		odb.close();
				
		//cerramos el statement
		sentencia.close();
		
		//banner final
		System.out.println("CARGA CHARACTERS FINALIZADA");
		System.out.println();
		
		
	}
	
	/**
	 * metodo para rellenar el atributo spouse de los objetos Character
	 * @throws SQLException
	 */
	private static void cargarPareja() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM marriage";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		//banner
		System.out.println();
		System.out.println("CARGA MARRIAGE");
		System.out.println();
						
		//abrimos la conexion con la bbdd de Neodatis
		ODB odb = ODBFactory.open("lotr.db");
		
		//creamos un caracter nuevo que se llame "unknown person" para cubrir los null de la segunda columna
		Character character = new Character();
		
		character.setName("Unknown Person");
		character.setId("unknown");
		
		//almacenamos el personaje en la bbdd
		odb.store(character);
		
		//corremos la tabla marriage
		while(rs.next()) {
					
			//obtenemos los objetos de la clase Character y corremos la coleccion
			Objects<Character> objetos = odb.getObjects(Character.class);
			while (objetos.hasNext()) {
				//creamos un objeto de tipo de character donde almacenamos los datos de cada Character
				Character ch = objetos.next();
				//si el id de la columna 1 de la tabla spouses coincide con el id del objeto Character, tenemos que buscar su pareja
				//si el id de la columna 2 de la table spouses en null, le asignamos como pareje el objeto "Unknown Person"
				//en caso contrario, volvemos a correr la tabla Character de Neodatis hasta encontrar el id de la pareja
				//una vez encontrada, almacenamos el objeto Character en el atributo correspondiente
				if (ch.getId().equals(rs.getString(1))) {
					if(rs.getString(2) == null) ch.setSpouse(character);
					else {
						Objects<Character> objects = odb.getObjects(Character.class);
						while (objects.hasNext()) {
							Character cha = objects.next();
							if(cha.getId().equals(rs.getString(2))) {
								ch.setSpouse(cha);
							}
						}
					}
					
					//actualizamos los datos del personaje
					odb.store(ch);
					
				}
				
			}
			
		}
		
		//cerramos la conexion con la bbdd de Neodatis
		odb.close();
		
		//cerramos el statement
		sentencia.close();
		
		//banner final
		System.out.println("CARGA SPOUSE FINALIZADA");
		System.out.println();
				
	}
	
	/**
	 * metodo para cargar objetos libro en la BBDD de Neodatis
	 * @throws SQLException
	 */
	private static void cargarLibro() throws SQLException {
		
		//variables locales
				String sql="SELECT * FROM books";					//sentencia que queremos ejecutar
				Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
				ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
				
				//banner inicial
				System.out.println();
				System.out.println("CARGA BOOK");
				System.out.println();
				
				//abrimos conexion con la bbdd de Neodatis
				ODB odb = ODBFactory.open("lotr.db");
				
				//corremos la tabla books de la BBDD de MariaDb
				while (rs.next()) {
					
					//para cada registro creamos un objeto book en el que inicialmente almacenamos su id y su titulo
					Book book = new Book();
					book.setId(rs.getNString(1));
					book.setTitle(rs.getString(2));
					
					//almacenamos el book en la bbdd de Neodatis
					odb.store(book);
					
				}
				
				//cerramos la conexion con la bbdd de Neodatis
				odb.close();
				
				//cerramos el statement
				sentencia.close();
				
				//banner final
				System.out.println("CARGA LIBRO FINALIZADA");
				System.out.println();
		
	}
	
	/**
	 * banner para cargar capitulos en la bbdd de Neodatis
	 * @throws SQLException
	 */
	private static void cargaChapter() throws SQLException {
		
		//variables locales
				String sql="SELECT * FROM chapters";				//sentencia que queremos ejecutar
				Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
				ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
				
				//banner inicial
				System.out.println();
				System.out.println("CARGA CHAPTERS");
				System.out.println();
						
				//abrimos la conexion con la BBDD de Neodatis
				ODB odb = ODBFactory.open("lotr.db");
				
				//corremos la coleccion de capitulos
				while(rs.next()) {
				
					//creamos un objeto de tipo chapter en el que inicialmente almacenamos su id y su nombre
					Chapter chapter = new Chapter();
					
					chapter.setId(rs.getString(1));
					chapter.setChapter_name(rs.getString(2));
					
					//obtenemos los objetos de la clase Book y corremos la coleccion
					//si el id del book coincide con el id de la columna 3, almacenamos el capitulo en el book
					//y almacenamos el book en el capitulo
					Objects<Book> objetos = odb.getObjects(Book.class);
					while (objetos.hasNext()) {
						Book book = objetos.next();
						if (book.getId().equals(rs.getString(3))) {
							chapter.setId_book(book);
							book.setChapters(chapter);
							odb.store(book);
						}
					}
					
					//almacenamos el capitulo
					odb.store(chapter);
					
				}
				
				//cerramos la conexion con la BBDD de Neodatis
				odb.close();
						
				//cerramos el statement
				sentencia.close();
						
				System.out.println("CARGA CHAPTERS FINALIZADA");
				System.out.println();
		
		
		
	}
	
	private static void cargaMovies() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM movies";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
		
		System.out.println();
		System.out.println("CARGA MOVIE");
		System.out.println();
		
		//corremos toda la coleccion
		ODB odb = ODBFactory.open("lotr.db");
		while (rs.next()) {
			
			Movie movie = new Movie();
			
			movie.setId(rs.getString(1));
			movie.setName(rs.getString(2));
			movie.setRuntimeInMinutes(Integer.valueOf(rs.getString(3)));
			movie.setBudgetInMillions(Integer.valueOf(rs.getString(4)));
			movie.setBoxOfficeRevenueInMillions(Integer.valueOf(rs.getString(5)));
			movie.setAcademyAwardNominations(Integer.valueOf(rs.getString(6)));
			movie.setAcademyAwardWins(Integer.valueOf(rs.getString(7)));
			movie.setTomatoesScore(Integer.valueOf(rs.getString(8)));
			
			odb.store(movie);
			
		}
		
		odb.close();
		
		//cerramos el statement
		sentencia.close();
		
		System.out.println("CARGA MOVIE FINALIZADA");
		System.out.println();
		
	}
	
	private static void updateBookAndMovie() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM books_movies";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
				
		System.out.println();
		System.out.println("UPDATE BOOK AND MOVIE");
		System.out.println();
		
		//corremos toda la coleccion
		ODB odb = ODBFactory.open("lotr.db");
		
		while(rs.next()) {
			
			//obtenemos los objetos de la clase Movie
			Objects<Movie> objetos = odb.getObjects(Movie.class);
			while (objetos.hasNext()) {
				Movie movie = objetos.next();
				if (movie.getId().equals(rs.getString(2))) {
					Objects<Book> objetos2 = odb.getObjects(Book.class);
					while (objetos2.hasNext()) {
						Book book = objetos2.next();
						if (book.getId().equals(rs.getString(3))) {
							book.setMovies(movie);
							movie.setBooks(book);
							odb.store(book);
							odb.store(movie);
						}
					}
				}
			}
			
		}
		
				
		odb.close();
				
		//cerramos el statement
		sentencia.close();
				
		System.out.println("UPDATE FINALIZADO");
		System.out.println();

		
		
	}
	
	private static void cargaDialogo() throws SQLException {
		
		//variables locales
		String sql="SELECT * FROM dialogs";				//sentencia que queremos ejecutar
		Statement sentencia = connection.createStatement(); //creamos el statement para poder realizar la consulta
		ResultSet rs = sentencia.executeQuery(sql);			//cogemos la informacion de la consulta
						
		System.out.println();
	    System.out.println("CARGA DIALOG");
		System.out.println();
				
		ODB odb = ODBFactory.open("lotr.db");
				
		while(rs.next()) {
			
			Dialog dialog = new Dialog();
			
			dialog.setId(rs.getString(1));
			dialog.setDialog(rs.getString(2));
			System.out.println(rs.getString(1) + " " + rs.getString(2));
					
			//obtenemos los objetos de la clase Movie
			Objects<Movie> objetos = odb.getObjects(Movie.class);
			while (objetos.hasNext()) {
				Movie movie = objetos.next();
				if (movie.getId().equals(rs.getString(3))) {
					movie.setDialogs(dialog);
					dialog.setId_movie(movie);
					odb.store(movie);
				}
			}
			
			//obtenemos los objetos de la clase Character
			Objects<Character> objetos2 = odb.getObjects(Character.class);
			while (objetos.hasNext()) {
				Character character = objetos2.next();
				if (character.getId().equals(rs.getString(4))) {
					dialog.setId_character(character);
				}
			}
			
			odb.store(dialog);
			
		}
				
						
		odb.close();
						
		//cerramos el statement
		sentencia.close();
						
		System.out.println("CARGA DIALOGO FINALIZADO");
		System.out.println();
		
	}
	
	/**
	 * Muestra por pantalla cuántos personajes están casados y cuántos no
	 */
	private static void consulta1() {
		
		int casados = 0;
		int noCasados = 0;
		
		System.out.println();
		System.out.println("Personajes casados y no casados");
		System.out.println("-------------------------------");
		System.out.println();
		
		//abrimos la conexion con la BBDD
		ODB odb = ODBFactory.open("lotr.db");
		
		IQuery consulta = new CriteriaQuery(Character.class, Where.isNotNull("spouse"));
		
		try {
			Objects<Character> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Character character = objetos.next();
				casados++;
			}
			System.out.println("Personajes casados: " + casados);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado objetos");
		}
		
		consulta =  new CriteriaQuery(Character.class, Where.isNull("spouse"));
		
		try {
			Objects<Character> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Character character = objetos.next();
				noCasados++;
			}
			System.out.println("Personajes no casados: " + noCasados);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado objetos");
		}
		
		odb.close();
		
		
	}
	
	/**
	 * Muestra por pantalla todos los miembros del clan Baggins, así como el 
	 *	total de todos ellos.
	 */
	private static void consulta2() {
		
		int miembros = 0;
		
		System.out.println();
		System.out.println("Personajes del clan Baggins");
		System.out.println("----------------------------");
		System.out.println();
		
		//corremos toda la coleccion y mostramos el conjunto de todos los planetas
		ODB odb = ODBFactory.open("lotr.db");
		
		IQuery consulta = new CriteriaQuery(Character.class, Where.like("name", "%Baggins%"));
		
		try {
			Objects<Character> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Character character = objetos.next();
				System.out.println(character.getName());
				miembros++;
			}
			System.out.println("Miembros de Baggins: " + miembros);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado objetos");
		}
		
		odb.close();
		
	}
	
	/**
	 * Muestra por pantalla toda la información de las películas que tenga un 
	 * presupuesto mayor a 100 millones, con una puntuación en Rotten 
	 * Tomatoes menor a 70.
	 */
	private static void consulta3() {
		
		System.out.println();
		System.out.println("Peliculas con presupuesto mayor de l00 millones y TomatoScore < 70. Metodo toString");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println();
		
		ODB odb = ODBFactory.open("lotr.db");
				
		ICriterion criterio = new And().add(Where.gt("budgetInMillions", 100)).add(Where.lt("TomatoesScore", 70));
				
		IQuery consulta = new CriteriaQuery(Movie.class,criterio);
				
				
		try {
			Objects<Movie> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Movie movie = objetos.next();
				System.out.println(movie.toString());
			}
		} catch (IndexOutOfBoundsException ex) {
				System.out.println("No se han encontrado objetos");
		}
				
		odb.close();
		
	}
	
	/**
	 * Muestra por pantalla todos los reinos que contengan Valinor, así como los 
	 * personajes que pertenezcan a ese reino. Hazlo primero consultando la 
     * Clase Realm, y luego consultando la clase Character. El resultado debe 
     * ser el mismo
	 */
	private static void consulta4() {
		
		ODB odb = ODBFactory.open("lotr.db");
				
	    IQuery consulta = new CriteriaQuery(Realm.class, Where.like("name", "%Valinor%"));
	    
	    System.out.println();
		System.out.println("Listado con los reinos Valinor y sus personajes. Metodo toString");
		System.out.println("-----------------------------------------------------------------");
		System.out.println();
				
		try {
			Objects<Realm> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Realm realm = objetos.next();
				System.out.println(realm.toString());
				System.out.println(realm.getCharacters().toString());
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado objetos");
		}
		
		consulta = new CriteriaQuery(Character.class, Where.like("realm.name", "%Valinor%"));
		
		System.out.println();
		System.out.println("Listado con los personajes del Realm Valinor. Metodo toString");
		System.out.println("-------------------------------------------------------------");
		System.out.println();
		
		try {
			Objects<Character> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Character character = objetos.next();
				System.out.println(character.toString());
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado objetos");
		}
		
				
		odb.close();
				
		
		
	}
	
	/**
	 * Muestra por pantalla todas las películas relacionadas con el libro “The Hobbit”.

	 */
	private static void consulta5() {
		
		ODB odb = ODBFactory.open("lotr.db");
		
	    IQuery consulta = new CriteriaQuery(Book.class, Where.like("title", "%The Hobbit%"));
	    
	    System.out.println();
		System.out.println("Listado con las pelicula del libro The Hobbit");
		System.out.println("---------------------------------------------");
		System.out.println();
				
		try {
			Objects<Book> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				Book book = objetos.next();
				ArrayList<Movie> movies = new ArrayList<Movie>();
				movies.addAll(book.getMovies());
				for(int i = 0; i < movies.size(); i++) {
					System.out.println(movies.get(i).getName());
				}
				
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado elementos");
		}
	
		
		odb.close();
		
	}

	private static void consulta6() {
		
		ArrayList<String> listadoReinos = new ArrayList<String>();
		
		ODB odb = ODBFactory.open("lotr.db");
		
	    IQuery consulta = new CriteriaQuery(Movie.class, Where.like("name", "%The Return of the King%"));
	    
	    System.out.println();
		System.out.println("Listado con los reinos que aparecen en la  pelicula el retorno del Rey");
		System.out.println("----------------------------------------------------------------------");
		System.out.println();
		
		try {
			Objects<Movie> objetos = odb.getObjects(consulta);
			while(objetos.hasNext()) {
				//cogemos la pelicula
				Movie movie = objetos.next();
				//almacenamos los dialogos de la pelicula en un array
				ArrayList<Dialog> dialog = new ArrayList<Dialog>();
				dialog.addAll(movie.getDialogs());
				//obtenemos todos los objetos de la clase Realm
				Objects<Realm> realms = odb.getObjects(Realm.class);
				//corremos cada uno de los objetos de la clase Realm
				while(realms.hasNext()) {
					Realm realm = realms.next();
					//separamos los reinos, hay compuestos
					String[] reinos = realm.getName().split(",");
					for(int i = 0; i < reinos.length; i++) {
						//corremos el array de dialogs y vemos si cada linea incluye el reino
						for(int j = 0; j < dialog.size(); j++) {
							if(dialog.get(j).getDialog().contains(reinos[i])) {
								//si aparece un reino, vemos si esta en el listado y sino lo almacenamos
								if (!listadoReinos.contains(reinos[i])) listadoReinos.add(reinos[i]);
							}
						}
					}
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("No se han encontrado elementos");
		}
		
		//imprimimos los reinos que aparecen
		for(int i = 0; i < listadoReinos.size(); i++) {
			System.out.println(listadoReinos.get(i).toString());
		}
		
		System.out.println("total: " + listadoReinos.size() +  " reinos");
		
	}

}
