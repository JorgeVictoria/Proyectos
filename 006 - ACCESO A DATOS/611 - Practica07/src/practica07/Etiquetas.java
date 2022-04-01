package practica07;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Etiquetas {

	public static void main(String[] args) {
		
		try {
			
			//creamos un objeto File, con el fichero XML que vamos a leer
			File inputFile = new File("C://workspace//got.xml");
			
			//creamos los diferentes elementos SAX
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			//creamos un objeto userHandler, llamando a su constructor
			UserHandler userhandler = new UserHandler();
			
			//llamamos al metodo parse para trabajar con la funcion
			saxParser.parse(inputFile, userhandler);
			
			
			//control de excepciones
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class UserHandler extends DefaultHandler {
	
	//variables miembro, de tipo booleanas para controlar las etiquetas que vamos leyendo
	boolean gotId = false;
	boolean gotName = false;
	boolean gotGender = false;
	boolean gotCulture = false;
	boolean gotBorn = false;
	boolean gotDied = false;
	boolean gotAlive = false;
	boolean gotTitle = false;
	boolean gotAlias = false;
	boolean gotFather = false;
	boolean gotMother = false;
	boolean gotSpouse = false;
	boolean gotAllegiances = false;
	boolean gotBook = false;
	boolean gotSeason = false;
		
	
	//inicio del procesado de las etiquetas XML
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//leemos la etiqueta de apertura hasta encontrar la correspodiente´
		//una vez encontrada se marca a true para indicar que etiqueta debemos imprimir
		if (qName.equalsIgnoreCase("id")) {
			gotId = true;
		} else if (qName.equalsIgnoreCase("name")) {
			gotName = true;
		} else if (qName.equalsIgnoreCase("gender")) {
			gotGender = true;
		}  else if (qName.equalsIgnoreCase("culture")) {
			gotCulture = true;
		}  else if (qName.equalsIgnoreCase("born")) {
			gotBorn = true;
		} else if (qName.equalsIgnoreCase("died")) {
			gotDied = true;
		} else if (qName.equalsIgnoreCase("alive")) {
			gotAlive = true;
		} else if (qName.equalsIgnoreCase("title")) {
			gotTitle = true;
		} else if (qName.equalsIgnoreCase("alias")) {
			gotAlias = true;
		} else if (qName.equalsIgnoreCase("father")) {
			gotFather = true;
		} else if (qName.equalsIgnoreCase("mother")) {
			gotMother = true;
		} else if (qName.equalsIgnoreCase("spouse")) {
			gotSpouse = true;
		} else if (qName.equalsIgnoreCase("allegiances")) {
			gotAllegiances = true;
		} else if (qName.equalsIgnoreCase("book")) {
			gotBook = true;
		} else if (qName.equalsIgnoreCase("season")) {
			gotSeason = true;
		} 
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if (qName.equalsIgnoreCase("character")) {
			System.out.println("End Element :" + qName);
			System.out.println("--------------------------------------");
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		
		//en funcion de que etiqueta de apertura este marcada a true
		//imprimimos el nombre de la etiqueta y su contenido
		if (gotId) {
			System.out.println("Id: " + new String(ch, start, length));
			gotId = false;
		} else if (gotName) {
			System.out.println("Name: " + new String(ch, start, length));
			gotName = false;
		} else if (gotGender) {
			System.out.println("Gender: " + new String(ch, start, length));
			gotGender = false;
		} else if (gotCulture) {
			System.out.println("Culture: " + new String(ch, start, length));
			gotCulture = false;
		} else if (gotBorn) {
			System.out.println("Born: " + new String(ch, start, length));
			gotBorn = false;
		} else if (gotDied) {
			//ojo que hay etiquetas sin contenido, me crea un doble salto de linea
		    if(new String(ch, start, length).length() == 3) System.out.println("Died: ");
		    else System.out.println("Died: " + new String(ch, start, length));
			gotDied = false;
		} else if (gotAlive) {
			System.out.println("Alive: " + new String(ch, start, length));
			gotAlive = false;
		} else if (gotTitle) {
			System.out.println("Title: " + new String(ch, start, length));
			gotTitle = false;
		} else if (gotAlias) {
			System.out.println("Alias: " + new String(ch, start, length));
			gotAlias = false;
		} else if (gotFather) {
			System.out.println("Father: " + new String(ch, start, length));
			gotFather = false;
		} else if (gotMother) {
			System.out.println("Mother: " + new String(ch, start, length));
			gotMother = false;
		} else if (gotSpouse) {
			//ojo que hay etiquetas sin contenido, me crea un doble salto de linea
		    if(new String(ch, start, length).length() == 3) System.out.println("Spouse: ");
			else System.out.println("Spouse: " + new String(ch, start, length));
			gotSpouse = false;
		} else if (gotAllegiances) {
			//ojo que hay etiquetas sin contenido, me crea un doble salto de linea
		    if(new String(ch, start, length).length() == 13) System.out.println("Allegiances: ");
		    else System.out.println("Allegiances: " + new String(ch, start, length));
			gotAllegiances = false;
		} else if (gotBook) {
			System.out.println("Book: " + new String(ch, start, length));
			gotBook = false;
		} else if (gotSeason) {
			System.out.println("Season: " + new String(ch, start, length));
			gotSeason = false;
		}
	}
			
}

	


