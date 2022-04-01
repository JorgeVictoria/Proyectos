package Ejercicio04;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class U06E04 {
	
	//lo usaremos para filtrar archivos gps
	public static FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
				String tmp = file.getName().toLowerCase();
				if (tmp.endsWith(".gps")){
					return true;
				}
					return false;
				}
			};

	public static void main(String[] args) {
		
		//variables locales
		String fichero; //almacena el fichero
		String origen ="origen"; 	//almacena la ciudad de origen
		String destino ="destino";	//almacena la ciudad de destino
		String carpetaActual; 	//almacena la carpeta actual
		
		Scanner stdin = new Scanner(System.in); //entrada por teclado
		
		boolean flag=false;
		
		//preguntamos por el fichero, en este caso suponemos que lo tenemos en el directorio actual
		System.out.print("Fichero (.GPS): ");
		fichero = stdin.nextLine();
		
		//suponiendo que vamos a trabajar desde el actual directorio, lo almacenamos
		carpetaActual = System.getProperty("user.dir");
		
		//creamos el objeto File 
		File d = new File(carpetaActual);
		
		//vemos si hay ficheros con la extension gps dentro del directorio
		File[] listaFicheros = d.listFiles(filter);
		
		//si hay archivos gps, buscamos el que queremos
		if(listaFicheros.length > 0) {
			for(int i = 0; i < listaFicheros.length; i++) {
				if(listaFicheros[i].getName().toLowerCase().equals(fichero.toLowerCase())) flag=true;
			}
		}
		
		//si no hemos encotrado nada finalizamos el programa
		if(flag == false) {
			System.out.println("No hemos encontrado el fichero " + fichero);
			System.exit(0);
		}
		
		//si existe el fichero continuamos con el programa
		
		//mientras el origen y el destino no esten vacios
		while(!origen.isEmpty() && !destino.isEmpty()) {
		
			//preguntamos por la ciudad de origen
			System.out.print("Origen: ");
			origen = stdin.nextLine();
			
			//preguntamos por la ciudad de destino
			System.out.print("Destino: ");
			destino = stdin.nextLine();
			
			//si origen y destino no estan vacias
			if(!origen.isEmpty() && !destino.isEmpty()) {
				Grafo unGrafo = new Grafo(fichero);
				String cadena = unGrafo.caminoMasCorto(origen, destino);
				System.out.println(cadena);
			}//fin del if
		
		}//fin del while
		
	}//fin del main

}//fin de la clase

//*****************************************clase Grafo*********************************************
class Grafo{ 
	
	public static String[] datos; 	//para almacenar los datos del fichero
	public static int contador = 0;  	//las posiciones del array de datos
	public static int lineasTotales = 0; //lineas totales del fichero
	
	//variables miembro
	private int nV; //numero de vertices
	private int nA; //numero de aristas
	private int[][] tA; //tabla de adyacencias
	private String[] nombreVertice; //nombre de los vertices
	
