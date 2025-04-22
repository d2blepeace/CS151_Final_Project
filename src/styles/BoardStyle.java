package styles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Strategy interface for defining the visual style of the Mancala board.
 * Implementations will provide specific drawing logic for pits, mancalas, and stones.
 */
public interface BoardStyle {
    void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit);
    void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala);
    void drawStone(Graphics2D g2, Shape containerShape, int stones);
    Shape getMancalaShape(Rectangle2D bounds);
    Shape getPitShape(Rectangle2D bounds);
    int getMancalaSpacing();
    int getPitSpacing();
    int getRowSPacing();
}
