
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class PaintPixelsDiagonally extends JFrame {

	// screen resolution
	public static int width = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	public static int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getHeight();

	private static final long serialVersionUID = 2L;

	public PaintPixelsDiagonally() {
		setTitle("Paint Pixels Diagonally");
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// paints in pixels in diagonal fashion -> mess with RGB values inside for cool
	// effects
	public void diagonalPaint(int R, int G, int B, Graphics g, int x1, int y1, int x2, int y2) {
		for (int i = y1; i <= y2; i++) {
			int xi = x1;
			int yi = i;
			do {
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
				// up and right a pixel
				xi++;
				yi--;
			} while (xi <= x2 && yi >= y1);
		}
		for (int i = x1; i <= x2; i++) {
			int xi = i;
			int yi = y2;
			do {
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
				xi++;
				yi--;
			} while (xi <= x2 && yi >= y1);
		}
	}

	// Here is how you can mess with the values
	public void diagonalPaint2(int R, int G, int B, Graphics g, int x1, int y1, int x2, int y2, boolean rfl,
			boolean gfl, boolean bfl, int rmh, int rml, int gmh, int gml, int bmh, int bml, int rdx, int rdy, int gdx,
			int gdy, int bdx, int bdy) {
		// rfl, gfl, bfl are used to initially add or subtract an color value
		// rmh, gmh, bmh are used for the max value for a color value (must be less than
		// 256)
		// rml, gml, bml are used for the min value for a color value (must be greater
		// or equal to 0)
		// rdx, gdx, bdx are used to increment/decrement a color value when they evenly
		// go into xi
		// rdy, gdy, bdy are used to increment/decrement a color value when they evenly
		// go into yi
		for (int i = y1; i <= y2; i++) {
			int xi = x1;
			int yi = i;
			do {
				// manipulate RGB values
				// red
				if (yi % rdy == 0 && xi % rdx == 0) {
					if (rfl) {
						R++;
						if (R >= rmh) { // max reached
							rfl = false;
							if (R >= 256) { // problems where program doesn't catch truth statement in time
								R = 255;
							}
						}
					} else {
						R--;
						if (R <= rml) { // minimum reached
							rfl = true;
							if (R <= -1) { // problems where program doesn't catch truth statement in time
								R = 0;
							}
						}
					}
				}
				// green
				if (yi % gdy == 0 && xi % gdx == 0) {
					if (gfl) {
						G++;
						if (G >= gmh) { // max reached
							gfl = false;
							if (G >= 256) {
								G = 255;
							}
						}
					} else {
						G--;
						if (G <= gml) { // minimum reached
							gfl = true;
							if (G <= -1) {
								G = 0;
							}
						}
					}
				}
				// blue
				if (yi % bdy == 0 && xi % bdx == 0) {
					if (bfl) {
						B++;
						if (B >= bmh) { // max reached
							bfl = false;
							if (B >= 256) {
								B = 255;
							}
						}
					} else {
						B--;
						if (B <= bml) { // minimum reached
							bfl = true;
							if (B <= -1) {
								B = 0;
							}
						}
					}
				}
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
				// up and right a pixel
				xi++;
				yi--;
			} while (xi <= x2 && yi >= y1);
		}
		for (int i = x1; i <= x2; i++) {
			int xi = i;
			int yi = y2;
			do {
				// manipulate RGB values
				// red
				if (yi % rdy == 0 && xi % rdx == 0) {
					if (rfl) {
						R++;
						if (R >= rmh) { // max reached
							rfl = false;
						}
					} else {
						R--;
						if (R <= rml) { // minimum reached
							rfl = true;
						}
					}
				}
				// green
				if (yi % gdy == 0 && xi % gdx == 0) {
					if (gfl) {
						G++;
						if (G >= gmh) { // max reached
							gfl = false;
							if (G >= 256) {
								G = 255;
							}
						}
					} else {
						G--;
						if (G <= gml) { // minimum reached
							gfl = true;
							if (G <= -1) {
								G = 0;
							}
						}
					}
				}
				// blue
				if (yi % bdy == 0 && xi % bdx == 0) {
					if (bfl) {
						B++;
						if (B >= bmh) { // max reached
							bfl = false;
							if (B >= 256) {
								B = 255;
							}
						}
					} else {
						B--;
						if (B <= bml) { // minimum reached
							bfl = true;
							if (B <= -1) {
								B = 0;
							}
						}
					}
				}
				g.setColor(new Color(R, G, B));
				g.drawLine(xi, yi, xi, yi); // color pixel
				xi++;
				yi--;
			} while (xi <= x2 && yi >= y1);
		}
	}

	public void paint(Graphics g) {
	}

	public static void main(String[] args) {
		PaintPixelsDiagonally c = new PaintPixelsDiagonally();
		c.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen

		Graphics g = c.getGraphics();
		// hard coded values
		int R = 200; // red
		int G = 20; // green
		int B = 30; // blue
		int x1 = 200; // top left x
		int y1 = 200; // top left y
		int x2 = 300; // bottom right x
		int y2 = 300; // bottom right y
		c.diagonalPaint(R, G, B, g, x1, y1, x2, y2);

		c.diagonalPaint(R, G, B, g, 400, 300, 800, 500);

		c.diagonalPaint2(R, G, B, g, 400, 400, 600, 600, false, false, true, 200, 3, 230, 0, 255, 0, 15, 10, 50, 30, 40,
				40);

		// initial add/sub start state
		boolean rfl = true;
		boolean bfl = false;
		boolean gfl = true;
		// mh = max color value, ml = min color value
		int rmh = 200; // max height red can be (can't be higher than 255)
		int rml = 3; // least height red can be (can't be lower than 0)
		int gmh = 230;
		int gml = 0;
		int bmh = 255;
		int bml = 0;
		// values when pixel at (x, y) is divisible by them it will increment/decrement
		// color value
		int rdx = 15;
		int rdy = 10;
		int gdx = 50;
		int gdy = 30;
		int bdx = 40;
		int bdy = 40;
		c.diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
				bdx, bdy);
		//
		R = 2;
		G = 200;
		B = 240;

		x1 = 0;
		y1 = 0;
		x2 = width;
		y2 = height;

		rfl = true;
		bfl = false;
		gfl = true;

		rmh = 200;
		rml = 3;
		gmh = 230;
		gml = 30;
		bmh = 255;
		bml = 20;

		rdx = 15;
		rdy = 10;
		gdx = 5;
		gdy = 30;
		bdx = 20;
		bdy = 40;

		c.diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
				bdx, bdy);
		//
		R = 150;
		G = 200;
		B = 50;

		x1 = 400;
		y1 = 300;
		x2 = 800;
		y2 = 500;

		rfl = false;
		bfl = false;
		gfl = false;

		rmh = 255;
		rml = 30;
		gmh = 255;
		gml = 30;
		bmh = 255;
		bml = 30;

		rdx = 15;
		rdy = 10;
		gdx = 15;
		gdy = 10;
		bdx = 15;
		bdy = 10;

		c.diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
				bdx, bdy);
		//
		R = 50;
		G = 50;
		B = 200;

		x1 = 200;
		y1 = 300;
		x2 = 1000;
		y2 = 400;

		rfl = true;
		bfl = false;
		gfl = true;

		rmh = 100;
		rml = 20;
		gmh = 100;
		gml = 20;
		bmh = 255;
		bml = 120;

		rdx = 7;
		rdy = 5;
		gdx = 7;
		gdy = 5;
		bdx = 15;
		bdy = 15;

		c.diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
				bdx, bdy);
		//
		R = 200;
		G = 50;
		B = 100;

		x1 = 20;
		y1 = 100;
		x2 = 220;
		y2 = 320;

		rfl = false;
		bfl = false;
		gfl = true;

		rmh = 255;
		rml = 100;
		gmh = 100;
		gml = 30;
		bmh = 140;
		bml = 50;

		rdx = 7;
		rdy = 5;
		gdx = 7;
		gdy = 5;
		bdx = 15;
		bdy = 15;

		c.diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
				bdx, bdy);
		//

		c.generateRandomDiagonalPaint2Boxes(g, 100, 300, 300, 1, 1, 50, 50);
		
		c.generateRandomDiagonalPaint2Boxes(g, 100, 300, 300, 1, 1, 50, 50);
		
		

	}

	// there could be more parameters for finer control
	public void generateRandomDiagonalPaint2Boxes(Graphics g, int n, int maxX, int maxY, int dxl, int dyl, int dxh,
			int dyh) {

		for (int i = 0; i < n; i++) {
			int R = random(0, 255);
			int G = random(0, 255);
			int B = random(0, 255);

			int x1 = random(0, width);
			int y1 = random(0, height);
			int x2;
			int y2;
			if (x1 + maxX > width) {
				x2 = random(x1, width);
			} else {
				x2 = random(x1, x1 + maxX);
			}
			if (y1 + maxY > height) {
				y2 = random(y1, height);
			} else {
				y2 = random(y1, y1 + maxY);
			}

			boolean rfl;
			boolean bfl;
			boolean gfl;
			if (random(0, 1) == 0) {
				rfl = false;
			} else {
				rfl = true;
			}
			if (random(0, 1) == 0) {
				bfl = false;
			} else {
				bfl = true;
			}
			if (random(0, 1) == 0) {
				gfl = false;
			} else {
				gfl = true;
			}

			int rmh = random(0, 255);
			int rml = random(0, rmh);
			int gmh = random(0, 255);
			int gml = random(0, gmh);
			int bmh = random(0, 255);
			int bml = random(0, bmh);

			int rdx = random(dxl, dxh);
			int rdy = random(dyl, dyh);
			int gdx = random(dxl, dxh);
			int gdy = random(dyl, dyh);
			int bdx = random(dxl, dxh);
			int bdy = random(dyl, dyh);

			diagonalPaint2(R, G, B, g, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
					bdx, bdy);
		}
	}

	public static int random(int a, int b) {
		return (int) ((Math.random() * (b - a + 1)) + a);
	}

	public static void print(Object o) { // just used for printing to console
		System.out.print(o.toString());
	}
}
