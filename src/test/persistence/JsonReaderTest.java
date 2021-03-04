package persistence;

import model.Card;
import model.Hand;
import model.Player2PastShown;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Hand wr = reader.readHand();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlayer2PastShown() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlayer2PastShown.json");
        try {
            Player2PastShown pastShown = reader.readPlayer2PastShown();
            assertEquals(0, pastShown.getArrayList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderHand() {
        JsonReader reader = new JsonReader("./data/testReaderHand.json");
        try {
            Hand player1 = reader.readHand();
            assertEquals(8, player1.getRockNum());
            assertEquals(2, player1.getPaperNum());
            assertEquals(3, player1.getScissorNum());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
