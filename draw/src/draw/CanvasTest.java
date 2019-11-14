package draw;

import java.awt.Frame;
import java.awt.Graphics2D;

public class CanvasTest {
	public static void main() {
		BlackCanvas cvs = BlackCanvas.getCanvas();
		Frame f = new Frame("Play the Monster");
		f.add(cvs);
		f.setSize(cvs.getSize());
		f.setVisible(true);
		while() {
			Graphics2D g = (Graphics2D) g;
		}
	}
}
