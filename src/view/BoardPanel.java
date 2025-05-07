package view;

import controller.GameController;
import model.MancalaBoard;
import styles.BoardStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Thai Nguyen, Zain Khan
 * The BoardPanel handles drawing the Mancala board and reporting mouse clicks
 * on pits to the GameController.
 */
public class BoardPanel extends JPanel {
    private final MancalaBoard board;
    private BoardStyle style;
    private GameController controller;
    // Pit and board layout constants
    private static final int PIT_WIDTH = 60;
    private static final int PIT_HEIGHT = 80;
    private static final int MANCALA_WIDTH = 60;
    private static final int MANCALA_HEIGHT = 200;
    private static final int BOARD_PADDING = 20;
    private static final int START_X = 100;
    private static final int TOP_ROW_Y = 50;

    public BoardPanel(MancalaBoard board, BoardStyle style) {
        this.board = board;
        this.style = style;
        setPreferredSize(new Dimension(800, 400));
    }

    /**
     * Inject the GameController and attach mouse listener for pit clicks.
     */
    public void setController(GameController controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pitIndex = getPitIndexAt(e.getPoint());
                if (pitIndex >= 0) {
                    controller.handlePitClick(pitIndex);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard((Graphics2D) g);
    }

    /**
     * Draws the board background, pits, and Mancalas using the current style.
     */
    private void drawBoard(Graphics2D g2) {
        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();
        int mancalaSpacing = style.getMancalaSpacing();

        // Calculate row width and total board width
        int rowWidth = 6 * PIT_WIDTH + 5 * spacing;
        int totalWidth = rowWidth + 2 * (MANCALA_WIDTH + mancalaSpacing);
        int totalHeight = MANCALA_HEIGHT + 50;

        // Center the board
        int boardX = ((getWidth() - totalWidth) / 2 );
        int boardY = (getHeight() - totalHeight) / 2;

        int topRowY = boardY + 25;
        int bottomRowY = topRowY + PIT_HEIGHT + rowSpacing;
        int startX = boardX + MANCALA_WIDTH + mancalaSpacing;

        // Board background
        g2.setColor(style.getBoardBackgroundColor());
        g2.fillRoundRect(boardX, boardY, totalWidth, totalHeight, 40, 40);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(boardX, boardY, totalWidth, totalHeight, 40, 40);

        // Draw pits: top row (B6–B1), bottom row (A1–A6)
        for (int i = 0; i < 6; i++) {
            int x = startX + i * (PIT_WIDTH + spacing);

            // Player B pits (top)
            Rectangle2D topPit = new Rectangle2D.Double(x, topRowY, PIT_WIDTH, PIT_HEIGHT);
            style.drawPit(g2, topPit, board.getPit(12 - i).getStoneCount(), false);

            // Player A pits (bottom)
            Rectangle2D bottomPit = new Rectangle2D.Double(x, bottomRowY, PIT_WIDTH, PIT_HEIGHT);
            style.drawPit(g2, bottomPit, board.getPit(i).getStoneCount(), true);
        }

        // Draw Mancalas
        Rectangle2D mancalaB = new Rectangle2D.Double(
                boardX + BOARD_PADDING,
                topRowY,
                MANCALA_WIDTH,
                MANCALA_HEIGHT
        );
        Rectangle2D mancalaA = new Rectangle2D.Double(
                boardX + totalWidth - MANCALA_WIDTH - BOARD_PADDING,
                topRowY,
                MANCALA_WIDTH,
                MANCALA_HEIGHT
        );

        style.drawMancala(g2, mancalaA, board.getPit(6).getStoneCount(), true);
        style.drawMancala(g2, mancalaB, board.getPit(13).getStoneCount(), false);
    }

    /**
     * Given a mouse point, returns the pit index (0–13) or -1 if the click is outside pits.
     */
    private int getPitIndexAt(Point p) {
        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();
        int mancalaSpacing = style.getMancalaSpacing();

        // Match same layout math as drawBoard
        int rowWidth = 6 * PIT_WIDTH + 5 * spacing;
        int totalWidth = rowWidth + 2 * (MANCALA_WIDTH + mancalaSpacing);
        int totalHeight = MANCALA_HEIGHT + 50;

        int boardX = ((getWidth() - totalWidth) / 2 );
        int boardY = (getHeight() - totalHeight) / 2;
        int topRowY = boardY + 25;
        int bottomRowY = topRowY + PIT_HEIGHT + rowSpacing;
        int startX = boardX + MANCALA_WIDTH + mancalaSpacing;

        // Top row detection (Player B: pits 12–7)
        if (p.y >= topRowY && p.y <= topRowY + PIT_HEIGHT) {
            int col = (p.x - startX) / (PIT_WIDTH + spacing);
            if (col >= 0 && col < 6) {
                return 12 - col;
            }
        }

        // Bottom row detection (Player A: pits 0–5)
        if (p.y >= bottomRowY && p.y <= bottomRowY + PIT_HEIGHT) {
            int col = (p.x - startX) / (PIT_WIDTH + spacing);
            if (col >= 0 && col < 6) {
                return col;
            }
        }

        return -1;
    }

    /**
     * Allows switching between different visual board styles at runtime.
     */
    public void setBoardStyle(BoardStyle boardStyle) {
        this.style = boardStyle;
        repaint();
    }
}
