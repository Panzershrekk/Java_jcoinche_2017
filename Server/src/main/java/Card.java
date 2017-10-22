public class Card {

    private Character      type;
    private Character   number;
    private int score;
    private int assetScore;

    public Card(Character type, Character number)
    {
        this.type = type;
        this.number = number;
    }

    public Card(Character type, Character number, int score, int assetScore) {
        this.type = type;
        this.number = number;
        this.score = score;
        this.assetScore = assetScore;
    }

    public Character getNumber() {
        return number;
    }

    public Character getType() {
        return type;
    }

    public int getAssetScore() {
        return assetScore;
    }

    public int getScore() {
        return score;
    }
}