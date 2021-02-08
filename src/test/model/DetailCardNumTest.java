package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetailCardNumTest {
    private DetailCardNum testCardNum;
    private Card testRockCard;

    @BeforeEach
    void runBefore() {
        testRockCard = new Card("rock");
        testCardNum = new DetailCardNum(testRockCard,5);
    }

    @Test
    void testRemoveOneCard(){
        assertEquals(5,testCardNum.getNumber());
        testCardNum.removeOneCard();
        assertEquals(4,testCardNum.getNumber());
    }

    @Test
    void testAddOneCard(){
        assertEquals(5,testCardNum.getNumber());
        testCardNum.addOneCard();
        assertEquals(6,testCardNum.getNumber());
    }
}
