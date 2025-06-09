import javax.swing.*;
import java.awt.*;

public class ConversionPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel yearNameLabel;
    private JLabel dndYearLabel;
    private JLabel convertedYearLabel;

    public ConversionPanel(String yearName, int dndYear, int convertedYear) {
        setLayout(new GridLayout(1, 1, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 0));

        yearNameLabel = new JLabel(String.format("%s", yearName));
        yearNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        yearNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(yearNameLabel);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        dndYearLabel = new JLabel(String.format("Inputted Year: %d", dndYear));
        convertedYearLabel = new JLabel(String.format("Converted Year: %d", convertedYear));
        innerPanel.add(dndYearLabel);
        innerPanel.add(Box.createHorizontalStrut(40));
        innerPanel.add(convertedYearLabel);

        add(innerPanel);
    }

    public void setYearName(String yearName) {
        yearNameLabel.setText(yearName);
    }

    public void setDndYear(int dndYear) {
        dndYearLabel.setText(String.format("Inputted Year: %d", dndYear));
    }

    public void setConvertedYear(int convertedYear) {
        convertedYearLabel.setText(String.format("Converted Year: %d", convertedYear));
    }
}
