public class GameManager {

    enum Asset{
        CLUB,
        DIAMOND,
        SPADE,
        HEARTH,
    }

    private int TeamScore1;
    private int TeamScore2;
    private boolean GameStarted;
    //private Card[];
    private Asset currentAsset;
    private boolean Coinched;
    private boolean CounterCoinched;

    public GameManager() {
        this.TeamScore1 = 0;
        this.TeamScore2 = 0;
        this.Coinched = false;
        this.CounterCoinched = false;
    }

    public void shuffle(){
    }

    public void distrib(){

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
}
