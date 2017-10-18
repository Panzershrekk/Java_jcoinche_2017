import java.util.Map;

public class Hand {

    private int inHand;
    private Map<String, Card> hand;


    public Hand() {
        inHand = 0;
    }

    public void addCard(String type, Character number) {
        hand.put(type+number, new Card(type, number));
        inHand += 1;
    }

    public void playCard(String type, Character number) {
        hand.remove(type+number);
        inHand -= 1;
    }
}
