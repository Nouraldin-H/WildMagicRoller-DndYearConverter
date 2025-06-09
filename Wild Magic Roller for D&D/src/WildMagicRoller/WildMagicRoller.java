package WildMagicRoller;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.Timer;

public class WildMagicRoller {
	/* TODO:
	 * - Add a settings/preferences icon
	 * - Add a checkbox that ""
	 * - Make the history panel resizable
	 * - Add a toggle that makes the sorcerer table roll d100 instead of d50.
	 * - Move the text prompts to an XML file or dictionary.
	*/
    protected static Locale locale;
	private static ResourceBundle bundle;
	
	static JMenu editMenu;
	
	static JMenuItem propertiesItem;
	static JMenuItem languageMenuItem;
	
	protected static int COOLDOWN_MS = 1000; // 1 second cooldown
	protected static int HISTORY_MAX_LINES = 10;

	final Random random = new Random();
	
	static JButton rerollButtonSorcerer;
    static String rerollButtonSorcererName;
    static String sorcererSurgeTableName;
    
    static JButton rerollButtonBarbarian;
    static String rerollButtonBarbarianName;
    static String barbarianSurgeTableName;
    
    static JButton rerollButtonCastZone;
    static String rerollButtonCastZoneName;
    static String castZoneSurgeTableName;
    
	// Create a new JFrame to display the result
	static JFrame mainFrame;
	static String mainFrameName;
	
