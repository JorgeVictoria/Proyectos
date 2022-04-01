package practica06;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Practica06 {
	
	//variables globales
	private static Element raiz;
	private static Document documento;

	public static void main(String[] args) {
		
		try {
			
			//Crear una factoria que permita usar un parser:
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			//Crear un builder que permite crear documentos DOM usando un parser
			documento = builder.parse(new File("C://Workspace/animales.xml"));
			
			//los nodos de texto adyacentes los fusiona
			documento.getDocumentElement().normalize();
			
			//PARTE 01. MOSTRAR ALGUNOS ELEMENTOS DEL FICHERO XML CON GETELEMENTSBYTAGNAME
			System.out.println("PASO 1. LEER CON GETELEMENTBYTAGNAME");
			System.out.println("");
			
			//imprimimos el elemento raiz
			System.out.println("---" + documento.getDocumentElement().getNodeName());
			
			//crear una lista con todos los nodos Character
			NodeList personajes = documento.getElementsByTagName("character");
			
			//recorremos los diferentes
			for (int i = 0; i < personajes.getLength(); i++) {
				//Creamos un objeto nodo con cada elemento Character
				Node personaje = personajes.item(i);
				if (personaje.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) personaje;
					//imprimimos las diferentes caracteristicas de cada personaje
					System.out.println("------id");
					System.out.println("---------" + elemento.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("------name");
					System.out.println("---------" + elemento.getElementsByTagName("name").item(0).getTextContent());
					System.out.println("------titles");
					System.out.println("---------title");
					int titulos = elemento.getElementsByTagName("title").getLength();
					//como hay personajes con varios titulos, creamos un array con la cantidad de elementos titulos
					for(int j = 0; j < titulos; j++)
						System.out.println("---------------" + elemento.getElementsByTagName("title").item(j).getTextContent());
				}
				
			}
			
			//PASO2. LEER EL FICHERO XML CON GETCHILDNODES
			System.out.println();
			System.out.println("PASO 2. LEER CON GETCHILDNODES");
			System.out.println();
			
			//creamos un nuevo NodeList
			personajes = documento.getChildNodes();
			NodeList indice1 = personajes.item(0).getChildNodes();
			//primer subnivel
			for(int i = 0; i < indice1.getLength(); i++){
				if(i % 2!= 0) {
					System.out.print("-----");
					System.out.println(indice1.item(i).getNodeName());
					//segundo subnivel
					NodeList indice2 = indice1.item(i).getChildNodes();
					if(indice2.getLength() > 0) {
						for(int k = 0; k < indice2.getLength(); k++) {
							if(k % 2 != 0) {
								System.out.print("-------");
								System.out.println(indice2.item(k).getNodeName());
								//tercer subnivel
								NodeList indice3 = indice2.item(k).getChildNodes();
								//mostramos el contenido de los nodos de tercer nivel
								if(indice3.getLength() > 1 ) {
									for(int j = 0; j < indice3.getLength(); j++) {
										if(j % 2 != 0) {
											System.out.print("---------");
											System.out.println(indice3.item(j).getTextContent());
										}
									}
								}
								//si los nodos de segundo nivel no tienen nodos hijo, mostramos su contenido
								else {
									System.out.print("---------");
									System.out.println(indice2.item(k).getTextContent());
								}
							}
						}
					}
				}
			}

			//PASO 3 LEER CON GETCHILDNODES, METODO RECURSIVO
			System.out.println();
			System.out.println("PASO 3. LEER CON GETCHILDNODES, METODO RECURSIVO");
			System.out.println();
			//llamada al metodo que imprime el fichero xml
			imprimirXML(indice1,5);
			
			//PASO 4.creamos los actores de los personajes. Sabemos de antemano el orden de los actores
			String[] actores = {"Alfie Allen", "Isaac Hempstead-Wright", "Art Parkinson", "Richard Madden", "Sophie Turner"};
			
			//corremos todos los nodos character e incluimos un nuevo nodo hijo llamada playedBy, al que le insertamos el nombre del actor
			//en este caso los actores estan en el mismo orden de los personajes representados
			personajes = documento.getElementsByTagName("character");
			for(int i = 0; i < personajes.getLength(); i++) {
				Node personaje  = personajes.item(i);
				if (personaje.getNodeType() == Node.ELEMENT_NODE) {
					Element playedBy = documento.createElement("playedBy");
					playedBy.appendChild(documento.createTextNode(actores[i]));
					personaje.appendChild(playedBy);
				}
			}
			
			//PASO 5. Creamos un nuevo personaje
			//por cada elemento que debemos insertar, llamaremos al metodo correspondiente
			
			//cogemos el elemento raiz y le añadimos un personaje
			raiz = documento.createElement("character");
			documento.getDocumentElement().appendChild(raiz);
			
			//pasamos el id
			unNodo("id","583");
			
			//pasamos el nombre
			unNodo("name","Jon Snow");
			
			//pasamos el genero
			unNodo("gender", "Male");
			 
			//pasamos la cultura
			unNodo("culture", "Northmen");
			
			//pasamos  fecha y lugar de nacimiento
			unNodo("born","In 283 AC, at Winterfell");
			
			//pasamos cuando murio
			unNodo("died","");
			
			//pasamos si esta muerto
			unNodo("alive", "TRUE");
			
			//pasamos los titulos
			variosNodos ("titles", "title","Lord Commander of the Night's Watch","King in the North" );
			
			//pasamos los alias
			variosNodos("aliases","alias","Lord Snow","Ned Stark's Bastard","The Snow of Winterfell","The Crow-Come-Over","998th Lord Commander of the Night's Watch",
			"The Bastard of Winterfell", "The Black Bastard of the Wall","Lord Crow");
			
			//pasamos el padre
			unNodo("father","Eddard Stark");
			
			//pasamos la madre
			unNodo("mother","");
			
			//pasamos la pareja
			unNodo("spouse","");
			
			//pasamos alianzas
			unNodo("allegiances","");
			
			//pasamos los libros
			variosNodos("books", "book", "A Game of Thrones (1996)", "A Clash of Kings (1998)","A Storm of Swords (2000)","A Feast for Crows (2005)", "A Dance with Dragons (2011)");
			
			//pasamos las series
			variosNodos("tvSeries", "season", "season 1", "season 2", "season 3", "season 4", "season 5", "season 6", "season 7", "season 8");
			
			//pasamos el actor
			unNodo("playedBy", "Kit Harington");
			
			//escribimos en un nuevo fichero
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(documento);
	         Result resultado = new StreamResult(new java.io.File("C://workspace//mixml.xml"));
	         transformer.transform(source, resultado);

	         
			
			} catch(Exception e){
				System.err.print("Error: " + e.getMessage());
			}

			
	}
	/**
	 * funcion recursiva que imprime el fichero xml
	 * @param algo: pasamos un nodelist
	 * @param rayas: son las rayas horizontales que originalmente se deben imprimir por cada linea
	 */
	private static void imprimirXML(NodeList algo, int rayas) {
		
		for(int i = 0; i < algo.getLength(); i++) {
			if(i % 2!= 0) {
				for(int r = 0; r < rayas; r++) System.out.print("-");
				System.out.println(algo.item(i).getNodeName());
				NodeList nuevoNodeList = algo.item(i).getChildNodes();
				//al crear un nuevo nodeList, si solo tiene un elemento
				if (nuevoNodeList.getLength() < 2) {
					for(int r = 0; r < rayas+2; r++) System.out.print("-");
					System.out.println(algo.item(i).getTextContent());
				}
				/*if (nuevoNodeList.getLength() > 0)*/else  imprimirXML(nuevoNodeList,rayas+2);
			}
		}
		
	}
	
	/**
	 * metodo que rellena el fichero XML con nuevos elementos
	 * valido para nodos que no tienen hijos
	 * @param a: el nombre de la etiqueta
	 * @param b: el contenido de la etiqueta
	 */
	private static void unNodo(String a, String b) {
		
		Element elem =documento.createElement(a);
		Text text = documento.createTextNode(b);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
	
	/**
	 * metodo que rellena el fichero XML con nuevos elementos
	 * valido para nodos que tienen varios hijos
	 * @param a: nombre de la etiqueta principal
	 * @param b: nombre de la etiqueta hija
	 * @param variasCadenas: varargs con numero indefinido de cadenas, que son el contenido de las etiquetas hija
	 */
	private static void variosNodos(String a, String b, String...variasCadenas) {
		
		Element elem = documento.createElement(a);
		
		for(int i = 0; i < variasCadenas.length; i++ ) {
		
			Element subElem = documento.createElement(b);
			Text text = documento.createTextNode(variasCadenas[i]);
			if(i == 0)raiz.appendChild(elem);
			elem.appendChild(subElem);
			subElem.appendChild(text);
		}
		
	}

}
