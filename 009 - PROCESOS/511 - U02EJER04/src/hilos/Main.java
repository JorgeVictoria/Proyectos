package hilos;

public class Main {

	public static void main(String[] args) {
		
		Hilos hilo1 = new Hilos(30000);
		Hilos hilo2 = new Hilos(50000);
		
		hilo1.setName("hilo1");
		hilo2.setName("hilo2");
		
		hilo1.start();
		hilo2.start();
		
		System.out.println("Soy el " + Thread.currentThread().getName() + " y voy a esperar 4000 milisesgundos");
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getName() + "interrumpido");
			return;
		}

	
	}
}
