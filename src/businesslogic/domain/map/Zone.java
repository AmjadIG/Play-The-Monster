package businesslogic.domain.map;

public class Zone {
    private Case[][] gridCase;
    private int x;
    private int y;

    public String showGridCase() {
		for (int i = 0; i < gridCase.length; i++) {
			for (int j = 0; j < gridCase.length; j++) {
				System.out.print(gridCase[i][j].toString());
			}
			System.out.println();
		}
		return null;
	}
    public Zone( int x, int y) {
        this.setGridCase(new Case[30][30]);
        for (int i = 0; i < gridCase.length; i++) {
			for (int j = 0; j < gridCase.length; j++) { 
				gridCase[i][j] = new Case(i,j);
			}
		}
        this.setX(x);
        this.setY(y);
    }
    public Case getCase(int x,int y){
        return gridCase[x][y];
    }
	public Case[][] getGridCase() {
		return gridCase;
	}
	public void setGridCase(Case[][] gridCase) {
		this.gridCase = gridCase;
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
