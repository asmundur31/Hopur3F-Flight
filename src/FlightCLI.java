package src;

import java.util.Scanner; // Import the Scanner class

import src.controllers.FlightMananger;
import src.datastructures.Flight;

import java.time.*;


public class FlightCLI {
	private static Flight[] flights;
	private static Scanner scan;
	
	private static void search() throws ClassNotFoundException {
		flights = FlightMananger.search();
		pickFlight();
	}
	
	private static void searchByDate() throws ClassNotFoundException {
		System.out.println("Search by date 'yyyy-mm-dd'");
		final String date = scan.nextLine();
		final LocalDate ld = LocalDate.parse(date);
		// Leita af flugum eftir gefni dagsetningu
		flights = FlightMananger.search(ld);
		// Prentum út flugin
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
		int n = Integer.valueOf(scan.nextLine());
		Flight mainFlight = flights[n-1];
		System.out.println("Þú hefur valið flugið:");
		System.out.println(mainFlight);
		BookingCLI.bookFlight(mainFlight);
		// go to BookingCLI so this class wont be to big
	}
	
	public static void main(final String args[]) throws ClassNotFoundException {
		scan = new Scanner(System.in);
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
		//......
		scan.close();
	}

	
}

