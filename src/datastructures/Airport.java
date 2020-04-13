package src.datastructures;

public class Airport {

  private String name;
  private String city;
  private double latitude;
  private double longtitude;

  /* Fastayrðing gagna:
        - name inniheldur nafn flugvallar.
        - city inniheldur nafn borgar/bæjar sem flugvöllur er í.
        - latitude inniheldur breiddargráðu flugvallar.
        - longtitude inniheldur lengdargráðu flugvallar.
  */

  // Smiður fyrir flugvöll
  public Airport(String name, String city, double latitude, double longtitude) {
    this.name = name;
    this.city = city;
    this.latitude = latitude;
    this.longtitude = longtitude;
  }

  // Notkun: String name = a.getName()
  // Fyrir:  a er hlutur af taginu Airport.
  // Eftir:  name inniheldur nafn flugvallarins a.
  public String getName() {
    return name;
  }

  // Notkun: String city = a.getCity()
  // Fyrir:  a er hlutur af taginu Airport.
  // Eftir:  city inniheldur nafn borgarinnar/bæjarins sem 
  //         flugvöllur a er í.
  public String getCity() {
    return city;
  }

  // Notkun: double lat = a.getLatitude()
  // Fyrir:  a er hlutur af taginu Airport.
  // Eftir:  lat inniheldur breiddagráðu flugvallarins.
  public double getLatitude() {
    return latitude;
  }

  // Notkun: double lon = a.getLongtitude()
  // Fyrir:  a er hlutur af taginu Airport.
  // Eftir:  lat inniheldur lengdargráðu flugvallarins.
  public double getLongtitude() {
    return longtitude;
  }
  
  // toString aðferð fyrir flugvöll
  public String toString() {
	  return name;
  }
}