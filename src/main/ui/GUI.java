package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Box;


public class GUI extends JFrame implements ActionListener {
    public GUI() {
        JFrame frame = new JFrame("Card Game");
        frame.setLayout(new BorderLayout());
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createVerticalBox();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel3.add(b1);
        panel.setBackground(Color.WHITE);

        JButton rock = new JButton();
        JButton paper = new JButton();
        JButton scissor = new JButton();


        rock.setIcon(new ImageIcon("./data/rock.png"));
        paper.setIcon(new ImageIcon("./data/paper.png"));
        scissor.setIcon(new ImageIcon("./data/scissor.png"));
        Dimension cardSize = new Dimension(200,300);
        rock.setPreferredSize(cardSize);
        paper.setPreferredSize(cardSize);
        scissor.setPreferredSize(cardSize);

        rock.setActionCommand("rock");
        rock.addActionListener(this);
        scissor.setActionCommand("scissor");
        scissor.addActionListener(this);
        paper.setActionCommand("paper");
        paper.addActionListener(this);

        panel.setBackground(Color.YELLOW);
        panel4.setBackground(Color.BLUE);
        panel4.setPreferredSize(new Dimension(600,300));
        panel5.setBackground(Color.BLACK);
        panel5.setPreferredSize(new Dimension(300,300));


        panel3.add(panel4);
        b1.add(Box.createHorizontalStrut(270));
        panel3.add(panel5);
        panel2.add(rock);
        panel2.add(scissor);
        panel2.add(paper);
        frame.add(panel);
        frame.add(panel2,BorderLayout.SOUTH);
        frame.add(panel3,BorderLayout.NORTH);
        frame.setBounds(1900,1000,1900,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new GUI();
    }
}

