
/*
 *	I am in the interview process with a company in Monrovia for a java development position. During the last round
 *	I was asked about threads and realized that I did not know enough. I decided to dive a little bit into it today.
 *	Starting with the classic bank account example. This example only functions correctly if the keyword
 *	synchronized is used. If not a race condition is created for both threads withdrawing the same money. 
 */

public class Main {
	
	public static void main(String[] args) {
			
			Account account = new Account();
			AccountHolder accountHolder = new AccountHolder(account);
			Thread t1 = new Thread(accountHolder);
			Thread t2 = new Thread(accountHolder);
			t1.setName("Jack");	//Name the thread to keep track of it through the program
			t2.setName("Joya");	//Name the thread to keep track of it through the program
			
			t1.start();
			t2.start();
		}

}
