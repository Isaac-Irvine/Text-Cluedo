import java.util.ArrayList;
import java.util.List;

public class Player {
    private int num;
    private String character;
    private List<Card> cards;

    public Player(int num, String character) {
        this.num = num;
        this.character = character;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }


    public String toString() {
        return "Player " + num + ": " + character;
    }
}
