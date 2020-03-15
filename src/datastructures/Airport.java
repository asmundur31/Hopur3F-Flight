package src.datastructures;

public class Airport {
  private String name;
  private String city;
  private float latitude;
  private float longtitude;

  public Airport(String name, String city, float latitude, float longtitude) {
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

  public float getLatitude() {
    return latitude;
  }

  public float getLongtitude() {
    return longtitude;
  }
}