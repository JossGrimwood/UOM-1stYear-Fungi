package cards;

public class Card {
    protected CardType type;
    protected String cardName;

    public Card(CardType type, String name) {
        cardName = name;
        this.type = type;
    }

    public CardType getType(){
        return type;
    }

    public String getName() {
        return cardName;
    }
}
