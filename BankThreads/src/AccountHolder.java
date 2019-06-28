
/*
 * 	A simple class that holds the functionality of withdrawing money for the threads.
 */

public class AccountHolder implements Runnable {
	private Account account;

	//Constructor for this class to automatically set up an account upon object initialization
	public AccountHolder(Account account) {
		this.account = account;
	}

	//A method that tells the threads what exactly to do once I call start on them
	@Override
	public void run() {
		for (int i = 1; i <= 4; i++) {	//Each of the two threads loop 4 times to withdraw a total of 8k from one account that only has 6k in it
			makeWithdrawal(2000);
			if (account.getBalance() < 0) {	//This should only fire if the threads are not synchronized, if they are the account should never drop below 0
				System.out.println("account is overdrawn!");
			}
		}
	}

	//Method to make a withdraw from the account
	private synchronized void makeWithdrawal(int withdrawAmount) {
		if (account.getBalance() >= withdrawAmount) {	//If there exists enough funds, withdraw
			System.out.println(Thread.currentThread().getName()	+ " is going to withdraw $"+withdrawAmount);
			try {
				Thread.sleep(2000);	//This sleep on the thread proves that the other thread cannot access the synchronized method
			} catch (InterruptedException ex) {
			}
			account.withrdraw(withdrawAmount);
			System.out.println(Thread.currentThread().getName()	+ " completes the withdrawal of $"+withdrawAmount);
		} else {	//Not enough dough for withdraw, this should be thrown if the synchronization worked
			System.out.println("Not enough in account for "	+ Thread.currentThread().getName() + " to withdraw "
					+ account.getBalance());
		}
	}
}