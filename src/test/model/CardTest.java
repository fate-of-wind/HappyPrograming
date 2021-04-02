package model;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card testRockCard;
    private Card testPaperCard;
    private Card testScissorCard;
    private Card testExceptionCard;

    @BeforeEach
    void runBefore() {
        testRockCard = new Card("rock");
        testPaperCard = new Card("paper");
        testScissorCard = new Card("scissor");
        testExceptionCard = new Card("exception");
    }

    @Test
    void testCompare() {
        try {
            assertEquals("evened",testRockCard.compare(testRockCard));
            assertEquals("evened",testPaperCard.compare(testPaperCard));
            assertEquals("evened",testScissorCard.compare(testScissorCard));
            assertEquals("win",testPaperCard.compare(testRockCard));
            assertEquals("win",testRockCard.compare(testScissorCard));
            assertEquals("win",testScissorCard.compare(testPaperCard));
            assertEquals("lose",testPaperCard.compare(testScissorCard));
            assertEquals("lose",testScissorCard.compare(testRockCard));
            assertEquals("lose",testRockCard.compare(testPaperCard)); // test all conditions
        } catch (InvalidCard e) {
            fail("should not be throw");
        }
    }

    @Test
    void testCompareExceptionRock() {
        try {
            testRockCard.compare(testExceptionCard);
            fail("should throw a exception ");
        } catch (InvalidCard e) {
            //expected
        }
    }

    @Test
    void testCompareExceptionPaper() {
        try {
            testPaperCard.compare(testExceptionCard);
            fail("should throw a exception ");
        } catch (InvalidCard e) {
            //expected
        }
    }

    @Test
    void testCompareExceptionScissor() {
        try {
            testScissorCard.compare(testExceptionCard);
            fail("should throw a exception ");
        } catch (InvalidCard e) {
            //expected
        }
    }

    @Test
    void testToJson() {
        JSONObject x = new JSONObject();
        x.put("kind","paper");
        String json = x.toString();
        assertEquals(json, testPaperCard.toJson().toString());
    }
}
