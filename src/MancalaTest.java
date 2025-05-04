import model.MancalaBoard;
import styles.*;
import view.BoardPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Test method to test the program
 */

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            MancalaBoard board = new MancalaBoard();
            board.startBoard(4);

            BoardPanel boardPanel = new BoardPanel(board, new ClassicBoardStyle());
            frame.add(boardPanel, BorderLayout.CENTER);

            // Use new StyleSelectorPanel
            StyleSelector styleSelector = new StyleSelector(boardPanel);
            frame.add(styleSelector, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
