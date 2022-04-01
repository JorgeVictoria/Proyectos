package examen;

public class IndiceMuni {
	
	//variables globales
	String municipio;
	long posicion;
	
	//constructor
	IndiceMuni(String muni, int pos){
		this.municipio = muni;
		this.posicion = pos;
	}
	
	public String toString() {
		String cadena = "Elemento " + this.posicion + " Pueblo: " + this.municipio;
		
		return cadena;
		
		
	}
	
	public int compareTo(IndiceMuni p) {
		if(this.municipio.compareTo(p.municipio) < 0) return -1;
		else if (this.municipio.compareTo(p.municipio) > 0) return 1;
		else return 0;
	}

}
