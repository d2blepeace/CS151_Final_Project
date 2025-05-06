package styles;

import java.awt.*;
import java.awt.geom.*;

public class ModernBoardStyle implements BoardStyle {
    // Modern color scheme
    private static final Color PIT_COLOR = new Color(240, 240, 240); // Off-white
    private static final Color MANCALA_COLOR = new Color(200, 200, 200); // Light gray
    private static final Color STONE_COLOR = new Color(100, 100, 100); // Dark gray
    private static final Color OUTLINE_COLOR = new Color(80, 80, 80); // Charcoal

    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {
        Shape pit = getPitShape(bounds);

        // Draw pit background
        g2.setColor(PIT_COLOR);
        g2.fill(pit);

        // Draw outline
        g2.setColor(OUTLINE_COLOR);
        g2.draw(pit);

        drawStone(g2, pit, stones);
    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {
        Shape mancala = getMancalaShape(bounds);

        // Draw mancala background
        g2.setColor(MANCALA_COLOR);
        g2.fill(mancala);

        // Draw outline
        g2.setColor(OUTLINE_COLOR);
        g2.draw(mancala);

        drawStone(g2, mancala, stones);
    }

    @Override
    public void drawStone(Graphics2D g2, Shape containerShape, int stones) {
        Rectangle bounds = containerShape.getBounds();
        int stoneSize = 8;
        int stonesPerRow = 5;
        int padding = 4;

        int gridWidth = stonesPerRow * (stoneSize + padding);
        int rows = (int) Math.ceil(stones / (double) stonesPerRow);

        int startX = bounds.x + (bounds.width - gridWidth)/2;
        int startY = bounds.y + (bounds.height - (rows * (stoneSize + padding)))/2;

        g2.setColor(STONE_COLOR);
        for(int i = 0; i < stones; i++) {
            int col = i % stonesPerRow;
            int row = i / stonesPerRow;
            int x = startX + col * (stoneSize + padding);
            int y = startY + row * (stoneSize + padding);
            g2.fillOval(x, y, stoneSize, stoneSize);
        }
    }

    @Override
    public Color getBoardBackgroundColor() {
        return new Color(255, 255, 255); // Pure white
    }

    @Override
    public Shape getMancalaShape(Rectangle2D bounds) {
        return new RoundRectangle2D.Double(
                bounds.getX(),
                bounds.getY(),
                bounds.getWidth(),
                bounds.getHeight(),
                25, 25 // Rounded corners
        );
    }

    @Override
    public Shape getPitShape(Rectangle2D bounds) {
        return new Rectangle2D.Double(
                bounds.getX(),
                bounds.getY(),
                bounds.getWidth(),
                bounds.getHeight()
        );
    }

    @Override
    public int getMancalaSpacing() {
        return 35; // Slightly tighter spacing
    }

    @Override
    public int getPitSpacing() {
        return 15; // Compact pit arrangement
    }

    @Override
    public int getRowSPacing() {
        return 25; // Closer row spacing
    }
}