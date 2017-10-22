import java.util.Vector;

public class Hand {
    private Vector<Card> hand;

    public Hand() {
        this.hand = new Vector<Card>();
    }

    public void readHand(String sendCard)
    {
        if (sendCard.startsWith("CARD:"))
            fillHand(sendCard);
    }

    public void fillHand(String info)
    {
        int i = 0;
        int pos1 = 5;
        int pos2 = 6;

        while (i != 8)
        {
            this.hand.add(new Card(info.charAt(pos1), info.charAt(pos2)));
            pos1 += 3;
            pos2 += 3;
            i++;
        }
        printHand();
    }

    public void printHand()
    {
        for (Card c : hand)
            System.out.println(c.getType().toString() + c.getNumber().toString() + " ; ");
    }
}
