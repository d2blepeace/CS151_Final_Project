package view;

import model.MancalaBoard;
import styles.BoardStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;

public class BoardPanel extends JPanel {
    private MancalaBoard board;
    private BoardStyle style;

    //Constructor
    public BoardPanel(MancalaBoard board, BoardStyle style) {
        this.board = board;
        this.style = style;
        setPreferredSize(new Dimension(800, 400));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard((Graphics2D)g);
    }

    private void drawBoard(Graphics2D g2) {
        // Board background
        int boardX = 0;
        int boardY = 20;
        int boardWidth = 675;
        int boardHeight = 250;

        //Set dimension and spacing for the board
        int pitWidth = 60;
        int pitHeight = 80;
        int mancalaWidth = 60;
        int mancalaHeight = 200;

        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();
        int mancalaSpacing = style.getMancalaSpacing();

        int topRowY = 50;
        int bottomRowY = topRowY + pitHeight + rowSpacing;
        int startX = 100;

        // Draw the main board shape (rounded rectangle)
        g2.setColor(style.getBoardBackgroundColor());
        g2.fillRoundRect(boardX, boardY, boardWidth, boardHeight, 40, 40);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(boardX, boardY, boardWidth, boardHeight, 40, 40);

        //Draw pits: (top: B6–B1, bottom: A1–A6)
        for (int i = 0; i < 6; i++) {
            // Top row (Player B)
            Rectangle2D topPit = new Rectangle2D.Double(startX + i * (pitWidth + spacing), topRowY, pitWidth, pitHeight);
            style.drawPit(g2, topPit, board.getPit(12 - i).getStoneCount(), false);
            // Bottom row (Player A)
            Rectangle2D bottomPit = new Rectangle2D.Double(startX + i * (pitWidth + spacing), bottomRowY, pitWidth, pitHeight);
            style.drawPit(g2, bottomPit, board.getPit(i).getStoneCount(), true);
        }

        // Draw Mancalas
        Rectangle2D mancalaA = new Rectangle2D.Double(startX - mancalaSpacing - mancalaWidth,
                topRowY, mancalaWidth, mancalaHeight);
        Rectangle2D mancalaB = new Rectangle2D.Double(startX + 6 * (pitWidth + spacing) + spacing,
                topRowY, mancalaWidth, mancalaHeight);

        // Player A's mancala (index 6)
        style.drawMancala(g2, mancalaA, board.getPit(6).getStoneCount(), true);
        // Player B's mancala (index 13)
        style.drawMancala(g2, mancalaB, board.getPit(13).getStoneCount(), false);
    }

    public void setBoardStyle(BoardStyle boardStyle) {
        // Update the style and redraw the board
        this.style = boardStyle;
        repaint();
    }
}
