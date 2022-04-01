package hilos;

public class Main {

	public static void main(String[] args) {
		
		//creamos los hilos
		CuentaVocales letraA = new CuentaVocales("letraA",'a');
		CuentaVocales letraE = new CuentaVocales("letraE",'e');
		CuentaVocales letraI = new CuentaVocales("letraI",'i');
		CuentaVocales letraO = new CuentaVocales("letraO",'o');
		CuentaVocales letraU = new CuentaVocales("letraU",'u');
		
		//iniciamos los hilos
		letraA.start();
		letraE.start();
		letraI.start();
		letraO.start();
		letraU.start();

	}

}
