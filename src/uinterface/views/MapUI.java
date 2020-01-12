package uinterface.views;


import javax.swing.*;
import java.awt.*;

public class MapUI extends JPanel implements Runnable{
    private static final long serialVersionUID = 5247029752758712281L;
    public static MapUI mapUI = null;

    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    private final int DELAY = 25;
    private Thread animator;
    private MonsterUI monster = null;

    private MapUI() {
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        monster = new MonsterUI(this);
        addKeyListener(monster);

    }

    static public MapUI createBoard() {
        if(mapUI == null) {
            return new MapUI();
        } else {
            return mapUI;
        }
    }

    private void update() {
        monster.update();
    }

    private void drawObjects(Graphics g) {
        monster.draw(g);
        //Sync the painting on system that buffer graphics events
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);
        Image backg = Toolkit.getDefaultToolkit().createImage("../../assets/background.png");
        arg0.drawImage(backg,0,0,null);
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while(true) {
            update();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if(sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
