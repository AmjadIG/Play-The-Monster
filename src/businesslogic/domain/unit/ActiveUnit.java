package businesslogic.domain.unit;

import businesslogic.domain.AbstractUnit;
import uinterface.views.GameFrame;

import java.awt.*;

public abstract class ActiveUnit extends AbstractUnit {
	private GameFrame board;
    private int idType;
    private int posX = 0;
	private int posY = 0;
	private int dx = 0;
	private int dy = 0;
	private int speed = 1;
	private final int M_WIDTH = 10;
	private final int M_HEIGHT = 10;
	private int attack;
    private int defense;
    private int health;
    private AbstractRole role;
	public ActiveUnit() {
		super();
	}

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

    public int getAttack() { return attack; }

    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }

    public void setDefense(int defense) { this.defense = defense; }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }

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
