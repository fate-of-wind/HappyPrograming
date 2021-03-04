package persistence;

import model.Card;
import model.Hand;
import model.Player2PastShown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
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
    void testWriterInvalidFile() {
        try {
            Player2PastShown pastShown = new Player2PastShown();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlayer2PastShown() {
        try {
            Player2PastShown pastShown = new Player2PastShown();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlayer2PastShown.json");
            writer.open();
            writer.write(pastShown);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlayer2PastShown.json");
            pastShown = reader.readPlayer2PastShown();
            assertEquals(0, pastShown.getArrayList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralPlayer2PastShown() {
        try {
            Player2PastShown pastShown = new Player2PastShown();
            pastShown.addCardToPastShown(testPaperCard);
            pastShown.addCardToPastShown(testRockCard);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayer2PastShown.json");
            writer.open();
            writer.write(pastShown);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayer2PastShown.json");
            pastShown = reader.readPlayer2PastShown();
            List<Card> pastPlayer2Shown = pastShown.getArrayList();
            assertEquals(2, pastPlayer2Shown.size());
            assertEquals(testPaperCard.getKinds(),pastPlayer2Shown.get(0).getKinds());
            assertEquals(testRockCard.getKinds(),pastPlayer2Shown.get(1).getKinds());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterHand() {
        try {
            Hand player1 = new Hand(6,3,5);
            JsonWriter writer = new JsonWriter("./data/testWriterHand.json");
            writer.open();
            writer.write(player1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterHand.json");
            player1 = reader.readHand();
            assertEquals(6, player1.getRockNum());
            assertEquals(3,player1.getPaperNum());
            assertEquals(5,player1.getScissorNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
