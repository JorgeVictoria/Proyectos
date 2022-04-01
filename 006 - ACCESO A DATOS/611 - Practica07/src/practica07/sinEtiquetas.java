package practica07;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class sinEtiquetas extends DefaultHandler {

	public static void main(String[] args) {
		
try {
			
			//creamos un objeto File, con el fichero XML que vamos a leer
			File inputFile = new File("C://workspace//got.xml");
			
			//creamos los diferentes elementos SAX
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			//creamos un objeto userHandler, llamando a su constructor
			UserHandlerTwo userhandler = new UserHandlerTwo();
			
			//llamamos al metodo parse para trabajar con la funcion
			saxParser.parse(inputFile, userhandler);
			
			
			//control de excepciones
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class UserHandlerTwo extends DefaultHandler {
	
	//variables miembro
	String[] myArray = new String[2]; //creamos un array con el nombre de etiqueta y su texto
	String temp = "";
	int contador = 0;
	String principal = ""; //es la etiqueta para marcar cada personaje.
	
		
	//inicio del procesado de las etiquetas XML
	@Override
	public void startDocument() throws SAXException {
        
    }
	
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//imprimimos el elemento root del documento
		if (contador == 0) {
			System.out.println(qName);
		}
		
		//imprimimos la segunda linea que representa los diferentes elementos del elemento raiz
		//almacenamos dicha etiqueta en una variable para saber cuando llegamos a esa etiqueta al correr el documento
		//si qName es igual a la variable, imprimimos esa etiqueta.
		//este paso es un añadido para adornar el resultado, pero es prescindible si no sabemos la estructura
		if (contador == 1 || qName.equals(principal)) {
			principal = qName;
			System.out.println(qName.toUpperCase());
		}
		
		//incrementamos el contador y ya no entramos al condicional anterior
		contador++;
		
		//almacenamos el resto de las etiquetas
		myArray[0] = qName;
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		//si la etiquetq tiene contenido, imprimimos etiqueta y contenido
		if (myArray[1].length() >0 ) System.out.println(myArray[0] + " : " + myArray[1]);
		
		//si la etiqueta no tiene contenido, solo imprimimos la etiqueta
		else if(!myArray[0].equals(temp) && myArray[1].length() == 0) System.out.println(myArray[0] + " : ");
		
		//almaceno la ultima etiqueta que se ha impreso y evito la impresion de etiquetas no deseadas
		temp = myArray[0];
		
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		
		//almacenamos el contenido de cada etiqueta
		myArray[1] = new String(ch, start, length);
		
		//si la etiqueta no empieza por digito o letra, cadena en blanco
		//evitamos saltos de lineas y cosas raras
		if (myArray[1].charAt(0) < 48) myArray[1] = "";
		
	}
	
	public void endDocument() throws SAXException {
       
    }
			
}
			
			
