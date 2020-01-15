package businesslogic.client.domain.map;

public class Map {
    //private List<Zone> gridZone;
	private Zone[][] gridZone;
	private Dungeon dungeon;

	public Map() {
		this.setGridZone(new Zone[5][5]);
		dungeon = new Dungeon(0,1,1,"red");
		for (int i = 0; i < gridZone.length; i++) {
			for (int j = 0; j < gridZone.length; j++) {
				if(dungeon.getX() == i && dungeon.getY() == j) {
					gridZone[i][j] = dungeon;
				}else {
					gridZone[i][j] = new Zone(i,j);
				}
			}
		}
	}
	
    public String showMap() {
		for (int i = 0; i < gridZone.length; i++) {
			for (int j = 0; j < gridZone.length; j++) {
				System.out.print(gridZone[i][j].toString());
			}
			System.out.println();
		}
		return null;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public Zone[][] getGridZone() {
		return gridZone;
	}

	public void setGridZone(Zone[][] gridZone) {
		this.gridZone = gridZone;
	}
}
