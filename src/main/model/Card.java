package model;

import persistence.Writable;
import org.json.JSONObject;

// Card is three kind of card: rock, paper, and scissor
// Rock is large than scissor but smaller than paper. Paper is larger than rock but smaller than scissor.
// It's a circle.
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


    // EFFECTS: Compare card to show which one is winner
    // throw a exception when card is out of Rock,Paper,Scissor
    public String compare(Card card) throws InvalidCard {
        if (!card.getKinds().equals("rock") && !card.getKinds().equals("paper") && !card.getKinds().equals("scissor")) {
            throw new InvalidCard();
        } else if (this.getKinds().equals("rock")) {
            return rockCompare(card);
        } else if (this.getKinds().equals("scissor")) {
            return scissorCompare(card);
        } else  {
            return paperCompare(card);
        }
    }

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