	//constructor
	public Grafo(String nomFichero) {
		
		//declaramos una variable BufferedReader
		BufferedReader br = null;
		
		try {
			//crear un objeto BufferedReader al que se le pasa
			//un objeto FileReader con el nombre del fichero
			br = new BufferedReader(new FileReader(nomFichero));
			
			
			//leer la primera linea, guardando en un string
			String texto = br.readLine();
			
			//repetir mientras no se llegue al final del fichero
			while(texto != null) {
				
				//contamos las lineas del fichero y leemos
				contador++;
				texto = br.readLine();
				
				
			}//fin del while
			
			//almacenamos las lineas totales leidas
			lineasTotales = contador;
			
			//conocido el numero de lineas construimos el array de datos
			datos = new String[contador];
			
			//ponemos el contador a cero
			contador = 0;
			
			//creamos un bufferedReader
			//con el metodo br.mark y br.reset deberia haber reiniciado el buffer anterior
			//pero me da error al aplicarlos
			BufferedReader br2 = new BufferedReader(new FileReader(nomFichero));
			
			//volvemos a correr el fichero rellenando de nuevo los datos
			texto = br2.readLine();
			while(texto != null) {
				
				datos[contador]=texto.trim();
				contador++;
				texto = br2.readLine();
				
				
			} //fin del while
			
			//ahora vamos rellenando las variables miembro
			contador=0;
			
			//la primera linea indica el numero de indices
			nV = Integer.parseInt(datos[0]);
			contador++;
			
			//la segunda linea indica el numero de aristas
			nA = Integer.parseInt(datos[1]);
			contador++;
			
			//creamos el array de vertices
			nombreVertice = new String[nV];
			
			//creamos el array de adyacentes
			tA = new int[nV][nV];
			
			//rellenamos el array de vertices
			for(int i = 2; i < nV+2; i++) {
				int pos = datos[i].indexOf(" "); //el espacio separa el nº vertice del nombre de vertice
				nombreVertice[i-2] = datos[i].substring(pos+1);
				contador++;
			}
			
			//rellenamos la tabla de adyacentes
			for(int i = contador; i < lineasTotales; i++) {
				
				//como son 3 campos, cogemos la primera y ultima posicion del separador
				int pos1 = datos[i].indexOf(" ");
				int pos2 = datos[i].lastIndexOf(" ");
				
				//volcamos cada campo en variables
				int temp1 = Integer.parseInt(datos[i].substring(0, pos1));
				int temp2 = Integer.parseInt(datos[i].substring(pos1+1, pos2));
				
				//los valores no pueden ser negativos o mayores que el numero de vertices
				if(temp1 >= nV || temp1 < 0 || temp2 >= nV || temp2 < 0) {
					throw new IllegalArgumentException ("Error formato en " + nomFichero + " linea " + i);
				}
				
				//el primer campo no puede igual que el segundo campo
				if(temp1 == temp2) {
					throw new IllegalArgumentException ("Error formato en " + nomFichero + " linea " + i);
				}
				
				//si esta todo correcto
				tA[temp1][temp2] = Integer.parseInt(datos[i].substring(pos2+1));
				tA[temp2][temp1] = Integer.parseInt(datos[i].substring(pos2+1));
		
			}
		//tratamiento de errores
		} catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(br != null)
					br.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}
		
	} //fin del constructor
	
	//metodos
	
	public String caminoMasCorto(String origen, String destino) {
		
		//variables locales
		boolean cOrigen = false;
		boolean cDestino = false;
		String cadena = "";
		int d=0,o=0; //numero de vertice de origen y destino
		
		//comprobamos que la ciudad de origen estan dentro de los datos
		for(int i = 0; i < nombreVertice.length; i++) {
			if(nombreVertice[i].toLowerCase().equals(origen.toLowerCase())) {
				cOrigen = true;
				o = i;
			}
			if(nombreVertice[i].toLowerCase().equals(destino.toLowerCase())) {
				cDestino = true;
				d = i;
			}
		}
		
		if(cOrigen == false && cDestino == false) cadena = cadena + "ciudad de origen " + origen + " y destino " + destino + " no encontradas.";
		else if(cOrigen == false) cadena = cadena + "ciudad de origen " + origen +  " no encontrada.";
		else if(cDestino == false) cadena = cadena + "ciudad de destino " + destino +  " no encontrada.";
		else if(origen.equals(destino)) cadena = cadena + origen +"(0) -> " + origen;
		//llamada al metodo
		else cadena = Dijsktra(o,d);
		
		return cadena;
	} //fin del metodo
	
	//metodo dijsktra
	private String Dijsktra(int ori, int des) {
		//variables locales
		ArrayList S = new ArrayList();
		int[] D = new int[nV];
		int[] P = new int[nV];
		int menos = 0;
		//int pos = 0;
		int tramo;
		int distancia=0;
		int w=-1;
		int v=-1;
		String camino = "";
		
		//inicialmente S contiene el vertice origen(x)
		S.add(ori); 
	
		//para cada vertice
		for(int i = 0; i < nV; i++) {
			//si i no es el origen
			if(i != ori) {
				//si hay arista entre i y origen
				if(tA[ori][i] > 0) D[i] = tA[ori][i];
				//si no hay arista ponemos el maximo valor entero
				else D[i] = Integer.MAX_VALUE;
			}
		} //fin del for
		
		//explorar el grafo
		for(int i = 0; i < nV; i++) {
			if(S.size() == nV) break;
			//corremos el array de distancias, buscando la posicion que no esté en S y que tenga la menor distancia
			menos = Integer.MAX_VALUE; //inicializamos la variable para ver que posicion tiene menos
			for(int j = 0; j < nV; j++) {
				//comprobamos que j no está en S
				if(!S.contains(j)) {
				//comprobamos que tenga el valor mas bajo
					if(D[j] < menos) {
						menos = D[j];
						w = j; //asignamos a v la posicion en D[i]
					}
				}
			}
			//añadimos el vertice w al conjunto S
			S.add(w);
			
			//volvemos a correr V-S, o sea todos los vertices que no esten en S
			for(int j = 0; j < nV; j++) {
				//comprobamos que j no está en S
				if(!S.contains(j)) {
				//comprobamos que hay una arista w - j
					if(tA[w][j] > 0) {
						v = j;
						if (D[v] + tA[w][v] < D[v]) {
							D[v] = D[w] + tA[w][v];
							P[v] = w;
						}
					}
				}
			}
			
			
			
		} //fin exploracion del grafo
		
		//test de arrays
		/*for(int i = 0; i<nV; i++) System.out.print(i + ":" + D[i] + " ");
		System.out.println();
		for(int i = 0; i<nV; i++) System.out.print(i + ":" + P[i] + " ");
		System.out.println();*/

		distancia = 0;
		
		camino = nombreVertice[des];
		
		
		v = des;
		
		
		while (v != ori){
			//en el vertice previo al destino se me va a 0, asi que añado esto
			if(P[v]==0) {
				tramo = tA[v][ori];
				distancia = distancia + tramo;
				camino = nombreVertice[ori] + " (+" + tramo + ") -> " + camino;
				v=ori;
			} else {
				tramo = tA[v][P[v]];
				distancia = distancia + tramo;
				camino = nombreVertice[P[v]] + " (+" + tramo + ") ->" + camino;
				v = P[v];
			}
		}
		
		camino ="  " + camino + " Total: " + distancia;
		return camino;
		
		
		
	} //fin de dijsktra
	
	
	
} //fin de la clase grafo
