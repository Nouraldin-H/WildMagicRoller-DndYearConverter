package to_darkmode;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class DndYearConverter {
    static JFrame mainFrame;
    static JPanel mainPanel;
    static JComboBox<String> conversionFromComboBox;
    static JTextField dndYearTextField;
    static JPanel conversionPanelContainer1;
    ConversionPanelFactory conversionPanelFactory;
    static JScrollPane scrollPane;
    
    static JLabel dndYearLabel;
    static JButton convertButton;
    static JButton resetButton;
    
    String yearName;
    
    static JPanel conversionPanel;

    static Map<String, Integer> conversionTable1;
    static Map<String, JPanel> conversionPanels1;

    static JPanel checkboxPanel;
    static JCheckBox switchViewCheckBox;
    static JCheckBox darkModeCheckBox;
    
    private static boolean darkMode = true;

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
        conversionPanelFactory = new ConversionPanelFactory();

        createGUI();
        updateTheme();
    }

    private void createGUI() {
        mainFrame = new JFrame("D&D Year Converter");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        //mainPanel.setLayout(new FlowLayout());

        Set<String> conversionTypes = new TreeSet<>(conversionTable1.keySet());

        conversionFromComboBox = new JComboBox<>(conversionTypes.toArray(new String[0]));
        conversionFromComboBox.setSelectedItem("Dalereckoning (DR)"); // Set the default selection
        mainPanel.add(conversionFromComboBox);

        dndYearLabel = new JLabel("D&D Year:");
        mainPanel.add(dndYearLabel);

        dndYearTextField = new JTextField(10);
        mainPanel.add(dndYearTextField);

        convertButton = new JButton("Convert");
        mainPanel.add(convertButton);

        resetButton = new JButton("Reset");
        mainPanel.add(resetButton);

        switchViewCheckBox = new JCheckBox("Switch to split view (applies after clicking `Convert`)");

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
                    JOptionPane.showMessageDialog(mainFrame, "Invalid input. Please enter a valid D&D year.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateTheme();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetConversionPanels();
                mainFrame.pack();
            }
        });

        switchViewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = switchViewCheckBox.isSelected();
                conversionPanelFactory.setSplitView(isSelected);
                //resetConversionPanels();
                mainFrame.pack();
            }
        });

        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	darkMode = !darkMode; // toggle the darkMode boolean
                updateTheme(); // update the theme
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    // Dark mode enabled
//                    setDarkModeUI();
//                } else {
//                    // Dark mode disabled
//                    setLightModeUI();
//                }
            }
        });
        if (darkMode) {
        	updateLayout(mainPanel);
        }
        else {
        	updateLayout(mainPanel);
        }

        checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkboxPanel.add(switchViewCheckBox);
        checkboxPanel.add(darkModeCheckBox);

        scrollPane = new JScrollPane(conversionPanelContainer1);
        mainFrame.getContentPane().add(mainPanel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(checkboxPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
        mainFrame.setMinimumSize(new Dimension(500, 200));
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        updateTheme();
    }
    
    public static void updateLayout(JPanel panel) {
    	((FlowLayout) panel.getLayout()).setHgap(10);
    	panel.revalidate();
    	panel.repaint();
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
            yearName = entry.getKey();
            conversionPanel = conversionPanelFactory.createConversionPanel(yearName, dndYear, convertedYear);
            conversionPanelContainer1.add(conversionPanel);
            conversionPanels1.put(yearName, conversionPanel);
        }

        mainFrame.pack();
    }

    private void resetConversionPanels() {
        conversionPanelContainer1.removeAll();
        conversionPanels1.clear();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    static Color background;
    static Color foreground;
    static Color buttonBackground;
    static Color buttonForeground;
    static Color promptBackground;
    static Color promptForeground;
    //Color promptBorder;
    static Color historyBackground;
    static Color historyForeground;
    static Color checkboxBackground;
    static Color checkboxForeground;
    static Color CPFbackground;
    static Color CPFforeground;
    static Color CPFTextPanelBackground;
    static Color CPFTextPanelForeground;
    //private Color historyBorder = Color.BLACK;

    protected void updateTheme() {
        if (darkMode) {
            background = new Color(64, 64, 64);
            foreground = new Color(0, 0, 0); // button panel
            buttonBackground = new Color(192, 192, 192); // button panel
            buttonForeground = new Color(0, 0, 0); // button panel
            promptBackground = new Color(230, 230, 230); // button panel
            promptForeground = new Color(0, 0, 0);
            historyBackground = new Color(240, 240, 240);
            historyForeground = new Color(0, 0, 0);
            checkboxBackground = new Color(240, 240, 240); // checkbox panel
            checkboxForeground = new Color(0, 0, 0); // checkbox panel
            CPFbackground = new Color(230, 230, 230); // output panel for bottom background (inputted, converted)
            CPFforeground = new Color(0, 0, 0);
            CPFTextPanelBackground = new Color(230, 230, 230); // output panel for whole background
            CPFTextPanelForeground = new Color(30, 30, 30); // output panel for bottom text
            //System.out.println("Light Mode");
        } else {
            background = new Color(192, 192, 192);
            foreground = new Color(255, 255, 255); // button panel
            buttonBackground = new Color(80, 80, 80); // button panel
            buttonForeground = new Color(255, 255, 255); // button panel
            promptBackground = new Color(60, 60, 60); // button panel
            promptForeground = new Color(255, 255, 255);
            historyBackground = new Color(80, 80, 80);
            historyForeground = new Color(255, 255, 255);
            checkboxBackground = new Color(80, 80, 80); // checkbox panel
            checkboxForeground = new Color(255, 255, 255); // checkbox panel
            CPFbackground = new Color(75, 75, 75); // output panel for bottom background text (inputted, converted)
            CPFforeground = new Color(255, 255, 255);
            CPFTextPanelBackground = new Color(75, 75, 75); // output panel for whole background
            CPFTextPanelForeground = new Color(255, 255, 255); // output panel for bottom text
        	//System.out.println("Dark Mode");
        }

        mainPanel.setBackground(promptBackground);
        mainPanel.setForeground(promptForeground);
        dndYearLabel.setBackground(background);
        dndYearLabel.setForeground(foreground);
        conversionPanelContainer1.setBackground(background);
        conversionPanelContainer1.setForeground(foreground);
        convertButton.setBackground(buttonBackground);
        convertButton.setForeground(buttonForeground);
        resetButton.setBackground(buttonBackground);
        resetButton.setForeground(buttonForeground);
        switchViewCheckBox.setBackground(checkboxBackground);
        switchViewCheckBox.setForeground(checkboxForeground);
        darkModeCheckBox.setBackground(checkboxBackground);
        darkModeCheckBox.setForeground(checkboxForeground);
        conversionFromComboBox.setBackground(buttonBackground);
        conversionFromComboBox.setForeground(buttonForeground);
        dndYearTextField.setBackground(checkboxBackground);
        dndYearTextField.setForeground(checkboxForeground);
        scrollPane.setBackground(historyBackground);
        scrollPane.setForeground(historyForeground);
        checkboxPanel.setBackground(checkboxBackground);
        checkboxPanel.setForeground(checkboxForeground);
        if (ConversionPanelFactory.CPFpanel != null) {
        	ConversionPanelFactory.CPFpanel.setBackground(CPFbackground);
            ConversionPanelFactory.CPFpanel.setForeground(CPFforeground);
            ConversionPanelFactory.innerPanel.setBackground(CPFbackground);
            ConversionPanelFactory.innerPanel.setForeground(CPFforeground);
            ConversionPanelFactory.yearNameLabel.setBackground(CPFTextPanelBackground);
            ConversionPanelFactory.yearNameLabel.setForeground(CPFTextPanelForeground);
            ConversionPanelFactory.dndYearLabel.setBackground(CPFTextPanelBackground);
            ConversionPanelFactory.dndYearLabel.setForeground(CPFTextPanelForeground);
            ConversionPanelFactory.convertedYearLabel.setBackground(CPFTextPanelBackground);
            ConversionPanelFactory.convertedYearLabel.setForeground(CPFTextPanelForeground);
            if (ConversionPanelFactory.separator != null) {
            ConversionPanelFactory.separator.setBackground(CPFbackground);
            ConversionPanelFactory.separator.setForeground(CPFforeground);
            }
        }

        updateOutputPanelsTheme();

        mainFrame.pack();
    }
    private void updateOutputPanelsTheme() {
        for (Map.Entry<String, JPanel> entry : conversionPanels1.entrySet()) {
            JPanel panel = entry.getValue();
            panel.setBackground(CPFTextPanelBackground);
            panel.setForeground(CPFTextPanelForeground);

            // Update the components within the panel
            for (Component component : panel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel subPanel = (JPanel) component;
                    subPanel.setBackground(CPFTextPanelBackground);
                    subPanel.setForeground(CPFTextPanelForeground);

                    // Update the components within the sub-panel
                    for (Component subComponent : subPanel.getComponents()) {
                        if (subComponent instanceof JLabel) {
                            JLabel label = (JLabel) subComponent;
                            label.setBackground(CPFTextPanelBackground);
                            label.setForeground(CPFTextPanelForeground);
                        }
                        // Update other component types as needed
                    }
                } else if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setBackground(CPFTextPanelBackground);
                    label.setForeground(CPFTextPanelForeground);
                }
                // Update other component types as needed
            }
        }
    }


    
