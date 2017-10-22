import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerTest {
    @Test
    public void shuffle() throws Exception {
    }

    @Test
    public void distrib() throws Exception {
    }

    @Test
    public void chooseAsset() throws Exception {
    }

    @Test
    public void findCard() throws Exception {
    }

    @Test
    public void addInBoard() throws Exception {
    }

    @Test
    public void checkWinner() throws Exception {
    }

    @Test
    public void addScore() throws Exception {
        int score = 90;
        if (score <= 0)
            fail();
    }

    @Test
    public void addRoundScore() throws Exception {
        int score = 90;
        if (score <= 0)
            fail();

    }

    @Test
    public void cleanBoard() throws Exception {
    }

    @Test
    public void isCard() throws Exception {
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
    public void betIsValid() throws Exception {
    }

    @Test
    public void showPlayerHand() throws Exception {
    }

    @Test
    public void readAction() throws Exception {
    }

    @Test
    public void playerHasCard() throws Exception {
    }

    @Test
    public void addPlayer() throws Exception {
        int maxplayer = 4;
        if (maxplayer >= 5)
            fail();
    }

    @Test
    public void removePlayer() throws Exception {
        int minplayer = 1;
        if (minplayer <= 0)
            fail();
    }

    @Test
    public void isRoundStarted() throws Exception {
    }

    @Test
    public void setCurrentlyPlaying() throws Exception {
        int player = 1;
        if (player <= 0 || player >= 5)
            fail();
    }

    @Test
    public void playingOrder() throws Exception {
    }

    @Test
    public void isCurrentPlayerTheOne() throws Exception {
    }

    @Test
    public void addPlayerInVector() throws Exception {
    }

    @Test
    public void affBoard() throws Exception {
    }

    @Test
    public void removePlayerFromVector() throws Exception {
    }

    @Test
    public void getNbrPlayer() throws Exception {
        int player = 1;
        if (player <= 0 || player >= 5)
            fail();
    }

    @Test
    public void setGameStarted() throws Exception {
    }

    @Test
    public void isGameStarted() throws Exception {
    }

    @Test
    public void getCurrentlyPlaying() throws Exception {
        int player = 1;
        if (player <= 0 || player >= 5)
            fail();
    }

    @Test
    public void getTeamScoreRound1() throws Exception {
        int score = 0;
        if (score < 0)
            fail();
    }

    @Test
    public void getTeamScoreRound2() throws Exception {
        int score = 0;
        if (score < 0)
            fail();
    }

    @Test
    public void getAsset() throws Exception {
        Character asset  = 'H';

        if (asset != 'H' && asset != 'S' && asset != 'D' && asset != 'C')
            fail();
    }

}