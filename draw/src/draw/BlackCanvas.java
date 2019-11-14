package draw;

import java.awt.Canvas;
import java.awt.Color;

public class BlackCanvas extends Canvas {
	private static BlackCanvas cvs = null;
	private BlackCanvas() {
		setBackground(Color.black);
		setSize(800,600);
	}
	
	public static BlackCanvas getCanvas() {
		if(cvs == null) {
			return new BlackCanvas();
		}
		else {
			return cvs;
		}
	}
}
