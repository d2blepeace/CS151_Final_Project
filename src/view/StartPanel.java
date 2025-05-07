package view;

import javax.swing.*;
import java.awt.*;

/**
 * Zain Khan
 * Startup screen for Mancala:
 *  - Choose stones per pit (3–4)
 *  - Pick whether Player A or Player B goes first
 *  - Click Start Game to fire the listener
 */
public class StartPanel extends JPanel {
    public interface StartListener {
        /**
         * @param stonesPerPit  how many stones in each pit (3 or 4)
         * @param playerAStarts true if Player A should take the first turn
         */
        void onStart(int stonesPerPit, boolean playerAStarts);
    }

    public StartPanel(StartListener listener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));

        // Stones per pit
        JLabel stoneLabel = new JLabel("Enter stones per pit (3–4):");
        stoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(stoneLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(3, 3, 4, 1));
        spinner.setMaximumSize(new Dimension(60, spinner.getPreferredSize().height));
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(spinner);

        add(Box.createVerticalStrut(15));

        // Who goes first?
        JLabel firstLabel = new JLabel("Who goes first?");
        firstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(firstLabel);

        JRadioButton aButton = new JRadioButton("Player A");
        JRadioButton bButton = new JRadioButton("Player B");
        aButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        group.add(aButton);
        group.add(bButton);
        aButton.setSelected(true); // default

        add(aButton);
        add(bButton);

        add(Box.createVerticalStrut(20));

        // tart button
        JButton startBtn = new JButton("Start Game");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startBtn);

        add(Box.createVerticalGlue());

        // Listener
        startBtn.addActionListener(e -> {
            int stones = (Integer) spinner.getValue();
            boolean aStarts = aButton.isSelected();
            listener.onStart(stones, aStarts);
        });
    }
}