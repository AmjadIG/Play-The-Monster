package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GuiTest extends JFrame {

	private static final long serialVersionUID = 8074557183589786494L;

	public GuiTest() {
		initGui();
		
	}
	
	public void initGui() {
		Board b = Board.createBoard();
		add(b);
		addKeyListener(b.getKeyListeners()[0]);
		setResizable(false);
		pack();
		setTitle("Play the monster");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			JFrame gui = new GuiTest();
			gui.setVisible(true);
		});
	}

}
