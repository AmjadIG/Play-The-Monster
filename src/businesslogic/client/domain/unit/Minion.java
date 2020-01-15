package businesslogic.client.domain.unit;

public class Minion extends NonPlayerCharacter {
    private String sentence;

    public Minion() {
        this.sentence = "Hey Boss !";
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
