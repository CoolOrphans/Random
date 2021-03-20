
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class ColorfulScreen extends JFrame {

	public int width = 1280;
	public int height = 720;

	private static final long serialVersionUID = 1L;

	public ColorfulScreen() {
		setTitle("COLORFUL SCREEN");
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		int R = 40;
		int G = 100;
		int B = 230;
		// addition -> subtraction and vice versa if reach max
		boolean RM = true;
		boolean GM = true;
		boolean BM = true;

		int rmh = 200; // max height red can be (can't go past 255)
		int rml = 3; // least height red can be (can't go lower than 0)
		int gmh = 230;
		int gml = 0;
		int bmh = 255;
		int bml = 0;

		// used for making sure if x % n and inc color, it doesn't do it again because
		// already did
		boolean rmb = true;
		boolean gmb = true;
		boolean bmb = true;
		
		//add/sub when divisible
		int rmx = 1;
		int rmy = 100;
		int gmx = 50;
		int gmy = 30;
		int bmx = 40;
		int bmy = 40;
		for (int x = 0; x < width; x++) {
			rmb = true;
			gmb = true;
			bmb = true;
			for (int y = 0; y < height; y++) {
				g.setColor(new Color(R, G, B));
				// update RGB

				// red
				if (y % rmy == 0 || rmb && x % rmx == 0) {
					rmb = false; // so it only changes in x once
					if (RM) {
						R++;
						if (R == rmh) {
							RM = false;
						}
					} else {
						R--;
						if (R == rml) {
							RM = true;
						}
					}
				}

				// green
				if (y % gmy == 0) {
					if (GM) {
						G++;
						if (G == gmh) {
							GM = false;
						}
					} else {
						G--;
						if (G == gml) {
							GM = true;
						}
					}
				}

				//blue
				if (y % bmy == 0 && bmx % 5 == 0) {
					if (BM) {
						B++;
						if (B == bmh) {
							BM = false;
						}
					} else {
						B--;
						if (B == bml) {
							BM = true;
						}
					}
				}

				g.drawLine(x, y, x, y);	// color pixel
			}
		}
	}
//	g.fillOval(100, 100, width, height);

	public static void main(String[] args) {
		ColorfulScreen c = new ColorfulScreen();
		c.paint(c.getGraphics());
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}
}
