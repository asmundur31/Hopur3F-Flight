package src.datastructures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

  private Airport to;
  private Airport from;
  private Airplain airplain;
  private String flightNumber;
  private LocalDateTime date;

  /* Fastayrðing gagna:
        - to er flugvöllurinn sem flugið flýgur til.
        - from er flugvöllurinn sem flugið flýgur frá.
        - airplain er flugvélin sem flýgur flugið.
        - flightNumber er flugnúmer flugsins.
        - date er tímasetning hvenær áætluð brottför eru.
  */

  // Smiður fyrir flug
  public Flight(Airport to, Airport from, Airplain airplain, 
		  String flightNumber, LocalDateTime date) {
    this.to = to;
    this.from = from;
    this.airplain = airplain;
    this.flightNumber = flightNumber;
    this.date = date;
  }

  // Notkun: Airport a = f.getTo()
  // Fyrir:  f er hlutur af taginu Flight.
  // Eftir:  a er flugvöllurinn sem flogið er til.
  public Airport getTo() {
    return to;
  }

  // Notkun: Airport a = f.getFrom()
  // Fyrir:  f er hlutur af taginu Flight.
  // Eftir:  a er flugvöllurinn sem flogið er frá.
  public Airport getFrom() {
    return from;
  }

  // Notkun: Airplain a = f.getAirplain()
  // Fyrir:  f er hlutur af taginu Flight.
  // Eftir:  a er flugvélinn sem flýgur flugið.
  public Airplain getAirplain() {
    return airplain;
  }

  // Notkun: String fn = f.getFlightNumber()
  // Fyrir:  f er hlutur af taginu Flight.
  // Eftir:  fn er flugnúmer flugsins.
  public String getFlightNumber() {
	  return flightNumber;
  }

  // Notkun: LocalDateTime d = f.getTo()
  // Fyrir:  f er hlutur af taginu Flight.
  // Eftir:  d er brottfarartími flugsins.
  public LocalDateTime getDate() {
	  return date;
  }
  
  // toString aðferð fyrir flug
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm");
    String text = date.format(formatter);
	  return getFrom() +" To "+getTo() + ",    " + text;
  }
}