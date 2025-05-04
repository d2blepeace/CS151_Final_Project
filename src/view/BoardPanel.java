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
        // Board and pit dimensions
        int pitWidth = 60;
        int pitHeight = 80;
        int mancalaWidth = 60;
        int mancalaHeight = 200;
        int outerPadding = 20;

        int spacing = style.getPitSpacing();
        int rowSpacing = style.getRowSPacing();
        int mancalaSpacing = style.getMancalaSpacing();

        int rowWidth = (6 * pitWidth) + (5 * spacing);
        int boardWidth = mancalaWidth * 2 + mancalaSpacing * 2 + rowWidth;
        int boardHeight = mancalaHeight + 50;

        //Center of the board
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = (getHeight() - boardHeight) / 2;

        // Draw background board
        g2.setColor(style.getBoardBackgroundColor());
        g2.fillRoundRect(boardX, boardY, boardWidth, boardHeight, 40, 40);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(boardX, boardY, boardWidth, boardHeight, 40, 40);

        // Y-positions for rows
        int topRowY = boardY + 25;
        int bottomRowY = topRowY + pitHeight + rowSpacing;
        int startX = boardX + mancalaWidth + mancalaSpacing;

        // Draw pits
        for (int i = 0; i < 6; i++) {
            int x = startX + i * (pitWidth + spacing);
            Rectangle2D topPit = new Rectangle2D.Double(x, topRowY, pitWidth, pitHeight);
            Rectangle2D bottomPit = new Rectangle2D.Double(x, bottomRowY, pitWidth, pitHeight);
            // B6-B1
            style.drawPit(g2, topPit, board.getPit(12 - i).getStoneCount(), false);
            // A1 - A6
            style.drawPit(g2, bottomPit, board.getPit(i).getStoneCount(), true);
        }
        // Mancalas
        Rectangle2D mancalaA = new Rectangle2D.Double(boardX + outerPadding, topRowY,
                mancalaWidth, mancalaHeight);
        Rectangle2D mancalaB = new Rectangle2D.Double(boardX + boardWidth - mancalaWidth - outerPadding, topRowY,
                mancalaWidth, mancalaHeight);

        style.drawMancala(g2, mancalaA, board.getPit(6).getStoneCount(), true);
        style.drawMancala(g2, mancalaB, board.getPit(13).getStoneCount(), false);
    }
    // Set the style of the board
    public void setBoardStyle(BoardStyle newStyle) {
        this.style = newStyle;
        repaint(); // Refresh the board with the new style
    }
}
