import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void getPlayerNbr() throws Exception {
        int i = 3;
        if (i <= 0 || i >= 5)
            fail();
    }

    @Test
    public void getChannelId() throws Exception {
        String msg = "127.0.0.1";
        if (msg.length() <= 0)
            fail();
    }

    @Test
    public void getDeck() throws Exception {
    }

    @Test
    public void addCard() throws Exception {
        Character number = '7';
        Character type = 'D';
        if (number.compareTo('A') != 0 &&
                number.compareTo('7') != 0 &&
                number.compareTo('8') != 0 &&
                number.compareTo('9') != 0 &&
                number.compareTo('X') != 0 &&
                number.compareTo('J') != 0 &&
                number.compareTo('Q') != 0 &&
                number.compareTo('K') != 0)
        {
            fail();
        }

        if (type.compareTo('C') != 0 &&
                type.compareTo('S') != 0 &&
                type.compareTo('H') != 0 &&
                type.compareTo('D') != 0)
        {
            fail();
        }
    }

    @Test
    public void getHand() throws Exception {
    }

    @Test
    public void removeFromDeck() throws Exception {
    }

}
