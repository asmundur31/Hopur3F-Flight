package src.datastructures;

public class Airplain {
	
  private String name;
  private Boolean[][] availableSeats;
  private Boolean[][] needsAssistance;
  private Boolean[][] wantsFood;

  public Airplain(String name) {
    this.name = name;
    this.availableSeats = new Boolean[10][4];
    for(int i=0; i<10; i++) {
      for(int j=0; j<4; j++) {
        availableSeats[i][j] = true;
      }
    }
    this.needsAssistance = new Boolean[10][4];
    this.wantsFood = new Boolean[4][10];
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

  public static void main(String[] args) {
    Airplain a = new Airplain("ThunderBolt");
    a.setAvailableSeats('a',10);
  }
}