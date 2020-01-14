package businesslogic.client.domain;

import businesslogic.client.domain.map.Case;

public abstract class AbstractUnit {
    private int idUnit;
    private String name;
    private Case position;

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }
}
