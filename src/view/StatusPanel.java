package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * View component for the game status text
 */
public class StatusPanel extends JPanel {
    private JLabel turnLabel;

    public StatusPanel() {
        turnLabel = new JLabel("Welcome to Mancala!");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(turnLabel);
    }

    public void updateTurn(Player player) {
        turnLabel.setText(player.getName() + "'s Turn");
        turnLabel.setForeground(player.isPlayerA() ? Color.BLUE : Color.RED);
    }

    public void showWinner(Player winner) {
        if (winner == null) {
            turnLabel.setText("It's a tie!");
        } else {
            turnLabel.setText(winner.getName() + " wins!");
        }
        turnLabel.setForeground(Color.BLACK);
    }
}
