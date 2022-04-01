/**
 * 
 */
package lotr;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ClassRepresentation;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 * @author Jorge Victoria Andreu
 * @version 1.0
 * @since 24jan2022
 * Clase para programar los metodos para el manejo de la BBDD
 */
public class NeoDatisDB_LOTR_Management {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		///nos creamos una lista para almacenar las clases
		List<Class> classList = new ArrayList<Class>();
		//creamos otra lista con los nombres de los indices que queremos usar
		String[] nombreIndices = {"bookIndex", "chapterIndex", "characterIndex", "dialogIndex", "movieIndex", "realmIndex"};
		//almacenamos las clases en la lista
		classList.add(Book.class);
		classList.add(Chapter.class);
		classList.add(Character.class);
		classList.add(Dialog.class);
		classList.add(Movie.class);
		classList.add(Realm.class);
		
		//metodo para crear los indices de cada una de las 
		for(int i = 0; i < nombreIndices.length; i++) {
			creaIndex(classList.get(i), nombreIndices[i]);
		}
		
		//creamos un Character con un id inventado. Como es para testeo, solo rellenamos id y nombre
		Character character = new Character();
		character.setId("001122");
		character.setName("Jorge, el Oscuro");
		//vamos a crear el objeto con un id nuevo. Se creará
		guardaObjeto(character, "lotr.db");
		//volvemos a intentar guardar el mismo objeto, con el mismo id, logicamente, y dará error
		guardaObjeto(character, "lotr.db");
		
		//creamos un Character con un id inventado. Como es para testeo, solo rellenamos id y nombre
		Movie movie = new Movie();
		movie.setId("001122");
		movie.setName("El Silmarillion");
		//vamos a crear el objeto con un id nuevo. Se creará
		guardaObjeto(movie, "lotr.db");
		//volvemos a intentar guardar el mismo objeto, con el mismo id, logicamente, y dará error
		guardaObjeto(movie, "lotr.db");
		
		//pruebas con realm
		//prueba con id y nombre que ya existen. Error
		Realm realm = new Realm();
		realm.setId("5000027");
		realm.setName("Tirion");
		guardaObjeto(realm, "lotr.db");
		//prueba con id nuevo y nombre que existe. Guarda el objeto
		realm.setId("001122");
		realm.setName("Tirion");
		guardaObjeto(realm, "lotr.db");
		//prueba con id existente y nombre nuevo. Error
		realm.setId("5000027");
		realm.setName("Torrent");
		guardaObjeto(realm, "lotr.db");
		//prueba con id y nombre nuevo
		realm.setId("001123");
		realm.setName("Torrent");
		guardaObjeto(realm, "lotr.db");
		
		//CONSULTAS CON METODO GETVALUE
		//sacar por pantalla info de Aragorn
		mostrarCharacter("Aragorn II");
		
		//sacar por pantalla los minutos de la trilogia de el El señor de los anillos
		mostrarMinutos("The Lord of the Ring");
		
		//mostrar por pantalla cada uno de los libros con el numero de capitulos
		mostrarLibros();
		
		//mostrar por pantalla la nota media de las trilogias
		String[] peliculas = {"The Lord of the Ring", "The Hobbit"};
		verNotaMedia(peliculas);
		
