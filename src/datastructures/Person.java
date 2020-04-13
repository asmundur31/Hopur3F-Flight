package src.datastructures;

public class Person {

  private String name;
  private String ssn;
  private Booking[] bookings;
  private String email;
  private String phoneNumber;

  /* Fastayrðing gagna:
        - name inniheldur nafn persónu.
        - ssn inniheldur kennitölu persónu.
        - bookings er listi allra bókanna sem persóna hefur bókað.
        - email er netfang persónu.
        - phoneNumber er símanúmer persónu.
  */
  
  // Smiður fyrir persónu
  public Person(String name, String ssn, String email, String phoneNumber) {
    this.name = name;
    this.ssn = ssn;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.bookings = new Booking[0];
  }

  // Notkun: String name = p.getName()
  // Fyrir:  p er hlutur af taginu Person.
  // Eftir:  name inniheldur nafn persónu p.
  public String getName() {
    return name;
  }

  // Notkun: p.setName(n)
  // Fyrir:  p er hlutur af taginu Person og n er strengur með 
  //         nýju nafni.
  // Eftir:  Búið er að setja nýtt nafn á persónu p, þ.e. frá name
  //         í nýtt nafn n.
  public void setName(String name) {
    this.name = name;
  }

  // Notkun: String ssn = p.getSsn()
  // Fyrir:  p er hlutur af taginu Person.
  // Eftir:  ssn inniheldur kennitölu persónu p.
  public String getSsn() {
    return ssn;
  }

  // Notkun: Bookng[] bookings = p.getBookings()
  // Fyrir:  p er hlutur af taginu Person.
  // Eftir:  bookings inniheldur allar bókanir sem persóna p hefur
  //         bókað.
  public Booking[] getBookings() {
    return bookings;
  }

  // Notkun: p.addBooking(b)
  // Fyrir:  p er hlutur af taginu Person og b er ný bókun sem p
  //         var að bóka.
  // Eftir:  Búið er að bæta þessari nýju bókun við listann af
  //         bókunum, bookings.
  public void addBooking(Booking booking) {
    Booking[] newBookings = new Booking[bookings.length+1];
    for(int i=0; i<bookings.length; i++) {
      newBookings[i] = bookings[i];
    }
    newBookings[bookings.length] = booking;
    this.bookings = newBookings;
  }

  // Notkun: String e = p.getEmail()
  // Fyrir:  p er hlutur af taginu Person
  // Eftir:  e inniheldur netfang persónu p.
  public String getEmail() {
    return email;
  }

  // Notkun: p.setEmail(e)
  // Fyrir:  p er hlutur af taginu Person og e er strengur með 
  //         nýju netfangi.
  // Eftir:  Búið er að setja nýtt netfang fyrir persónu p, þ.e.
  //         frá email í nýtt netfang e.
  public void setEmail(String email) {
    this.email = email;
  }

  // Notkun: String ph = p.getPhoneNumber()
  // Fyrir:  p er hlutur af taginu Person.
  // Eftir:  ph inniheldur símanúmer persónu p.
  public String getPhoneNumber() {
    return phoneNumber;
  }

  // Notkun: p.setPhoneNumber(ph)
  // Fyrir:  p er hlutur af taginu Person og ph er strengur með 
  //         nýju símanúmeri.
  // Eftir:  Búið er að setja nýtt símanúmer fyrir persónu p, þ.e.
  //         frá phoneNumber í nýtt símanúmer ph.
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}