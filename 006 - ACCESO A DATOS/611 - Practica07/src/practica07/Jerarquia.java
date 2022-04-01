package practica07;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Jerarquia {

	public static void main(String[] args) {
		
		try {
					
					//creamos un objeto File, con el fichero XML que vamos a leer
					File inputFile = new File("C://workspace//got.xml");
					
					//creamos los diferentes elementos SAX
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser saxParser = factory.newSAXParser();
					
					//creamos un objeto userHandler, llamando a su constructor
					UserHandlerThree userhandler = new UserHandlerThree();
					
					//llamamos al metodo parse para trabajar con la funcion
					saxParser.parse(inputFile, userhandler);
					
					
					//control de excepciones
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		class UserHandlerThree extends DefaultHandler {
			
			//arrayList donde almacenamos las distintas etiquetas y su contenido
			ArrayList<String> test = new ArrayList<String>();
			//para montar cadenas
			String cadena;
			//para contar las lineas
			int contador = 3;
				
			//inicio del procesado de las etiquetas XML
			@Override
			public void startDocument() throws SAXException {}
			
			@Override
			public void startElement( String uri, String localName, String qName, Attributes attributes) throws SAXException {
				
				//añadimos la etiqueta de apertura al arrayList
				test.add(pintar() + qName);
				//el contador de lineas los incrementamos para un nuevo subnivel
				contador = contador + 3;

			}
			
			//metodo para pintar las lineas horizontales
			private String pintar() {
				
				//creamos una cadena y la rellenamos con lineas horizontales
				String lineas = "";
				for (int i = 0; i < contador; i++) {
					lineas = lineas+"-";
				}
				
				return lineas;
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				
				//fin de etiqueta, restamos lineas para subir un nivel
				contador = contador-3;
			}
			
			@Override
			public void characters(char ch[], int start, int length) throws SAXException {
				
					//creamos una cadena con los parametros de entrada, que será el valor
					cadena = new String(ch, start, length);
					//las etiquetas vacias o solo de apertura van acompañadas de un salto de linea, que eliminamos
					if (cadena.charAt(0) == 10) {
						test.add("");
					//si la etiqueta tiene algun valor, la añadimos normal
					} else test.add(pintar() + cadena);
				
				
		
			}
			
			public void endDocument() throws SAXException {
				
				//con este iterador vamos leyendo el arrayList
				Iterator<String> nombreIterator = test.iterator();
				while(nombreIterator.hasNext()){
					String elemento = nombreIterator.next();
					if (elemento.length() == 0) System.out.print("");
					else System.out.println(elemento);
				}
				
				
		       
		    }
					
		}
					
					
