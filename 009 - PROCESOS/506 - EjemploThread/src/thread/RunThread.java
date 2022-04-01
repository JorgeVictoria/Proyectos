package thread;

public class RunThread {

	public static void main(String[] args) {
		
		//se crea un nuevo hilo de ejecucion
		HelloThread hilo = new HelloThread();
		
		//se arranca el hilo creado anteriormente
		hilo.start();
		
		//mostramos datos por pantalla para mostrar donde estamos
		System.out.println("Hola desde el hilo principal");
		System.out.println("Proceso acabando");
		
	}

}
