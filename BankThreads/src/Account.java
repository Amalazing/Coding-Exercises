
/*
 * 	A class that represents an account with the bank
 */

public class Account {
	
	private int balance = 6000;	//Current funds in the account
	
	public int getBalance() {
		return balance;
	}
	
	public void withrdraw(int amount) {
		balance = balance - amount;
	}

}