package model;
// Hand is hand card of each player. At first, each Hand contains 15 cards. - 5 rock, 5 paper, and 5 scissors

public class Hand {
    Card rockCard = new Card("rock");
    Card paperCard = new Card("paper");
    Card scissorCard = new Card("scissor");


    DetailCardNum rock;
    DetailCardNum paper;
    DetailCardNum scissor;

    //REQUIRES: rockNum, paperNum, scissorNum >=0
    //EFFECT: number of three kind of card in hand is to rockNum,paperNum,scissorNum
    //set three detailed card number to rock, paper, scissor in hand
    public Hand(int rockNum, int paperNum, int scissorNum) {
        rock = new DetailCardNum(rockCard, rockNum);
        paper = new DetailCardNum(paperCard, paperNum);
        scissor = new DetailCardNum(scissorCard, scissorNum);
    }

    public int getRockNum() {
        return this.rock.getNumber();
    }

    public int getPaperNum() {
        return this.paper.getNumber();
    }

    public int getScissorNum() {
        return this.scissor.getNumber();
    }

    //MODIFIES:this
    //EFFECTS: remove one card of specific kind, so number of this kind minus 1
    public void removeSpecificOneCard(Card card) {
        if (card.getKinds().equals(rockCard.getKinds())) {
            this.rock.removeOneCard();
        } else if (card.getKinds().equals(paperCard.getKinds())) {
            this.paper.removeOneCard();
        } else {
            this.scissor.removeOneCard();
        }
    }

    //MODIFIES:this
    //EFFECTS: add one card of specific kind, so number of this kind minus 1
    public void addSpecificOneCard(Card card) {
        if (card.getKinds().equals(rockCard.getKinds())) {
            this.rock.addOneCard();
        } else if (card.getKinds().equals(paperCard.getKinds())) {
            this.paper.addOneCard();
        } else {
            this.scissor.addOneCard();
        }
    }
}

