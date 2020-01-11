package businesslogic.client;

import javafx.event.Event;

import java.util.List;

public class StateGame {
    private List<Event> state;

    public List<Event> getState() {
        return state;
    }

    public void setState(List<Event> state) {
        this.state = state;
    }

    public void notifyState(){ //notify to the view the changes of the game

    }

    public void updateBuffer(){ //update the state of ObjectBuffer

    }
}
