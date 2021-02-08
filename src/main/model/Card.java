package model;
// Card is three kind of card: rock, paper, and scissor
// Rock is large than scissor but smaller than paper. Paper is larger than rock but smaller than scissor.
// It's a circle.

public class Card {
    private String kinds;

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
}
