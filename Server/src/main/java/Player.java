import java.util.Vector;

public class Player {

    private int playerNbr;
    private String channelId;
    private Vector<Card> deck;

    public Player(int playerNbr, String channelId)
    {
        this.playerNbr = playerNbr;
        this.channelId = channelId;
        this.deck = new Vector<Card>();
        System.out.println(playerNbr + " " + channelId);
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setPlayerNbr(int playerNbr) {
        this.playerNbr = playerNbr;
    }

    public int getPlayerNbr() {
        return playerNbr;
    }

    public String getChannelId() {
        return channelId;
    }

    public Vector<Card> getDeck() {
        return deck;
    }

    public void addCard(Character type, Character number)
    {
        this.deck.add(new Card(type, number));
    }

    public String getHand()
    {
        String card = "";
        for (Card c : deck)
            card += c.getType().toString() + c.getNumber().toString() + ";";
        return (card);
    }

    public void printDeck()
    {
        for (Card c : deck) {
            System.out.println(c.getType().toString() + c.getNumber().toString());
        }
        System.out.println("\n");
    }

    public void removeFromDeck(Card c)
    {
        deck.remove(c);
    }
}
