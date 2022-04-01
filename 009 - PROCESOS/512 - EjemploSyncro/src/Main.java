
public class Main {

	public static void main(String[] args) {
		
		//creamos un objeto runnable
		VerificarCuenta vc = new VerificarCuenta();
		
		//creamos 2 hilos
		Thread Luis = new Thread(vc, "Luis");
		Thread Manuel = new Thread(vc, "Manuel");
		
		//ejecutamos los hilos
		Luis.start();
		Manuel.start();
		
		

	}

}
