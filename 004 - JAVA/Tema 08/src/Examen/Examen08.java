package Examen;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Examen08 {
	
	private static int opcion = 0;
	private static Scanner opc = new Scanner(System.in);//opcion del menu
	private static Scanner stdin;
	private static Scanner stdout;
	private static File fichero = new File("C:\\workspace\\platos.txt");
	private static Set<Plato> bar = new TreeSet<>();
	private static int contador=1;

	
	public static void main(String[] args) throws IOException {
		
		while(opcion != 8) {
			System.out.println("1. Leer platos de fichero");
			System.out.println("2. Guardar platos en fichero.");
			System.out.println("3. Insertar nuevo plato");
			System.out.println("4. Borra plato");
			System.out.println("5. Mostrar menu semanal");
			System.out.println("6. Mostrar platos ordenados por precio");
			System.out.println("7. Msotrar platos ordenados por calorias");
			System.out.println("8. Salir");
			System.out.println("Elige opcion:");
			opcion = opc.nextInt();
			
			switch(opcion) {
			case 1: leerPLatos();
					break;
			case 2: GuardarPLatos(); 
					break;
			case 3: InsertarPlato();
					break;
			case 4: BorrarPlato();
					break;
			case 5: MenuSemanal();
					break;
			case 6: OrdenarPrecio();
					break;
			case 7: OrdenarCalorias();
					break;
			case 8: break;
			} //fin case
		} //fin while

	}

	private static void leerPLatos() {
		
		String cadena = "";
		String nom = "";
		String tip = "";
		int cal = 0;
		double pre;
		
		try {
            //indicamos desde vamos a coger los datos, en este caso el fichero
            stdin = new Scanner(fichero);

            while(stdin.hasNext()){     //mientras haya tokens
                //leemos token
                cadena = stdin.next();
                //limpiamos token
                cadena = cadena.replaceAll("[\\,\\:\\\"\\n\\{\\}]","");
                //quitamos espacios
                cadena = cadena.trim();
                //pasamos token a minusculas
                cadena = cadena.toLowerCase();
                if(contador==2) nom = cadena;
                if(contador==4) tip = cadena;
                if(contador==6) cal = Integer.parseInt(cadena);
                if(contador==8) {
                	pre = Double.parseDouble(cadena);
                	bar.add(new Plato(nom,tip,cal,pre));
                	contador = 0;
                }
                contador++;
            }
            
            }catch (FileNotFoundException e) {
    			System.out.println("Error: Fichero no encontrado");
    			System.out.println(e.getMessage());
    		} catch (Exception e) {
    			System.out.println("Error de lectura del fichero");
    			System.out.println(e.getMessage());
    		}
		
	}
	
	private static void GuardarPLatos() throws IOException {
		
		String cadena;
		BufferedWriter bw = new BufferedWriter(new FileWriter("platos.txt"));
		for (Plato pla : bar) {
            cadena = "{nombre: \"" + pla.getNombre() + "\", tipo: \"" + pla.getTipo() + "\", calorias: \""  + pla.getCalorias() + "\", tipo: \"" + pla.getPrecio() + "}";
            bw.write(cadena);
            bw.newLine();
		}
		
	}
	
	private static void InsertarPlato() {
		
		Scanner leerCadena = new Scanner(System.in);
		Scanner leerNumero = new Scanner(System.in);
		
		String nom = "";
		String tip = "";
		int cal = 0;
		double pre;
		
		System.out.print("Nombre:");
		nom = leerCadena.nextLine();
		System.out.print("\ntipo:");
		tip = leerCadena.nextLine();
		System.out.print("\nCalorias:");
		cal = leerNumero.nextInt();
		System.out.print("\nprecio:");
		pre = leerNumero.nextDouble();
		
		bar.add(new Plato(nom,tip,cal,pre));
		
	}
	
	private static void BorrarPlato() {
		boolean flag = false;
		Scanner leerCadena = new Scanner(System.in);
		String cadena ="";
		
		System.out.println("Elige el nombre del plato a borrar");
		cadena = leerCadena.nextLine();
		
		Iterator itr = bar.iterator();           //todavia no coge ningun elemento
        while (itr.hasNext()) {                 //mientras queden elementos que coger
            Plato pla =(Plato) itr.next();        //cogemos el siguiente valor
            if(cadena.equals(pla.getNombre())) {
            	itr.remove();
            	flag = true;
            	
            }
        }                  
		
		if (flag == true) System.out.println("Plato Borrado");
		else System.out.println("No existe el plato");
		
	}
	
	private static void MenuSemanal() {
		
		int primeros = 0;
		int segundos = 0;
		int postres = 0;
		
		
		//comprobamos que al menos hayan 5 primeros, 5 segundos y 5 postres
		Iterator itr = bar.iterator();           //todavia no coge ningun elemento
        while (itr.hasNext()) {                 //mientras queden elementos que coger
            Plato pla =(Plato) itr.next();        //cogemos el siguiente valor
            if(pla.getTipo().equals("1")) primeros++;
            if(pla.getTipo().equals("2")) segundos++;
            if(pla.getTipo().equals("p")) postres++;
        }
        
        if(primeros >= 5 && segundos >= 5 && postres >= 5) {
        	
        	
        	
        	
        } else System.out.println("Faltan platos");
        
        
		
	}
	
	private static void OrdenarPrecio() {
		
		ArrayList<Plato> precio = new ArrayList<>();
		precio.addAll(bar);
		
		precio.sort(
				new Comparator<Plato>() {
					public int compare(Plato p1, Plato p2){
	                    if(p1.getPrecio() < p2.getPrecio()) return 1;
	                    if(p1.getPrecio() > p2.getPrecio()) return -1;
	                    return 0;
	                }
	            }
	        );
		
		for(Plato p: precio) {
			System.out.print("Nombre: " + p.getNombre() + "  ,");
			System.out.print("tipo: " + p.getTipo() + "  ,");
			System.out.print("calorias: " + p.getCalorias() + "  ,");
			System.out.println("Precio: " + p.getPrecio() + "  ");
		}
		
	}
	
	private static void OrdenarCalorias() {
		
		ArrayList<Plato> precio = new ArrayList<>();
		precio.addAll(bar);
		
		precio.sort(
				new Comparator<Plato>() {
					public int compare(Plato p1, Plato p2){
	                    if(p1.getCalorias() < p2.getCalorias()) return 1;
	                    if(p1.getCalorias() > p2.getCalorias()) return -1;
	                    return 0;
	                }
	            }
	        );
		
		for(Plato p: precio) {
			System.out.print("Nombre: " + p.getNombre() + "  ,");
			System.out.print("tipo: " + p.getTipo() + "  ,");
			System.out.print("calorias: " + p.getCalorias() + "  ,");
			System.out.println("Precio: " + p.getPrecio() + "  ");
		}
		
	}
		
}
	


class Plato implements Comparable<Plato> {
	
	//variables miembro
	private String nombre;
	private String tipo;
	private int calorias;
	private double precio;
	
	//constructor
	public Plato(String nom, String tip, int cal, double pre ) {
		this.nombre = nom;
		this.tipo = tip;
		this.calorias = cal;
		this.precio = pre;
	}
	
	//getters
	public String getNombre() {return this.nombre;}
	public String getTipo() {return this.tipo;}
	public int getCalorias() {return this.calorias;}
	public double getPrecio() {return this.precio;}
	
	//setters
	public void setNombre(String nom) {this.nombre=nom;}
	public void setTipo(String tip) {this.tipo = tip;}
	public void setCalorias(int cal) {this.calorias = cal;}
	public void setPrecio(double pre) {this.precio = pre;}
	
	@Override
	public int compareTo(Plato p) {
		int valor = this.getNombre().compareTo(p.getNombre());
		return valor;
	}
	
}
