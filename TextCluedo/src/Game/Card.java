package Game;

import java.util.Objects;

public class Card {
    /**
     * Game.Card type could be room, suspect or weapon.
     */
    public enum CardType {
        ROOM, SUSPECT, WEAPON
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
     * Game.Card Type
     * @return
     */
    public CardType getType() {
        return type;
    }

    /**
     * Game.Card equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name) &&
                type == card.type;
    }

    /**
     * Game.Card hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    /**
     * Game.Card name
     * @return
     */
    public String toString() {
        return name;
    }
}
