
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.JFrame;

public class ColorfulScreen2 extends JFrame {

	public static int width = 1280;
	public static int height = 720;

	private static final long serialVersionUID = 2L;

	public ColorfulScreen2() {
		setTitle("COLORFUL SCREEN");
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//paints pixels in diagonal fashion -> mess with RGB values inside to make cool shit
	public void diagonalPaint(int R, int G, int B, Graphics g, int x1, int y1, int x2, int y2) {	
		g.setColor(new Color(R, G, B));
		for (int i = y1; i <= y2; i++) {
			int xi = x1;
			int yi = i;
			do {
				xi++;
				yi--;
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
			} while (yi >= y1);
		}

		for (int i = x1; i <= x2; i++) {
			int xi = i;
			int yi = y2;
			do {
				xi++;
				yi--;
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
			} while (xi <= x2);
		}
	}

	public void paint(Graphics g) {
		int R = 200;
		int G = 20;
		int B = 30;
		diagonalPaint(R, G, B, g, 200, 200, 300, 300);
	}

	public static void drawChessboardBackground(Graphics g, int x, int y, int w, int h) {
		int s = 8;
		int ni = w / s + 1;
		int nj = h / s + 1;
		Shape clip = g.getClip();
		g.setClip(x, y, w, h);
		for (int j = 0; j < nj; j++) {
			for (int i = 0; i < ni; i++) {
				g.setColor(i % 2 != j % 2 ? Color.WHITE : Color.LIGHT_GRAY);
				g.fillRect(x + i * s, y + j * s, s, s);
			}
		}
		g.setClip(clip);
	}

	public static void main(String[] args) {
		ColorfulScreen2 c = new ColorfulScreen2();
		// c.drawChessboardBackground(c.getGraphics(), 0, 0, width, height);
		c.paint(c.getGraphics());
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}
}
