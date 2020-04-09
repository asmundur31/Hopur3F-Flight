package src.datastructures;

public class Airplain {
	
  private String name;
  private Boolean[][] availableSeats;
  private Boolean[][] needsAssistance;
  private Boolean[][] wantsFood;

  public Airplain(String name, Boolean[][] seats, Boolean[][] assist, Boolean[][] food) {
    this.name = name;
    this.availableSeats = seats;
    this.needsAssistance = assist;
    this.wantsFood = food;
  }

  public String getName() {
    return name;
  }

  public Boolean[][] getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(char s, int r) {
    int i = s-'a';
    int j = r;
    availableSeats[i][j] = false;
  }

  public Boolean[][] getNeedsAssistance() {
    return needsAssistance;
  }

  public void setNeedsAssistance(char s, int r) {
    int i = s-'a';
    int j = r;
    needsAssistance[i][j] = false;
  }

  public Boolean[][] getWantsFood() {
    return wantsFood;
  }

  public void setWantsFood(char s, int r) {
    int i = s-'a';
    int j = r;
    wantsFood[i][j] = false;
  }
}