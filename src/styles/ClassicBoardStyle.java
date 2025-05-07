package styles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Thai Nguyen, Zain Khan
 * Classic Board GUI with wooden color theme visual
 */
public class ClassicBoardStyle implements BoardStyle{
    // Field of the colors
    private static final Color PIT_COLOR = new Color(139, 69,19); //Brown wood color
    private static final Color MANCALA_COLOR = new Color(160,82, 45); // Wood color
    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {
        Shape pit = getPitShape(bounds);
        g2.setColor(PIT_COLOR);
        g2.fill(pit);
        g2.setColor(Color.BLACK); //outline
        g2.draw(pit);
        drawStone(g2, pit, stones);

        // Add pit labels
        String label = isPlayerAPit ?
                "A" + (1 + (int)((bounds.getX()-100)/80)) : // Bottom row
                "B" + (6 - (int)((bounds.getX()-100)/80));  // Top row

        // Set font
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));
        // Set label color
        g2.setColor(Color.YELLOW);
        g2.drawString(label, (float)bounds.getCenterX() - 8, (float)bounds.getMaxY() + 15);
    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {
        Shape mancala = getMancalaShape(bounds);
        g2.setColor(MANCALA_COLOR);
        g2.fill(mancala);
        g2.setColor(Color.BLACK);
        g2.draw(mancala);
        drawStone(g2, mancala, stones);

        //Add Labels
        String label = isPlayerAMancala ? "Mancala A" : "Mancala B";
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));
        g2.setColor(Color.yellow);
        g2.drawString(label, (float)bounds.getCenterX() - 30, (float)bounds.getY() - 5);
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
        return new Color(205, 133, 63); // light wood
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
