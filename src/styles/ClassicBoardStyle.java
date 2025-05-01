package styles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Classic Board GUI with wooden color theme visual
 */
public class ClassicBoardStyle implements BoardStyle{
    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {
        Shape pit = getPitShape(bounds);
        g2.setColor(new Color(139, 69,19)); //Brown wood color
        g2.fill(pit);
        g2.setColor(Color.BLACK);
        g2.draw(pit);
        drawStone(g2, pit, stones);
    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {
        Shape mancala = getMancalaShape(bounds);
        g2.setColor(new Color(160,82, 45)); //Wood color
        g2.fill(mancala);
        g2.setColor(Color.BLACK);
        g2.draw(mancala);
        drawStone(g2, mancala, stones);
    }

    @Override
    public void drawStone(Graphics2D g2, Shape containerShape, int stones) {
        Rectangle bounds = containerShape.getBounds();
        int radius = 10;  // diameter of each stone
        int stonesPerRow = 4;
        int rows = (int) Math.ceil(stones / (double) stonesPerRow);

        int gridWidth = stonesPerRow * (radius + 2);
        int gridHeight = rows * (radius + 2);

        // Calculate starting (x, y) to center the stone group
        int startX = bounds.x + (bounds.width - gridWidth) / 2;
        int startY = bounds.y + (bounds.height - gridHeight) / 2;

        g2.setColor(Color.WHITE);
        for (int i = 0; i < stones; i++) {
            int col = i % stonesPerRow;
            int row = i / stonesPerRow;

            int x = startX + col * (radius + 2);
            int y = startY + row * (radius + 2);
            g2.fillOval(x, y, radius, radius);
        }
    }
    @Override
    public Color getBoardBackgroundColor() {
        return new Color(205, 133, 63); // light wood tone
    }
    @Override
    public Shape getMancalaShape(Rectangle2D bounds) {
        return new RoundRectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), 50, 50);
    }

    @Override
    public Shape getPitShape(Rectangle2D bounds) {
        return new Ellipse2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    @Override
    public int getMancalaSpacing() {
        return 40;
    }

    @Override
    public int getPitSpacing() {
        return 20;
    }

    @Override
    public int getRowSPacing() {
        return 30;
    }
}
