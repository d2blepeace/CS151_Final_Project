package styles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Daft Punk inspired GUI Board
 * Pit: Neon cyan outline (0, 255, 255), black pit
 * Mancala: Neon pink outline - (255, 0, 255)
 * Stones: Gold - (255, 215, 0)
 */
public class DaftPunkBoardStyle implements BoardStyle{
    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {
        Shape pit = getPitShape(bounds);
        g2.setColor(new Color(20, 20, 20)); // black
        g2.fill(pit);
        g2.setColor(new Color(0, 255, 255)); // neon cyan outline
        g2.draw(pit);
        drawStone(g2, pit, stones);
    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {
        Shape mancala = getMancalaShape(bounds);
        g2.setColor(new Color(30, 30, 30)); // dark gray
        g2.fill(mancala);
        g2.setColor(new Color(255, 0, 255)); // neon pink outline
        g2.draw(mancala);
        drawStone(g2, mancala, stones);
    }

    @Override
    public void drawStone(Graphics2D g2, Shape containerShape, int stones) {
        Rectangle bounds = containerShape.getBounds();
        int radius = 10;
        int stonesPerRow = 4;
        int rows = (int) Math.ceil(stones / (double) stonesPerRow);

        int gridWidth = stonesPerRow * (radius + 3);
        int gridHeight = rows * (radius + 3);

        int startX = bounds.x + (bounds.width - gridWidth) / 2;
        int startY = bounds.y + (bounds.height - gridHeight) / 2;

        g2.setColor(new Color(255, 215, 0));
        for (int i = 0; i < stones; i++) {
            int col = i % stonesPerRow;
            int row = i / stonesPerRow;
            int x = startX + col * (radius + 3);
            int y = startY + row * (radius + 3);
            g2.fillOval(x, y, radius, radius);
        }
    }

    @Override
    public Color getBoardBackgroundColor() {
        return new Color(15, 15, 15);
    }

    @Override
    public Shape getMancalaShape(Rectangle2D bounds) {
        return new RoundRectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), 40, 40);
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
