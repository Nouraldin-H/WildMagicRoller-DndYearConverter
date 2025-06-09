import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DndYearConverter {
    private JFrame frame;
    private JPanel mainPanel;
    private JComboBox<String> conversionFromComboBox;
    private JTextField dndYearTextField;
    private JPanel conversionPanelContainer1;

    private Map<String, Integer> conversionTable1;
    private Map<String, JPanel> conversionPanels1;

    public DndYearConverter() {
        conversionTable1 = new TreeMap<>();
        conversionTable1.put("Cormyr Reckoning (CR)", -25);
        conversionTable1.put("Dalereckoning (DR)", 0);
        conversionTable1.put("Kozakuran Calendar (KC)", 74);
        conversionTable1.put("Moonshae Reckoning (MR)", -200);
        conversionTable1.put("Mulhorand Calendar (MC)", 2136);
        conversionTable1.put("Tethyr-reckoning (TR)", 212);
        conversionTable1.put("Netheril Year (NY)", 3859);
        conversionTable1.put("North-reckoning (NR)", -1032);
        conversionTable1.put("Present Reckoning (PR)", -1358);
        conversionTable1.put("Shou Year (SY)", 1250);
        conversionTable1.put("Timesong of Serôs (TS)", 70);
        conversionTable1.put("Ulutiun Calendar (UC)", 2550);
        conversionTable1.put("Wa Year (WY)", 418);

        conversionPanels1 = new HashMap<>();

        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("D&D Year Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        Set<String> conversionTypes = new TreeSet<>(conversionTable1.keySet());

        conversionFromComboBox = new JComboBox<>(conversionTypes.toArray(new String[0]));
        mainPanel.add(conversionFromComboBox);

        JLabel dndYearLabel = new JLabel("D&D Year:");
        mainPanel.add(dndYearLabel);

        dndYearTextField = new JTextField(10);
        mainPanel.add(dndYearTextField);

        JButton convertButton = new JButton("Convert");
        mainPanel.add(convertButton);

        JButton resetButton = new JButton("Reset");
        mainPanel.add(resetButton);

        conversionPanelContainer1 = new JPanel();
        conversionPanelContainer1.setLayout(new BoxLayout(conversionPanelContainer1, BoxLayout.Y_AXIS));

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedConversionFrom = (String) conversionFromComboBox.getSelectedItem();
                String dndYearText = dndYearTextField.getText();
                try {
                    int dndYear = Integer.parseInt(dndYearText);
                    updateConversionPanels(selectedConversionFrom, dndYear);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid D&D year.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetConversionPanels();
                frame.pack();
            }
        });

        JScrollPane scrollPane = new JScrollPane(conversionPanelContainer1);
        frame.getContentPane().add(mainPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(500, 200));
        frame.pack();
        frame.setVisible(true);
    }

    private void updateConversionPanels(String selectedConversionFrom, int dndYear) {
        conversionPanelContainer1.removeAll();
        conversionPanels1.clear();

        int conversionOffset1 = 0;
        if (conversionTable1.containsKey(selectedConversionFrom)) {
            conversionOffset1 = conversionTable1.get(selectedConversionFrom);
        }

        for (Map.Entry<String, Integer> entry : conversionTable1.entrySet()) {
            int convertedYear = dndYear + entry.getValue() - conversionOffset1;
            String yearName = entry.getKey();
            JPanel conversionPanel = createConversionPanel(yearName, dndYear, convertedYear);
            conversionPanelContainer1.add(conversionPanel);
            conversionPanels1.put(yearName, conversionPanel);
        }

        frame.pack();
    }

    private JPanel createConversionPanel(String yearName, int dndYear, int convertedYear) {
        JPanel panel = new JPanel(new GridLayout(1, 1, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 0));

        JLabel yearNameLabel = new JLabel(String.format("%s", yearName));
        yearNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        yearNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(yearNameLabel);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        JLabel dndYearLabel = new JLabel(String.format("Inputted Year: %d", dndYear));
        JLabel convertedYearLabel = new JLabel(String.format("Converted Year: %d", convertedYear));
        innerPanel.add(dndYearLabel);
        innerPanel.add(Box.createHorizontalStrut(40));
        innerPanel.add(convertedYearLabel);

        panel.add(innerPanel);
        
        return panel;
    }

//    private JPanel createConversionPanelMode2(String yearName, int dndYear, int convertedYear) {
//        JPanel panel = new JPanel(new BorderLayout(10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 0));
//
//        JLabel yearNameLabel = new JLabel(String.format("%s", yearName));
//        yearNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
//        yearNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        panel.add(yearNameLabel, BorderLayout.NORTH);
//
//        JPanel innerPanel = new JPanel();
//        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
//        JLabel dndYearLabel = new JLabel(String.format("Inputted Year: %d", dndYear));
//        JLabel convertedYearLabel = new JLabel(String.format("Converted Year: %d", convertedYear));
//        innerPanel.add(dndYearLabel);
//        innerPanel.add(Box.createHorizontalStrut(40));
//        innerPanel.add(convertedYearLabel);
//
//        panel.add(innerPanel, BorderLayout.CENTER);
//
//        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
//        panel.add(separator, BorderLayout.SOUTH);
//
//        return panel;
//    }
 
    private void resetConversionPanels() {
        conversionPanelContainer1.removeAll();
        conversionPanels1.clear();
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DndYearConverter();
            }
        });
    }
}
