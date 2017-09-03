package com.ex2115;

import java.security.SecureRandom;

public class SupermarketSimulator {
	
	private static SecureRandom rand = new SecureRandom();
	private static Line<Customer> line = new Line<Customer>();
	private static final int SIMULATIONTIME = 720;
	private static final int AVGWAIT = 4;
	
	public SupermarketSimulator() {
	}
		
	public static void simulateLine() {
		Customer firstCustomer = new Customer();
				
		// set arrival time for the first customer
		int arrivalTime = rand.nextInt(AVGWAIT) + 1;
		int minute = arrivalTime;
		
//		// first customer
		firstCustomer.setArrivalTime(arrivalTime); // 1. determine customer's arrival time
		firstCustomer.setMinutes_served(rand.nextInt(AVGWAIT) + 1);  // 2. determine customer's service time
		firstCustomer.setMinute_discarded(firstCustomer.getArrivalTime() + firstCustomer.getMinutes_served());
		firstCustomer.setMinutes_waiting(firstCustomer.getArrivalTime() - minute);
//	
		line.enqueue(firstCustomer); // 3. begin serving the client
		
		int nextCustArrivalTime = minute + (rand.nextInt(AVGWAIT) + 1);
		int timeUntilServiceCompleted = 0;
		for (; minute < SIMULATIONTIME; minute++) {

			if (minute == nextCustArrivalTime)	{// 5. if next customer arrives proceeed
				System.out.printf("%s%n", "New customer arrives");
				
				Customer newCustomer = new Customer(nextCustArrivalTime, rand.nextInt(AVGWAIT) + 1);
				newCustomer.setMinute_discarded(minute);
				newCustomer.setMinutes_waiting(newCustomer.getArrivalTime() - minute);
				line.enqueue(newCustomer);
				nextCustArrivalTime = minute + (rand.nextInt(AVGWAIT) + 1);
			}
				
			if (!line.isEmpty() && line.peek().getMinutes_served() == timeUntilServiceCompleted) {
				System.out.printf("%s%n", "Service completed");
				line.dequeue();
				timeUntilServiceCompleted = 0;
			}
			
			if (++timeUntilServiceCompleted > 4)
				timeUntilServiceCompleted = 0;
			
			System.out.printf("%d", line.getMaxCustomersInLine());
		}
				
	}
}