	// Create a new JPanel to hold the label and button
	final static JPanel panel = new JPanel(new BorderLayout());
	final JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); //changes button orientation. (2, 1) means buttons arranged on top of each other. (1, 2) means buttons arranged beside each other. Other values are possible
	final JPanel darkModePanel = new JPanel(new BorderLayout());
	protected static String rollLabelNameNotHTML;
	protected static String rollLabelName;
	static JLabel rollLabel = new JLabel(rollLabelName);
    final List<String> history = new ArrayList<>();
    static JButton historyButton;
    static String historyButtonName;
    final JPanel historyPanel = new JPanel(new BorderLayout());
    static String editMenuName;
    protected static String clearRerollsConfirmName;
    protected static String rollResultName;
    
    static JButton helpButton;

    final JScrollPane helpScrollPane = new JScrollPane(WildMagicHelpInfo.helpMessage);
    
    final JTextArea historyArea = new JTextArea(5, 20);
    final JScrollPane historyScrollPane = new JScrollPane(historyArea);
    
    final JLabel historyLabel = new JLabel();
	
	final Timer cooldownTimerBarbarian = new Timer(COOLDOWN_MS, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            rerollButtonBarbarian.setEnabled(true);
            cooldownTimerBarbarian.stop();
        }
    });
	final Timer cooldownTimerSorcerer = new Timer(COOLDOWN_MS, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            rerollButtonSorcerer.setEnabled(true);
            cooldownTimerSorcerer.stop();
        }
    });
	final Timer cooldownTimerCastZone = new Timer(COOLDOWN_MS, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            rerollButtonCastZone.setEnabled(true);
            cooldownTimerCastZone.stop();
        }
    });
	List<Integer> rolls = new ArrayList<>();
    List<String> prompts = new ArrayList<>();
    public WildMagicRoller(Locale locale) {
        bundle = ResourceBundle.getBundle("MessagesBundle", locale);
        InitializeUI();
    }
	public void InitializeUI() {
		//createLayout(rollLabel, rerollButtonSorcerer, rerollButtonBarbarian, rerollButtonCastZone);
		mainFrameName = bundle.getString("title");
		mainFrame = new JFrame(mainFrameName);
		rollResultName = bundle.getString("youRolledA");
	    rerollButtonSorcererName = bundle.getString("rollSorcererWildMagicSurges");
	    sorcererSurgeTableName = bundle.getString("sorcererSurgeTable");
	    rerollButtonSorcerer = new JButton(rerollButtonSorcererName);
	    rerollButtonBarbarianName = bundle.getString("rollBarbarianWildMagicSurges");
	    barbarianSurgeTableName = bundle.getString("barbarianSurgeTable");
	    rerollButtonBarbarian = new JButton(rerollButtonBarbarianName);
	    rerollButtonCastZoneName = bundle.getString("rollZoneWildMagicSurges");
	    castZoneSurgeTableName = bundle.getString("castZoneSurgeTable");
	    rerollButtonCastZone = new JButton(rerollButtonCastZoneName);
	    rollLabelNameNotHTML = bundle.getString("youHaventRolledYet");
	    rollLabelName = bundle.getString("youHaventRolledYetHMTL");
	    rollLabel.setText(rollLabelName);
	    historyButtonName = bundle.getString("showOrHideHistory");
	    historyButton = new JButton(historyButtonName);
	    helpButton = new JButton(bundle.getString("help"));
	    darkModeButton = new JButton(bundle.getString("toggleDarkOrLightMode"));
	    clearRerollsButton = new JButton(bundle.getString("clearRolls"));
	    clearRerollsConfirmName = bundle.getString("clearRerollsConfirm");
	    historyButtonTooltipName = bundle.getString("historyButtonTooltipName");
		helpButtonTooltipName = bundle.getString("help");
		darkModeButtonTooltipName = bundle.getString("darkModeButtonTooltipName");
		clearRerollsButtonTooltipName = bundle.getString("clearRerollsButtonTooltipName");
		SwingUtilities.invokeLater(() -> invokeMenuBar());
	}
    
    private Color background = Color.WHITE;
    private Color foreground = Color.BLACK;
    private Color buttonBackground = Color.LIGHT_GRAY;
    private Color buttonForeground = Color.BLACK;
    private Color helpButtonBackground = Color.LIGHT_GRAY;
    private Color helpButtonForeground = Color.BLACK;
    private Color promptBackground = new Color(230, 230, 230);
    private Color promptForeground = Color.BLACK;
    //private Color promptBorder = Color.BLACK;
    private Color historyBackground = new Color(240, 240, 240);
    private Color historyForeground = Color.BLACK;
    //private Color historyBorder = Color.BLACK;
    //private Font font = new Font("Arial", Font.PLAIN, 14);
    
    static JButton darkModeButton;
    protected static String darkModeButtonName;
    static JButton clearRerollsButton;
    protected static String clearRerollsButtonName;
    
    Properties properties = new Properties();
    private boolean darkMode = Boolean.parseBoolean(properties.getProperty("darkModeOnLaunch", "true"));
    
    protected void updateTheme() {
    	
        try (FileInputStream fis = new FileInputStream("src\\WildMagicRoller\\config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int buttonFontSize = Integer.parseInt(properties.getProperty("buttonFontSize", "12"));
        String buttonFontType = properties.getProperty("buttonFontType", "Arial");
        int buttonFontStyle = Integer.parseInt(properties.getProperty("buttonFontStyle", "1"));
        int panelGeneralFontSize = Integer.parseInt(properties.getProperty("panelGeneralFontSize", "12"));
        String panelGeneralFontType = properties.getProperty("panelGeneralFontType", "Arial");
        int panelGeneralFontStyle = Integer.parseInt(properties.getProperty("panelGeneralFontStyle", "1"));
        int panelHistoryFontSize = Integer.parseInt(properties.getProperty("panelHistoryFontSize", "12"));
        String panelHistoryFontType = properties.getProperty("panelHistoryFontType", "Arial");
        int panelHistoryFontStyle = Integer.parseInt(properties.getProperty("panelHistoryFontStyle", "1"));

        // Set the global font size for all components
        Font buttonFont = new Font(buttonFontType, buttonFontStyle, buttonFontSize);
        Font panelGeneralFont = new Font(panelGeneralFontType, panelGeneralFontStyle, panelGeneralFontSize);
        Font panelHistoryFont = new Font(panelHistoryFontType, panelHistoryFontStyle, panelHistoryFontSize);
        rollLabel.setFont(panelGeneralFont);
        rerollButtonBarbarian.setFont(buttonFont);
        rerollButtonSorcerer.setFont(buttonFont);
        rerollButtonCastZone.setFont(buttonFont);
        helpButton.setFont(buttonFont);
        WildMagicHelpInfo.helpMessage.setFont(panelGeneralFont);
        historyButton.setFont(buttonFont);
        darkModeButton.setFont(buttonFont);
        clearRerollsButton.setFont(buttonFont);
        historyArea.setFont(panelHistoryFont);
        
        
        if (darkMode) {
        	System.out.println("Dark Mode");
            background = Color.DARK_GRAY;
            foreground = Color.WHITE;
            buttonBackground = Color.GRAY;
            buttonForeground = Color.WHITE;
            helpButtonBackground = new Color(180, 180, 180);
            helpButtonForeground = Color.WHITE;
            promptBackground = new Color(60, 60, 60);
            promptForeground = Color.WHITE;
            //promptBorder = Color.WHITE;
            historyBackground = new Color(80, 80, 80);
            historyForeground = Color.WHITE;
            //historyBorder = Color.WHITE;
            //font = new Font("Arial", Font.PLAIN, 14);
        } else {
        	System.out.println("Light Mode");
            background = Color.LIGHT_GRAY;
            foreground = Color.BLACK;
            buttonBackground = Color.LIGHT_GRAY; //button color change
            buttonForeground = Color.BLACK; //button text color change
            helpButtonBackground = new Color(140, 140, 140);
            helpButtonForeground = Color.BLACK;
            promptBackground = new Color(230, 230, 230); //prompt color change
            promptForeground = Color.BLACK; //prompt color change
            //promptBorder = Color.BLACK;
            historyBackground = new Color(240, 240, 240); //history pane color change
            historyForeground = Color.BLACK; //history pane color change
            //historyBorder = Color.BLACK;
            //font = new Font("Arial", Font.PLAIN, 14);
        }
        panel.setBackground(promptBackground);
        panel.setForeground(promptForeground);
        //panel.setBorder(BorderFactory.createLineBorder(promptBorder, 1));
        rollLabel.setBackground(background);
        rollLabel.setForeground(foreground);
        buttonPanel.setBackground(background);
        buttonPanel.setForeground(foreground);
        historyPanel.setBackground(historyBackground);
        historyPanel.setForeground(historyForeground);
        //historyPanel.setBorder(BorderFactory.createLineBorder(historyBorder, 1));
        historyLabel.setBackground(historyBackground);
        historyLabel.setForeground(historyForeground);
        mainFrame.getContentPane().setBackground(background);
        mainFrame.getContentPane().setForeground(foreground);
        rerollButtonBarbarian.setBackground(buttonBackground);
        rerollButtonBarbarian.setForeground(buttonForeground);
        rerollButtonSorcerer.setBackground(buttonBackground);
        rerollButtonSorcerer.setForeground(buttonForeground);
        rerollButtonCastZone.setBackground(buttonBackground);
        rerollButtonCastZone.setForeground(buttonForeground);
        helpButton.setBackground(helpButtonBackground);
        helpButton.setForeground(helpButtonForeground);
        historyButton.setBackground(buttonBackground);
        historyButton.setForeground(buttonForeground);
        darkModeButton.setBackground(buttonBackground);
        darkModeButton.setForeground(buttonForeground);
        clearRerollsButton.setBackground(buttonBackground);
        clearRerollsButton.setForeground(buttonForeground);
        historyScrollPane.setBackground(historyBackground);
        historyScrollPane.setForeground(historyForeground);
        historyArea.setBackground(historyBackground);
        historyArea.setForeground(historyForeground);
        //updateLabels();
    }
    
    private void createLayout(JComponent... arg) {
        Container pane = mainFrame.getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
    }
    
    public static void updateLayout(JPanel panel) {
    	((BorderLayout) panel.getLayout()).setHgap(10);
    	panel.revalidate();
    	panel.repaint();
    }
    
    static Locale loadLanguagePreference() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("src\\WildMagicRoller\\lang_config.properties")) {
            props.load(in);
            String language = props.getProperty("language", "en");
            String country = props.getProperty("country", "US");
            return new Locale(language, country);
        } catch (IOException e) {
            return new Locale("en", "US"); // Default to English if there's an error
        }
    }

    private static void showLanguageDialog() {
        String[] options = {"English", "Français", "Español", "العربية"};
        int choice = JOptionPane.showOptionDialog(mainFrame,
                bundle.getString("selectLanguage"),
                bundle.getString("languageSelection"),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        locale = null;
        switch (choice) {
            case 0:
                locale = new Locale("en", "US");
                break;
            case 1:
                locale = new Locale("fr", "FR");
                break;
            case 2:
                locale = new Locale("es", "ES");
                break;
            case 3:
                locale = new Locale("ar", "AE"); // Using UAE for Modern Standard Arabic
                break;
        }

        if (locale != null) {
            updateLanguage(locale);
            saveLanguagePreference(locale);
        }
    }
    
    private static void updateLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("MessagesBundle", locale);
        rollResultName = bundle.getString("youRolledA");
        rerollButtonSorcerer.setText(bundle.getString("rollSorcererWildMagicSurges"));
        rerollButtonBarbarian.setText(bundle.getString("rollBarbarianWildMagicSurges"));
        rerollButtonCastZone.setText(bundle.getString("rollZoneWildMagicSurges"));
        historyButton.setText(bundle.getString("showOrHideHistory"));
        rollLabelName = bundle.getString("youHaventRolledYetHMTL");
        rollLabel.setText(rollLabelName);
        helpButton.setText(bundle.getString("help"));
        darkModeButton.setText(bundle.getString("toggleDarkOrLightMode"));
        clearRerollsButton.setText(bundle.getString("clearRolls"));
        clearRerollsConfirmName = bundle.getString("clearRerollsConfirm");
        languageMenuItem.setText(bundle.getString("changeLanguage"));
        propertiesItem.setText(bundle.getString("properties"));
        mainFrameName = bundle.getString("title");
        mainFrame.setTitle(mainFrameName);
        editMenu.setText(bundle.getString("settings"));
    }
    
    private static void saveLanguagePreference(Locale locale) {
        Properties props = new Properties();
        props.setProperty("language", locale.getLanguage());
        props.setProperty("country", locale.getCountry());
        try (FileOutputStream out = new FileOutputStream("src\\WildMagicRoller\\lang_config.properties")) {
            props.store(out, "DO NOT MODIFY THIS FILE UNLESS YOU KNOW WHAT YOU ARE DOING");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isFirstLaunch() {
        Properties props = new Properties();
        boolean firstLaunch = Boolean.parseBoolean(props.getProperty("firstLaunch", "true"));
        try (FileInputStream in = new FileInputStream("src\\WildMagicRoller\\config.properties")) {
            props.load(in);
            System.out.println(Boolean.parseBoolean(props.getProperty("firstLaunch")));
            firstLaunch = Boolean.parseBoolean(props.getProperty("firstLaunch", "true"));
        } catch (IOException e) {
        	firstLaunch = true;
        }

        if (firstLaunch) {
            props.setProperty("firstLaunch", "false");
            try (FileOutputStream out = new FileOutputStream("src\\WildMagicRoller\\config.properties")) {
                props.store(out, "Defaults: \r\n"
                		+ "buttonFontSize=12\r\n"
                		+ "buttonFontStyle=1\r\n"
                		+ "buttonFontType=Arial\r\n"
                		+ "darkModeOnLaunch=false\r\n"
                		+ "firstLaunch=false\r\n"
                		+ "historyOpen=true\r\n"
                		+ "panelGeneralFontSize=12\r\n"
                		+ "panelGeneralFontStyle=1\r\n"
                		+ "panelGeneralFontType=Arial\r\n"
                		+ "panelHistoryFontSize=12\r\n"
                		+ "panelHistoryFontStyle=1\r\n"
                		+ "panelHistoryFontType=Arial\r\n"
                		+ "DO NOT CHANGE THE ABOVE INFORMATION");
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions in writing the file
            }
        }
        return firstLaunch;
    }
    
	public static void main(String[] args) {
		// Create a Random object to generate random numbers
		Locale locale = loadLanguagePreference();
		WildMagicRoller roller = new WildMagicRoller(locale);
		
		EventQueue.invokeLater(() -> {
			
			//roller.setVisible(true);

            if (isFirstLaunch()) {
            	roller.showLanguageDialog();
            	roller.saveLanguagePreference(locale); // Ensure firstLaunch is set to false after showing the dialog
            }
        });
		
		roller.run();
        roller.updateTheme();
        
        Thread watchThread = new Thread(roller::watchPropertiesFile);
	    watchThread.setDaemon(true);
	    watchThread.start();
        
//        roller.setApplicationIcon();
        
//        String osName = System.getProperty("os.name");
//        if (osName != null && osName.toLowerCase().contains("windows")) {
//            // Apply dark mode on Mac
//            try {
//                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            UIManager.put("control", new Color(128, 128, 128));
//            UIManager.put("nimbusBase", new Color(18, 30, 49));
//            UIManager.put("nimbusFocus", new Color(115, 164, 209));
//            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
//            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
//            UIManager.put("text", new Color(230, 230, 230));
//        } else {
//            // Apply light mode on other platforms
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
		
//		// Create a new JFrame to display the result
//        JFrame frame = new JFrame("D100 Roller");
//        frame.setSize(400, 200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Create a JPanel to hold the label with the result
//        JPanel panel = new JPanel();
//        JLabel label = new JLabel("You rolled a " + roll + "! " + sentence);
//        panel.add(label);
//
//        // Add the panel to the frame and display it
//        frame.add(panel);
//        frame.setVisible(true);
	}
//	private void setApplicationIcon() {
//        ImageIcon icon = null;
//
//        // Try to load the icon from the same directory as the program
//        try {
//            String iconPath = System.getProperty("user.dir") + "/icon.png";
//            icon = new ImageIcon(iconPath);
//        } catch (Exception e) {
//            // Failed to load the icon from the directory, ignore the exception
//        }
//
//        // If the icon is still null, try to load it from the resource stream
//        if (icon == null) {
//            try {
//                icon = new ImageIcon(getClass().getResource("/WildMagicRoller/icon.png"));
//            } catch (Exception e) {
//                // Failed to load the icon from the resource stream, ignore the exception
//            }
//        }
//
//        // Set the application icon if it has been successfully loaded
//        if (icon != null) {
//            mainFrame.setIconImage(icon.getImage());
//        }
//    }
	
	private void watchPropertiesFile() {
	    try {
	        Path filePath = Paths.get("config.properties").toAbsolutePath();
	        WatchService watchService = FileSystems.getDefault().newWatchService();
	        filePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

	        while (true) {
	            WatchKey key = watchService.take();
	            for (WatchEvent<?> event : key.pollEvents()) {
	                Path changedFile = (Path) event.context();
	                if ("config.properties".equals(changedFile.toString())) {
	                    // Reload and apply the properties when the file changes
	                    loadProperties();
	                    updateTheme(); // You may need to refresh other aspects of your program
	                }
	            }
	            key.reset();
	        }
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	
	private static void invokeMenuBar() {
        // Create the menu section that appears similar to "File" "Edit" "View" etc.
        JMenuBar menuBar = new JMenuBar();

        // Create the "Settings" menu
		editMenuName = bundle.getString("settings");
        editMenu = new JMenu(editMenuName);
        menuBar.add(editMenu);

        propertiesItem = new JMenuItem(bundle.getString("properties"));
        languageMenuItem = new JMenuItem(bundle.getString("changeLanguage"));
        //JMenuItem themeItem = new JMenuItem("Toggle Dark/Light Mode");
        //JMenuItem clearItem = new JMenuItem("Clear Rolls");

        editMenu.add(propertiesItem);
        editMenu.add(languageMenuItem);
        //editMenu.add(themeItem);
        //editMenu.add(clearItem);
        
        JFrame propertiesFrame = new JFrame(bundle.getString("properties"));
        JTextArea propertiesTextArea = new JTextArea(20, 40);
        JButton saveButton = new JButton(bundle.getString("save"));
        JButton cancelButton = new JButton(bundle.getString("cancel"));
		String configFilePathTest = "src\\WildMagicRoller\\config.properties";
		String configFilePath = "WildMagicRoller\\config.properties";

		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		// Add the button panel to the properties frame
		propertiesFrame.add(buttonPanel, BorderLayout.SOUTH);
        
		propertiesItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        try {
		            Desktop desktop = Desktop.getDesktop();
		            if (desktop.isSupported(Action.OPEN)) {
		                // Open the configuration file with the default program or let the user choose a program
		                desktop.open(new File(configFilePathTest));
		            } else {
		                // Handle if the platform does not support opening files
		                JOptionPane.showMessageDialog(mainFrame, "File opening is not supported on this platform.");
		            }
		        } catch (IOException e) {
		            // Handle any exceptions
		            e.printStackTrace();
		            JOptionPane.showMessageDialog(mainFrame, "An error occurred while opening the properties file.");
		        }
		    }
		});
		
		languageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLanguageDialog();
            }
        });
        
     // Add an action listener for the Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the edited text from the JTextArea
                String editedText = propertiesTextArea.getText();

                // Save the edited text back to the config.properties file
                try {
                    PrintWriter writer = new PrintWriter(configFilePath);
                    writer.print(editedText);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle any potential exceptions here
                }

                // Close the properties window
                propertiesFrame.dispose();
            }
        });

        // Add an action listener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Close the properties window without saving changes
                propertiesFrame.dispose();
            }
        });
        
        //fileMenu.addSeparator();

