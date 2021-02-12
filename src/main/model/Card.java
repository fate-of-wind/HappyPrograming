package model;
// Card is three kind of card: rock, paper, and scissor
// Rock is large than scissor but smaller than paper. Paper is larger than rock but smaller than scissor.
// It's a circle.

public class Card {
    private final String kinds;

    /*
    REQUIRES: kind can only be "rock", "paper", or "scissor"
    EFFECTS: kind of card is set to kind, only three kinds of card
    */
    public Card(String kind) {
        this.kinds = kind;
    }

    public String getKinds() {
        return kinds;
    }

    // REQUIRES: only in ROCK, PAPER, SCISSOR
    // EFFECTS
    public String compare(Card card) {
        if (this.getKinds().equals("rock") && card.getKinds().equals("rock")) {
            return "=";
        } else if (this.getKinds().equals("rock") && card.getKinds().equals("paper")) {
            return "<";
        } else if (this.getKinds().equals("rock") && card.getKinds().equals("scissor")) {
            return ">";
        } else if (this.getKinds().equals("scissor") && card.getKinds().equals("rock")) {
            return "<";
        } else if (this.getKinds().equals("scissor") && card.getKinds().equals("scissor")) {
            return "=";
        } else if (this.getKinds().equals("scissor") && card.getKinds().equals("paper")) {
            return ">";
        } else if (this.getKinds().equals("paper") && card.getKinds().equals("rock")) {
            return ">";
        } else if (this.getKinds().equals("paper") && card.getKinds().equals("paper")) {
            return "=";
        } else {
            return "<";
        }
    }
}
