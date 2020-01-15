package businesslogic.client.domain.unit;

import java.util.Random;

public class Minion extends NonPlayerCharacter {
    private String sentence;

    public Minion() {
        this.sentence = "Hey Boss !";
        setIdUnit(new Random().nextInt(10000));
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
