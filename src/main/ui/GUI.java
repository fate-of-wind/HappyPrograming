package ui;
//GUI is main class of my game, used to show graphic version to player

import model.Card;
import model.Hand;
import model.Player2PastShown;
import model.InvalidCard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


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
    private int countOpenOrClose;
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
    private JLabel opponentPastShownRockNum;
    private JLabel opponentPastShownScissorNum;
    private JLabel opponentPastShownPaperNum;
    private JTextArea pastShownReport;
    private JPanel panelPastShownVersion;
    private CardLayout c1;
    private JPanel panel;
    private JPanel panelCardButton;
    private JPanel panelAbove;
    private JPanel panelCondition;
    private JPanel panelTool;
    private JPanel panelRight;
    private JPanel panelCenter;
    private JPanel panelPastShownVersion1;
    private JPanel panelPastShownVersion2;
    private JButton rock;
    private JButton paper;
    private JButton scissor;
    private JButton save;
    private JButton load;
    private JButton newGame;
    private JButton switchMode;
    private JButton switchMode1;
    private JButton openOrCloseRecordingP2;
    private JFrame frame;
    private Icon winImgIcon;
    private Icon loseImgIcon;
    private Icon evenImgIcon;
    private JLabel imageLbl;


    //EFFECTS: make a graphic version program
    public GUI() {
        init();
        initGuiPart1();
        initGuiPart2();
        setLayoutInGUI();
        setButtonActionCL(rock, paper, scissor, save, load, newGame, switchMode, switchMode1, openOrCloseRecordingP2);
        setCardFormat(rock, paper, scissor);
        panelSetBackground();
        setPreferredSizeOfPanel();
        JScrollPane jsp = new JScrollPane(pastShownReport);
        Dimension size = pastShownReport.getPreferredSize();
        jsp.setBounds(110, 90, size.width, size.height);
        setLabelFont();
        firstPartAdding(jsp);
        c1.show(panelPastShownVersion, "card1");
        listOfAdding();
        panelPastShownVersion.setVisible(true);
        frame.setBounds(1900, 1000, 1700, 1100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }




    @Override
    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("rock")) {
            rockAction();
            displayGif();
        } else if (e.getActionCommand().equals("scissor")) {
            scissorActionPerform();
            displayGif();
        } else if (e.getActionCommand().equals("paper")) {
            paperActionPerform();
        } else if (e.getActionCommand().equals("save")) {
            saveAll();
        } else if (e.getActionCommand().equals("load")) {
            loadAll();
            updateConditionAfterLoad();
        } else if (e.getActionCommand().equals("newGame")) {
            init();
            updateConditionAfterLoad();
        } else if (e.getActionCommand().equals("switch")) {
            c1.next(panelPastShownVersion);
        } else if (e.getActionCommand().equals("open")) {
            checkShowOrNotShow();
        } else {
            c1.next(panelPastShownVersion);
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
        result = "";
        input = new Scanner(System.in);
        countOpenOrClose = 0;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReaderForPlayer1Hand = new JsonReader(JSON_STOREForPlayer1Hand);
        jsonWriterForPlayer1Hand = new JsonWriter(JSON_STOREForPlayer1Hand);
        jsonReaderForPlayer2Hand = new JsonReader(JSON_STOREForPlayer2Hand);
        jsonWriterForPlayer2Hand = new JsonWriter(JSON_STOREForPlayer2Hand);
    }

    // MODIFIES: this
    // EFFECTS: initializes frame, panel and button in GUI
    private void initGuiPart1() {
        panel = new JPanel();
        panelCardButton = new JPanel();
        panelAbove = new JPanel();
        panelCondition = new JPanel();
        panelTool = new JPanel();
        panelRight = new JPanel();
        panelCenter = new JPanel();
        panelPastShownVersion1 = new JPanel();
        panelPastShownVersion2 = new JPanel();
        rock = new JButton();
        paper = new JButton();
        scissor = new JButton();
        save = new JButton("save");
        load = new JButton("load");
        newGame = new JButton("nameGame");
        switchMode = new JButton("switch");
        openOrCloseRecordingP2 = new JButton("open or close P2 recording");
        pastShownReport = new JTextArea("", 10, 40);
        winImgIcon = new ImageIcon("./data/clap.gif");
        loseImgIcon = new ImageIcon("./data/lose.gif");
        evenImgIcon = new ImageIcon("./data/evened.gif");
        imageLbl = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: initializes frame, panel and button in GUI
    private void initGuiPart2() {
        switchMode1 = new JButton("switch");
        panelPastShownVersion = new JPanel(new CardLayout());
        frame = new JFrame("Card Game");
        c1 = (CardLayout) (panelPastShownVersion.getLayout());
        myRockNum = new JLabel("my rock num:" + "5");
        myScissorNum = new JLabel("my scissor num:" + "5");
        myPaperNum = new JLabel("my paper num:" + "5");
        otherRockNum = new JLabel("opponent rock num:" + "5");
        otherScissorNum = new JLabel("opponent scissor num:" + "5");
        otherPaperNum = new JLabel("opponent paper num:" + "5");
        myShown = new JLabel("I show:");
        opponentShown = new JLabel("player2 show:");
        opponentPastShownRockNum = new JLabel("Past rock num:");
        opponentPastShownPaperNum = new JLabel("Past scissor num:");
        opponentPastShownScissorNum = new JLabel("Past paper num:");
        resultLabel = new JLabel("result:");
    }

    //EFFECTS: display different gif when win or lose a turn
    private void displayGif() {
        if (result.equals("win")) {
            imageLbl.setIcon(winImgIcon);
        } else if (result.equals("lose")) {
            imageLbl.setIcon(loseImgIcon);
        } else {
            imageLbl.setIcon(evenImgIcon);
        }
    }

    //EFFECTS: set panel's size in GUI
    private void setPreferredSizeOfPanel() {
        panelCondition.setPreferredSize(new Dimension(600, 300));
        panelPastShownVersion.setPreferredSize(new Dimension(500, 300));
        panelPastShownVersion1.setPreferredSize(new Dimension(500, 300));
        panelPastShownVersion2.setPreferredSize(new Dimension(500, 300));
        panel.setPreferredSize(new Dimension(1000, 600));
        panelTool.setPreferredSize(new Dimension(400, 600));
        panelRight.setPreferredSize(new Dimension(500, 600));
        panelCenter.setPreferredSize(new Dimension(400, 600));
        save.setPreferredSize(new Dimension(100, 100));
        load.setPreferredSize(new Dimension(100, 100));
    }

    //EFFECTS: set layout of frame and panel in GUI
    private void setLayoutInGUI() {
        frame.setLayout(new BorderLayout());
        panelPastShownVersion2.setLayout(new GridLayout(4, 1, 5, 5));
        panelAbove.setLayout(new GridLayout(1, 2, 100, 5));
        panelTool.setLayout(new GridLayout(2, 2, 5, 5));
        panelCondition.setLayout(new GridLayout(2, 1, 5, 5));
        panelRight.setLayout(new GridLayout(3, 2, 5, 5));
    }

    //EFFECTS: set font format of label in GUI
    private void setLabelFont() {
        resultLabel.setFont(new Font("", Font.BOLD, 40));
        myRockNum.setFont(new Font("", Font.BOLD, 20));
        myScissorNum.setFont(new Font("", Font.BOLD, 20));
        myPaperNum.setFont(new Font("", Font.BOLD, 20));
        otherRockNum.setFont(new Font("", Font.BOLD, 20));
        otherScissorNum.setFont(new Font("", Font.BOLD, 20));
        otherPaperNum.setFont(new Font("", Font.BOLD, 20));
        myShown.setFont(new Font("", Font.BOLD, 30));
        opponentShown.setFont(new Font("", Font.BOLD, 30));
        pastShownReport.setFont(new Font("", Font.BOLD, 20));
        opponentPastShownRockNum.setFont(new Font("", Font.BOLD, 20));
        opponentPastShownPaperNum.setFont(new Font("", Font.BOLD, 20));
        opponentPastShownScissorNum.setFont(new Font("", Font.BOLD, 20));
    }

    //EFFECTS:part of adding method in GUI
    private void firstPartAdding(JScrollPane jsp) {
        panelPastShownVersion1.add(jsp);
        panelPastShownVersion1.add(switchMode1);
        panelAbove.add(panelCondition);
        panel.add(resultLabel);
        panel.add(imageLbl);
        panelPastShownVersion.add(panelPastShownVersion1, "card1");
        panelPastShownVersion.add(panelPastShownVersion2, "card2");
        panelAbove.add(panelPastShownVersion);
        panelCardButton.add(rock);
        panelCardButton.add(scissor);
        panelCardButton.add(paper);
        panelCardButton.add(openOrCloseRecordingP2);
    }

    //EFFECTS:part of adding method in GUI
    private void listOfAdding() {
        panelPastShownVersion2.add(opponentPastShownRockNum);
        panelPastShownVersion2.add(opponentPastShownPaperNum);
        panelPastShownVersion2.add(opponentPastShownScissorNum);
        panelPastShownVersion2.add(switchMode);
        panelCondition.add(myShown);
        panelCondition.add(opponentShown);
        panelRight.add(myRockNum);
        panelRight.add(otherRockNum);
        panelRight.add(myScissorNum);
        panelRight.add(otherScissorNum);
        panelRight.add(myPaperNum);
        panelRight.add(otherPaperNum);
        panelTool.add(save);
        panelTool.add(load);
        panelTool.add(newGame);
        frame.add(panelTool, BorderLayout.WEST);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panel);
        frame.add(panelCardButton, BorderLayout.SOUTH);
        frame.add(panelAbove, BorderLayout.NORTH);
    }

    //EFFECTS:set panel's Background in GUI
    private void panelSetBackground() {
        panel.setBackground(Color.WHITE);
        panelCondition.setBackground(Color.LIGHT_GRAY);
        panelPastShownVersion1.setBackground(Color.cyan);
        panelRight.setBackground(Color.LIGHT_GRAY);
        panelTool.setBackground(Color.WHITE);
        panelAbove.setBackground(Color.WHITE);
        panelCardButton.setBackground(Color.WHITE);
    }

    //EFFECTS: set format of card button
    private void setCardFormat(JButton rock, JButton paper, JButton scissor) {
        rock.setIcon(new ImageIcon("./data/rock.png"));
        paper.setIcon(new ImageIcon("./data/paper.png"));
        scissor.setIcon(new ImageIcon("./data/scissor.png"));
        Dimension cardSize = new Dimension(200, 300);
        rock.setPreferredSize(cardSize);
        paper.setPreferredSize(cardSize);
        scissor.setPreferredSize(cardSize);
    }


    //EFFECTS:set button action command and its actionListener
    private void setButtonActionCL(JButton rock, JButton paper, JButton
            scissor, JButton save, JButton load, JButton newGame, JButton switchMode, JButton switchMode1,
                                   JButton openOrCloseRecordingP2) {
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
        switchMode1.setActionCommand("switch1");
        switchMode1.addActionListener(this);
        openOrCloseRecordingP2.setActionCommand("open");
        openOrCloseRecordingP2.addActionListener(this);
    }

    //EFFECTS:check whether open or close the panel of player2 past shown
    private void checkShowOrNotShow() {
        if (countOpenOrClose % 2 == 0) {
            panelPastShownVersion.setVisible(false);
        } else {
            panelPastShownVersion.setVisible(true);
        }
        countOpenOrClose++;
    }

    //EFFECTS: rock action with update
    private void rockAction() {
        rockActionPerform();
        updateCondition();
    }

    //EFFECTS: When click paper card, perform that
    private void paperActionPerform() {
        try {
            if (player1.getPaperNum() > 0) {
                this.player1Chosen = paperCard;
                this.player2Chosen = randomShow();
                pastP2Shown.addCardToPastShown(player2Chosen);
                result = paperCard.compare(player2Chosen);
                evaluateResult(result);
                updateCondition();
                displayGif();
            }
        } catch (InvalidCard e) {
            System.out.println("invalid card");
        }
    }

    //EFFECTS: When click scissor card, perform that
    private void scissorActionPerform() {
        try {
            if (player1.getScissorNum() > 0) {
                this.player1Chosen = scissorCard;
                this.player2Chosen = randomShow();
                pastP2Shown.addCardToPastShown(player2Chosen);
                result = scissorCard.compare(player2Chosen);
                evaluateResult(result);
                updateCondition();
            }
        } catch (InvalidCard e) {
            System.out.println("invalid card");
        }
    }

    //EFFECTS: When click rock card, perform that
    private void rockActionPerform() {
        try {
            if (player1.getRockNum() > 0) {
                this.player1Chosen = rockCard;
                this.player2Chosen = randomShow();
                pastP2Shown.addCardToPastShown(player2Chosen);
                result = rockCard.compare(player2Chosen);
                evaluateResult(result);
            }
        } catch (InvalidCard e) {
            System.out.println("invalid card");
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
        pastShownReport.setText(pastP2Shown.toMakeString());
        countPastPlayer2ShownAndUpdate();
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
        pastShownReport.setText(pastP2Shown.toMakeString());
        countPastPlayer2ShownAndUpdate();
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

    //EFFECTS: used to count how many card of specific kind player2 shown past and update information during
    // each shown
    private void countPastPlayer2ShownAndUpdate() {
        int countRockNum = 0;
        int countPaperNum = 0;
        int countScissorNum = 0;
        for (Card c : pastP2Shown.getArrayList()) {
            if (c.getKinds().equals("rock")) {
                countRockNum++;
            } else if (c.getKinds().equals("paper")) {
                countPaperNum++;
            } else {
                countScissorNum++;
            }
        }
        opponentPastShownRockNum.setText("Past rock num: " + countRockNum);
        opponentPastShownScissorNum.setText("Past scissor num: " + countScissorNum);
        opponentPastShownPaperNum.setText("Past paper num: " + countPaperNum);
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

