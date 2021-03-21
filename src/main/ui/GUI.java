package ui;

import model.Card;
import model.Hand;
import model.Player2PastShown;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Box;


public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/pastPlayer2Shown.json";
    private static final String JSON_STOREForPlayer1Hand = "./data/player1Hand.json";
    private static final String JSON_STOREForPlayer2Hand = "./data/player2Hand.json";
    private Scanner input;
    private Hand player1;
    private Hand player2;
    private Card rockCard;
    private Card paperCard;
    private Card scissorCard;
    private Card player1Chosen;
    private Card player2Chosen;
    private String chosen;
    private String result;
    private Player2PastShown pastP2Shown;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private JsonReader jsonReaderForPlayer1Hand;
    private JsonWriter jsonWriterForPlayer1Hand;
    private JsonReader jsonReaderForPlayer2Hand;
    private JsonWriter jsonWriterForPlayer2Hand;
    private JLabel myRockNum;
    private JLabel myScissorNum;
    private JLabel myPaperNum;
    private JLabel otherRockNum;
    private JLabel otherScissorNum;
    private JLabel otherPaperNum;
    private JLabel myShown;
    private JLabel opponentShown;
    private JLabel resultLabel;

    // MODIFIES: this
    // EFFECTS: initializes two players and its hand
    private void init() {
        player1 = new Hand(5, 5, 5);
        player2 = new Hand(5, 5, 5);
        rockCard = new Card("rock");
        paperCard = new Card("paper");
        scissorCard = new Card("scissor");
        pastP2Shown = new Player2PastShown();
        result = "";
        input = new Scanner(System.in);

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReaderForPlayer1Hand = new JsonReader(JSON_STOREForPlayer1Hand);
        jsonWriterForPlayer1Hand = new JsonWriter(JSON_STOREForPlayer1Hand);
        jsonReaderForPlayer2Hand = new JsonReader(JSON_STOREForPlayer2Hand);
        jsonWriterForPlayer2Hand = new JsonWriter(JSON_STOREForPlayer2Hand);
    }

    public GUI() {
        init();
        JFrame frame = new JFrame("Card Game");
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JPanel panelCardButton = new JPanel();
        JPanel panelAbove = new JPanel();
        JPanel panelCondition = new JPanel();
        JPanel panelPastShown = new JPanel();
        JPanel panelTool = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel();


        JButton rock = new JButton();
        JButton paper = new JButton();
        JButton scissor = new JButton();
        JButton save = new JButton("save");
        JButton load = new JButton("load");
        JButton newGame = new JButton("nameGame");
        JButton switchMode = new JButton("switch");

        save.setActionCommand("save");
        load.setActionCommand("load");
        save.addActionListener(this);
        load.addActionListener(this);
        rock.setActionCommand("rock");
        rock.addActionListener(this);
        scissor.setActionCommand("scissor");
        scissor.addActionListener(this);
        paper.setActionCommand("paper");
        paper.addActionListener(this);
        newGame.setActionCommand("newGame");
        newGame.addActionListener(this);
        switchMode.setActionCommand("switch");
        switchMode.addActionListener(this);


        rock.setIcon(new ImageIcon("./data/rock.png"));
        paper.setIcon(new ImageIcon("./data/paper.png"));
        scissor.setIcon(new ImageIcon("./data/scissor.png"));
        Dimension cardSize = new Dimension(200,300);
        rock.setPreferredSize(cardSize);
        paper.setPreferredSize(cardSize);
        scissor.setPreferredSize(cardSize);

        panel.setBackground(Color.WHITE);
        panelCondition.setBackground(Color.LIGHT_GRAY);
        panelPastShown.setBackground(Color.cyan);
        panelRight.setBackground(Color.LIGHT_GRAY);
        panelTool.setBackground(Color.WHITE);
        panelAbove.setBackground(Color.WHITE);
        panelCardButton.setBackground(Color.WHITE);

        panelCondition.setPreferredSize(new Dimension(600,300));
        panelPastShown.setPreferredSize(new Dimension(500,300));
        panel.setPreferredSize(new Dimension(1000,600));
        panelTool.setPreferredSize(new Dimension(400,600));
        panelRight.setPreferredSize(new Dimension(500,600));
        panelCenter.setPreferredSize(new Dimension(400,600));
        save.setPreferredSize(new Dimension(100,100));
        load.setPreferredSize(new Dimension(100,100));

        JTextArea pastShownReport = new JTextArea("",10,40);
        JScrollPane jsp = new JScrollPane(pastShownReport);
        Dimension size = pastShownReport.getPreferredSize();
        jsp.setBounds(110,90,size.width,size.height);


        myRockNum = new JLabel("my rock num:" + "5");
        myScissorNum = new JLabel("my scissor num:" + "5");
        myPaperNum = new JLabel("my paper num:" + "5");
        otherRockNum = new JLabel("opponent rock num:" + "5");
        otherScissorNum = new JLabel("opponent scissor num:" + "5");
        otherPaperNum = new JLabel("opponent paper num:" + "5");
        myShown = new JLabel("I show:");
        opponentShown = new JLabel("player2 show:");


        resultLabel = new JLabel("result:");
        resultLabel.setFont(new Font("", Font.BOLD, 40));
        panel.add(resultLabel);

        myRockNum.setFont(new Font("", Font.BOLD, 20));
        myScissorNum.setFont(new Font("", Font.BOLD, 20));
        myPaperNum.setFont(new Font("", Font.BOLD, 20));
        otherRockNum.setFont(new Font("", Font.BOLD, 20));
        otherScissorNum.setFont(new Font("", Font.BOLD, 20));
        otherPaperNum.setFont(new Font("", Font.BOLD, 20));
        myShown.setFont(new Font("", Font.BOLD, 30));
        opponentShown.setFont(new Font("", Font.BOLD, 30));
        pastShownReport.setFont(new Font("", Font.BOLD, 20));


        panelPastShown.add(jsp);
        panelPastShown.add(switchMode);

        panelAbove.add(panelCondition);

        panelAbove.add(panelPastShown);

        panelCardButton.add(rock);
        panelCardButton.add(scissor);
        panelCardButton.add(paper);

        panelCondition.add(myShown);
        panelCondition.add(opponentShown);


        panelAbove.setLayout(new GridLayout(1,2,100,5));
        panelTool.setLayout(new GridLayout(2,2,5,5));
        panelCondition.setLayout(new GridLayout(2,1,5,5));
        panelRight.setLayout(new GridLayout(3,2,5,5));
        panelRight.add(myRockNum);
        panelRight.add(otherRockNum);
        panelRight.add(myScissorNum);
        panelRight.add(otherScissorNum);
        panelRight.add(myPaperNum);
        panelRight.add(otherPaperNum);


        panelTool.add(save);
        panelTool.add(load);
        panelTool.add(newGame);

        frame.add(panelTool,BorderLayout.WEST);
        frame.add(panelRight,BorderLayout.EAST);
        frame.add(panelCenter,BorderLayout.CENTER);
        frame.add(panel);

        frame.add(panelCardButton,BorderLayout.SOUTH);
        frame.add(panelAbove,BorderLayout.NORTH);
        frame.setBounds(1900,1000,1700,1100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //EFFECTS:set font size on Card number report


    @Override
    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("rock")) {
            rockActionPerform();
            updateCondition();
        } else if (e.getActionCommand().equals("scissor")) {
            scissorActionPerform();
            updateCondition();
        } else if (e.getActionCommand().equals("paper")) {
            paperActionPerform();
            updateCondition();
        } else if (e.getActionCommand().equals("save")) {
            saveAll();
        } else if (e.getActionCommand().equals("load")) {
            loadAll();
            updateConditionAfterLoad();
        } else if (e.getActionCommand().equals("newGame")) {
            init();
        } else {
            init();
        }

    }

    public static void main(String[] args) {
        new GUI();
    }

    //EFFECTS: When click paper card, perform that
    private void paperActionPerform() {
        this.player1Chosen = paperCard;
        this.player2Chosen = randomShow();
        pastP2Shown.addCardToPastShown(player2Chosen);
        if (player1.getPaperNum() > 0) {
            result = paperCard.compare(player2Chosen);
            evaluateResult(result);
        }
    }

    //EFFECTS: When click scissor card, perform that
    private void scissorActionPerform() {
        this.player1Chosen = scissorCard;
        this.player2Chosen = randomShow();
        pastP2Shown.addCardToPastShown(player2Chosen);
        if (player1.getScissorNum() > 0) {
            result = scissorCard.compare(player2Chosen);
            evaluateResult(result);
        }
    }

    //EFFECTS: When click rock card, perform that
    private void rockActionPerform() {
        this.player1Chosen = rockCard;
        this.player2Chosen = randomShow();
        pastP2Shown.addCardToPastShown(player2Chosen);
        if (player1.getRockNum() > 0) {
            result = rockCard.compare(player2Chosen);
            evaluateResult(result);
        }
    }

    //EFFECTS: change all label about player data every turn
    private void updateCondition() {
        myShown.setText("Card I shown: " + player1Chosen.getKinds());
        opponentShown.setText("Player2 shows: " + player2Chosen.getKinds());
        myScissorNum.setText("my scissor num:" + player1.getScissorNum());
        myPaperNum.setText("my paper num:" + player1.getPaperNum());
        myRockNum.setText("my rock num:" + player1.getRockNum());
        otherRockNum.setText("opponent rock num:" + player2.getRockNum());
        otherScissorNum.setText("opponent scissor num:" + player2.getScissorNum());
        otherPaperNum.setText("opponent paper num:" + player2.getPaperNum());
        resultLabel.setText("result:" + result);
    }

    //EFFECTS: change all label after load data
    private void updateConditionAfterLoad() {
        myShown.setText("Card I shown: ");
        opponentShown.setText("Player2 shows: ");
        myScissorNum.setText("my scissor num:" + player1.getScissorNum());
        myPaperNum.setText("my paper num:" + player1.getPaperNum());
        myRockNum.setText("my rock num:" + player1.getRockNum());
        otherRockNum.setText("opponent rock num:" + player2.getRockNum());
        otherScissorNum.setText("opponent scissor num:" + player2.getScissorNum());
        otherPaperNum.setText("opponent paper num:" + player2.getPaperNum());
        resultLabel.setText("result:");
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

    // EFFECTS: check if two players lose their all cards
    private String checkLoser() {
        if (player1.getRockNum() == 0 && player1.getScissorNum() == 0 && player1.getPaperNum() == 0) {
            return "player1 lose";
        } else if (player2.getRockNum() == 0 && player2.getScissorNum() == 0 && player2.getPaperNum() == 0) {
            return "player2 lose";
        } else {
            return "";
        }
    }

    // EFFECTS: save all content need to save
    private void saveAll() {
        savePlayer2PastShown();
        saveHandCondition();
    }

    // EFFECTS: load all content need to load
    private void loadAll() {
        loadPlayer2PastShown();
        loadHandCondition();
    }

    // EFFECTS: saves 2 players' hand states on file
    // TODO citation:code taken and modified from WorkRoomApp.java package in JsonSerializationDemo
    private void saveHandCondition() {
        try {
            jsonWriterForPlayer1Hand.open();
            jsonWriterForPlayer1Hand.write(player1);
            jsonWriterForPlayer1Hand.close();
            jsonWriterForPlayer2Hand.open();
            jsonWriterForPlayer2Hand.write(player2);
            jsonWriterForPlayer2Hand.close();
            System.out.println("Saved player hand to " + JSON_STOREForPlayer1Hand);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STOREForPlayer1Hand);
        }
    }

    // MODIFIES: this
    // EFFECTS: load 2 players' hand states from file
    // TODO citation:code taken and modified from WorkRoomApp.java package in JsonSerializationDemo
    private void loadHandCondition() {
        try {
            player1 = jsonReaderForPlayer1Hand.readHand();
            player2 = jsonReaderForPlayer2Hand.readHand();
            System.out.println("Loaded player hand from " + JSON_STOREForPlayer1Hand);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STOREForPlayer1Hand);
        }
    }

    // EFFECTS: save player2 past shown card on file
    private void savePlayer2PastShown() {
        try {
            jsonWriter.open();
            jsonWriter.write(pastP2Shown);
            jsonWriter.close();
            System.out.println("Saved player2 past shown to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load player2 past shown card from file
    private void loadPlayer2PastShown() {
        try {
            pastP2Shown = jsonReader.readPlayer2PastShown();
            System.out.println("Loaded player2 past shown from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

