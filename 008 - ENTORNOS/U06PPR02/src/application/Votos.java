package application;

public class Votos {
	
	//variables miembro
	//creamos un array donde almacenamos los votos de cada plataforma
	//pos0 : votos de Netflix
	//pos1 : votos de HBO
	//pos2 : votos de Disney+
	//pos3 : votos de otras Plataformas
	private int[] totalVotos = new int[4];  
	
	//constructor. Incluimos algunos votos para no partir de cero
	public Votos() {
		this.totalVotos[0] = 20;
		this.totalVotos[1] = 15;
		this.totalVotos[2] = 12;
		this.totalVotos[3] = 3;
	}

	//getter
	public int[] getTotalVotos() {
		return totalVotos;
	}
	
	//setter
	public void setTotalVotos(int pos) {
		this.totalVotos[pos] = this.totalVotos[pos] + 1;
	}
	
}
