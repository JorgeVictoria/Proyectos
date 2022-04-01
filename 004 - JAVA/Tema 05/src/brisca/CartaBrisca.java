package brisca;

public class CartaBrisca extends Carta {
	
	//variables de los objetos
	private int puntos; //valor en puntos de la carta

	//**********************************CONSTRUCTOR ZONE*****************************************************
	//metodo constructor que recibe 2 valores: el valor y el palo de la carta
	//llamamos al constructor padre para que cree el objeto
	//a continuacion le damos los puntos que le pertenezcan segun su valor
	public CartaBrisca(int val, int pal) {
		//creamos el objeto llamando a su padre
		super(val,pal);
		
		//le damos los puntos a la carta
		setPuntos();
		
	}//fin del constructor
	
	//******************************************GETTERS ZONE**************************************************
	
	//metodo getter que devuelve los puntos de la carta
	public int getPuntos() {
		return this.puntos;
	}
	
	//******************************************SETTERS ZONE**************************************************
	
	//metodo setter para calcular y darle a la carta su valor en puntos
	private void setPuntos() {
		
		//con el metodo switch, le damos los puntos a la carta
		switch(this.getValor()) {
		case 1: this.puntos = 11;
		        break;
		case 3: this.puntos = 10;
		        break;
		case 10: this.puntos = 2;
		         break;
		case 11: this.puntos = 3;
		         break;
		case 12: this.puntos = 4;
		         break;
		default: this.puntos = 0;
		}
	}
	

}
