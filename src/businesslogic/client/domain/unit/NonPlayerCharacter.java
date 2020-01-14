package businesslogic.client.domain.unit;

import businesslogic.client.domain.map.Case;
import uinterface.views.GameInterface;

public class NonPlayerCharacter extends ActiveUnit {
	private boolean standby;
	public NonPlayerCharacter() {
		super();
	}
    public boolean isStandby() {
        return standby;
    }
    public void setStandby(boolean standby) {
        this.standby = standby;
    }
}
