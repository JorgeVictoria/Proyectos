package brisca;

public class Brisca {
	
	//variables locales
	JugadorBrisca humano;
	JugadorBrisca computer;
	private int turno;
	BarajaEsp baraja;
    CartaBrisca muestra;

	//**********************************CONSTRUCTOR ZONE*****************************************************
	public Brisca() {
		
		//creamos el jugador humano
		this.humano = new JugadorBrisca();
		
		//creamos el jugador computer
		this.computer = new JugadorBrisca();
		
		//se crea la baraja de cartas
		this.baraja = new BarajaEsp();
		
	}
	
	//******************************************GETTERS ZONE**************************************************
	
	//metodo getter que devuelve el turno
	public int getTurno() {
		return this.turno;
	}
	
	//******************************************SETTERS ZONE**************************************************
	
	//metodo setter para establecer el turno
	public void setTurno(int unTurno) {
		this.turno = unTurno;
	}
	
	

}
