import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Panel extends JPanel {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = (int) screenSize.getWidth();
	public static int height = (int) screenSize.getHeight();

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public Panel() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static void main(String[] args) {
		JFrame window = new JFrame("Display");
		window.setUndecorated(true);
		device.setFullScreenWindow(window);
		Panel panel = new Panel();
		window.setSize(width, height);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel); // my panel gets added to the window
		window.setVisible(true); // see the window
		window.getRootPane().registerKeyboardAction(e -> window.dispose(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		panel.paint(panel.getGraphics());
		; // goes to draw method
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		boolean b1 = false; // diag
		boolean b2 = false; // diag with color variation
		boolean b3 = false; // squares
		boolean b4 = true;

		if (b1) {
			diagonalPaint(200, 50, 20, g, 0, 0, width, height);
			g.drawImage(image, 0, 0, width, height, this);
		}

		if (b2) {
			diagonalPaint2(random(0, 255), random(0, 255), random(0, 255), 0, 0, width, height, randomBool(),
					randomBool(), randomBool(), 100, random(0, 100), 100, random(0, 100), 100, random(0, 100),
					random(1, 50), random(1, 50), random(1, 50), random(1, 50), random(1, 50), random(1, 50));
			g.drawImage(image, 0, 0, width, height, this);
		}

		if (b3) {
			int boxn = 16;
			int rmh;
			int gmh;
			int bmh;
			int k = image.getWidth() / boxn;
			for (int ix = 0; ix < width; ix += k) {
				for (int iy = 0; iy < height; iy += k) {
					rmh = random(0, 255);
					gmh = random(0, 255);
					bmh = random(0, 255);
					diagonalPaint2(random(0, 255), random(0, 255), random(0, 255), ix, iy, ix + k, iy + k, randomBool(),
							randomBool(), randomBool(), rmh, random(0, rmh), gmh, random(0, gmh), bmh, random(0, bmh),
							random(1, 50), random(1, 50), random(1, 50), random(1, 50), random(1, 50), random(1, 50));
				}
			}
			g.drawImage(image, 0, 0, this);
		}

		if (b4) {
			int boxn = 16;
			int rmh;
			int gmh;
			int bmh;
			int k = image.getWidth() / boxn;
			for (int ix = 0; ix < width; ix += k) {
				for (int iy = 0; iy < height; iy += k) {
					if (Math.random() < .5) {
						rmh = random(0, 50);
						gmh = random(0, 40);
						bmh = random(0, 100);
						diagonalPaint2(random(0, 50), random(0, 40), random(0, 100), ix, iy, ix + k, iy + k,
								randomBool(), randomBool(), randomBool(), rmh, random(100, rmh), gmh, random(0, gmh),
								bmh, random(0, bmh), random(1, 25), random(1, 25), random(1, 25), random(1, 25),
								random(1, 25), random(1, 25));
					} else {
						rmh = random(0, 100);
						gmh = random(0, 60);
						bmh = random(0, 150);
						diagonalPaint2(random(0, 100), random(0, 60), random(0, 150), ix, iy, ix + k, iy + k,
								randomBool(), randomBool(), randomBool(), rmh, random(10, rmh), 10, random(10, gmh),
								bmh, random(10, bmh), random(1, 25), random(1, 25), random(1, 25), random(1, 25),
								random(1, 25), random(1, 25));
					}
				}
			}
		}
		g.drawImage(image, 0, 0, this);

	}

	public void paintComponent(Graphics g) {
	}

	public void diagonalPaint2(int R, int G, int B, int x1, int y1, int x2, int y2, boolean rfl, boolean gfl,
			boolean bfl, int rmh, int rml, int gmh, int gml, int bmh, int bml, int rdx, int rdy, int gdx, int gdy,
			int bdx, int bdy) {
		// rfl, gfl, bfl are used to initially add or subtract an color value
		// rmh, gmh, bmh are used for the max value for a color value (must be less than
		// 256)
		// rml, gml, bml are used for the min value for a color value (must be greater
		// or equal to 0)
		// rdx, gdx, bdx are used to increment/decrement a color value when they evenly
		// go into xi
		// rdy, gdy, bdy are used to increment/decrement a color value when they evenly
		// go into yi
		if (y2 >= height) {
			y2 = height - 1;
		}
		if (x2 >= width) {
			x2 = width - 1;
		}
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
						}
					} else {
						G--;
						if (G <= gml) { // minimum reached
							gfl = true;
						}
					}
				}
				// blue
				if (yi % bdy == 0 && xi % bdx == 0) {
					if (bfl) {
						B++;
						if (B >= bmh) { // max reached
							bfl = false;
						}
					} else {
						B--;
						if (B <= bml) { // minimum reached
							bfl = true;
						}
					}
				}
				if (R >= 256) {
					R = 255;
				} else if (R <= -1) {
					R = 0;
				}
				if (G >= 256) {
					G = 255;
				} else if (G <= -1) {
					G = 0;
				}
				if (B >= 256) {
					B = 255;
				} else if (B <= -1) {
					B = 0;
				}
				image.setRGB(xi, yi, new Color(R, G, B).getRGB());
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
						}
					} else {
						G--;
						if (G <= gml) { // minimum reached
							gfl = true;
						}
					}
				}
				// blue
				if (yi % bdy == 0 && xi % bdx == 0) {
					if (bfl) {
						B++;
						if (B >= bmh) { // max reached
							bfl = false;
						}
					} else {
						B--;
						if (B <= bml) { // minimum reached
							bfl = true;
						}
					}
				}
				if (R >= 256) {
					R = 255;
				} else if (R <= -1) {
					R = 0;
				}
				if (G >= 256) {
					G = 255;
				} else if (G <= -1) {
					G = 0;
				}
				if (B >= 256) {
					B = 255;
				} else if (B <= -1) {
					B = 0;
				}
				image.setRGB(xi, yi, new Color(R, G, B).getRGB());
				xi++;
				yi--;
			} while (xi <= x2 && yi >= y1);
		}
	}

	public void generateRandomDiagonalPaint2Boxes(int n, int maxX, int maxY, int dxl, int dyl, int dxh, int dyh) {
		for (int i = 0; i < n; i++) {
			int R = random(0, 255);
			int G = random(0, 255);
			int B = random(0, 255);

			int x1 = random(0, width - 1);
			int y1 = random(0, height - 1);
			int x2;
			int y2;
			if ((x1 + maxX) >= width) {
				x2 = random(x1, width - 1);
			} else {
				x2 = random(x1, x1 + maxX);
			}
			if ((y1 + maxY) >= height) {
				y2 = random(y1, height - 1);
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

			diagonalPaint2(R, G, B, x1, y1, x2, y2, rfl, gfl, bfl, rmh, rml, gmh, gml, bmh, bml, rdx, rdy, gdx, gdy,
					bdx, bdy);
		}
	}

	public void diagonalPaint(int R, int G, int B, Graphics g, int x1, int y1, int x2, int y2) {
		if (y2 >= height) {
			y2 = height - 1;
		}
		if (x2 >= width) {
			x2 = width - 1;
		}
		for (int i = y1; i <= y2; i++) {
			int xi = x1;
			int yi = i;
			do {
				image.setRGB(xi, yi, new Color(R, G, B).getRGB());
				// up and right a pixel
				xi++;
				yi--;
			} while (xi <= x2 && xi < width && yi >= y1);
		}
		for (int i = x1; i <= x2; i++) {
			int xi = i;
			int yi = y2;
			do {
				image.setRGB(xi, yi, new Color(R, G, B).getRGB());
				xi++;
				yi--;
			} while (xi <= x2 && xi < width && yi >= y1);
		}
	}

	public static int random(int a, int b) {
		return (int) ((Math.random() * (b - a + 1)) + a);
	}

	public static boolean randomBool() {
		if (random(0, 1) == 0) {
			return false;
		}
		return true;
	}

}