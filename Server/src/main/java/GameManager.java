import java.util.*;

public class GameManager {

    private int TeamScore1;
    private int TeamScore2;
    private int nbrPlayer;
    private boolean GameStarted;
    private Vector<Card> deck;
    private String currentAsset;
    private boolean Coinched;
    private boolean CounterCoinched;

    public GameManager() {
        this.TeamScore1 = 0;
        this.TeamScore2 = 0;
        this.nbrPlayer = 0;
        this.Coinched = false;
        this.CounterCoinched = false;
        this.deck = new Vector<Card>();
        this.deck.add(new Card("Club",'7'));
        this.deck.add(new Card("Club",'8'));
        this.deck.add(new Card("Club",'9'));
        this.deck.add(new Card("Club",'X'));
        this.deck.add(new Card("Club",'J'));
        this.deck.add(new Card("Club",'Q'));
        this.deck.add(new Card("Club",'K'));
        this.deck.add(new Card("Club",'A'));

        this.deck.add(new Card("Heart",'7'));
        this.deck.add(new Card("Heart",'8'));
        this.deck.add(new Card("Heart",'9'));
        this.deck.add(new Card("Heart",'X'));
        this.deck.add(new Card("Heart",'J'));
        this.deck.add(new Card("Heart",'Q'));
        this.deck.add(new Card("Heart",'K'));
        this.deck.add(new Card("Heart",'A'));

        this.deck.add(new Card("Spade",'7'));
        this.deck.add(new Card("Spade",'8'));
        this.deck.add(new Card("Spade",'9'));
        this.deck.add(new Card("Spade",'X'));
        this.deck.add(new Card("Spade",'J'));
        this.deck.add(new Card("Spade",'Q'));
        this.deck.add(new Card("Spade",'K'));
        this.deck.add(new Card("Spade",'A'));

        this.deck.add(new Card("Diamond",'7'));
        this.deck.add(new Card("Diamond",'8'));
        this.deck.add(new Card("Diamond",'9'));
        this.deck.add(new Card("Diamond",'X'));
        this.deck.add(new Card("Diamond",'J'));
        this.deck.add(new Card("Diamond",'Q'));
        this.deck.add(new Card("Diamond",'K'));
        this.deck.add(new Card("Diamond",'A'));
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void distrib(){
        int player = 1;
    }

    public String readAction(String action){
        if (action.compareTo("COINCHE") == 0) {
            this.Coinched = true;
            return ("PLAYER X COINCHED");
        }
        if (action.compareTo("COUNTERCOINCHE") == 0) {
            this.CounterCoinched = true;
            return ("PLAYER X COUNTERCOINCHED");
        }
        if (action.compareTo("PASS") == 0)
            return ("PLAYER X PASSED");
        return "COMMAND INVALID";
    }

    public void addPlayer()
    {
        this.nbrPlayer += 1;
    }

    public void removePlayer()
    {
        this.nbrPlayer -= 1;
    }
}
