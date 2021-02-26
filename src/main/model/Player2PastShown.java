package model;

import java.util.ArrayList;

public class Player2PastShown {
    private ArrayList<Card> player2PastShow;

    public Player2PastShown() {
        player2PastShow = new ArrayList<>();
    }

    public ArrayList<Card> getArrayList() {
        return player2PastShow;
    }

    //MODIFIES: this
    //EFFECTS: add a card to player2 past shown card list
    public void addCardToPastShown(Card card) {
        player2PastShow.add(card);
    }


    //EFFECTS: transfer those element in list into string
    public String toMakeString() {
        String pastShown = "";
        for (Card c : this.player2PastShow) {
            pastShown = pastShown + c.getKinds() + " ";
        }
        return pastShown;
    }
}
