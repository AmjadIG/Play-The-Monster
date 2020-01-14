package businesslogic.client.domain.unit;

import java.awt.Color;
import java.awt.Graphics;

import businesslogic.client.domain.AbstractUnit;
import businesslogic.client.domain.map.Case;
import gui.GameInterface;

public abstract class ActiveUnit extends AbstractUnit {
	public ActiveUnit(GameInterface board, Case position) {
		super();
		this.board = board;
	}

	private GameInterface board;
    private int idType;
    private int posX = 0;
	private int posY = 0;
	private int dx = 0;
	private int dy = 0;
	private int speed = 1;
	private final int M_WIDTH = 10;
	private final int M_HEIGHT = 10;
    private AbstractRole role;
    
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public AbstractRole getRole() {
        return role;
    }

    public void setRole(AbstractRole role) {
        this.role = role;
    }
    public boolean move(String direction) {
    	switch (direction) {
		case "up": moveUp();
		case "down" : moveDown();
		case "left" : moveLeft();
		case "right" : moveRight();
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
    }
    protected void speedDown() {
		if(speed>1) {
			speed--;
		}
	}
	protected void speedUp() {
		speed++;
	}
	protected void moveUp() {
		dy = -1;
	}
	protected void moveDown() {
		dy = +1;
	}
	protected void moveRight() {
		dx = +1;
	}
	protected void moveLeft() {
		dx = -1;
	}
	protected void setStaticOnX() {
		dx = 0;
	}
	protected void setStaticOnY() {
		dy = 0;
	}
	public void update() {
		int realdx = speed*dx;
		int realdy = speed*dy;
		if (posX + realdx < 0 || posX + realdx > board.getWidth()) {
			return;
		}
		if (posY + realdy < 0 || posY + realdy > board.getHeight()) {
			return;
		}
		posX = posX + realdx;
		posY = posY + realdy;
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(posX, posY, M_WIDTH, M_HEIGHT);
	}
	public void logPos() {
		String msg = String.format("ActiveUnit object: x:%s y:%s dx:%s dy:%s", 
				Integer.toString(posX), 
				Integer.toString(posY),
				Integer.toString(dx),
				Integer.toString(dy));
		System.out.println(msg);
	}
	
}