//        // Create the "View" menu
//        JMenu viewMenu = new JMenu("View");
//        menuBar.add(viewMenu);
//
//        JMenuItem viewItem1 = new JMenuItem("View Option 1");
//        JMenuItem viewItem2 = new JMenuItem("View Option 2");
//
//        viewMenu.add(viewItem1);
//        viewMenu.add(viewItem2);

        // Add action listeners for menu items
//        exitItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

        // Create a simple text area for the content
        JTextArea textArea = new JTextArea();
        mainFrame.add(new JScrollPane(textArea));

        mainFrame.setVisible(true);
        mainFrame.setJMenuBar(menuBar);
    }
	
	static String historyButtonTooltipName;
	static String helpButtonTooltipName;
	static String darkModeButtonTooltipName;
	static String clearRerollsButtonTooltipName;
	
	protected void run() {
		
		try (FileInputStream fis = new FileInputStream("src/WildMagicRoller/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		ImageIcon windowIcon = new ImageIcon("src/WildMagicRoller/icon.png");
		mainFrame.setIconImage(windowIcon.getImage());
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		helpButton.setSize(10, 10);
		helpButton.setPreferredSize(new Dimension(10, 10));
        helpButton.setToolTipText(helpButtonTooltipName); // Tooltip text for the button
        WildMagicHelpInfo.helpMessage.setEditable(false);
        WildMagicHelpInfo.helpMessage.setLineWrap(true);
        WildMagicHelpInfo.helpMessage.setWrapStyleWord(true);

        rollLabel.setVerticalAlignment(JLabel.TOP);
        rollLabel.setHorizontalAlignment(JLabel.CENTER);
        rollLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(rollLabel, BorderLayout.CENTER);
        //darkModeButton.setVerticalAlignment(JButton.RIGHT);
        ////darkModePanel.add(darkModeButton, BorderLayout.NORTH);
        //buttonPanel.setLayout((LayoutManager) new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //buttonPanel.add(rerollButtonSorcerer);
        //buttonPanel.add(rerollButtonCastZone);
        
        rerollButtonBarbarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Roll a new d100 and update the label with the new prompt
                int roll = random.nextInt(1, 9);
                String prompt = WildMagicPromptsBarbarian.getWildMagicPrompt(roll);
                rolls.add(roll);
                prompts.add(prompt);
                rollLabel.setText("<html>" + rollResultName + " " + roll + " " + barbarianSurgeTableName + "<br><br>" + prompt + "</html>");
                runRerollButtonHistoryHandle(roll, prompt);
                rerollButtonBarbarian.setEnabled(false);
                cooldownTimerBarbarian.start();
            }
        });
        buttonPanel.add(rerollButtonBarbarian, BorderLayout.SOUTH);
        
        rerollButtonSorcerer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Roll a new d100 and update the label with the new prompt
                int roll = random.nextInt(1, 51);
                String prompt = WildMagicPromptsSorcerer.getWildMagicPrompt(roll);
                rolls.add(roll);
                prompts.add(prompt);
                rollLabel.setText("<html>" + rollResultName + " " + roll + " " + sorcererSurgeTableName + "<br><br>" + prompt + "</html>");
                runRerollButtonHistoryHandle(roll, prompt);
                rerollButtonSorcerer.setEnabled(false);
                cooldownTimerSorcerer.start();
            }
        });
        buttonPanel.add(rerollButtonSorcerer, BorderLayout.SOUTH);
        
        rerollButtonCastZone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Roll a new d100 and update the label with the new prompt
                int roll = random.nextInt(1, 11);
                String prompt = WildMagicPromptsZone.getWildMagicPrompt(roll);
                rolls.add(roll);
                prompts.add(prompt);
                rollLabel.setText("<html>" + rollResultName + " " + roll + " " + castZoneSurgeTableName + "<br><br>" + prompt + "</html>");
                runRerollButtonHistoryHandle(roll, prompt);
                rerollButtonCastZone.setEnabled(false);
                cooldownTimerCastZone.start();
            }
        });
        buttonPanel.add(rerollButtonCastZone, BorderLayout.SOUTH);
        
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Toggle the visibility of the history panel
                historyScrollPane.setVisible(!historyScrollPane.isVisible());
                toggleHistory();
                historyPanel.add(historyLabel, BorderLayout.NORTH);
                historyPanel.add(historyScrollPane, BorderLayout.CENTER);
            }
        });
        historyButton.setToolTipText(historyButtonTooltipName);
        historyButton.setPreferredSize(new Dimension(20, 10));
        JPanel historyButtonPanel = new JPanel(new GridLayout(1, 4));
        panel.add(historyButtonPanel, BorderLayout.NORTH);
        	historyButtonPanel.setBackground(historyBackground);
        	historyButtonPanel.setForeground(historyForeground);
        	historyArea.setBackground(historyBackground);
        	historyArea.setForeground(historyForeground);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        helpScrollPane.setPreferredSize(new Dimension(600, 400)); // how wide or tall the help scroll pane is.
        helpScrollPane.setMaximumSize(new Dimension(800, 800));
        helpScrollPane.setMinimumSize(new Dimension(400, 300));
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the help button click event here
                // You can display a dialog, open a help file, or perform any other desired action
            	WildMagicHelpInfo.helpMessage.setText(WildMagicHelpInfo.helpMessage.getText());
            	SwingUtilities.invokeLater(() -> helpScrollPane.getViewport().setViewPosition(new Point(0, 0)));
                int result = JOptionPane.showOptionDialog(mainFrame, helpScrollPane, helpButtonTooltipName, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

                if (result == JOptionPane.OK_OPTION) {
                    String enteredText = WildMagicHelpInfo.helpMessage.getText();
                    System.out.println("Entered text: " + enteredText);
                }
                //WildMagicHelpInfo.helpMessage.setBackground(background);
                //WildMagicHelpInfo.helpMessage.setForeground(foreground);
            }
        });
        historyButtonPanel.add(helpButton, BorderLayout.WEST);
        
        darkModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                darkMode = !darkMode; // toggle the darkMode boolean
                updateTheme(); // update the theme
                
            }
        });
        darkModeButton.setPreferredSize(new Dimension(170, 10));
        darkModeButton.setToolTipText(darkModeButtonTooltipName);
        
        historyButtonPanel.add(darkModeButton, BorderLayout.EAST);
        if (darkMode) {
        	updateLayout(panel);
        }
        else {
        	updateLayout(panel);
        }
        
        clearRerollsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int result = JOptionPane.showConfirmDialog(
                        mainFrame,
                        clearRerollsConfirmName,
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            	if (result == JOptionPane.YES_OPTION) {
            		clearRerolls();
            		rollLabel.setText(rollLabelName);
            	}
            }
        });
        clearRerollsButton.setPreferredSize(new Dimension(10, 25));
        clearRerollsButton.setToolTipText(clearRerollsButtonTooltipName);
        
        historyButtonPanel.add(clearRerollsButton, BorderLayout.EAST);
        
        historyButtonPanel.add(historyButton, BorderLayout.EAST);
        
        // Set up the history panel
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        //historyScrollPane.setVisible(false);
        historyPanel.add(historyScrollPane, BorderLayout.CENTER);
        panel.add(historyPanel, BorderLayout.EAST);

        // Add the panel to the frame and display it
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setMinimumSize(new Dimension(690, 400));
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	private void loadProperties() {
	    try (FileInputStream input = new FileInputStream("src\\WildMagicroller\\config.properties")) {
	        properties.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void clearRerolls() {
        if (rolls != null) {
            rolls.clear();
            prompts.clear();
            updateHistory();
        }
    }
	
	protected void runRerollButtonHistoryHandle(int roll, String prompt) {
		String historyLine = rollResultName + " " + roll + ": s" + prompt;
        history.add(historyLine);
        if (history.size() > HISTORY_MAX_LINES) {
            history.remove(0);
        }
        updateHistory();
	}
	
	protected void updateHistory() {
	    StringBuilder builder = new StringBuilder();
	    int numEntries = Math.min(10, prompts.size());
	    for (int i = prompts.size() - numEntries; i < prompts.size(); i++) {
	        int index = i + 1;
	        builder.append("Roll ").append(index).append(": ").append(rollResultName + " " + rolls.get(i)).append("\n");
	        builder.append("Prompt ").append(index).append(": ").append(prompts.get(i)).append("\n\n");
	    }
	    historyArea.setText(builder.toString());
	}
	
	private void toggleHistory() {
		if (historyPanel.isVisible()) {
			historyPanel.setVisible(false);
		} else {
			historyPanel.setVisible(true);
		}
	}
}