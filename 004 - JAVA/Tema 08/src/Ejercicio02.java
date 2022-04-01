import java.util.Scanner;
import java.util.Stack;

public class Ejercicio02 {

	public static void main(String[] args) {
		
		String formula;
		Scanner stdin = new Scanner(System.in);
		Stack<String> pila = new Stack<>();
		int contador=0;
		
		System.out.print("Formula: ");
		formula = stdin.nextLine();
		
		while(contador < formula.length()) {
			if(formula.charAt(contador) ==  123) pila.push(String.valueOf(formula.charAt(contador)));
			if(formula.charAt(contador) ==  91) pila.push(String.valueOf(formula.charAt(contador)));
			if(formula.charAt(contador) ==  40) pila.push(String.valueOf(formula.charAt(contador)));
			
			if(formula.charAt(contador) ==  125) {
				if(pila.get(pila.size()-1).equals("{")) pila.pop();
				else break;
			}
			if(formula.charAt(contador) ==  93) {
				if(pila.get(pila.size()-1).equals("[")) pila.pop();
				else break;
			}
			if(formula.charAt(contador) ==  41) {
				if(pila.get(pila.size()-1).equals("(")) pila.pop();
				else break;
			}
			contador++;
		}
		
		System.out.println(pila.size());
		
		while(!pila.isEmpty()) System.out.println(pila.pop());
		
		

	}

}
