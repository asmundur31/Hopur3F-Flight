package src.datastructures;

public class Airplain {
	
  private String name;
  private Boolean[][] availableSeats;
  private Boolean[][] needsAssistance;
  private Boolean[][] wantsFood;

  /* Fastayrðing gagna:
        - name inniheldur nafn flugvélarinnar sem er úthlutuð
          fyrir ákveðið flug.
        - availableSeats er tvívítt sxr fylki, þar sem s er fjöldi
          sæta og r er fjöldi raða, sem geymir true ef sæti er
          laust annars false.
        - needsAssistance er tvívítt sxr fylki, þar sem s er fjöldi
          sæta og r er fjöldi raða, sem geymir true ef sæti er
          laust annars false.
        - wantsFood er tvívítt sxr fylki, þar sem s er fjöldi
          sæta og r er fjöldi raða, sem geymir true ef sæti er
          laust annars false.
  */
  
  // Smiður fyrir flugvél sem er úthlutuð fyrir hvert flug
  public Airplain(String name, Boolean[][] seats, Boolean[][] assist, Boolean[][] food) {
    this.name = name;
    this.availableSeats = seats;
    this.needsAssistance = assist;
    this.wantsFood = food;
  }

  // Notkun: String name = a.getName()
  // Fyrir:  a er hlutur af taginu Airplain.
  // Eftir:  name inniheldur nafn flugvélarinnar.
  public String getName() {
    return name;
  }

  // Notkun: Boolean[][] available = a.getAvailableSeats()
  // Fyrir:  a er hlutur af taginu Airplain.
  // Eftir:  available hefur fengið innihaldið úr availableSeats.
  public Boolean[][] getAvailableSeats() {
    return availableSeats;
  }

  // Notkun: a.setAvailableSeats(s,r)
  // Fyrir:  a er hlutur af taginu Airplain, s er hlutur af 
  //         taginu char A<=s<=breidd vélar og r er heiltala
  //         1<=r<=lengd vélar.
  // Eftir:  búið er að taka frá sæti (s,r), þ.e. uppfæra
  //         viðeigandi gildi í availableSeats frá true í false.
  public void setAvailableSeats(char s, int r) {
    int i = r - 1;
    int j = s - 'A';
    availableSeats[i][j] = false;
  }

  // Notkun: Boolean[][] need = a.getNeedsAssistance()
  // Fyrir:  a er hlutur af taginu Airplain.
  // Eftir:  need hefur fengið innihaldið úr needsAssistance.
  public Boolean[][] getNeedsAssistance() {
    return needsAssistance;
  }

  // Notkun: a.setNeedsAssistance(s,r)
  // Fyrir:  a er hlutur af taginu Airplain, s er hlutur af 
  //         taginu char A<=s<=breidd vélar og r er heiltala
  //         1<=r<=lengd vélar.
  // Eftir:  búið er að uppfæra sæti (s,r), þ.e. uppfæra
  //         viðeigandi gildi í needsAssistance frá false í true.
  public void setNeedsAssistance(char s, int r) {
    int i = r - 1;
    int j = s - 'A';
    needsAssistance[i][j] = true;
  }

  // Notkun: Boolean[][] wantsF = a.getWantsFood()
  // Fyrir:  a er hlutur af taginu Airplain.
  // Eftir:  wantsF hefur fengið innihaldið úr wantsFood.
  public Boolean[][] getWantsFood() {
    return wantsFood;
  }

  // Notkun: a.setWantsFood(s,r)
  // Fyrir:  a er hlutur af taginu Airplain, s er hlutur af 
  //         taginu char A<=s<=breidd vélar og r er heiltala
  //         1<=r<=lengd vélar.
  // Eftir:  búið er að uppfæra sæti (s,r), þ.e. uppfæra
  //         viðeigandi gildi í wantsFood frá false í true.
  public void setWantsFood(char s, int r) {
    int i = r - 1;
    int j = s - 'A';
    wantsFood[i][j] = true;
  }

  // Notkun: a.printAvailableSeats()
  // Fyrir:  a er hlutur af taginu Airplain.
  // Eftir:  Búið er að prenta út á staðalúttak öll laus sæti í
  //         flugvélinni þar sem fyrsta línan er aftasta röð
  //         vélarinnar og síðasta línan er fremsta.
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