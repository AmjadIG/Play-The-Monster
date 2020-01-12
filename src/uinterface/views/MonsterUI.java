package uinterface.views;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MonsterUI implements KeyListener {
    private final int M_WIDTH = 10;
    private final int M_HEIGHT = 10;
    private int posX = 0;
    private int posY = 0;
    private int dx = 0;
    private int dy = 0;
    private int speed = 1;
    private MapUI board;

    public MonsterUI(MapUI b) {
        board = b;
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
        String msg = String.format("Monster object: x:%s y:%s dx:%s dy:%s",
                Integer.toString(posX),
                Integer.toString(posY),
                Integer.toString(dx),
                Integer.toString(dy));
        System.out.println(msg);
    }

    private void speedDown() {
        if(speed>1) {
            speed--;
        }
    }

    private void speedUp() {
        speed++;
    }

    private void moveUp() {
        dy = -1;
    }

    private void moveDown() {
        dy = +1;
    }

    private void moveRight() {
        dx = +1;
    }

    private void moveLeft() {
        dx = -1;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(KeyEvent.VK_Q == arg0.getKeyCode()) {
            moveLeft();
        }
        if(KeyEvent.VK_D == arg0.getKeyCode()) {
            moveRight();
        }
        if(KeyEvent.VK_S == arg0.getKeyCode()) {
            moveDown();
        }
        if(KeyEvent.VK_Z == arg0.getKeyCode()) {
            moveUp();
        }
        if(KeyEvent.VK_N == arg0.getKeyCode()) {
            speedUp();
        }
        if(KeyEvent.VK_B == arg0.getKeyCode()) {
            speedDown();
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        int keycode = arg0.getKeyCode();
        if(keycode == KeyEvent.VK_Q) {
            dx = 0;
        }
        if(keycode == KeyEvent.VK_D) {
            dx = 0;
        }
        if(keycode == KeyEvent.VK_S) {
            dy = 0;
        }
        if(keycode == KeyEvent.VK_Z ) {
            dy = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
