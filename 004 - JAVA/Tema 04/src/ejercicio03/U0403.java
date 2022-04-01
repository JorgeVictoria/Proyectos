package ejercicio03;

public class U0403 {

	public static void main(String[] args) {
		
		//creamos numeros enteros entre el 0 y el 20
		System.out.println("Construir el Gna1 con Gna(10L) y generar 10 aleatorios enteros entre 0 y 20...");
		
		//creamos el objeto
		Gna gna1 = new Gna(10L);
		
		for(int i = 0; i < 20; i++) {
			int numero = Gna.valor(0, 20);
			System.out.print(numero + " ");
		}
		
		//creamos numeros long entre el 0 y 20
		System.out.println("\nConstruir el Gna2 con Gna() generar 20 aleatorios long entre 0 y 20...");
		//primero cogemos el conjunto fecha hora
		Gna.cogerHora();
		//construimos el objeto pasando como semilla el conjunto fecha hora
		Gna gna2 = new Gna();
		for(int i = 0; i < 20; i++) {
			Gna.valor(0L, 20L);
			System.out.print(" ");
		}
		
		//creamos la cadena aleatoria, ¿password?
		System.out.println("\nUsa Gna1 para generar 10 cadenas aleatorias llamando a valor(10, \"\")");
		//usamos gna1, pero vamos a probar pasandole la semilla de G2 para que cree cadenas diferentes
		gna1 = new Gna(gna2.semilla);
		String password = Gna.valor(10,"");
		System.out.print(password);
	}

}
