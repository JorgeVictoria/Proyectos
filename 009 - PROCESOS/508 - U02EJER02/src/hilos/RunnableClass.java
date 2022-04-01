package hilos;

public class RunnableClass implements Runnable {

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " empezando.");
		
		System.out.println(Thread.currentThread().getName() + " finalizado.");
		
	}

}
