import java.util.*;

public class GameManager {

    private int TeamScore1;
    private int TeamScore2;
    private int nbrPlayer;
    private boolean GameStarted;
    private Vector<Card> deck;
    private Vector<Card> board;
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
        this.board = new Vector<Card>();
        this.deck.add(new Card('C','7', 0, 0));
        this.deck.add(new Card('C','8', 0, 0));
        this.deck.add(new Card('C','9', 0, 14));
        this.deck.add(new Card('C','X', 10, 10));
        this.deck.add(new Card('C','J', 2, 20));
        this.deck.add(new Card('C','Q', 3, 3));
        this.deck.add(new Card('C','K', 4, 4));
        this.deck.add(new Card('C','A', 11, 11));

        this.deck.add(new Card('H','7', 0, 0));
        this.deck.add(new Card('H','8', 0, 0));
        this.deck.add(new Card('H','9', 0, 14));
        this.deck.add(new Card('H','X', 10, 10));
        this.deck.add(new Card('H','J', 2, 20));
        this.deck.add(new Card('H','Q', 3, 3));
        this.deck.add(new Card('H','K', 4, 4));
        this.deck.add(new Card('H','A', 11, 11));

        this.deck.add(new Card('S','7', 0, 0));
        this.deck.add(new Card('S','8', 0, 0));
        this.deck.add(new Card('S','9', 0, 14));
        this.deck.add(new Card('S','X', 10, 10));
        this.deck.add(new Card('S','J', 2, 20));
        this.deck.add(new Card('S','Q', 3, 3));
        this.deck.add(new Card('S','K', 4, 4));
        this.deck.add(new Card('S','A', 11, 11));

        this.deck.add(new Card('D','7', 0, 0));
        this.deck.add(new Card('D','8', 0, 0));
        this.deck.add(new Card('D','9', 0, 14));
        this.deck.add(new Card('D','X', 10, 10));
        this.deck.add(new Card('D','J', 2, 20));
        this.deck.add(new Card('D','Q', 3, 3));
        this.deck.add(new Card('D','K', 4, 4));
        this.deck.add(new Card('D','A', 11, 11));
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void distrib(){
        int player = 1;
    }

    public void addInBoard(Card played)
    {
        this.board.add(played);
    }

    public int checkWinner()
    {
        int i = 1;
        int winner = 1;
        Card currentWinner = new Card('N', 'N', -1, -1);
        Character asset = 'H';

        for (Card c : board)
        {
            if (c.getType().compareTo(asset) == 0)
            {
                //DOUBLE ASSET SCENARIO
                if (currentWinner.getType().compareTo(asset) == 0) {
                    if (c.getAssetScore() > currentWinner.getAssetScore()) {
                        currentWinner = c;
                        winner = i;
                    }
                }
                //CURRENT ASSET SCENARIO
                else if (c.getAssetScore() > currentWinner.getScore()) {
                    currentWinner = c;
                    winner = i;
                }
            }
            else {
                // CURRENT WINNER ASSET SCENARIO
                if (currentWinner.getType().compareTo(asset) == 0) {
                    if (c.getScore() > currentWinner.getAssetScore()) {
                        currentWinner = c;
                        winner = i;
                    }
                }
                // NO ASSET SCENARIO
                else if (c.getScore() > currentWinner.getScore()) {
                    currentWinner = c;
                    winner = i;
                }
            }
            i++;
        }
        return (winner);
    }

    public void addScore(int winner)
    {
        int score = 0;
        Character asset = 'C';

        for (Card c : board)
        {
            if (c.getType().compareTo(asset) == 0)
                score += c.getAssetScore();
            else
                score += c.getScore();
        }
        if (this.CounterCoinched == true)
            score *= 4;
        else if (this.Coinched == true)
            score *= 2;
        if (winner % 2 == 1)
            this.TeamScore1 += score;
        else
            this.TeamScore2 += score;
    }

    public void cleanBoard()
    {
        this.board.clear();
    }

    public boolean isCard(Character type, Character number)
    {
        if (number.compareTo('A') != 0 &&
                number.compareTo('7') != 0 &&
                number.compareTo('8') != 0 &&
                number.compareTo('9') != 0 &&
                number.compareTo('X') != 0 &&
                number.compareTo('J') != 0 &&
                number.compareTo('Q') != 0 &&
                number.compareTo('K') != 0)
        {
            return (false);
        }

        if (type.compareTo('C') != 0 &&
                type.compareTo('S') != 0 &&
                type.compareTo('H') != 0 &&
                type.compareTo('D') != 0)
        {
            return false;
        }
        return true;
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
        /*if (this.GameStarted == true)
        {*/
            if (action.startsWith("PLAY ") && action.length() == 7 && isCard(action.charAt(5), action.charAt(6)) == true)
                return ("Player played " + action.charAt(5) + action.charAt(6));
        /*}*/
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
