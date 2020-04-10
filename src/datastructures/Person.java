package src.datastructures;

public class Person {
  private String name;
  private String ssn;
  private Booking[] bookings;
  private String email;
  private String phoneNumber;

  public Person(String name, String ssn, String email, String phoneNumber) {
    this.name = name;
    this.ssn = ssn;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.bookings = new Booking[0];
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSsn() {
    return ssn;
  }

  public Booking[] getBookings() {
    return bookings;
  }

  public void addBooking(Booking booking) {
    Booking[] newBookings = new Booking[bookings.length+1];
    for(int i=0; i<bookings.length; i++) {
      newBookings[i] = bookings[i];
    }
    newBookings[bookings.length] = booking;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}