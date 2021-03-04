package persistence;

import model.Card;
import model.Hand;
import model.Player2PastShown;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads hand condition from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Player2PastShown readPlayer2PastShown() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer2PastShown(jsonObject);
    }

    public Hand readHand() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHand(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Hand parseHand(JSONObject jsonObject) {
        int rock = jsonObject.getInt("rock");
        int scissor = jsonObject.getInt("scissor");
        int paper = jsonObject.getInt("paper");
        Hand playerHand = new Hand(rock,paper,scissor);
        return playerHand;
    }

    private Player2PastShown parsePlayer2PastShown(JSONObject jsonObject) {
        Player2PastShown p = new Player2PastShown();
        addCards(p, jsonObject);
        return p;
    }

    private void addCards(Player2PastShown p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("player2past");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(p, nextCard);
        }
    }

    private void addCard(Player2PastShown p, JSONObject jsonObject) {
        String kind = jsonObject.getString("kind");

        Card card = new Card(kind);
        p.addCardToPastShown(card);
    }

}
