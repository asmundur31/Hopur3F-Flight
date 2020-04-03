package src;

import java.util.Scanner;  // Import the Scanner class

import src.database.FlightDB;

import java.time.*;

public class FlightCLI {
	static void searchByDate() throws ClassNotFoundException {
		System.out.println("Search by date 'yyyy-mm-dd'");
		final Scanner scan = new Scanner(System.in);
		final String date = scan.next();
		final LocalDate ld = LocalDate.parse(date);
		scan.close();
		FlightDB.search();
	}

	static void searchByAirportTo() {
		System.out.println("search by airport - to");
	}
	//......
	
	public static void main(final String args[]) throws ClassNotFoundException {
		final Scanner scan = new Scanner(System.in);
		System.out.println("Search flight");
		System.out.println("  Search by date: press 1");
		System.out.println("  Search by Airtport, to: press 2");
		System.out.println("  Search by Date, Airport, to: press 3");
		System.out.println("  Search by from, to: press 4");
		System.out.println("  Search by date from, to: press 5");
		final String nr = scan.nextLine();
		//System.out.println(nr.type());
		if (nr.equals("1")) searchByDate();
		if (nr.equals("2")) searchByAirportTo();
		scan.close();
		//......
	}
}

