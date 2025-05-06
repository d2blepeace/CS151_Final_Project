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
 * The BoardPanel handles drawing the Mancala board and reporting mouse clicks
 * on pits to the GameController.
 */
public class BoardPanel extends JPanel {
    private final MancalaBoard board;
    private BoardStyle style;
    private GameController controller;

    // Pit and board layout constants
    private static final int BOARD_X = 0;
    private static final int BOARD_Y = 20;
    private static final int BOARD_WIDTH = 675;
    private static final int BOARD_HEIGHT = 250;

    private static final int PIT_WIDTH = 60;
    private static final int PIT_HEIGHT = 80;
    private static final int MANCALA_WIDTH = 60;
    private static final int MANCALA_HEIGHT = 200;
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
        // Board background
        g2.setColor(style.getBoardBackgroundColor());
        g2.fillRoundRect(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT, 40, 40);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT, 40, 40);

        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();
        int mancalaSpacing = style.getMancalaSpacing();

        int bottomRowY = TOP_ROW_Y + PIT_HEIGHT + rowSpacing;

        // Draw pits: top row (B6–B1), bottom row (A1–A6)
        for (int i = 0; i < 6; i++) {
            // Top row (Player B)
            Rectangle2D topPit = new Rectangle2D.Double(
                    START_X + i * (PIT_WIDTH + spacing), TOP_ROW_Y,
                    PIT_WIDTH, PIT_HEIGHT);
            style.drawPit(g2, topPit, board.getPit(12 - i).getStoneCount(), false);

            // Bottom row (Player A)
            Rectangle2D bottomPit = new Rectangle2D.Double(
                    START_X + i * (PIT_WIDTH + spacing), bottomRowY,
                    PIT_WIDTH, PIT_HEIGHT);
            style.drawPit(g2, bottomPit, board.getPit(i).getStoneCount(), true);
        }

        // Draw Mancalas
        Rectangle2D mancalaA = new Rectangle2D.Double(
                START_X - mancalaSpacing - MANCALA_WIDTH,
                TOP_ROW_Y,
                MANCALA_WIDTH,
                MANCALA_HEIGHT);
        Rectangle2D mancalaB = new Rectangle2D.Double(
                START_X + 6 * (PIT_WIDTH + spacing) + spacing,
                TOP_ROW_Y,
                MANCALA_WIDTH,
                MANCALA_HEIGHT);

        style.drawMancala(g2, mancalaA, board.getPit(6).getStoneCount(), true);
        style.drawMancala(g2, mancalaB, board.getPit(13).getStoneCount(), false);
    }

    /**
     * Given a mouse point, returns the pit index (0–13) or -1 if the click is outside pits.
     */
    private int getPitIndexAt(Point p) {
        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();

        // Top row detection (pits 12–7)
        if (p.y >= TOP_ROW_Y && p.y <= TOP_ROW_Y + PIT_HEIGHT) {
            int col = (p.x - START_X) / (PIT_WIDTH + spacing);
            if (col >= 0 && col < 6) {
                return 12 - col;
            }
        }

        // Bottom row detection (pits 0–5)
        int bottomRowY = TOP_ROW_Y + PIT_HEIGHT + rowSpacing;
        if (p.y >= bottomRowY && p.y <= bottomRowY + PIT_HEIGHT) {
            int col = (p.x - START_X) / (PIT_WIDTH + spacing);
            if (col >= 0 && col < 6) {
                return col;
            }
        }

        // Ignore clicks on Mancalas
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
