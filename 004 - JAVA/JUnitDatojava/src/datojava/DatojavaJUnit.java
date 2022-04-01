package datojava;

public class DatojavaJUnit {
	
	public int sumar(int num1, int num2) {
		int resultado = num1 + num2;
		
		return resultado;
	}
	
	public int restar(int num1, int num2) {
		int resultado = num1 - num2;
		
		return resultado;
	}
	
	public boolean validarMax(int maximo) {
		boolean max = false;
		for(int i = 0;i<maximo;i++) {
			if(i==3) {
				max=true;
				break;
			}
		}
		return max;
	}

}
