package runnable;

public class Hilos {

	public static void main(String[] args) {
		
		//creamos un objeto runnable
		RunnableClass rc = new RunnableClass();
		
		//creamos 3 hilos
		Thread hilo1 = new Thread(rc);
		Thread hilo2 = new Thread(rc);
		Thread hilo3 = new Thread(rc);
		System.out.println("Hilos creados");
		
		
		//damos nombre a los hilos
		hilo1.setName("Hilo1");
		hilo2.setName("Hilo2");
		hilo3.setName("Hilo3");
		System.out.println("Hilos bautizados");
		
		//iniciamos los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();
		System.out.println("Todos los hilos han sido lanzados");
	}

}
