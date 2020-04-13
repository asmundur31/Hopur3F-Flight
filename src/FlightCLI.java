package src;

import java.util.Scanner; // Import the Scanner class

import src.controllers.FlightMananger;
import src.datastructures.Flight;

import java.time.*;
import java.time.format.DateTimeParseException;


public class FlightCLI {
	private static Flight[] flights;
	private static Scanner scan;
	
	private static void search() throws ClassNotFoundException {
    FlightMananger fm = new FlightMananger();
		flights = fm.search();
		pickFlight();
	}
	
	private static void searchByDate() throws ClassNotFoundException {
		System.out.println("Sláðu inn þá dagsetningu sem þig langar að fara (yyyy-mm-dd):");
		LocalDate ld = getDate();
    // Leita af flugum eftir gefni dagsetningu
    FlightMananger fm = new FlightMananger();
		flights = fm.search(ld);
		// Prentum út flugin
		pickFlight();
	}

	private static void searchByAirport(boolean to) throws ClassNotFoundException {
		if(to) {
			System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga til:");
		} else {
			System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga frá:");
		}
		String flugvollur = scan.nextLine();
    // Leita af flugum eftir því hvort til eða frá city
    FlightMananger fm = new FlightMananger();
		flights = fm.search(flugvollur, to);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDateAirport(boolean to) throws ClassNotFoundException {
		if(to) {
			System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga til:");
		} else {
			System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga frá:");
		}
		String city = scan.nextLine();
		System.out.println("Sláðu inn þá dagsetningu sem þig langar að fara (yyyy-mm-dd):");
    LocalDate ld = getDate();
		// Leita af flugum eftir því hvort til eða frá city og á ákveðinni dagsetningu
    FlightMananger fm = new FlightMananger();
    flights = fm.search(ld, city, to);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDepartureDestination() throws ClassNotFoundException {
		System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga frá:");
		String cityFra = scan.nextLine();
		System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga til:");
		String cityTil = scan.nextLine();
		// Leita af flugum frá cityFra og til cityTil
    FlightMananger fm = new FlightMananger();
    flights = fm.search(cityFra, cityTil);
		// Prentum út flugin
		pickFlight();
	}
	
	private static void searchByDateDepartureDestination() throws ClassNotFoundException {
		System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga frá:");
		String cityFra = scan.nextLine();
		System.out.println("Sláðu inn staðsetningu sem þig langar að fljúga til:");
		String cityTil = scan.nextLine();
		System.out.println("Sláðu inn þá dagsetningu sem þig langar að fara (yyyy-mm-dd):");
		LocalDate ld = getDate();
		// Leita af flugum frá cityFra og til cityTil á ákveðinni dagsetningu
    FlightMananger fm = new FlightMananger();
    flights = fm.search(ld, cityFra, cityTil);
		// Prentum út flugin
		pickFlight();
	}
  
  // Aðferð sem biður notendan um dagsetningu á réttu formi
  private static LocalDate getDate() {
    String date = scan.nextLine();
    LocalDate ld = null;
    while(ld == null) {
      try {
        ld = LocalDate.parse(date);
      } catch(DateTimeParseException e) {
        System.out.println("Þetta er ekki lögleg dagsetning, sláðu inn dagsetningu á forminu yyyy-mm-dd:");
        date = scan.nextLine();
      }
    }
    return ld;
  }

  // Aðferð sem biður notendan að gefa rétt gildi á n
  // þegar hann velur flug
  private static int getNumber(int max) {
    String num = scan.nextLine();
    int n = 0;
    while(n<1 || max<n) {
      try {
        n = Integer.parseInt(num);
        if(n<1 || max<n) {
          throw new NumberFormatException();
        } 
      } catch(NumberFormatException e) {
        System.out.println("Ekkert flug er númer "+num+", vinsamlegast reyndu aftur:");
        num = scan.nextLine();
      }
    }
    return n;
  }

	private static void pickFlight() throws ClassNotFoundException {
		int i = 1;
		for(Flight f : flights) {
			System.out.println();
			System.out.println(i++ + ": " + f);
		}
		if(i==1) {
      System.out.println("Því miður fundust enginn flug.");
      System.out.println("Takk fyrir að nota leitarvél Hóps 3F");
      System.out.println("Endilega láttu okkur vita hvað þér fannst");
      System.out.println("Öll komment eru vel þegin:");
      String comment = scan.nextLine();
      System.out.println("Komment:");
      System.out.println(comment);
		} else {
			System.out.println();
			System.out.println("Veldu flug á bilinum 1 til "+(i-1)+":");
      int n = getNumber(i-1);
			Flight mainFlight = flights[n-1];
			System.out.println("Þú hefur valið flugið:");
			System.out.println(mainFlight);
			BookingCLI.bookFlight(mainFlight, scan);
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
