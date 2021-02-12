package ui;

import model.Card;
import model.Hand;

import java.util.Scanner;

public class CardGame {
    private Scanner input;
    private Hand player1;
    private Hand player2;
    private Card rockCard;
    private Card paperCard;
    private Card scissorCard;
    private Card player2Chosen;


    // EFFECTS: runs the card game
    public CardGame() {
        runCardGame();
    }

    private void runCardGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    } // learn from TellerApp


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            showCard();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes two players and its hand
    private void init() {
        player1 = new Hand(5, 5, 5);
        player2 = new Hand(5, 5, 5);
        rockCard = new Card("rock");
        paperCard = new Card("paper");
        scissorCard = new Card("scissor");

        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new game");
        System.out.println("\ts -> show card");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: player show their card and compare them
    private String showCard() {
        System.out.println("\nselect card to compare");
        System.out.println("\tr->rock");
        System.out.println("\tp->paper");
        System.out.println("\ts->scissor");

        String result = "";
        player2Chosen = randomShow();
        String chosen = input.next();
        if (chosen.equals("r")) {
            if (player1.getRockNum() > 0) {
                result = rockCard.compare(player2Chosen);

            }
        } else if (chosen.equals("p")) {
            if (player1.getPaperNum() > 0) {
                result = paperCard.compare(player2Chosen);
            }
        } else if (chosen.equals("s")) {
            if (player1.getScissorNum() > 0) {
                result = scissorCard.compare(player2Chosen);
            }
        } else {
            System.out.println("Selection not valid...");
        }
        System.out.println(result);
        return result;
    }


    //EFFECT: player2 random show a card
    private Card randomShow() {
        double random = Math.random();
        if (random >= 0 && random <= 0.33) {
            return rockCard;
        } else if (random > 0.33 && random <= 0.66) {
            return scissorCard;
        } else {
            return paperCard;
        }
    }

    // EFFECTS: prints number of two player's three kinds card
    private void printHandCondition() {

    }

}
