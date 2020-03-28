package src.datastructures;

public class Airplain {
	
  private String name;
  private Boolean[][] availableSeats;
  private Boolean[][] needsAssistance;
  private Boolean[][] wantsFood;

  public Airplain(String name) {
    this.name = name;
    this.availableSeats = new Boolean[6][30];
    for(int i=0; i<6; i++) {
      for(int j=0; j<30; j++) {
        availableSeats[i][j] = true;
      }
    }
    this.needsAssistance = new Boolean[6][30];
    this.wantsFood = new Boolean[6][30];
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