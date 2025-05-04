package styles;

import view.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Select button for board styles
 */
public class StyleSelector extends JPanel {
    private JComboBox<String> styleComboBox;
    private HashMap<String, BoardStyle> styleMap;

    public StyleSelector(BoardPanel boardPanel) {
        setLayout(new FlowLayout());

        // Create styles
        BoardStyle classic = new ClassicBoardStyle();
        BoardStyle modern = new ModernBoardStyle();
        BoardStyle daftPunk = new DaftPunkBoardStyle();

        styleMap = new HashMap<>();
        styleMap.put("Classic", classic);
        styleMap.put("Modern", modern);
        styleMap.put("Daft Punk", daftPunk);

        styleComboBox = new JComboBox<>(styleMap.keySet().toArray(new String[0]));
        styleComboBox.addActionListener(e -> {
            String selected = (String) styleComboBox.getSelectedItem();
            boardPanel.setBoardStyle(styleMap.get(selected));
        });

        add(new JLabel("Select Style:"));
        add(styleComboBox);
    }
}
