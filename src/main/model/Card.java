package model;
// Card is three kind of card: rock, paper, and scissor
// Rock is large than scissor but smaller than paper. Paper is larger than rock but smaller than scissor.
// It's a circle.

import org.json.JSONObject;
import persistence.Writable;

public class Card implements Writable {
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
    // EFFECTS: Compare card to show which one is winner
    public String compare(Card card) {
        if (this.getKinds().equals("rock")) {
            return rockCompare(card);
        } else if (this.getKinds().equals("scissor")) {
            return scissorCompare(card);
        } else  {
            return paperCompare(card);
        }
    }

    // REQUIRES: only in ROCK, PAPER, SCISSOR
    // EFFECTS: Compare rock card to show which one is winner
    public String rockCompare(Card card) {
        if (card.getKinds().equals("rock")) {
            return "evened";
        } else if (card.getKinds().equals("paper")) {
            return "lose";
        } else {
            return "win";
        }
    }

    // REQUIRES: only in ROCK, PAPER, SCISSOR
    // EFFECTS: Compare scissor card to show which one is winner
    public String scissorCompare(Card card) {
        if (card.getKinds().equals("rock")) {
            return "lose";
        } else if (card.getKinds().equals("scissor")) {
            return "evened";
        } else {
            return "win";
        }
    }

    // REQUIRES: only in ROCK, PAPER, SCISSOR
    // EFFECTS: Compare paper card to show which one is winner
    public String paperCompare(Card card) {
        if (card.getKinds().equals("rock")) {
            return "win";
        } else if (card.getKinds().equals("paper")) {
            return "evened";
        } else {
            return "lose";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("kind",kinds);
        return json;
    }
}


