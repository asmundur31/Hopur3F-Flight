package src;

import java.util.Scanner;  // Import the Scanner class

import src.database.FlightDB;
import src.datastructures.Flight;

import java.time.*;

public class FlightCLI {
	private static Flight[] flights;
	
	private static void search() throws ClassNotFoundException {
		flights = FlightDB.search();
		pickFlight();
	}
	
	private static void searchByDate() throws ClassNotFoundException {
		System.out.println("Search by date 'yyyy-mm-dd'");
		final Scanner scan = new Scanner(System.in);
		final String date = scan.next();
		final LocalDate ld = LocalDate.parse(date);
		scan.close();
		// search date 2020-02-26
		flights = FlightDB.search(ld);
		pickFlight();
	}

	private static void searchByAirportTo() {
		System.out.println("search by airport - to");
	}
	//......
	
	private static void pickFlight() {
		
		System.out.println("Pick a Flight");
		int i = 1;
		for(Flight f : flights) {
			System.out.println();
			System.out.println(i++ + " " + f);
		}
		final Scanner scan = new Scanner(System.in);

		Flight mainFlight = flights[Integer.valueOf(scan.next())-1];
		scan.close();
		BookingCLI.bookFlight(mainFlight);
		// go to BookingCLI so this class wont be to big
	}
	
	public static void main(final String args[]) throws ClassNotFoundException {
		final Scanner scan = new Scanner(System.in);
		System.out.println("Search flight");
		System.out.println("  Search: press 0");
		System.out.println("  Search by date: press 1");
		System.out.println("  Search by Airtport, to: press 2");
		System.out.println("  Search by Date, Airport, to: press 3");
		System.out.println("  Search by from, to: press 4");
		System.out.println("  Search by date from, to: press 5");
		final String nr = scan.nextLine();
		//System.out.println(nr.type());

		if (nr.equals("0")) search();
		if (nr.equals("1")) searchByDate();
		if (nr.equals("2")) searchByAirportTo();
		scan.close();
		//......
	}

	
}

