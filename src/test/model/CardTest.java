package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card testRockCard;
    private Card testPaperCard;
    private Card testScissorCard;

    @BeforeEach
    void runBefore() {
        testRockCard = new Card("rock");
        testPaperCard = new Card("paper");
        testScissorCard = new Card("scissor");
    }

    @Test
    void TestCompare() {
        assertEquals("evened",testRockCard.compare(testRockCard));
        assertEquals("evened",testPaperCard.compare(testPaperCard));
        assertEquals("evened",testScissorCard.compare(testScissorCard));
        assertEquals("win",testPaperCard.compare(testRockCard));
        assertEquals("win",testRockCard.compare(testScissorCard));
        assertEquals("win",testScissorCard.compare(testPaperCard));
        assertEquals("lose",testPaperCard.compare(testScissorCard));
        assertEquals("lose",testScissorCard.compare(testRockCard));
        assertEquals("lose",testRockCard.compare(testPaperCard)); // test all conditions
    }
}
