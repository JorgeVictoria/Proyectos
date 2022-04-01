package fibonacci;

public class Fibonacci extends Thread {

	//variables miembro
	private int num;

	/**
	 * constructor de la clase
	 * @param num: recibe un numero entero como parametro
	 */
	public Fibonacci(int num) {
		this.num = num;
	}
	
	
	//getter
	public int getNum() {
		return num;
	}


	//setter
	public void setNum(int num) {
		this.num = num;
	}

	//metodo run para iniciar el hilo
	public void run() {
		
		calculoFibonacci(this.getNum());
		
	}
	
	/**
	 * metodo para calcular la secuencia de Fibonnaci
	 * @param num: numero entero que indica hasta que posicion de la secuencia Fibonacci debemos mostrar
	 * @return : no devuelve nada, se muestra la secuencia por pantalla
	 */
	public void calculoFibonacci(int num) {
		
		//varibales locales
		int num1 = 0;
		int num2 = 1;
		int temp;
		
		//si el numero es 1, mostramos solo el primer valor de la serie
		if (num == 1) System.out.print("Fibonacci: " + num1);
		
		//si no realizamos los calculos para imprimir la serie
		else {
			//imprimimos los dos primeros numeros de la seria
			System.out.print("Fibonacci: " + num1 + " " + num2);
		}
			//con este bucle vamos impriemiendo numeros de la secuencia hasta llegar al numero que hemos introducido
			for(int i = 3; i <= num; i++) { 
				//impresion por pantalla
				System.out.print(" " + (num1+num2));
				//almacenamos la suma de los dos ultimos numeros impresos de la secuencia en un temporal
				//num1 sera el mayor de esos dos numeros
				//num2 sera tiene el valor del temporal
				temp = num1+num2;
				num1 = num2;
				num2 = temp;
		}
		
	}
}
