public class Card {
    /**
     * Card type could be room, suspect or weapon.
     */
    public enum CardType {
        ROOM, SUSPECT, WEAPON;
    }

    private String name;
    private CardType type;

    /**
     * Create a card
     * @param name name of the item on the card
     * @param type type of card
     */
    public Card(String name, CardType type) {
        this.name = name;
        this.type = type;
    }


    /**
     * Get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Card Type
     * @return
     */
    public CardType getType() {
        return type;
        throw new Invalid
    }

    /**
     * Card name
     * @return
     */
    public String toString() {
        return name;
    }
}
