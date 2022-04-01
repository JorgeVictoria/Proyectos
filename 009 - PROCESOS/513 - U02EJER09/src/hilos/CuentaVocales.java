package hilos;

public class CuentaVocales extends Thread {
	
	//variables locales
	private static int totalVocales = 0;
	private static Frase frase = new Frase("aAeEx");
	
	//variables miembro
	private String nombre;		//nombre del hilo
	private char letra;			//letra que busca cada hilo
	private int total;			//total de las letras que busca ese hilo

	//constructor
	public CuentaVocales(String nombre, char letra) {
		this.nombre = nombre;
		this.letra = letra;
		this.total=0;
	}

	//getters
	public String getNombre() {
		return nombre;
	}
	
	public char getLetra() {
		return letra;
	}
	
	public int getTotal() {
		return total;
	}
	
	
	/**
	 * Metodo para contar vocales de un objeto frase. Si coincide con la vocal, incrementa el contador
	 * @param posicion la posicion de la frase que queremos comparar
	 * @throws InterruptedException
	 */
	private synchronized void contarVocales(int posicion) throws InterruptedException{
		//comparamos la letra de nuestro hilo con la letra de la frase en una posicion concreta
		//si coincide, lo indicamos y aumentamos el contador de vocales
		//si no lo indicamos
		//se aplica un sleep de 1 segundo
		if(Character.toString(this.letra).equals(Character.toString(frase.getFrase().charAt(posicion)).toLowerCase())){
			System.out.println("Hemos encontrado la letra " + this.getLetra() + " en la posicion " + posicion);
			this.total = this.total + 1;
			totalVocales++;
			
		} else System.out.println("No hemos encontrado la letra " + this.getLetra() + " en la posicion " + posicion);
		
		//mostramos el conteo por pantalla
		System.out.println("Total vocales " + this.getLetra() + ":" + this.getTotal());
		System.out.println("Total vocales :" + totalVocales);
		Thread.sleep(1000);
	}
	
	//metodo para arrancar los hilos
	@Override
	public void run() {
		//cada hilo recorre la frase letra a letra, para ver si coincide
		for(int i = 0; i < frase.getFrase().length();i++) {
			System.out.println("Soy el contador de " + this.getNombre() + " y voy a buscar la letra " + this.getLetra() + " en la pos " + i);
			try {
				contarVocales(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
