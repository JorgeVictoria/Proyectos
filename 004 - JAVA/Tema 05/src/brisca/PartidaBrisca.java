package brisca;

import java.util.Scanner;

public class PartidaBrisca {
	
	//variables globales
	 private static Brisca partidaBrisca;
	 private static Scanner stdin = new Scanner(System.in);
	 private static int tirada1; //almacena la posicion en la mano de la carta que se ha tirado primero
	 private static int tirada2; //almacena la posicion en la mano de la carta que se ha tirado despues

	public static void main(String[] args) {
		
		//creamos la partida de brisca
		partidaBrisca = new Brisca();
		
		//se baraja el mazo de cartas
		partidaBrisca.baraja.barajar();
		
		//se reparten las cartas
		for(int i = 0; i < 3; i++) {
			partidaBrisca.humano.setMano(i, partidaBrisca.baraja.sacaCartas());
			partidaBrisca.computer.setMano(i, partidaBrisca.baraja.sacaCartas());
		}
		
		//se establece la carta del fondo
		partidaBrisca.muestra = partidaBrisca.baraja.dimeCarta(0);
		
		//se establece el turno
		partidaBrisca.setTurno((int)(Math.random() * 2) + 1);
		
		//empezamos a jugar
		//mientras los jugadores tengan cartas
		while(partidaBrisca.humano.tieneCartas() == true || partidaBrisca.computer.tieneCartas() == true) {
			
			//mostramos quien juega
			if(partidaBrisca.getTurno() == 1) System.out.println ("Juega el Humano");
			else System.out.println("Juega Computer");
			
			//mostramos la carta de fondo
			System.out.println();
			System.out.println("Carta Fondo:" + partidaBrisca.muestra);
			
			//************************turno de jugada humano***********************************//
			if(partidaBrisca.getTurno()==1) {
				
				//mostramos las cartas de nuestra mano
				//si hay carta en la posicion, las mostramos, sino mostramos null
				mostrarMano();
				
				//elegimos carta
				tirada1 = eligeCarta(1);
				
				//mostramos la carta que hemos tirado y su valor
				verCarta(tirada1,1);
				
				//ahora elige carta computer
				tirada2 = eligeCarta(2);
				
				//mostramos la carta que ha tirado computer y su valor
				verCarta(tirada2,2);
				
				//contar puntos
				//primera opcion
				
				//si el palo de ambas cartas es igual
				if(partidaBrisca.computer.mano[tirada2].getPalo()==(partidaBrisca.humano.mano[tirada1].getPalo())){
					
					//si la primera carta es 3 y la segunda carta no es 1, gana el humano
					if(partidaBrisca.humano.mano[tirada1].getValor() == 3 && (partidaBrisca.computer.mano[tirada2].getValor()!=1)) {
						partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
					}
					
					//si la carta de computer es menor en puntos que la carta del humano
					 else if( partidaBrisca.computer.mano[tirada2].getPuntos() < partidaBrisca.humano.mano[tirada1].getPuntos()) {
						 partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
					
					//si la carta de computer es mayor en puntos que la carta del humano
					 }  else {
						 partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
						
						 //gana el jugador 2, cambiamos el turno
						partidaBrisca.setTurno(2);
					}
					//robamos carta
						robarCarta(1);
						
				} //fin de la primera opcion
				
				//segunda opcion
				//el humano tira el palo de la muestra y computer no
				else if(partidaBrisca.humano.mano[tirada1].getPalo() == partidaBrisca.muestra.getPalo() && partidaBrisca.computer.mano[tirada2].getPalo() != partidaBrisca.muestra.getPalo()) {
					partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
					//robamos carta
					robarCarta(1);
				} //fin de la segunda opcion
				
				//tercera opcion
				//el humano y computer tiran una carta que no es del palo de la muestra
				else if(partidaBrisca.humano.mano[tirada1].getPalo() != partidaBrisca.muestra.getPalo() && partidaBrisca.computer.mano[tirada2].getPalo() != partidaBrisca.muestra.getPalo()) {
					partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
					//robamos carta
					robarCarta(1);
				} //fin de la tercera opcion
				
				//cuarta opcion
				else {
					partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.humano.mano[tirada1].getPuntos() + partidaBrisca.computer.mano[tirada2].getPuntos());
					 //gana computer, cambiamos el turno
					partidaBrisca.setTurno(2);
					//robamos carta
					robarCarta(1);
				} //fin de la cuarta opcion
				
			} //fin del turno del humano
			
			//************************turno de jugada computer***********************************//
			else {
				
				//ahora elige carta computer
				tirada1 = eligeCarta(2);
				
				//mostramos la carta que ha tirado computer y su valor
				verCarta(tirada1,2);
				
				//mostramos las cartas de nuestra mano
				//si hay carta en la posicion, las mostramos, sino mostramos null
				mostrarMano();
				
				//elegimos carta
				tirada2 = eligeCarta(1);
				
				//mostramos la carta que hemos tirado y su valor
				verCarta(tirada2,1);
				
				//contar puntos
				//primera opcion
				
				//si el palo de ambas cartas es igual
				if(partidaBrisca.humano.mano[tirada2].getPalo()==(partidaBrisca.computer.mano[tirada1].getPalo())){
					
					//si la primera carta es 3 y la segunda carta no es 1, gana computer
					if(partidaBrisca.computer.mano[tirada1].getValor() == 3 && (partidaBrisca.humano.mano[tirada2].getValor()!=1)) {
						partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
					}
					
					//si la carta de computer es menor en puntos que la carta del humano
					 else if( partidaBrisca.humano.mano[tirada2].getPuntos() < partidaBrisca.computer.mano[tirada1].getPuntos()) {
						 partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
					
					//si la carta de computer es mayor en puntos que la carta del humano
					 }  else {
						 partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
						
						 //gana el jugador humano, cambiamos el turno
						partidaBrisca.setTurno(1);
					}
					//robamos carta
						robarCarta(2);
						
				} //fin de la primera opcion
				
				//segunda opcion
				//computer tira el palo de la muestra y el humano no
				else if(partidaBrisca.computer.mano[tirada1].getPalo() == partidaBrisca.muestra.getPalo() && partidaBrisca.humano.mano[tirada2].getPalo() != partidaBrisca.muestra.getPalo()) {
					partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
					//robamos carta
					robarCarta(2);
				} //fin de la segunda opcion
				
				//tercera opcion
				//el humano y computer tiran una carta que no es del palo de la muestra
				else if(partidaBrisca.computer.mano[tirada1].getPalo() != partidaBrisca.muestra.getPalo() && partidaBrisca.humano.mano[tirada2].getPalo() != partidaBrisca.muestra.getPalo()) {
					partidaBrisca.computer.setPuntos(partidaBrisca.computer.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
					//robamos carta
					robarCarta(2);
				} //fin de la tercera opcion
				
				//cuarta opcion
				else {
					partidaBrisca.humano.setPuntos(partidaBrisca.humano.getPuntos() + partidaBrisca.computer.mano[tirada1].getPuntos() + partidaBrisca.humano.mano[tirada2].getPuntos());
					 //gana computer, cambiamos el turno
					partidaBrisca.setTurno(1);
					//robamos carta
					robarCarta(2);
				} //fin de la cuarta opcion
				
			} //fin del turno del humano
			
			//mostramos marcador por pantalla
			System.out.println("-----------------------------------------------------");
			System.out.println("Jugador 1: " + partidaBrisca.humano.getPuntos() + " puntos ||  Jugador 2: " + partidaBrisca.computer.getPuntos() + " puntos");
			System.out.println("-----------------------------------------------------");
			
			pressAnyKeyToContinue();
            
			
		} //fin del while
		
		System.out.println("*******************GAME OVER********************");
		
	} //fin del main
	
