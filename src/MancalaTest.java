import model.MancalaBoard;
import styles.BoardStyle;
import styles.ClassicBoardStyle;
import view.BoardPanel;

import javax.swing.*;

/**
 * Test method to test the program
 */

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MancalaBoard board = new MancalaBoard();
            board.startBoard(4);

            BoardStyle style = new ClassicBoardStyle();
            BoardPanel panel = new BoardPanel(board, style);

            JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
