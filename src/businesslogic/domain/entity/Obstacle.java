package businesslogic.domain.entity;

public class Obstacle extends Entity {
    private boolean climbOn=false;

    public boolean isClimbOn() {
        return climbOn;
    }

    public void setClimbOn(boolean climbOn) {
        this.climbOn = climbOn;
    }
}