	//******************************************METODOS Y SUBRUTINAS ZONE*************************************
	
	//subrutina para mostrar la jugada del humano
	//
	public static void mostrarMano() {
		System.out.println();
		System.out.print("Tus cartas:");
		for(int i = 0; i <= 2; i++) {
			if ( partidaBrisca.humano.getMano(i) != null) { 
				System.out.print( " " + (i+1) + "." + partidaBrisca.humano.getMano(i).toString());
			}
			else System.out.print("[ Null ]");
		}
	} //fin mostrarMano
	
	//subrutina para preguntar al humano que carta elige
	public static int eligeCarta(int jugador) {
		//variables locales
		int tirada=0;  //almacena la posicion de la mano
		
		if(jugador == 1) { //si el jugador que elige es el humano
			System.out.println();
			System.out.print("Elige una carta (1,2,3):");
			tirada = stdin.nextInt();
			tirada = tirada - 1; //se reduce en uno para adapatar a la pos del array de mano
			
		} else { //si el jugador que elige es computer
			//comprobamos que no tenga 2 cartas null, y si las tiene forzamos a que elija la que queda
			if(partidaBrisca.computer.mano[0] == null && partidaBrisca.computer.mano[1] == null) tirada = 2;
			else if(partidaBrisca.computer.mano[0] == null && partidaBrisca.computer.mano[2] == null) tirada = 1 ;
			else if(partidaBrisca.computer.mano[1] == null && partidaBrisca.computer.mano[2] == null) tirada = 0;
			//puede ocurrir que solo haya una carta null y el math random elija la null, forzamos la carta
			else if(partidaBrisca.computer.mano[0] == null || partidaBrisca.computer.mano[1] == null || partidaBrisca.computer.mano[2] == null) {
				for(int i = 0; i < 2; i++) {
					if(partidaBrisca.computer.mano[i] != null ) {
						tirada = i;
						break;
					}
						
				}
			}
			else tirada =(int)(Math.random()*3);
		}
		
		return tirada;
	}
	
