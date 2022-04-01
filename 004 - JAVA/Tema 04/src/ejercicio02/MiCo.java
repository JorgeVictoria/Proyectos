package ejercicio02;

public class MiCo {
	
	//constantes
	static final String TEXTONEGRO = "\033[30m";
	static final String TEXTOROJO = "\033[31m";
	static final String TEXTOVERDE = "\033[32m";
	static final String TEXTOMARRON = "\033[33m";
	static final String TEXTOAZUL = "\033[34m";
	static final String TEXTOPURPURA = "\033[35m";
	static final String TEXTOCYAN = "\033[36m";
	static final String TEXTOGRIS = "\033[37m";
	static final String TEXTOGRISOSCURO = "\033[1;30m";
	static final String TEXTOROJOCLARO = "\033[1;31m";
	static final String TEXTOVERDECLARO = "\033[1;32m";
	static final String TEXTOMARRONCLARO = "\033[1;33m";
	static final String TEXTOAZULCLARO = "\033[1;34m";
	static final String TEXTOPURPURACLARO = "\033[1;35m";
	static final String TEXTOCYANCLARO = "\033[1;36m";
	static final String TEXTOBLANCO = "\033[1;37m";
	static final String FONDONEGRO = "\033[40m";
	static final String FONDOROJO = "\033[41m";
	static final String FONDOVERDE = "\033[42m";
	static final String FONDOMARRON = "\033[43m";
	static final String FONDOAZUL = "\033[44m";
	static final String FONDOPURPURA = "\033[45m";
	static final String FONDOCYAN = "\033[46m";
	static final String FONDOGRIS = "\033[47m";
	
	//creamos dos array con la lista de colores, para su posterior busqueda
	static String[] colores = {"TEXTONEGRO", "TEXTOROJO", "TEXTOVERDE", "TEXTOMARRON", "TEXTOAZUL", "TEXTOPURPURA", "TEXTOCYAN", "TEXTOGRIS", 
						"TEXTOGRISOSCURO", "TEXTOROJOCLARO", "TEXTOVERDECLARO", "TEXTOMARRONCLARO", "TEXTOAZULCLARO", "TEXTOPURPURACLARO", "TEXTOCYANCLARO", "TEXTOBLANCO"};
	static String[] fondos = {"FONDONEGRO", "FONDOROJO", "FONDOVERDE", "FONDOMARRON", "FONDOAZUL", "FONDOPURPURA", "FONDOCYAN", "FONDOGRIS"};
	
	//SUBRUTINAS
	
	public static void cls() {  //borrado de pantalla
		
		System.out.println("\033[2J");
		
	}
	
	public static void setColor(String texto, String fondo){
		
		//variables locales
		boolean existeFondo = false;
		boolean existeColor = false;
		String cadena;
		
		//comprobamos si los colores existen
		cadena = texto.toUpperCase();
		for (int i = 0; i < colores.length; i++){
			if(colores[i].equals(cadena)) existeColor = true;
		}
		
		//comprobamos si los fondos existen
		cadena = fondo.toUpperCase();
		for (int i = 0; i < fondos.length; i++){
			if(fondos[i].equals(cadena)) existeFondo = true;
		}
		
		//mensaje de error en caso de que los colores no existan
		assert (existeColor == true && existeFondo == true) : "error en la seleccion de colores";
		
		//Si los colores solicitados existen, se usan las constantes
		cadena = texto.toUpperCase();
		switch(cadena){
			case "TEXTOAZUL": System.out.println(TEXTOAZUL);
				 break;
			case "TEXTOAZULCLARO": System.out.println(TEXTOAZULCLARO);
				 break;
			case "TEXTOBLANCO": System.out.println(TEXTOBLANCO);
				 break;
			case "TEXTOCYAN": System.out.println(TEXTOCYAN);
				 break;
			case "TEXTOCYANCLARO": System.out.println(TEXTOCYANCLARO);
				 break;
			case "TEXTOGRIS": System.out.println(TEXTOGRIS);
				 break;
			case "TEXTOGRISOSCURO": System.out.println(TEXTOGRISOSCURO);
				 break;
			case "TEXTOMARRON": System.out.println(TEXTOMARRON);
				 break;
			case "TEXTOMARRONCLARO": System.out.println(TEXTOMARRONCLARO);
				 break;
			case "TEXTONEGRO": System.out.println(TEXTONEGRO);
				 break;
			case "TEXTOPURPURA": System.out.println(TEXTOPURPURA);
				 break;
			case "TEXTOPURPURACLARO": System.out.println(TEXTOPURPURACLARO);
				 break;
			case "TEXTOROJO": System.out.println(TEXTOROJO);
				 break;
			case "TEXTOROJOCLARO": System.out.println(TEXTOROJOCLARO);
				 break;
			case "TEXTOVERDE": System.out.println(TEXTOVERDE);
				 break;
			case "TEXTOVERDECLARO": System.out.println(TEXTOVERDECLARO);
				 break;
		}
		
		cadena = fondo.toUpperCase();
		switch(cadena){
		case "FONDOAZUL": System.out.println(FONDOAZUL);
						  break;
		case "FONDOCYAN": System.out.println(FONDOCYAN);
						  break;
		case "FONDOGRIS": System.out.println(FONDOGRIS);
						  break;
		case "FONDOMARRON":System.out.println(FONDOMARRON);
						  break;
		case "FONDONEGRO":System.out.println(FONDONEGRO);
						  break;
		case "FONDOPURPURA":System.out.println(FONDOPURPURA);
						  break;
		case "FONDOROJO":System.out.println(FONDOROJO);
						  break;
		case "FONDOVERDE":System.out.println(FONDOVERDE);
						  break;
		}
	}
	
	//subrutina para cambiar de posicion el cursor
	public static void setCursor(int columna, int fila){
		
		//mensaje de error si los dos datos no son correctos
		assert (columna >= 0 && fila >= 0) : "error en la seleccion de coordenadas";
		
		System.out.println("\033[" + fila + ";" + columna + "H");
			
	}
	
	//expansion de subrutina para recoger los colores con valores numericos(
	public static void setColor (int primerValor, int segundoValor, int tercerValor){
		
		assert primerValor == 0 || primerValor == 1 : "primer valor no valido";
		assert segundoValor >= 30 && segundoValor <= 37 : "segundo valor no valido";
		assert tercerValor >= 40 && tercerValor <= 47 : "tercer valor no valido";

		System.out.println("\033[" + primerValor + ";" + segundoValor + ";"  + tercerValor + "m");
		
	}
	
	//expansion de subrutina oara cambiar solo el color del texto
	public static void setColor (int primerValor, int segundoValor){
	
		assert primerValor == 0 || primerValor == 1 : "primer valor no valido";
		assert segundoValor >= 30 && segundoValor <= 37 : "segundo valor no valido";
		
		System.out.println("\033[" + primerValor + ";" + segundoValor + "m");
	}
	
	//expansion de subrutina oara cambiar solo el color de fondo
	public static void setColor (int tercerValor){
	
		assert tercerValor >= 40 && tercerValor <= 47 : "valor no valido";

		System.out.println("\033[" + tercerValor + "m");
		
	}

}
