package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Card rockCard;
    private Card paperCard;
    private Card scissorCard;

    DetailCardNum rock;
    DetailCardNum paper;
    DetailCardNum scissor;

    private Hand testHand;

    @BeforeEach
    void runBefore(){
        rockCard = new Card("rock");
        paperCard = new Card("paper");
        scissorCard = new Card("scissor");
        testHand = new Hand(5,5,5);
    }

    @Test
    void testRemoveSpecificOneCard(){
        assertEquals(5,testHand.getPaperNum());
        assertEquals(5,testHand.getRockNum());
        assertEquals(5,testHand.getScissorNum());
        testHand.removeSpecificOneCard(rockCard);
        assertEquals(4,testHand.getRockNum());
        testHand.removeSpecificOneCard(paperCard);
        testHand.removeSpecificOneCard(paperCard);
        assertEquals(3,testHand.getPaperNum());
        testHand.removeSpecificOneCard(scissorCard);
        testHand.removeSpecificOneCard(scissorCard);
        testHand.removeSpecificOneCard(scissorCard);
        assertEquals(2,testHand.getScissorNum());
    }

    @Test
    void testAddSpecificOneCard(){
        assertEquals(5,testHand.getPaperNum());
        assertEquals(5,testHand.getRockNum());
        assertEquals(5,testHand.getScissorNum());
        testHand.addSpecificOneCard(rockCard);
        assertEquals(6,testHand.getRockNum());
        testHand.addSpecificOneCard(paperCard);
        testHand.addSpecificOneCard(paperCard);
        assertEquals(7,testHand.getPaperNum());
    }

    @Test
    void  testAddSpecificOneCardMultipleTime(){
        testHand.addSpecificOneCard(scissorCard);
        testHand.addSpecificOneCard(scissorCard);
        testHand.addSpecificOneCard(scissorCard);
        assertEquals(8,testHand.getScissorNum());
    }
}
