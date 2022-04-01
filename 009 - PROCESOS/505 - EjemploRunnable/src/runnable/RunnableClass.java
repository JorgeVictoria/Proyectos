package runnable;

public class RunnableClass implements Runnable {
	
	public void run() {
		for(int i = 1; i < 5; i++) {
			System.out.println("Ejecutando " + Thread.currentThread().getName() + " : " + 1);
		}
	}

}
