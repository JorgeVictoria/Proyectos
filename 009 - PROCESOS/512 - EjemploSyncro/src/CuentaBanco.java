
public class CuentaBanco {
	
	//variables miembro
	private int balance=50;

	//constructor
	public CuentaBanco() {
	}

	//getter
	public int getBalance() {
		return balance;
	}
	
	public void retiroBancario(int retiro) {
		balance = balance-retiro;
	}
	

}
