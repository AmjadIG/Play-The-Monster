package businesslogic.client.domain.unit;

public class NonPlayerCharacter extends ActiveUnit {
    private boolean standby;

    public boolean isStandby() {
        return standby;
    }

    public void setStandby(boolean standby) {
        this.standby = standby;
    }
}