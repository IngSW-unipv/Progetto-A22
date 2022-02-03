package map;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.function.*;

public class Hexagons {

	/** Height of an equilateral triangle with side length = 1 */
	private static final double H = Math.sqrt(3) / 2;

	static class Hexagon {
		final int row;
		final int col;
		final double sideLength;

		public Hexagon(int r, int c, double a) {
			this.row = r;
			this.col = c;
			this.sideLength = a;
		}

		double getCenterX() {
			return 2 * H * sideLength * (col + (row % 2) * 0.5);
		}

		double getCenterY() {
			return 3 * sideLength / 2 * row;
		}

		void foreachVertex(BiConsumer<Double, Double> f) {
			double cx = getCenterX();
			double cy = getCenterY();
			f.accept(cx + 0, cy + sideLength);
			f.accept(cx - H * sideLength, cy + 0.5 * sideLength);
			f.accept(cx - H * sideLength, cy - 0.5 * sideLength);
			f.accept(cx + 0, cy - sideLength);
			f.accept(cx + H * sideLength, cy - 0.5 * sideLength);
			f.accept(cx + H * sideLength, cy + 0.5 * sideLength);
		}
	}

	public void draw(JPanel panel, int width, int height, int radius) {
		
		//offset deve dipendere dal raggio dell'esagono 
		int offset = 50 * radius/20;

		//pulisco il pannello
		panel.removeAll();

		//creo esagoni
		final Hexagon[][] grid = new Hexagon[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				grid[row][col] = new Hexagon(row, col, radius);
			}
		}
		
		//dimensioni del pannello il base alla quantità degli esagoni e alla loro grandezza (BETA)
		int widthPanel = (int) (radius * 0.866) * 2 * width + (offset * 2);
		int heightPanel = (int) (radius * 2 * height) + (offset * 2);
		
		panel.setPreferredSize(new Dimension(widthPanel, heightPanel));

		//Aggiungo esagoni
		panel.add(new JComponent() {
			@Override
			public void paint(Graphics g) {
				g.setColor(new Color(255, 0, 0));
				final int[] xs = new int[6];
				final int[] ys = new int[6];
				for (Hexagon[] row : grid) {
					for (Hexagon h : row) {
						final int[] i = { 0 };
						h.foreachVertex((x, y) -> {
							xs[i[0]] = (int) ((double) x) + offset;
							ys[i[0]] = (int) ((double) y) + offset;
							i[0]++;
						});
						//g.fillPolygon(xs, ys, 6);
						g.drawPolygon(xs, ys, 6);

						if (radius > 20) {
							g.drawString("(" + h.row + "," + h.col + ")", (int) (h.getCenterX() - 15 + offset),
									(int) (h.getCenterY() + 12 + offset));
						}
					}
				}
				
			}
		});
		
		//ridisegno il pannello
		panel.repaint();
		panel.revalidate();

	}
}