import java.util.*;

public class GameManager {

    private int TeamScore1;
    private int TeamScore2;
    private int TeamScoreRound1;
    private int TeamScoreRound2;
    private int nbrPlayer;
    private boolean GameStarted;
    private int nbrInitAction;
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
        this.TeamScoreRound1 = 0;
        this.TeamScoreRound2 = 0;
        this.nbrPlayer = 0;
        this.Coinched = false;
        this.CounterCoinched = false;
        this.RoundStarted = false;
        this.nbrInitAction = 0;
        this.asset = chooseAsset();
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
        return ("");
    }

    public Character chooseAsset()
    {

        int random = 1 + (int)(Math.random() * ((4 - 1) + 1));
        Character asset = 'H';

        if (random == 1)
            asset = 'H';
        else if (random == 2)
            asset = 'C';
        else if (random == 4)
            asset = 'D';
        else if (random == 3)
            asset = 'S';
        return (asset);
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
        if (this.CounterCoinched == true)
            betValue *= 4;
        else if (this.Coinched == true)
            betValue *= 2;
        if (winner % 2 == 1)
            this.TeamScore1 += TeamScoreRound1 + betValue;
        else
            this.TeamScore2 += TeamScoreRound2 + betValue;
    }

    public void addRoundScore(int winner)
    {
        int score = 0;

        for (Card c : board)
        {
            if (c.getType().compareTo(asset) == 0)
                score += c.getAssetScore();
            else
                score += c.getScore();
        }
        if (winner % 2 == 1)
            this.TeamScoreRound1 += score;
        else
            this.TeamScoreRound2 += score;
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

    public String showPlayerHand(String player)
    {
        String msg = "HAND: ";
        for (Player p : players)
        {
            if (p.getChannelId().compareTo(player) == 0)
                msg += p.getHand();
        }
        return (msg);
    }

    public String readAction(String action, String player){
        String msg = "";
        if (isGameStarted() == true) {
            if (this.board.size() == 4)
                return (checkWinner() + "");
            if (action.compareTo("HAND") == 0)
                return (showPlayerHand(player));
           if (isCurrentPlayerTheOne(player) == true) {
                if (isRoundStarted() == false) {
                    if (action.startsWith("BET ") && action.length() <= 7 && betIsValid(action) == true) {
                        msg = currentlyPlaying + "";
                        PlayingOrder();
                        nbrInitAction += 1;
                        if (nbrInitAction == 4)
                            RoundStarted = true;
                        return ("ACTION: PLAYER" + msg + "BET" + betValue);
                    }
                    if (action.compareTo("COINCHE") == 0 && betValue > 0) {
                        this.Coinched = true;
                        msg = currentlyPlaying + "";
                        PlayingOrder();
                        nbrInitAction += 1;
                        if (nbrInitAction == 4)
                            RoundStarted = true;
                        return ("ACTION: PLAYER " + msg + " COINCHED");
                    }
                    if (action.compareTo("COUNTERCOINCHE") == 0 && Coinched == true) {
                        this.CounterCoinched = true;
                        msg = currentlyPlaying + "";
                        PlayingOrder();
                        nbrInitAction += 1;
                        if (nbrInitAction == 4)
                            RoundStarted = true;
                        return ("ACTION: PLAYER " + msg + " COUNTERCOINCHED");
                    }
                    if (action.compareTo("PASS") == 0) {
                        msg = currentlyPlaying + "";
                        PlayingOrder();
                        nbrInitAction += 1;
                        if (nbrInitAction == 4)
                            RoundStarted = true;
                        return ("ACTION: PLAYER " + msg + " PASSED");
                    }
                }
                else  {
                    if (action.startsWith("PLAY ") && action.length() == 7 && isCard(action.charAt(5), action.charAt(6)) == true && playerHasCard(player, action.charAt(5), action.charAt(6)) == true) {
                        this.board.add(findCard(action.charAt(5), action.charAt(6)));
                        PlayingOrder();
                        if (this.board.size() == 4) {
                            int winner = checkWinner();
                            addRoundScore(winner);
                            cleanBoard();
                            if (TeamScore1 >= 3000)
                                return ("ACTION: TEAM 1 WON.");
                            if (TeamScore2 >= 3000)
                                return ("ACTION: TEAM 2 WON.");
                            if (players.elementAt(1).getDeck().isEmpty() == true)
                            {
                                nbrInitAction = 0;
                                RoundStarted = false;
                                shuffle();
                                distrib();
                                chooseAsset();
                                addScore(winner);
                                betValue = 0;
                                return ("ACTION: Round is over Re-shuffling for everyone.\nTeam1 won:" + getTeamScoreRound1() + "\nTeam2 won: " + getTeamScoreRound2()  +" this round\nCurrent asset is " + asset);
                            }
                            return ("ACTION: Player played " + action.charAt(5) + action.charAt(6) + "\n");
                        } else
                            return ("ACTION: Player played " + action.charAt(5) + action.charAt(6));
                    }
                    else
                        return ("INFO: you don't have this card");
                }
                return "INFO: Command invalid";
            }
            return "INFO: Not your turn";
        }
        return "INFO: Awaiting player";
    }


    public boolean playerHasCard(String player, Character type, Character number)
    {
        for (Player p : players)
        {
            if (p.getChannelId().compareTo(player) == 0)
            {
                for (Card c: p.getDeck())
                {
                    if (c.getNumber().compareTo(number) == 0 && c.getType().compareTo(type) == 0) {
                        p.removeFromDeck(c);
                        return (true);
                    }
                }
            }
        }
        return (false);
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

    public String affBoard()
    {

        String msg = "Board:";
        for (Card c : board)
            msg += c.getType().toString() + c.getNumber().toString() + ";" ;
        return (msg);
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

    public int getTeamScoreRound1() {
        return TeamScoreRound1;
    }

    public int getTeamScoreRound2() {
        return TeamScoreRound2;
    }

    public Character getAsset() {
        return asset;
    }
}
