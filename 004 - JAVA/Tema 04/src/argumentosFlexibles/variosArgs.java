package argumentosFlexibles;

public class variosArgs {

	public static void main(String[] args) {
		
		//variables locales
		int total; //reoge la suma de todos los enteros que pasamos
		
		//llamada a funcion
		total = sumar(5,10, 28, 13);
		
		//imprimimos la suma
		System.out.println(total);

	}
	
	private static int sumar(int... nums) {
		
		//variables locales
		int suma = 0;
		
		for (int i = 0; i <nums.length; i++) suma = suma + nums[i];
		
		return suma;
	}

}
