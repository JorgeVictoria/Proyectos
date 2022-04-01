package brisca;

//declararamos la clase inmutable
public class Carta {
	
  //variables globales
  private final static int BASTOS = 1;
  private final static int COPAS = 2;
  private final static int ESPADAS = 3;
  private final static int OROS = 4;
  
  //variables del objeto
   private int valor;
   private int palo;

  //**********************************CONSTRUCTOR ZONE*****************************************************

  //recibe 2 valores enteros, uno para el valor y otro para el palo
  public Carta(int val, int pal){
      //comprobamos que el valor esta entre 1 y 7 o entre 10 y 12
      if(val < 1 || val == 8 || val == 9 || val > 12){
          throw new IllegalArgumentException("El valor de la carta no es valido");
      } else this.valor = val;

      //comprobamos que el palo tiene un valor entre 1 y 4, ya que son 4 los palos de la baraja
      if(pal != BASTOS && pal != COPAS && pal != ESPADAS && pal != OROS){
          throw new IllegalArgumentException("El palo de la carta no es correcto");
      } else this.palo = pal;
  }

  //******************************************GETTERS ZONE**************************************************

  //este getter devuelve el valor de la carta
  public int getValor(){
      return this.valor;
  }

  //este getter devuelve el palo de la carta
  public int getPalo(){
      return this.palo;
  }

  //*******************************************METODOS Y SUBRUTINAS ZONE************************************

  //metodo que sobresecibre el metodo toString. Devuelve una cadena con la carta
  //la van a utilizar los objetos, no es estatica
  @Override
  public String toString(){

      //variables locales
      String cadena = "["; //variable sobre la que vamos a construir la cadena con la carta
      
      //primero le damos el valor. Recordamos que el 1, 10, 11, 12 son figuras.
      switch(this.valor){
          case 1:  cadena = cadena + "AS de ";
                   break;
          case 2: 
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:  cadena = cadena + this.valor + " de ";
                   break;
          case 10: cadena = cadena + "SOTA de ";
                   break;
          case 11: cadena = cadena + "CABALLO de ";
                   break;
          case 12: cadena = cadena + "REY de ";
      } //fin del switch

      //a continuacion le damos el palo
      switch(this.palo){
          case 1: cadena = cadena + "BASTOS]";
          		  break;
          case 2: cadena = cadena + "COPAS]";
                  break;
          case 3: cadena = cadena + "ESPADAS]";
                  break;
          case 4: cadena = cadena + "OROS]";
                  break;
      } //fin del switch

      //devolvemos la cadena
      return cadena;
      
  } //fin de toString

  
} //fin de la clase
