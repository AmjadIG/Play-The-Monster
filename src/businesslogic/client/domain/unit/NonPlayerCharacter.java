package businesslogic.client.domain.unit;

import businesslogic.client.domain.map.Case;
import gui.GameInterface;

public class NonPlayerCharacter extends ActiveUnit {
	private boolean standby;
	public NonPlayerCharacter(GameInterface board, Case position) {
		super(board, position);
	}
    public boolean isStandby() {
        return standby;
    }
    public void setStandby(boolean standby) {
        this.standby = standby;
    }
}
