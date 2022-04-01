package Arrays;

public class ejemplo03 {

	public static void main(String[] args) {
		
		int multi[][] = new int [10][10];
		
		for (int i=0 ; i < 10; i++) {
			for (int j=0 ; j < 10; j++ ) {
				multi [i][j] = (int)(Math.random() * 1000);
			}
		}
		
		for (int h = 0; h <= 9; h++) {
			System.out.print("------");
		}
		System.out.println();
		
		for (int i=0 ; i < 10; i++) {
			for (int j=0 ; j < 10; j++ ) {
				System.out.printf("| %3d ", multi [i][j]);
				if(j==9) System.out.print("|");
			}
			System.out.println();
			for (int h = 0; h <= 9; h++) {
				System.out.print("------");
			}
			System.out.println();
		}

	}

}
