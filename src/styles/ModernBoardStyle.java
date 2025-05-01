package styles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ModernBoardStyle implements BoardStyle{
    @Override
    public void drawPit(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAPit) {

    }

    @Override
    public void drawMancala(Graphics2D g2, Rectangle2D bounds, int stones, boolean isPlayerAMancala) {

    }

    @Override
    public void drawStone(Graphics2D g2, Shape containerShape, int stones) {

    }

    @Override
    public Color getBoardBackgroundColor() {
        return null;
    }

    @Override
    public Shape getMancalaShape(Rectangle2D bounds) {
        return null;
    }

    @Override
    public Shape getPitShape(Rectangle2D bounds) {
        return null;
    }

    @Override
    public int getMancalaSpacing() {
        return 0;
    }

    @Override
    public int getPitSpacing() {
        return 0;
    }

    @Override
    public int getRowSPacing() {
        return 0;
    }
}
