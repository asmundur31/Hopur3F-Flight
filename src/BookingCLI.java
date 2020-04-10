package src;

import java.util.Scanner;

import src.controllers.PersonMananger;
import src.datastructures.Flight;
import src.datastructures.Person;

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
          System.out.println("Símanúmer (xxx-xxxx):");
          phone = scan.nextLine();
          p = PersonMananger.createPerson(name, ssn, email, phone);
        } else { // Notandi hefur bókað áður
          System.out.println("Kennitala: ");
          ssn = scan.nextLine();
          p = PersonMananger.getPerson(ssn);
          while(p==null) {
            System.out.println("Þessi notandi er ekki til.");
            System.out.println("Reyna aftur? (J/N)");
            Boolean aftur = getAns();
            if(aftur) { // Notandi vildi reyna aftur
              ssn = scan.nextLine();
              p = PersonMananger.getPerson(ssn);
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
      Boolean[][] laust = mainFlight.getAirplain().getAvailableSeats();
      System.out.println("Laus sæti: ");
      for(int i=0; i<laust.length; i++) {
        char s = (char) ('A' + i);
        for(int j=0; j<laust[0].length; j++) {
          if(laust[i][j]) {
            System.out.print((j+1)+""+s+" ");
          } else {
            System.out.print("   ");
          }
        }
        if(i==(laust.length-1)/2) {
          System.out.println();
          System.out.println();
        } else {
          System.out.println();
        }
      }
      // Leifum notanda að velja sæti
      System.out.println("Veldu sæti með tölu og stórum staf (t.d. 5C eða 28F):");
      String validSaeti = getSeat(laust);
      System.out.println(validSaeti);
      // Uppfærum töfluna Airplain í DB með því að setja þetta
      // sæti sem ekki laust hjá réttri flugvél ---VANTAR

      // ...
    } else { // Notandi vildi ekki bóka
      System.out.println("Takk fyrir að nota leitarvél Hóps 3F");
      System.out.println("Endilega láttu okkur vita hvað þér fannst");
      System.out.println("Öll komment eru vel þegin:");
      String comment = scan.nextLine();
      System.out.println("Komment:");
      System.out.println(comment);
    }
		// get person information
		// do booking
		
  }
  
  // Aðferð sem athugar hvort notandi svarar játandi eða ekki
  private static Boolean getAns() {
    String svar = scan.nextLine();
    if(svar.equals("") ||
       svar.equals("J") || 
       svar.equals("j") || 
       svar.equals("Já") || 
       svar.equals("Y") || 
       svar.equals("y") || 
       svar.equals("Yes") ||
       svar.equals("Jebbs")) {
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
      s = saeti.charAt(saeti.length()-1)-'A';
      r = Integer.parseInt(saeti.substring(0, saeti.length()-1))-1;
    } catch(NumberFormatException e) {
      System.out.println("Ekki löglegt sæti, athugaðu hvort þú er með tölu og stóran staf.");
    }
    System.out.println(s+" "+r+" "+available[s][r]);
    while(s<0 || s>=available.length || r<0 || r>=available[0].length || !available[s][r]){
      System.out.println("Sæti ekki laust, veldu annað sæti:");
      saeti = scan.nextLine();
      try {
        s = saeti.charAt(saeti.length()-1)-'A';
        r = Integer.parseInt(saeti.substring(0, saeti.length()-1))-1;
      } catch(NumberFormatException e) {
        System.out.println("Ekki löglegt sæti, athugaðu hvort þú er með tölu og stóran staf.");
      }
    }
    return saeti;
  }
}
