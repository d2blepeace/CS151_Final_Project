import javax.swing.*;
import java.awt.*;

import controller.GameController;
import model.MancalaBoard;
import styles.ClassicBoardStyle;
import view.BoardPanel;
import view.StartPanel;
import view.StatusPanel;
import styles.StyleSelector;

public class MancalaTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Create the frame and use GridBagLayout for perfect centering
            final JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;

            // 2) Prepare model + views (but don't start the board yet)
            MancalaBoard board = new MancalaBoard();
            BoardPanel boardPanel = new BoardPanel(board, new ClassicBoardStyle());
            StatusPanel statusPanel = new StatusPanel();

            // 3) Wire controller
            GameController controller = new GameController(board, boardPanel, statusPanel);
            boardPanel.setController(controller);
            statusPanel.setController(controller);

            // 4) Build the "gameUI" container (board + status)
            JPanel gameUI = new JPanel();
            gameUI.setLayout(new BoxLayout(gameUI, BoxLayout.Y_AXIS));
            gameUI.add(Box.createVerticalGlue());
            boardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameUI.add(boardPanel);
            gameUI.add(Box.createVerticalStrut(5));
            statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameUI.add(statusPanel);
            gameUI.add(Box.createVerticalGlue());

            // 5) Add StyleSelector
            StyleSelector styleSelector = new StyleSelector(boardPanel);
            styleSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameUI.add(styleSelector);
            gameUI.add(Box.createVerticalGlue());

            // 6) Create StartPanel (lets user pick stones & first player)
            StartPanel startPanel = new StartPanel((stonesPerPit, playerAStarts) -> {
                // initialize board
                board.startBoard(stonesPerPit);
                // set first player
                if (!playerAStarts) {
                    controller.switchPlayer();
                }
                // update UI
                statusPanel.showWelcome();
                boardPanel.repaint();

                // swap out StartPanel for gameUI   —both use the same gbc to stay centered
                frame.getContentPane().removeAll();
                frame.getContentPane().add(gameUI, gbc);
                frame.revalidate();
                frame.repaint();
            });

            // 6) Show the StartPanel in center
            frame.getContentPane().add(startPanel, gbc);
            frame.pack();
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}