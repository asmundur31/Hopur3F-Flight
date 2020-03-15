public class Flight {
  private Airport to;
  private Airport from;
  private Airplain airplain;
  private String flightNumber;
  private Date date;

  public Flight(Airport to, Airport from, Airplain airplain, String flightNumber, Date date) {
    this.to = to;
    this.from = from;
    this.airplain = airplain;
    this.flightNumber = flightNumber;
    this.date = date;
  }
}