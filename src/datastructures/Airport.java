package src.datastructures;

public class Airport {
  private String name;
  private String city;
  private double latitude;
  private double longtitude;

  public Airport(String name, String city, double latitude, double longtitude) {
    this.name = name;
    this.city = city;
    this.latitude = latitude;
    this.longtitude = longtitude;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongtitude() {
    return longtitude;
  }
}