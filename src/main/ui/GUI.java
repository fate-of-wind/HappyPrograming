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
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setBackground(Color.WHITE);

        JButton rock = new JButton();
        JButton paper = new JButton("paper");
        JButton scissor = new JButton("scissor");

        ImageIcon rockIcon = new ImageIcon(getClass().getResource("./data/rock.png"));
        rock.setIcon(rockIcon);
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
        panel2.setBackground(Color.BLUE);
        panel2.setPreferredSize(new Dimension(600,300));

        panel.add(panel2);
        //panel.add(rock);
        //panel.add(scissor);
        //panel.add(paper);
        frame.add(panel);
        //frame.add(panel2);
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

