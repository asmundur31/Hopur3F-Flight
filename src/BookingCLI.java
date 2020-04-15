package src;

import java.time.LocalDateTime;
import java.util.Scanner;

import src.controllers.BookingMananger;
import src.controllers.PersonMananger;
import src.datastructures.Booking;
import src.datastructures.Flight;
import src.datastructures.Person;

// Hér er einfaldur commad line interface klasi sem býr til viðmót
// fyrir notenda sem langar að skoða flug, bóka flug eða búa til flug
public class BookingCLI {
  private static Scanner scan;

	public static void bookFlight(Flight mainFlight, Scanner scanner) throws ClassNotFoundException {
    scan = scanner;
    System.out.println("Má bjóða þér að bóka flugið? (J/N)");
    Boolean b = getAns();
    if(b) { // Notandi vildi bóka
      String name = "";
      String ssn = "";
      String email = "";
      String phone = "";
      Person p = null;
      // Höldum áfram að að biðja notanda um að nýskrá sig
      // eða finna sig í DB
      while(p==null) {
        // Athugum hvort notandi sé nýr eða hefur bókað áður
        System.out.println("Nýskráning? (J/N)");
        Boolean ny = getAns();
        if(ny) { // Notandi er nýr
          // Vantar kannski villumeðhöndlun ef sett inn
          // upplýsingar á rangan hátt
          System.out.println("Nafn:");
          name = scan.nextLine();
          System.out.println("Kennitala:");
          ssn = scan.nextLine();
          System.out.println("Netfang:");
          email = scan.nextLine();
          System.out.println("Símanúmer:");
          phone = scan.nextLine();
          p = new Person(name, ssn, email, phone);
          PersonMananger pm = new PersonMananger();
          pm.createPerson(p);
        } else { // Notandi hefur bókað áður
          System.out.println("Kennitala: ");
          ssn = scan.nextLine();
          PersonMananger pm = new PersonMananger();
          p = pm.getPerson(ssn);
          while(p==null) {
            System.out.println("Þessi notandi er ekki til.");
            System.out.println("Reyna aftur? (J/N)");
            Boolean aftur = getAns();
            if(aftur) { // Notandi vildi reyna aftur
              System.out.println("Kennitala: ");
              ssn = scan.nextLine();
              p = pm.getPerson(ssn);
            } else { // Notandi vildi ekki reyna aftur og við hættum
              break;
            }
          }
        }
      }
      // Búum til Booking fyrir flugið mainFlight sem
      // Person p ætlar að bóka
      // p er ný búinn að skrá sig eða var skráður áður
      // Byrjum að birta laus sæti
      System.out.println("Laus sæti: ");
      mainFlight.getAirplain().printAvailableSeats();
      // Leifum notanda að velja sæti
      System.out.println("Veldu sæti með tölu og stórum staf (t.d. 5C eða 28F):");
      String validSaeti = getSeat(mainFlight.getAirplain().getAvailableSeats());
      // Uppfærum flugvélina með því að setja þetta
      // sæti sem ekki laust hjá réttri flugvél
      char s = validSaeti.charAt(validSaeti.length()-1);
      int r = Integer.parseInt(validSaeti.substring(0, validSaeti.length()-1));
      mainFlight.getAirplain().setAvailableSeats(s, r);
      System.out.println("Má bjóða þér mat í fluginu? (J/N)");
      if (getAns()) {
    	  mainFlight.getAirplain().setWantsFood(s,r);
      }
      System.out.println("Má bjóða þér aðstoð í fluginu? (J/N)");
      if (getAns()) {
    	  mainFlight.getAirplain().setNeedsAssistance(s,r);
      }
      
      // Búum nú til bókun fyrir þessa person
      Booking nyBokun = new Booking(s, r, p, mainFlight, LocalDateTime.now());
      p.addBooking(nyBokun);
      BookingMananger bm = new BookingMananger();
      bm.createBooking(nyBokun);
      // Prentum út bókunina
      System.out.println("Hér er bókunin þín, takk fyrir viðskiptin!");
      System.out.println(nyBokun);
    }
    System.out.println("Takk fyrir að nota leitarvél Hóps 3F");
    System.out.println("Endilega láttu okkur vita hvað þér fannst");
    System.out.println("Öll komment eru vel þegin:");
    String comment = scan.nextLine();
    System.out.println("Komment:");
    System.out.println(comment);
    // Ef við viljum geyma einhver feedback þá gætum við það
  }
  
  // Aðferð sem athugar hvort notandi svarar játandi eða ekki
  private static Boolean getAns() {
    String svar = scan.nextLine();
    if(svar.equals("") ||
       svar.equals("J") || 
       svar.equals("j") || 
       svar.equals("Já") || 
       svar.equals("já") || 
       svar.equals("Y") || 
       svar.equals("y") || 
       svar.equals("Yes") ||
       svar.equals("yes") || 
       svar.equals("Jebbs") ||
       svar.equals("jebbs")) {
      return true;
    } else {
      return false;
    }
  }

  // Aðferð til að athuga hvort valið sæti sé laust og löglegt
  private static String getSeat(Boolean[][] available) {
    String saeti = scan.nextLine();
    int s = -1;
    int r = -1;
    try {
      r = available.length-Integer.parseInt(saeti.substring(0, saeti.length()-1));
      s = available[0].length-(saeti.charAt(saeti.length()-1)-'A'+1);
    } catch(NumberFormatException e) {
      System.out.println("Ekki löglegt sæti, athugaðu hvort þú er með tölu og stóran staf.");
    }
    while(r<0 || r>=available.length || s<0 || s>=available[0].length || !available[r][s]){
      System.out.println("Sæti ekki laust, veldu annað sæti:");
      saeti = scan.nextLine();
      try {
        r = available.length-Integer.parseInt(saeti.substring(0, saeti.length()-1));
        s = available[0].length-(saeti.charAt(saeti.length()-1)-'A'+1);
      } catch(NumberFormatException e) {
        System.out.println("Ekki löglegt sæti, athugaðu hvort þú er með tölu og stóran staf.");
      }
    }
    return saeti;
  }
}