	//subrutina para ver la carta tirada por el humano y su valor en puntos
	public static void verCarta(int tirada,int jugador) {
		if(jugador == 1) { //vemos la carta del humano
		System.out.println("\nHas tirado el " + partidaBrisca.humano.mano[tirada].toString());
		System.out.println("La carta vale " + partidaBrisca.humano.mano[tirada].getPuntos() + " puntos.");
		} else { //vemos la carta de computer
			System.out.println("\nComputer has tirado el " + partidaBrisca.computer.mano[tirada].toString());
			System.out.println("La carta vale " + partidaBrisca.computer.mano[tirada].getPuntos() + " puntos.");
		}
	}
	
	//subrutina para robar carta
	public static void robarCarta(int jugador) {
		if(jugador == 1) { //si el turno era del humano
			if(partidaBrisca.baraja.hayCartas()) { //si quedan cartas robamos
				partidaBrisca.humano.mano[tirada1] = partidaBrisca.baraja.sacaCartas();
				partidaBrisca.computer.mano[tirada2] = partidaBrisca.baraja.sacaCartas();
			} else { //si no quedan cartas, dejamos la posicion de la mano a null
				partidaBrisca.humano.mano[tirada1] =null;
				partidaBrisca.computer.mano[tirada2] = null;
			}
		} else //el turno era de computer
			if(partidaBrisca.baraja.hayCartas()) { //si quedan cartas robamos
				partidaBrisca.computer.mano[tirada1] = partidaBrisca.baraja.sacaCartas();
				partidaBrisca.humano.mano[tirada2] = partidaBrisca.baraja.sacaCartas();
			} else { //si no quedan cartas, dejamos la posicion de la mano a null
				partidaBrisca.computer.mano[tirada1] =null;
				partidaBrisca.humano.mano[tirada2] = null;
			}
	}
	
	static public void pressAnyKeyToContinue(){
	    String seguir;
	    Scanner teclado = new Scanner(System.in);
	    System.out.println("Press Enter key to continue...");
	    try
	    {
	        seguir = teclado.nextLine();
	    }
	    catch(Exception e)
	    {}
	}

}
