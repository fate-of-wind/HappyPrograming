package ui;

import model.Hand;

public class Main {
    public static void main(String[] args) {
        new CardGame();
        Hand a = new Hand(5, 5, 5);
        System.out.println(a.getPaperNum() + a.getRockNum() + a.getScissorNum());
    }
}