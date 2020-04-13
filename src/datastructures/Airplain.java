package src.datastructures;

public class Airplain {
	
  private String name;
  private Boolean[][] availableSeats;
  private Boolean[][] needsAssistance;
  private Boolean[][] wantsFood;

  /* Fastayrðing gagna:
        - name inniheldur nafn flugvélarinnar sem er úthlutuð
          fyrir ákveðið flug.
        - availableSeats er tvívítt fylki sem geymir true ef sæti 
          er laust annars false.
        - needsAssistance er tvívítt fylki sem geymir true ef sæti 
          er laust annars false.
        - wantsFood er tvívítt fylki sem geymir true ef sæti 
          er laust annars false.
  */
  
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
    int i = r - 1;
    int j = s - 'A';
    availableSeats[i][j] = false;
  }

  public Boolean[][] getNeedsAssistance() {
    return needsAssistance;
  }

  public void setNeedsAssistance(char s, int r) {
    int i = r - 1;
    int j = s - 'A';
    needsAssistance[i][j] = false;
  }

  public Boolean[][] getWantsFood() {
    return wantsFood;
  }

  public void setWantsFood(char s, int r) {
    int i = r - 1;
    int j = s - 'A';
    wantsFood[i][j] = false;
  }

  // Aðferð sem prentar út laus sæti
  public void printAvailableSeats() {
    for(int i=0; i<availableSeats.length; i++) {
      for(int j=0; j<availableSeats[0].length; j++) {
        char s = (char) ('A' + availableSeats[0].length - j - 1);
        int r = availableSeats.length-i;
        if(availableSeats[i][j]) {
          for(int bil=0; bil<(int)(Math.log10(availableSeats.length)-(int)(Math.log10(r))); bil++) {
            System.out.print(" ");
          }
          System.out.print("" + r + s + " ");
        } else {
          System.out.print("    ");
        }
        if(j==(availableSeats[0].length-1)/2) {
          System.out.print("  ");
        }
      }
      System.out.println();
    }
  }
}