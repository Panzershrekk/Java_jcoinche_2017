import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void setNumber() throws Exception {
        Character number = 'A';

        if (number.compareTo('A') != 0 &&
                number.compareTo('7') != 0 &&
                number.compareTo('8') != 0 &&
                number.compareTo('9') != 0 &&
                number.compareTo('X') != 0 &&
                number.compareTo('J') != 0 &&
                number.compareTo('Q') != 0 &&
                number.compareTo('K') != 0)
        {
            fail("Wrong type of Card");
        }
    }

    @Test
    public void setType() throws Exception {
        String type = "Spade";

        if (type.compareTo("Diamond") != 0 &&
                type.compareTo("Spade") != 0 &&
                type.compareTo("Heart") != 0 &&
                type.compareTo("Club") != 0)
        {
            fail("Wrong type of Card");
        }
    }

}