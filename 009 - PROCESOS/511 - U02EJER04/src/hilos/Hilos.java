package hilos;

public class Hilos extends Thread{
	
	private int tiempo;
	
	public Hilos(int tiempoEspera) {
		
		this.tiempo = tiempoEspera;
	}

	@Override
	public void run() {
		
		System.out.println("Soy el " + Thread.currentThread().getName() + " y voy a esperar " + this.tiempo + "milisegundos.");
		try {
			Thread.sleep(this.tiempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getName() + "interrumpido");
			return;
		}
		
		System.out.println(Thread.currentThread().getName() + " acabando");
	}

}
