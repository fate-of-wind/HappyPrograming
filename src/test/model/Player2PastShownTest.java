package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Player2PastShownTest {
    Player2PastShown testPastShown;
    private Card testRockCard;
    private Card testPaperCard;
    private Card testScissorCard;

    @BeforeEach
    void runBefore() {
        testRockCard = new Card("rock");
        testPaperCard = new Card("paper");
        testScissorCard = new Card("scissor");
        testPastShown = new Player2PastShown();
    }

    @Test
    void testAddCardToPastShownEmpty() {
        List<Card> res = new ArrayList<>();
        assertEquals(res,testPastShown.getArrayList());
    }

    @Test
    void testAddCardToPastShownOne() {
        List<Card> res = new ArrayList<>();
        res.add(testPaperCard);
        testPastShown.addCardToPastShown(testPaperCard);
        assertEquals(res,testPastShown.getArrayList());
    }

    @Test
    void testAddCardToPastShownMultiple() {
        List<Card> res = new ArrayList<>();
        res.add(testPaperCard);
        res.add(testRockCard);
        res.add(testScissorCard);
        testPastShown.addCardToPastShown(testPaperCard);
        testPastShown.addCardToPastShown(testRockCard);
        testPastShown.addCardToPastShown(testScissorCard);
        assertEquals(res,testPastShown.getArrayList());
    }

    @Test
    void testToMakeStringOne() {
        testPastShown.addCardToPastShown(testPaperCard);
        assertEquals("paper ",testPastShown.toMakeString());
    }

    @Test
    void testToMakeStringMultiple() {
        testPastShown.addCardToPastShown(testPaperCard);
        testPastShown.addCardToPastShown(testRockCard);
        testPastShown.addCardToPastShown(testScissorCard);
        assertEquals("paper rock scissor ",testPastShown.toMakeString());
    }
}
