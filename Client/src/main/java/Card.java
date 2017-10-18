public class Card {

    private String      type;
    private Character   number;

    public Card(String type, Character number) {
        this.type = type;
        this.number = number;

    }

    public void setNumber(Character number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Character getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}
