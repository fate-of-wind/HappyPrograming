package model;

// detailed number of each kind of card a player's hand have
public class DetailCardNum {
    private int cardNum;
    private Card card;

    //REQUIRES: number >= 0
    //EFFECTS: build a compound about number of a kind of card
    //kind of card is set to kind, its number is set to number
    public DetailCardNum(Card kind, int number) {
        this.card = kind;
        this.cardNum = number;
    }

    public int getNumber() {
        return cardNum;
    }

    //MODIFIES:this
    //EFFECTS: remove one card of this kind, so number of this kind minus 1
    public void removeOneCard() {
        this.cardNum--;
    }

    //MODIFIES:this
    //EFFECTS: add one card of this kind, so number of this kind plus 1
    public void addOneCard() {
        this.cardNum++;
    }
}
