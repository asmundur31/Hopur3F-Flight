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

	private static void searchByAirport(boolean to) throws ClassNotFoundException {
		System.out.println("Sláðu inn flugvöll:");
		String flugvollur = scan.nextLine();
		// Leita af flugum eftir því hvort til eða frá flugvelli
		flights = FlightMananger.search(flugvollur, to);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDateAirport(boolean to) throws ClassNotFoundException {
		System.out.println("Sláðu inn flugvöll:");
		String flugvollur = scan.nextLine();
		System.out.println("Sláðu inn þá dagsetningu sem þig langar að fara (yyyy-mm-dd):");
		String date = scan.nextLine();
		LocalDate ld = LocalDate.parse(date);
		// Leita af flugum eftir því hvort til eða frá flugvelli og á ákveðinni dagsetningu
		flights = FlightMananger.search(ld, flugvollur, to);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDepartureDestination() throws ClassNotFoundException {
		System.out.println("Sláðu inn flugvöll sem þig langar að fljúga frá:");
		String flugvollurFra = scan.nextLine();
		System.out.println("Sláðu inn flugvöll sem þig langar að fljúga til:");
		String flugvollurTil = scan.nextLine();
		// Leita af flugum frá flugvollurFra og til FlugvollurTil
		flights = FlightMananger.search(flugvollurFra, flugvollurTil);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDateDepartureDestination() {
		System.out.println("Sláðu inn flugvöll sem þig langar að fljúga frá:");
		String flugvollurFra = scan.nextLine();
		System.out.println("Sláðu inn flugvöll sem þig langar að fljúga til:");
		String flugvollurTil = scan.nextLine();
		System.out.println("Sláðu inn þá dagsetningu sem þig langar að fara (yyyy-mm-dd):");
		String date = scan.nextLine();
		LocalDate ld = LocalDate.parse(date);
		// Leita af flugum frá flugvollurFra og til FlugvollurTil á ákveðinni dagsetningu
		flights = FlightMananger.search(ld, flugvollurFra, flugvollurTil);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void pickFlight() {
		int i = 1;
		for(Flight f : flights) {
			System.out.println();
			System.out.println(i++ + " " + f);
		}
		if(i==1) {
			System.out.println("Því miður fundust enginn flug.");
		} else {
			System.out.println("Pick a Flight:");
			int n = Integer.valueOf(scan.nextLine());
			Flight mainFlight = flights[n-1];
			System.out.println("Þú hefur valið flugið:");
			System.out.println(mainFlight);
			BookingCLI.bookFlight(mainFlight);
			// go to BookingCLI so this class wont be to big
		}
	}
	
	public static void main(final String args[]) throws ClassNotFoundException {
		scan = new Scanner(System.in);
		System.out.println("Search flight");
		System.out.println("  Search: press 0");
		System.out.println("  Search by date: press 1");
		System.out.println("  Search by departure: press 2");
		System.out.println("  Search by destination: press 3");
		System.out.println("  Search by date and departure: press 4");
		System.out.println("  Search by date and destination: press 5");
		System.out.println("  Search by departure and destination: press 6");
		System.out.println("  Search by date, departure and destination: press 7");
		final String nr = scan.nextLine();

		if (nr.equals("0")) search();
		else if (nr.equals("1")) searchByDate();
		else if (nr.equals("2")) searchByAirport(false);
		else if (nr.equals("3")) searchByAirport(true);
		else if (nr.equals("4")) searchByDateAirport(false);
		else if (nr.equals("5")) searchByDateAirport(true);
		else if (nr.equals("6")) searchByDepartureDestination();
		else if (nr.equals("7")) searchByDateDepartureDestination();
		scan.close();
	}
}

