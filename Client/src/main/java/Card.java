public class Card {

    private Character      type;
    private Character   number;

    public Card(Character type, Character number) {
        this.type = type;
        this.number = number;

    }

    public void setType(Character type) {
        this.type = type;
    }

    public void setNumber(Character number) {
        this.number = number;
    }

    public Character getNumber() {
        return number;
    }

    public Character getType() {
        return type;
    }
}