//    private void setDarkModeUI() {
//        // Set dark mode UI colors
//        mainPanel.setBackground(Color.DARK_GRAY);
//        mainPanel.setForeground(Color.WHITE);
//        conversionPanelContainer1.setBackground(Color.DARK_GRAY);
//        conversionPanelContainer1.setForeground(Color.WHITE);
//        checkboxPanel.setBackground(Color.DARK_GRAY);
//        checkboxPanel.setForeground(Color.WHITE);
//        switchViewCheckBox.setBackground(Color.DARK_GRAY);
//        switchViewCheckBox.setForeground(Color.WHITE);
//        darkModeCheckBox.setBackground(Color.DARK_GRAY);
//        darkModeCheckBox.setForeground(Color.WHITE);
//        frame.getContentPane().setBackground(Color.DARK_GRAY);
//        frame.getContentPane().setForeground(Color.WHITE);
//        UIManager.put("Label.foreground", Color.WHITE);
//        UIManager.put("TextField.background", Color.DARK_GRAY);
//        UIManager.put("TextField.foreground", Color.WHITE);
//        UIManager.put("ComboBox.background", Color.DARK_GRAY);
//        UIManager.put("ComboBox.foreground", Color.WHITE);
//        UIManager.put("Button.background", Color.DARK_GRAY);
//        UIManager.put("Button.foreground", Color.WHITE);
//        SwingUtilities.updateComponentTreeUI(frame);
//    }
//
//    private void setLightModeUI() {
//        // Set light mode UI colors
//        mainPanel.setBackground(null);
//        mainPanel.setForeground(null);
//        conversionPanelContainer1.setBackground(null);
//        conversionPanelContainer1.setForeground(null);
//        frame.getContentPane().setBackground(null);
//        frame.getContentPane().setForeground(null);
//        UIManager.put("Label.foreground", null);
//        UIManager.put("TextField.background", null);
//        UIManager.put("TextField.foreground", null);
//        UIManager.put("ComboBox.background", null);
//        UIManager.put("ComboBox.foreground", null);
//        UIManager.put("Button.background", null);
//        UIManager.put("Button.foreground", null);
//        SwingUtilities.updateComponentTreeUI(frame);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DndYearConverter();
                darkModeCheckBox.setSelected(darkMode);
            }
        });
    }
}
