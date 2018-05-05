
package View;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Population.Chromosome;

public class RectBoardGameView extends JComponent {

	private int _CELL_SIZE = 0;
	private int _TOP_OFFSET = 0;
	private int _LEFT_OFFSET = 0;

	private int rows = 0;
	private int cols = 0;
	private List<Chromosome> population;

	public void setSize(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public void setPopulation(List<Chromosome> population) {
		this.population = population;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (population == null) {
			// No data model, quit painting
			return;
		}

		if (rows <= 0 || cols <= 0) {
			// Empty data model, quit painting
			return;
		}

		_CELL_SIZE = Math.min(getWidth() / cols, getHeight() / rows);
		_TOP_OFFSET = (getHeight() - (_CELL_SIZE * rows)) / 2;
		_LEFT_OFFSET = (getWidth() - (_CELL_SIZE * cols)) / 2;

		// Use this to make better looking circles
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int row = 0; row < rows; row += 1) {
			for (int col = 0; col < cols; col += 1) {
				int index = row * cols + col;
				Chromosome value = population.get(index);
				System.out.println("Index: " + index + " " + value);
				drawCell(row, col, value, g);
			}
		}
	}

	private void drawCell(int row, int col, Chromosome value, Graphics g) {
		int x = col * _CELL_SIZE + _LEFT_OFFSET;
		int y = row * _CELL_SIZE + _TOP_OFFSET;

		g.setColor(Color.decode("#6888BB"));
		g.fillRect(x + 1, y + 1, _CELL_SIZE - 2, _CELL_SIZE - 2);

		if (value != null) {
			g.setColor(Color.PINK);
			g.fillRect(x + 6, y + 6, _CELL_SIZE - 12, _CELL_SIZE - 12);
		}
	}

}
