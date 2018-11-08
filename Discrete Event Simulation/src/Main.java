/*
This is a discrete event simulation of a group of customers getting served by a taco truck.
*/
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in); // Allows taking in user input
		boolean power = true; // Used to end the program

		// Main program loop
		while (power) {

			menu(); // Calls menu to display to user their options

			int userInput = in.nextInt(); // Take in user input
			in.nextLine(); // Clear the buffer

			if (userInput == 1) { // User wants to run a simulation
				
				int customerArrivalTime = 0; // Random time for a customer to appear for a service
				int simulationTime = 0; // Total amount of simulated time
				int currentTime = 0;	// The current time in the simulation
				int numOfCustomers = 0; // Total number of customers
				int numOfHappyCustomers = 0;	// A variable that keeps track of the number of orders completed

				System.out.println("Please enter the amount of time that the simulation to should run (Simulated time)");
				simulationTime = in.nextInt(); // Take in total simulation time
				System.out.println("Please enter the number of customers that should attempt to get food during this time");
				numOfCustomers = in.nextInt(); // Take in total number of customers during the simulation

				System.out.println();
				System.out.println("The following are all of the customers before the simulation");
				System.out.println();
				
				customer[] customers = new customer[numOfCustomers];	// Initialing an array to hold the customers
				customer[] queue = new customer[numOfCustomers];	// An array that represents the queue
				customer[] customerBeingServed = new customer[1];	// An array that represents the customer currently being served
				
				// A loop to set up the simulation before it begins
				for(int i = 0; i < numOfCustomers; i++) {
					String nameTemp = "C" + (i + 1);	// Name for each customer
					customer temp = new customer(ThreadLocalRandom.current().nextInt(0, simulationTime + 1), ThreadLocalRandom.current().nextInt(2, 5 + 1), nameTemp);	// Create each customer with an arrival time and name
					temp.printStuff(); // Printing out each customer before the simulation
					customers[i] = temp;	// Add each customer to the customer array
				}
				tacoTruck tacoTime = new tacoTruck(0,0);	// Initializing the taco truck
				
				System.out.println();
				System.out.println("The following are all of the customers satisfied during the simulation");
				System.out.println();
				
				// The simulation loop
				while (currentTime < simulationTime) {
					
					// A loop to check if a customer is arriving
					for (int i = 0; i < numOfCustomers; i++) {
						
						if (customers[i].getAT() == currentTime) {	// Runs if a customer has arrived	
							if (customerBeingServed[0] == null) {	// Runs if there are no customers being served
								customerBeingServed[0] = customers[i];	// Put the customer in the being served state		
							}else {
								for (int j = 0; j < queue.length; j++) {
									if (queue[j] == null) {
										queue[i] = customers[j];	// Add the customer that arrived to the queue because someone is someone being served
									}
								}
							}
						}
					}
					
					if (customerBeingServed[0] == null) {	//Runs if there are no customers in line or being served
						tacoTime.addWT();	// Adds time to the time waiting variable in the taco truck
					}else {	// Runs if there is a customer being served
						tacoTime.addTS();	// Adds time to the time serving variable in the taco truck
						customerBeingServed[0].beingServed();	// Decreases the needed time to complete the service for a given customer
						
						if (customerBeingServed[0].timeToServe == 0) {	// Runs when taco time has satisfied a customer
							numOfHappyCustomers++;	// Add 1 to happy customer count
							customerBeingServed[0].leaveTime = currentTime;	// Update the customers leave time
							customerBeingServed[0].printStuff();
							customerBeingServed[0] = null;	// No customer currently in line
							
							if (queue[0] == null) {	// Runs if no customers are in line
								
							}else {
								customerBeingServed[0] = queue[0];	//Add the next customer to being served from the line
							}
						}
					}
					currentTime++;	// Decreasing the time and starting the next time step
				}
				tacoTime.printStuff();
				System.out.println("The number of customers served | " + numOfHappyCustomers);

			}
			if (userInput == 2) { // User wants to quit the program
				System.out.println("Goodbye");
				power = false; // End the while loop
			}
		}
	}

	// A simple method to display the menu to the user
	public static void menu() {
		System.out.println("Please select an option");
		System.out.println("1 | Run a simulation");
		System.out.println("2 | Quit");
		System.out.println();
	}

	// A class to represent each customer
	// Allows the incrementing of time variables relating to the customers
	public static class customer {

		int arrivalTime = 0; // The time a customer arrives to the truck
		int leaveTime = 0; // The time which the customer leaves
		int waitTime = 0; // The amount of time the customer is in Line (queue)
		int timeToServe = 0;
		String name = "";

		// Constructor
		customer(int at, int tts, String n) {
			arrivalTime = at;
			name = n;
			timeToServe = tts;
		}
		
		// Getter for time to serve
		public int getTTS() {
			return timeToServe;
		}

		// Getter for name
		public String getName() {
			return name;
		}
		
		// Getter for arrival time
		public int getAT() {
			return arrivalTime;
		}

		// Getter for leave time
		public int getLT() {
			return leaveTime;
		}

		// Getter for wait time
		public int getWT() {
			return waitTime;
		}

		// A way to add to the time the customer has waited in line (queue)
		public void addWT() {
			waitTime++;
		}
		
		// A way to add to the time the customer has waited in line (queue)
		public void beingServed() {
			timeToServe--;
		}
		
		// A method to print out all of the instance variables
		public void printStuff() {
			System.out.println("Name | " + name);
			System.out.println("Arrival Time | " + arrivalTime);
			System.out.println("Leave Time | " + leaveTime);
			System.out.println("Wait Time | " + waitTime);
			System.out.println("Time To Serve | " + timeToServe);
			System.out.println();
		}
	}

	// A class to represent the taco truck
	// Allows the incrementing of time variables relating to the truc
	public static class tacoTruck {

		int timeServing = 0; // A variable representing the amount of time the truck spends serving customers
		int timeWaiting = 0; // A variable representing the amount of time the truck is waiting for a customer

		// constructor
		tacoTruck(int tw, int ts) {
			timeServing = ts;
			timeWaiting = tw;
		}

		// Getter for time serving
		public int getTS() {
			return timeServing;
		}

		// Getter for time waiting
		public int getTW() {
			return timeWaiting;
		}

		// A method to add to the wait time variable when there are no customers
		public void addWT() {
			timeWaiting++;
		}

		// A method to add to the serving time while the truck is serving customers
		public void addTS() {
			timeServing++;
		}
		
		// A method to print out all of the instance variables
		public void printStuff() {
			System.out.println("Taco Time:");
			System.out.println("Time spent serving customers | " + timeServing);
			System.out.println("Time spent waiting for customers | " + timeWaiting);
			System.out.println();
		}
	}
}