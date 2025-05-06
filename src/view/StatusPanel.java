package view;

import controller.GameController;
import model.Player;

import javax.swing.*;
import java.awt.*;

/** Zain Khan and Thai Nguyen
 * Shows whose turn it is and an Undo button.
 * Controller is injected after construction.
 */
public class StatusPanel extends JPanel {
    private final JLabel turnLabel;
    private final JButton undoButton;
    private GameController controller;

    public StatusPanel() {
        setLayout(new FlowLayout());
        turnLabel = new JLabel("Welcome to Mancala!");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));

        undoButton = new JButton("Undo");
        // initially disabled until there's something to undo
        undoButton.setEnabled(false);

        add(turnLabel);
        add(undoButton);
    }

    /** Injects the controller and wires up the Undo button. */
    public void setController(GameController controller) {
        this.controller = controller;
        undoButton.addActionListener(e -> controller.handleUndo());
        undoButton.setEnabled(controller.canUndo());
    }

    // Show Welcome text before starting game
    public void showWelcome() {
        turnLabel.setText("Welcome to Mancala!");
        turnLabel.setForeground(Color.BLACK);
        undoButton.setEnabled(false);
    }
    /** Call this after every move or undo to update the display. */
    public void updateTurn(Player player) {
        turnLabel.setText(player.getName() + "'s Turn");
        turnLabel.setForeground(player.isPlayerA() ? Color.BLUE : Color.RED);
        // enable/disable undo based on controller’s state
        undoButton.setEnabled(controller.canUndo());
    }


    /** Call when game is over to display the winner. */
    public void showWinner(Player winner) {
        if (winner == null) {
            turnLabel.setText("It's a tie!");
        } else {
            turnLabel.setText(winner.getName() + " wins!");
        }
        turnLabel.setForeground(Color.BLACK);
        undoButton.setEnabled(false);
    }
}
