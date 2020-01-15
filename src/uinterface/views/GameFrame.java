package uinterface.views;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = -7733279898963926334L;
	private GameInterface gameInterface;
	public GameFrame(GameInterface gameInterface) {
		this.gameInterface = gameInterface;
		add(gameInterface);
		setResizable(false);
		pack();
		setTitle("Play the monster");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
	
