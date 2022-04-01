import java.util.Stack;

public class Ejercicio01 {

	public static void main(String[] args) {
		
		Stack<String> pila = new Stack<>();
		pila.push("Juan");
		pila.push("Ana");
		pila.push("Luis");
		
		System.out.println(pila.size());
		pila.pop();
		System.out.println(pila.size());
		System.out.println(pila.get(0));
		System.out.println(pila.size());
		while(!pila.isEmpty()) {
			System.out.println(pila.pop());
		}

	}

}
