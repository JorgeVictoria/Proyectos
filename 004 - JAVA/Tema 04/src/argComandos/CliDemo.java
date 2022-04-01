package argComandos;

public class CliDemo {

	public static void main(String[] args) {
		
		System.out.println("Usted ha tecleado " + args.length + " argumentos");
		
		if(args.length > 0) {
			System.out.print("Son:");
			for(int i = 0; i<args.length; i++) {
				System.out.print(" " + args[i]);
			}
		}

	}

}
