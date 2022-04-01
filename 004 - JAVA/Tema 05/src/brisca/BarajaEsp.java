package brisca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BarajaEsp {
	
	//variables de objeto
	private CartaBrisca[] baraja; //array de cartas de la brisca
	private int cuantasCartas; //almacena el numero de cartas que quedan en el mazo

	//**********************************CONSTRUCTOR ZONE*****************************************************
	
	//metodo constructor que inicializa un array de tipo CartaBrisca
	//dicho array tiene 40 posiciones, que equivalen al numero de cartas de la brisca
	public BarajaEsp() {
		//creamos una baraja con 40 cartas
		this.baraja = new CartaBrisca[40];
		
		//rellenamos la baraja
		this.rellenarBaraja();
		
		//inicializamos la variable que calcula cuantas cartas quedan por jugar
		this.cuantasCartas = baraja.length;
	}
	
	//******************************************GETTERS ZONE**************************************************
	
	//metodo getter que sirve para saber el numero de cartas que hay en el mazo
	//la usaremos desde el metodo hayCarta()
	public int getCuantasCartas() {
		return cuantasCartas;
	}
	
	//******************************************METODOS Y SUBRUTINAS ZONE*************************************
	
	//subrutina para rellenar la baraja con objetos cartaBrisca
	public void rellenarBaraja() {
		for(int i = 1; i <= this.baraja.length; i++) {
			//BASTOS
			if( i <= 7) this.baraja[i-1] = new CartaBrisca(i,1);
			else if(i == 8) this.baraja[i-1] = new CartaBrisca(10, 1);
			else if(i == 9) this.baraja[i-1] = new CartaBrisca(11, 1);
			else if(i == 10) this.baraja[i-1] = new CartaBrisca(12, 1);
			//COPAS
			else if( i <= 17) this.baraja[i-1] = new CartaBrisca(i-10,2);
			else if(i == 18) this.baraja[i-1] = new CartaBrisca(10, 2);
			else if(i == 19) this.baraja[i-1] = new CartaBrisca(11, 2);
			else if(i == 20) this.baraja[i-1] = new CartaBrisca(12, 2);
			//ESPADAS
			else if( i <= 27) this.baraja[i-1] = new CartaBrisca(i-20, 3);
			else if(i == 28) this.baraja[i-1] = new CartaBrisca(10, 3);
			else if(i == 29) this.baraja[i-1] = new CartaBrisca(11, 3);
			else if(i == 30) this.baraja[i-1] = new CartaBrisca(12, 3);
			//OROS
			else if( i <= 37) this.baraja[i-1] = new CartaBrisca(i-30, 4);
			else if(i == 38) this.baraja[i-1] = new CartaBrisca(10, 4);
			else if(i == 39) this.baraja[i-1] = new CartaBrisca(11, 4);
			else if(i == 40) this.baraja[i-1] = new CartaBrisca(12, 4);
			}
		}
		
		//subrutina para barajar las cartas
		public void barajar() {
			
			//creamos una lista a la que le pasamos el array
			List<CartaBrisca> lista = Arrays.asList(this.baraja);
			
			//este metodo permuta aleatoriamente los elementos de la lista
	        Collections.shuffle(lista);

	        //convertimos esa lista en el array de la baraja. Ya tenemos las cartas barajadas
	        lista.toArray(this.baraja);
		}
		
		//subrutina para ver si quedan cartas en el mazo
		public boolean hayCartas() {
			
			if (this.getCuantasCartas() > 0) return true;
			else return false;
		}
		
		//metodo para coger carta del mazo
		public CartaBrisca sacaCartas() {
			
			//variable local que se devuelve
			CartaBrisca unaCarta;
			
			//si no hay cartas devuelve excepcion
			if (this.getCuantasCartas() == 0) throw new IllegalArgumentException("No quedan cartas en el mazo");
			
			unaCarta = this.baraja[this.getCuantasCartas()-1]; //si hay cartas, cogemos la de arriba
			
			this.cuantasCartas--; //descontamos una carta del mazo
			
			return unaCarta; //devolvemos la carta
			
		}
		
		//metodo que devuelve una carta
		//se utiliza para ver la carta de muestra
		public CartaBrisca dimeCarta(int i) {
			
			//variables locales
			CartaBrisca unaCarta = null;
			
			//si el parametro i es mayor de las cartas que quedan o
			//no quedan cartas en el mazo, el objeto es null
			if (i > this.getCuantasCartas() || this.getCuantasCartas() == 0) unaCarta = null;
			
			//en caso contrario creamos una carta con el valor y el palo de la posicion que recibimos
			//podria valer para ver una carta del mazo en una posicion x
			//pero la usaremos para ver la carta de la posicion 0 o  muestra
			else unaCarta = new CartaBrisca(this.baraja[i].getValor(),this.baraja[i].getPalo());
			
			//devolvemos la carta
			return unaCarta;
			
		}
		
		//metodo para imprimir la baraja. Se usa el metodo toString de la clase carta 
			@Override
			public String toString() {
				
				String cadena = "";
				
				for(int i = 0; i < this.baraja.length; i++) {
					cadena = cadena + this.baraja[i].toString() ;
				}
				
				return cadena;
				
			}

}
