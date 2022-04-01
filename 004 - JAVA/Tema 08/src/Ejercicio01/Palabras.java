package Ejercicio01;

public class Palabras {
	
	//variables miembro
	private String palabra;
	private int contador;
	
	//constructor
	public Palabras (String p, int c) {
		this.palabra = p;
		this.contador = c;
	}
	
	//getters
	public String getPalabra() { return this.palabra; }
	public int getContador() { return this.contador; }
	
	//setter
	public void setPalabra(String p) { this.palabra = p; }
	public void setContador(int c) { this.contador = contador+c;}
	
	/*@Override
	public boolean equals(Object obj) {
		if(this == obj) return true; //son el mismo objeto. No podemos crear nada
		if(obj == null) return true; //Objeto null. No podemos crear nada
		if ( this.getClass() != obj.getClass() ) return true; // Objeto de distinta clase. No podemos crear nada
		Palabras otra = (Palabras)obj;
		if(otra.palabra == null) return true;	//atributo palabra vacio. No creamos nada
		if(this.palabra.equals(otra.palabra)) {
			this.setContador(1); //palabra encontrada
			return true;
		}
		return false;
		
	}*/

}
