import controller.GameController;
import model.MancalaBoard;
import styles.*;
import view.BoardPanel;
import view.StatusPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Test method to test the program
 */

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Setup main frame
            JFrame frame = new JFrame("Mancala Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            //Status Panel
            StatusPanel statusPanel = new StatusPanel();
            frame.add(statusPanel, BorderLayout.NORTH);

            // Model
            MancalaBoard board = new MancalaBoard();
            board.startBoard(4); // Start with 4 stones per pit

            // View
            BoardPanel boardPanel = new BoardPanel(board, new ClassicBoardStyle());
            frame.add(boardPanel, BorderLayout.CENTER);

            // Controller
            GameController controller = new GameController(board, boardPanel, statusPanel);

            // Control panel for style
            JPanel controlPanel = new JPanel(new FlowLayout());


            // StyleSelector component
            StyleSelector styleSelector = new StyleSelector(boardPanel);
            controlPanel.add(styleSelector);

            // Undo button
            JButton undoButton = new JButton("Undo");
            undoButton.addActionListener(e -> controller.undoMove());
            controlPanel.add(undoButton);

            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
