package brisca;

public class JugadorBrisca {
	
	//variables del objeto
	private int puntos; //puntos de cada jugador
	CartaBrisca[] mano; //mano de cartas de cada jugador
	
	//**********************************CONSTRUCTOR ZONE*****************************************************
	
	//metodo constructor de cada jugador. No recibe parametros
	//a cada jugador se le pone el contador de puntos a cero
	//y se le crea un array de cartas para las 3 cartas de su mano
	public JugadorBrisca() {
		this.puntos = 0;
		this.mano = new CartaBrisca[3];
	}
	
	//******************************************GETTERS ZONE**************************************************
	
	//metodo getter para obtener la puntuacion de cada jugador
	public int getPuntos() {
		return this.puntos;
	}
	
	//metodo getter para obtner la carta de la mano´
	//recibe la posicion y devuelve la carta
	public CartaBrisca getMano(int pos) {
		CartaBrisca unaCarta = this.mano[pos];
		return unaCarta;
	}
	
	//******************************************SETTERS ZONE*************************************
	
	//metodo setter para recibir una carta
	public void setMano(int pos, CartaBrisca carta) {
		this.mano[pos] = carta;
	}
	
	//metodo setter para sumar puntos
		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}
		
		//******************************************METODOS Y SUBRUTINAS ZONE*************************************
		
	//metodo para saber si los jugadores tiene cartas en sus manos
	public boolean tieneCartas() {
		
		boolean resultado = false;
		
		for(int i = 0; i <= 2; i++) {
			if(this.mano[i] != null) { //si al menos una carta no es null seguimos jugando
				resultado = true;
			}
		}
		
		return resultado;
	}
}
