import java.util.*;

public class GameManager {

    private int TeamScore1;
    private int TeamScore2;
    private int nbrPlayer;
    private boolean GameStarted;
    private boolean RoundStarted;
    private Vector<Card> deck;
    private Vector<Card> board;
    private Vector<Player> players;
    private int currentlyPlaying;
    private Character asset;
    private boolean Coinched;
    private boolean CounterCoinched;
    private int distribOrder;
    private int distribPlayer;
    private int betValue;

    public GameManager() {
        this.TeamScore1 = 0;
        this.TeamScore2 = 0;
        this.nbrPlayer = 0;
        this.Coinched = false;
        this.CounterCoinched = false;
        this.RoundStarted = false;
        this.asset = 'H';
        this.deck = new Vector<Card>();
        this.board = new Vector<Card>();
        this.players = new Vector<Player>();
        this.currentlyPlaying = 1;
        this.distribPlayer = 1;
        this.distribOrder = 0;
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
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public String distrib(){
        String card = "";
        int limit = distribOrder + 8;
        while (distribOrder != limit)
        {
            for (Player p : players)
            {
                if (p.getPlayerNbr() == distribPlayer) {
                    p.addCard(deck.elementAt(distribOrder).getType(), deck.elementAt(distribOrder).getNumber());
                }
            }
            card += (deck.elementAt(distribOrder).getType().toString() + deck.elementAt(distribOrder).getNumber().toString());
            card += ";";
            distribOrder++;
        }
        if (distribOrder > 30)
            distribOrder = 0;
        this.distribPlayer += 1;
        return ("CARD:" + card);
    }


    public Card findCard(Character type, Character number)
    {
        for (Card c: deck)
        {
            if (c.getType() == type && c.getNumber() == number)
                return (c);
        }
        return (null);
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

        for (Card c : board)
        {
            if (c.getType().compareTo(asset) == 0)
                score += c.getAssetScore();
            else
                score += c.getScore();
        }
        if (this.CounterCoinched == true)
            betValue *= 4;
        else if (this.Coinched == true)
            betValue *= 2;
        if (winner % 2 == 1)
            this.TeamScore1 += score + betValue;
        else
            this.TeamScore2 += score + betValue;
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

    public boolean betIsValid(String bet)
    {
        int i = 4;
        String betTmp = "";

        while (i < bet.length())
        {
            betTmp += bet.charAt(i);
            i++;
        }
        if (Integer.valueOf(betTmp) >= 80 && Integer.valueOf(betTmp) <= 160 && (Integer.valueOf(betTmp) % 10) == 0)
        {
            this.betValue = Integer.valueOf(betTmp);
            return (true);
        }
        return (false);
    }

    public String readAction(String action, String player){
        String msg = "";
        if (isGameStarted() == true) {
            if (this.board.size() == 4)
                return (checkWinner() + "");
            if (isCurrentPlayerTheOne(player) == true) {
                if (action.startsWith("BET ") && action.length() <= 7 && betIsValid(action) == true) {
                    msg = currentlyPlaying + "";
                    PlayingOrder();
                    return ("PLAYER" + msg + "BET" + betValue);
                }
                if (action.compareTo("COINCHE") == 0 && betValue > 0) {
                    this.Coinched = true;
                    msg = currentlyPlaying + "";
                    PlayingOrder();
                    return ("PLAYER " + msg + " COINCHED");
                }
                if (action.compareTo("COUNTERCOINCHE") == 0 && Coinched == true) {
                    this.CounterCoinched = true;
                    msg = currentlyPlaying + "";
                    PlayingOrder();
                    return ("PLAYER " + msg + " COUNTERCOINCHED");
                }
                if (action.compareTo("PASS") == 0) {
                    msg = currentlyPlaying + "";
                    PlayingOrder();
                    return ("PLAYER " + msg + " PASSED");
                }
                if (isRoundStarted() == true) {
                    if (action.startsWith("PLAY ") && action.length() == 7 && isCard(action.charAt(5), action.charAt(6)) == true) {
                        this.board.add(findCard(action.charAt(5), action.charAt(6)));
                        PlayingOrder();
                        if (this.board.size() == 4) {
                            int winner = checkWinner();
                            cleanBoard();
                            return ("Player played " + action.charAt(5) + action.charAt(6) + "\n" + winner);
                        } else
                            return ("Player played " + action.charAt(5) + action.charAt(6));
                    }
                }
                return "Command invalid";
            }
            return "Not your turn";
        }
        return "Awaiting player";
    }

    public void addPlayer()
    {
        this.nbrPlayer += 1;
    }

    public void removePlayer()
    {
        this.nbrPlayer -= 1;
    }

    public boolean isRoundStarted() {
        return RoundStarted;
    }

    public void setCurrentlyPlaying(int currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public void PlayingOrder()
    {
        if (this.currentlyPlaying == 4)
            setCurrentlyPlaying(1);
        else
            setCurrentlyPlaying(this.currentlyPlaying + 1);
    }

    public boolean isCurrentPlayerTheOne(String channelId)
    {
        for (Player p : players)
        {
            if (p.getPlayerNbr() == currentlyPlaying && p.getChannelId().compareTo(channelId) == 0)
                return (true);
        }
        return (false);
    }

    public void addPlayerInVector(String id)
    {
        int i = 1;
        if (this.players.size() == 0)
            this.players.add(new Player(1, id));
        else
        {
            for (Player p : this.players) {
                if (p.getPlayerNbr() == i)
                    i++;
            }
            this.players.add(new Player(i, id));
        }
    }

    public void removePlayerFromVector(String id)
    {
        for (Player p : this.players)
        {
            if (p.getChannelId().compareTo(id) == 0) {
                this.players.remove(p);
            }
        }
    }

    public int getNbrPlayer() {
        return nbrPlayer;
    }

    public void setGameStarted(boolean gameStarted) {
        GameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return GameStarted;
    }

    public int getCurrentlyPlaying() {
        return currentlyPlaying;
    }
}
