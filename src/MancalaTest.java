import model.MancalaBoard;
import styles.BoardStyle;
import styles.ClassicBoardStyle;
import styles.StyleSelector;
import view.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MancalaBoard board = new MancalaBoard();
            board.startBoard(4);

            BoardStyle style = new ClassicBoardStyle();
            BoardPanel panel = new BoardPanel(board, style);
            StyleSelector selector = new StyleSelector(panel); // Add style selector

            JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(selector, BorderLayout.NORTH); // Add selector to the top
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}