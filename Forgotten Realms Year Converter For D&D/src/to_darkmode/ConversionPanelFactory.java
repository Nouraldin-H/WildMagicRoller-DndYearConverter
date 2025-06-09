package to_darkmode;

import javax.swing.*;
import java.awt.*;

public class ConversionPanelFactory {
    boolean splitView;
    
    static JPanel CPFpanel;
    static JPanel innerPanel;
    static JLabel yearNameLabel;
    static JLabel dndYearLabel;
    static JLabel convertedYearLabel;
    
    static JSeparator separator;

    public ConversionPanelFactory() {
        splitView = false;
    }

    public void setSplitView(boolean splitView) {
        this.splitView = splitView;
    }

    public JPanel createConversionPanel(String yearName, int dndYear, int convertedYear) {
        if (splitView) {
            return createConversionPanelMode2(yearName, dndYear, convertedYear);
        } else {
            return createConversionPanelMode1(yearName, dndYear, convertedYear);
        }
    }

    static JPanel createConversionPanelMode1(String yearName, int dndYear, int convertedYear) {
        CPFpanel = new JPanel(new GridLayout(1, 1, 0, 0));
        CPFpanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 0));

        yearNameLabel = new JLabel(String.format("%s", yearName));
        yearNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        yearNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        CPFpanel.add(yearNameLabel);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));

        dndYearLabel = new JLabel(String.format("Inputted Year: %d", dndYear));
        convertedYearLabel = new JLabel(String.format("Converted Year: %d", convertedYear));
        
        innerPanel.add(dndYearLabel);
        innerPanel.add(Box.createHorizontalStrut(40));
        innerPanel.add(convertedYearLabel);

        CPFpanel.add(innerPanel);

        return CPFpanel;
    }

    static JPanel createConversionPanelMode2(String yearName, int dndYear, int convertedYear) {
        CPFpanel = new JPanel(new BorderLayout(10, 10));
        CPFpanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 0));

        yearNameLabel = new JLabel(String.format("%s", yearName));
        yearNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        yearNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        CPFpanel.add(yearNameLabel, BorderLayout.NORTH);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        dndYearLabel = new JLabel(String.format("Inputted Year: %d", dndYear));
        convertedYearLabel = new JLabel(String.format("Converted Year: %d", convertedYear));
        innerPanel.add(dndYearLabel);
        innerPanel.add(Box.createHorizontalStrut(10));
        innerPanel.add(convertedYearLabel);

        CPFpanel.add(innerPanel, BorderLayout.CENTER);

        separator = new JSeparator(SwingConstants.HORIZONTAL);
        CPFpanel.add(separator, BorderLayout.SOUTH);

        return CPFpanel;
    }
}
