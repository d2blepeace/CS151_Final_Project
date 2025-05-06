import controller.GameController;
import model.MancalaBoard;
import styles.BoardStyle;
import styles.ClassicBoardStyle;
import styles.StyleSelector;
import view.BoardPanel;
import view.StatusPanel;

import javax.swing.*;
import java.awt.*;

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Model
            MancalaBoard board = new MancalaBoard();
            board.startBoard(4);

// 2) Views
            BoardPanel boardPanel = new BoardPanel(board, new ClassicBoardStyle());
            StatusPanel statusPanel = new StatusPanel();

// 3) Controller (now that both views exist)
            GameController controller = new GameController(board, boardPanel, statusPanel);

// 4) Wire controller into views
            boardPanel.setController(controller);
            statusPanel.setController(controller);

// 5) Rest of your setup…
            StyleSelector selector = new StyleSelector(boardPanel);

            JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(selector,    BorderLayout.NORTH);
            frame.add(boardPanel,  BorderLayout.CENTER);
            frame.add(statusPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

// Initialize turn display
            statusPanel.updateTurn(board.getCurrentPlayer());
        });
    }
}
