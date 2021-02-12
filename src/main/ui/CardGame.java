package ui;

import model.Card;
import model.Hand;
import model.Player2PastShown;

import java.util.Scanner;

public class CardGame {
    private Scanner input;
    private Hand player1;
    private Hand player2;
    private Card rockCard;
    private Card paperCard;
    private Card scissorCard;
    private Card player1Chosen;
    private Card player2Chosen;
    private String chosen;
    private Player2PastShown pastP2Shown;

    // EFFECTS: runs the card game
    public CardGame() {
        runCardGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCardGame() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (checkLoser().equals("player1 lose")) {
                System.out.println("you lose, even cannot beat computer");
                keepGoing = false;
            } else if (checkLoser().equals("player2 lose")) {
                System.out.println("Congratulation!!! you made it!!!");
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
            printHandCondition();
        } else if (command.equals("n")) {
            init();
        } else if (command.equals("p")) {
            System.out.println(pastP2Shown.toMakeString());
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
        pastP2Shown = new Player2PastShown();

        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new game(make you hand back to 15, I wish you won't lose again)");
        System.out.println("\ts -> show card");
        System.out.println("\tp -> show past player2 shown");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays three cards to user
    private void displayInShowCard() {
        System.out.println("\nselect card to compare");
        System.out.println("\tr->rock");
        System.out.println("\tp->paper");
        System.out.println("\ts->scissor");
    }

    // EFFECTS: player show their card and compare them, then transfer result to evaluation
    private void showCard() {
        displayInShowCard();
        String result = "";
        this.player2Chosen = randomShow();
        pastP2Shown.addCardToPastShown(player2Chosen);
        chosen = input.next();
        recordPlayer1Chosen();
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
        System.out.println("result: " + result + " in this turn");
        evaluateResult(result);
    }

    //MODIFIES: this
    //EFFECTS: evaluate the result after show card, if win then get opponent's shown card.
    //if lose, then give your shown card to opponent. if evened, then both throw shown card.
    private void evaluateResult(String result) {
        if (result.equals("win")) {
            player1.addSpecificOneCard(player2Chosen);
            player2.removeSpecificOneCard(player2Chosen);
        } else if (result.equals("lose")) {
            player1.removeSpecificOneCard(player1Chosen);
            player2.addSpecificOneCard(player1Chosen);
        } else if (result.equals("evened")) {
            player1.removeSpecificOneCard(player1Chosen);
            player2.removeSpecificOneCard(player2Chosen);
        }
    }


    //MODIFIES: this
    //EFFECTS: convert input string to Card that player1 chosen
    private void recordPlayer1Chosen() {
        if (chosen.equals("r")) {
            this.player1Chosen = rockCard;
        } else if (chosen.equals("p")) {
            this.player1Chosen = paperCard;
        } else if (chosen.equals("s")) {
            this.player1Chosen = scissorCard;
        }
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
        System.out.println("\nCondition:");
        System.out.print("\tMy Rock Num: " + player1.getRockNum());
        System.out.println("\t    Opponent Rock Num: " + player2.getRockNum());
        System.out.print("\tMy Paper Num: " + player1.getPaperNum());
        System.out.println("\t    Opponent Paper Num: " + player2.getPaperNum());
        System.out.print("\tMy Scissor Num: " + player1.getScissorNum());
        System.out.println("\tOpponent Scissor Num: " + player2.getScissorNum());
    }

    //EFFECTS: check if two players lose their all cards
    private String checkLoser() {
        if (player1.getRockNum() == 0 && player1.getScissorNum() == 0 && player1.getPaperNum() == 0) {
            return "player1 lose";
        } else if (player2.getRockNum() == 0 && player2.getScissorNum() == 0 && player2.getPaperNum() == 0) {
            return "player2 lose";
        } else {
            return "";
        }
    }
}
