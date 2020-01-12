package businesslogic.client.domain.map;

import businesslogic.client.domain.*;

public class Case {
    private AbstractUnit occupiedBy;
    private int x;
    private int y;

    public Case(int x, int y) {
     this.x = x;
     this.y = y;
	}
    
    @Override
	public String toString() {
		return "Case [occupiedBy=" + occupiedBy + ", x=" + x + ", y=" + y + "]";
	}
	public boolean isOccupied(){
        return (occupiedBy != null);
    }

    public AbstractUnit getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(AbstractUnit occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