		//mostrar por pantalla dialogos con nombres de hobbits y quien lo dice
		sacaHobbit();

	}

	/**
	 * metodo para crear los diferentes indices de todas las clases de la BBDD de Neodatis
	 * @param nombreIndices, String con el nombre del indice
	 * @param clase, clase para la cual vamos a crear el indice 
	 */
	private static void creaIndex(Class clase, String nombreIndice) {
		
		System.out.println("Creando Indices");
		System.out.println("---------------");
		System.out.println();
		
		
		//abrimos la BBDD de neodatis
		ODB odb = ODBFactory.open("lotr.db");
		
		//creamos el indice de cada una de las clases
		if (!odb.getClassRepresentation(clase).existIndex(nombreIndice)) {
			String[] fieldIndex = {"id"};
			ClassRepresentation representacionClase = odb.getClassRepresentation(clase);
			representacionClase.addUniqueIndexOn(nombreIndice, fieldIndex, true);
		}
		
		System.out.println();
		System.out.println("Fin Creacion Indice");
		System.out.println("--------------------");
		System.out.println();
	
		//cerramos conexion con la BBDD
		odb.close();
		
	}
	
	/**
	 * metodo para guardar un objeto en una BBDD de Neodatis
	 * @param objeto, el objeto a guardar
	 * @param mombreDB, nombre de la BBDD
	 */
	private static void guardaObjeto(Object objeto, String nombreDB) {
		
		Boolean exito = true;
		
		System.out.println("---------------------------------------------------------");
		System.out.println("Creando objeto de tipo " + objeto.getClass().toString());
		System.out.println("---------------------------------------------------------");
		System.out.println();
		
		ODB odb = ODBFactory.open(nombreDB);
		
		try {
			odb.store(objeto);
		} catch (ODBRuntimeException e) {
		  exito = false;
		  System.out.println(e.getMessage());
		  System.out.println(e.getCause());
		}
		
		odb.close();
		if(exito) System.out.println("objeto guardado con exito");
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Fin de creacion de objeto de tipo " + objeto.getClass().toString());
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * metodo para mostrar los datos de un Character
	 * @param personaje, nombre del personaje que queremos mostrar
	 */
	private static void mostrarCharacter(String personaje) {
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Datos del personaje buscado");
		System.out.println("-------------------------------------------------------");
		//abrimos la BBDD Neodatis
		ODB odb = ODBFactory.open("lotr.db");
		
		//pasamos el filtro para buscar solo un personaje
		ICriterion criterio = Where.like("name", personaje);
		
		//obtenemos todos los valores
		Values valores = odb.getValues(new ValuesCriteriaQuery(Character.class,criterio).field("id").field("name").field("wikiUrl").field("race").field("birth").field("gender")
				.field("death").field("hair").field("height").field("realm").field("spouse"));
		
		try {
			while (valores.hasNext()) {
				ObjectValues valorObjetos = (ObjectValues)valores.next();
				System.out.println("Id: " + valorObjetos.getByIndex(0));
				System.out.println("Name: " + valorObjetos.getByIndex(1));
				System.out.println("WikiUrl: " + valorObjetos.getByIndex(2));
				System.out.println("Race: " + valorObjetos.getByIndex(3));
				System.out.println("Birth: " + valorObjetos.getByIndex(4));
				System.out.println("Gender: " + valorObjetos.getByIndex(5));
				System.out.println("Death: " + valorObjetos.getByIndex(6));
				System.out.println("Hair: " + valorObjetos.getByIndex(7));
				System.out.println("Heght: " + valorObjetos.getByIndex(8));
				//para los atributos de tipo objeto debemos crear previamente un objeto de ese tipo y volcar los datos
				//como metodo alternativo, se puede modificar el metodo toString de la clase para que solo muestre el nombre
				//y no haria falta crear ningun objeto
				Realm realm = new Realm();
				realm = (Realm) valorObjetos.getByIndex(9);
				System.out.println("Realm: " + realm.getName());
				Character character = new Character();
				character = (Character) valorObjetos.getByIndex(10);
				System.out.println("Spouse: " + character.getName());
			}
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("No se ha encontrado a " + personaje);
			
		}
		
		odb.close();
		
	}
	
	/**
	 * metodo para mostrar el metraje en minutos de una o varias peliculas
	 * @param string, cadena con el texto de la pelicula/s a bsucra
	 */
	private static void mostrarMinutos(String string) {
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Total nº minutos trilogia El Señor de los Anillos");
		System.out.println("-------------------------------------------------------");

		
		ODB odb = ODBFactory.open("lotr.db");
		
		//pasamos el filtro para buscar solo la trilogia
		ICriterion criterio = Where.like("name", "%" + string + "%");
		
		Values valores = odb.getValues(new ValuesCriteriaQuery(Movie.class,criterio).sum("runtimeInMinutes"));
		
		//Mostramos datos por pantalla
		try {
			while (valores.hasNext()) {
				ObjectValues valorObjetos = (ObjectValues)valores.next();
				BigDecimal largo = (BigDecimal)valorObjetos.getByAlias("runtimeInMinutes");
				System.out.println("Duracion Trilogia El Señor de los Anillos: " + largo + " minutos.");
				
			}
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("No se ha encontrado ninguna pelicula");
			
		}
		
		odb.close();
		
	}
	
	/**
	 * metodo para mostrar la cantidad de capitulos de los libros
	 */
	private static void mostrarLibros() {
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Total nº capitulos de los libros");
		System.out.println("-------------------------------------------------------");

		ODB odb = ODBFactory.open("lotr.db");
		
		Values valores = odb.getValues(new ValuesCriteriaQuery(Book.class).field("title").field("chapters"));
		
		try {
			while (valores.hasNext()) {
				ObjectValues valorObjetos = (ObjectValues)valores.next();
				System.out.println("Titulo: " + valorObjetos.getByIndex(0));
				//al coger el indice uno, que contiene una lista de elementos Capitulo
				//nos devuelve el mensaje "list with n elements", donde element es el numero de elementos de la lista
				//partimos la cadena
				
				String totalTitulos = valorObjetos.getByIndex(1).toString();
				String[] cadena = totalTitulos.split(" ");
				System.out.println("Total capitulos: " + cadena[2] + " capitulos");
				
			}
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("No se ha encontrado ninguna pelicula");
			
		}
		
		odb.close();

		
	}
	
	/**
	 * metodo para ver la nota media de una serie de peliculas
	 * @param pelicula, texto con parte del titulo de la/s peliculas/s
	 */
	private static void verNotaMedia(String[] pelicula) {
		
		ODB odb = ODBFactory.open("lotr.db");
		
		for(int i = 0; i < pelicula.length; i++) {
			//pasamos el filtro para buscar solo a Aragorn II
			ICriterion criterio = Where.like("name", "%" + pelicula[i] + "%");
			
			Values valores = odb.getValues(new ValuesCriteriaQuery(Movie.class,criterio).sum("TomatoesScore").count("total"));
			
			try {
				while (valores.hasNext()) {
					ObjectValues valorObjetos = (ObjectValues)valores.next();
					BigDecimal suma = (BigDecimal)valorObjetos.getByAlias("TomatoesScore");
					BigInteger total = (BigInteger)valorObjetos.getByAlias("total");
					BigDecimal convert = new BigDecimal(total);
					System.out.println("--------------------------------------------------------");
					System.out.printf("Puntuacion media Trilogia " + pelicula[i] + " : " + suma.divide(convert) + "\n");
				}
			} catch (IndexOutOfBoundsException e) {
				
				System.out.println("No se ha encontrado ninguna pelicula de la trilogia de " + pelicula[i]);
				
			//me esta dando problemas el redondeo de la nota de la trilogia de el Hobbit, asi que desvio el error a este try/catch para que la app no se quede colgada 
			} catch( ArithmeticException ex) {
				System.out.println("Error al calcular la nota media de la trilogia " + pelicula[i]);
			}
			System.out.println("--------------------------------------------------------");
		}
		
		odb.close();
		
	}
	
	/**
	 * metodo para sacar textos con Hobbits y quien lo dice
	 */
	private static void sacaHobbit() {
		
		//vamos a crear una lista donde vamos a almacenar los nombres de los Hobbits
		List<String> hobbits = new ArrayList<String>();
		
		//abrimos la BBDD
		ODB odb = ODBFactory.open("lotr.db");
		
		//vamos a buscar en la coleccion Characters aquellos que son Hobbits
		ICriterion criterio = Where.like("race", "Hobbit");
		Values values = odb.getValues(new ValuesCriteriaQuery(Character.class, criterio).field("name"));
		try {
			while(values.hasNext()) {
				ObjectValues valorObjetos = values.nextValues();
				String nombre = (String) valorObjetos.getByAlias("name");
				//como en los dialogos suelen aparecer solo los nombres, filtramos
				String[] nombreCompleto = nombre.split(" ");
				hobbits.add(nombreCompleto[0]);
			}
		} catch(IndexOutOfBoundsException ex) {
			System.out.println("Ningun Hobbbit encontrado");
		}
		
		//vamos a recoger todos los dialogos. Error al intentar mete
		//Values valores = odb.getValues(new ValuesCriteriaQuery(Dialog.class).field("dialog").field("character"));
		Values valores = odb.getValues(new ValuesCriteriaQuery(Dialog.class).field("dialog"));
		
		//para evitar repeticion de frases
		String repetido = "";
		
		try {
			while(valores.hasNext()) {
				ObjectValues valorObjetos2 = valores.nextValues();
				String frase =(String) valorObjetos2.getByAlias("dialog");
				for(int i = 0; i < hobbits.size(); i++) {
					if(frase.contains(hobbits.get(i).toString())) {
						if(!frase.equals(repetido))System.out.println(frase);
						repetido = frase;
						//al intentar coger el campo de personajes da error
						//sol muestro las frases en las que salen nombrados los hobbits
						/**Character character = new Character();
						character = (Character) valorObjetos2.getByAlias("character");
						System.out.println("Lo dice: " + character.getName());**/
					}
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		} catch (NullPointerException ex) {
			System.out.println("error");
		}
		
	}


}
