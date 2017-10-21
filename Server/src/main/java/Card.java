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

    public void setNumber(Character number) {
        this.number = number;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAssetScore(int assetScore) {
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