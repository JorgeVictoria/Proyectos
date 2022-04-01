public class Ejercicio9 {
	public static void main(String[] args) {
		String texto = "aaaaaaa.";
		ContarVocales cva = new ContarVocales(texto,"a");
		Thread a = new Thread(cva,"prueba");
		ContarVocales cve = new ContarVocales(texto,"e");
		Thread e= new Thread(cve,"prueba");
		ContarVocales cvi = new ContarVocales(texto,"i");
		Thread i = new Thread(cvi,"prueba");
		ContarVocales cvo = new ContarVocales(texto,"o");
		Thread o = new Thread(cvo,"prueba");
		ContarVocales cvu = new ContarVocales(texto,"u");
		Thread u = new Thread(cvu,"prueba");
		a.start();
		e.start();
		i.start();
		o.start();
		u.start();
		
	}
}


class ContarVocales implements Runnable{
	String texto = "";
	String vocal = "";
	
	public ContarVocales(String texto, String vocal) {
		this.texto = texto;
		this.vocal = vocal;
	}
	
	private synchronized void contar(String vocal,String texto) throws InterruptedException{
		int posicion, contador = 0;
		posicion = texto.indexOf(vocal);
		while(posicion != -1) {
			contador++;
			posicion = texto.indexOf(vocal, posicion+1);
		}
		Thread.sleep(1000); //Espera un segundo
		System.out.println(Thread.currentThread().getName() + " Vocal: " + vocal + ", aparece: " + contador + " veces.");
		Thread.sleep(1000);
	}

	@Override
	public void run() {
		try {
			contar(this.vocal, this.texto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}