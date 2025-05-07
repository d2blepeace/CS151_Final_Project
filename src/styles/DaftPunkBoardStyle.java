package styles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Thai Nguyen, Zain Khan
 * Daft Punk inspired GUI Board
 */
public class DaftPunkBoardStyle implements BoardStyle{
    // Fields for Color
    private static final Color PIT_COLOR = new Color(20, 20, 20); // BLack
    private static final Color MANCALA_COLOR = new Color(30, 30, 30); // Dark gray
    private static final Color STONE_COLOR = new Color(255, 215, 0); // Golden
    private static final Color OUTLINE_COLOR = new Color(0, 255, 255); // Ceon cyan outline

    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {
        Shape pit = getPitShape(bounds);
        g2.setColor(PIT_COLOR);
        g2.fill(pit);
        g2.setColor(OUTLINE_COLOR);
        g2.draw(pit);
        drawStone(g2, pit, stones);

        // Add Labels
        // Add pit labels
        String label = isPlayerAPit ?
                "A" + (1 + (int)((bounds.getX()-100)/80)) : // Bottom row
                "B" + (6 - (int)((bounds.getX()-100)/80));  // Top row

        // Set font
        g2.setFont(new Font("OCR A Extended", Font.BOLD, 12));
        // Set label color
        g2.setColor(Color.CYAN);
        g2.drawString(label, (float)bounds.getCenterX() - 8, (float)bounds.getMaxY() + 15);
    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {
        Shape mancala = getMancalaShape(bounds);
        g2.setColor(MANCALA_COLOR);
        g2.fill(mancala);
        g2.setColor(new Color(255, 0, 255)); // neon pink outline
        g2.draw(mancala);
        drawStone(g2, mancala, stones);

        // Draw label
        String label = isPlayerAMancala ? "Mancala A" : "Mancala B";
        g2.setFont(new Font("OCR A Extended", Font.BOLD, 12));
        g2.setColor(Color.CYAN);
        g2.drawString(label, (float)bounds.getCenterX() - 30, (float)bounds.getY() - 5);
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

        g2.setColor(STONE_COLOR);
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
