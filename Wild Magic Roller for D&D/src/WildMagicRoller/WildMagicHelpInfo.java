package WildMagicRoller;

import java.util.ResourceBundle;

import javax.swing.JTextArea;

public class WildMagicHelpInfo {
	static JTextArea helpMessage = new JTextArea(Integer.MAX_VALUE, Integer.MAX_VALUE);
	static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", WildMagicRoller.loadLanguagePreference());
	static {
	    helpMessage.setText(
	        String.format(
	            "%s\n\n%s\n\n%s\n\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
	            bundle.getString("closeMessageBoxWindows"),
	            bundle.getString("closeMessageBoxMac"),
	            bundle.getString("programDescription"),
	            bundle.getString("bestExperience"),
	            bundle.getString("howToUse"),
//	            String.format(bundle.getString("rollMessage"), WildMagicRoller.rollLabelNameNotHTML),
	            java.text.MessageFormat.format(bundle.getString("rollMessage"), WildMagicRoller.rollLabelNameNotHTML),
	            String.format(bundle.getString("barbarianRoll"), WildMagicRoller.rerollButtonBarbarianName),
	            String.format(bundle.getString("sorcererRoll"), WildMagicRoller.rerollButtonSorcererName),
	            String.format(bundle.getString("zoneRoll"), WildMagicRoller.rerollButtonCastZoneName),
	            bundle.getString("rollResult"),
	            bundle.getString("wildMagicLink"),
	            bundle.getString("linkExplanation"),
	            String.format(bundle.getString("historyButtonDescription"), WildMagicRoller.historyButtonName, WildMagicRoller.HISTORY_MAX_LINES),
	            String.format(bundle.getString("clearHistoryDescription"), WildMagicRoller.clearRerollsButtonName),
	            String.format(bundle.getString("darkModeButtonDescription"), WildMagicRoller.darkModeButtonName),
	            bundle.getString("iconSources"),
	            bundle.getString("iconSource1"),
	            bundle.getString("iconSource2"),
	            bundle.getString("lastUpdated")
	        )
	    );
	}
//	static String helpText = "Click X on the top right corner of this message to close this message box on Windows or Linux.\n"
//			+ "Click the red circle on the top left corner of this message to close this message box on Mac / OS X.\n\n"
//			+ ""
//			+ WildMagicRoller.mainFrameName + " is a program developed by Nouraldin Hassan. "
//			+ "The purpose of the program is to roll for the Wild Magic Surge table that exists within the Tabletop RPG 'Dungeons and Dragons', without the need for internet connection, right on your computer.\n\n"
//			+ ""
//			+ "For the best experience, make sure that you are the 'Dungeon Master' in Dungeons and Dragons that incororates Wild Magic in any way, shape, or form, into your campaigns or sessions. "
//			+ "Additionally, make sure you have a good understanding of Wild Magic in general.\n\n"
//			+ "~~~~~~ How to use this program:\n\n"
//			+ ""
//			+ "You will first see the message '" + WildMagicRoller.rollLabelNameNotHTML + "', alongside a row of four buttons above and two buttons below. \n"
//			+ "Click on '" + WildMagicRoller.rerollButtonBarbarianName + "' to roll from the Barbarian's Wild Magic Surge Table. The table simulates D8 rolls.\n"
//			+ "Click on '" + WildMagicRoller.rerollButtonSorcererName + "' to roll from the Sorcerer's Wild Magic Surge Table. The table simulates D50 rolls due to duplicate entries for all prompts in the original D100 table.\n"
//			+ "Click on '" + WildMagicRoller.rerollButtonCastZoneName + "' to roll from the Wild Magic Zone Surge Table. The table simulates D10 rolls.\n\n"
//			+ "Either 'Roll' button that you click on will overwrite the initial message with whatever roll you received (e.g. 'You rolled a 7'), followed by the prompt written underneath the overwritten message.\n"
//			+ "Whichever message appears will tell you what happens next in the game that either you, the 'Dungeon Master', or someone else rolled (e.g. 'You become invisible for the next minute. during that time, other creatures can't hear you. The invisibility ends if you attack or case a spell')\n"
//			+ "For a full list of Wild Magic Surges, please consult the wild magic table in the Basic Rules/Player's Handbook at either a physical or digital book, or on dndbeyond.com. "
//			+ "Alternatively, you may find the list and explanation of Wild Magic on the Black Citadel RPG website. \nLink: https://blackcitadelrpg.com/wild-magic-guide-5e/\n"
//			+ "(To access the URL, click and drag your mouse over the link, press CTRL + C, and then paste the link in your desired search engine/browser)\n\n"
//			+ ""
//			+ "Clicking on '" + WildMagicRoller.historyButtonName + "' Allows you to either hide or show the previous rolls that you have made, up to " + WildMagicRoller.HISTORY_MAX_LINES + " rolls and prompts (e.g. roll 10, prompt 10. Rolling again displays roll 11, prompt 11, but removes roll 1, prompt 1). "
//			+ "History is usually shown by default.\n\n"
//			+ ""
//			+ "Clicking on '" + WildMagicRoller.clearRerollsButtonName + "' resets the roll and prompt text back to '" + WildMagicRoller.rollLabelNameNotHTML + "', and removes all of the previous rolls in the history. USE WITH CAUTION.\n\n"
//			+ ""
//			+ "Clicking on '" + WildMagicRoller.darkModeButtonName + "' Changes the program theme/color from light to dark and vice versa, useful if the white background is disorienting for you, or if you prefer to use dark mode. \n"
//			+ "Dark mode is usually enabled by default.\n\n"
//			+ ""
//			+ "Icon sources:\n"
//			+ "- https://game-icons.net/1x1/lorc/magic-swirl.html\r\n"
//			+ "- https://www.freepngimg.com/png/81025-art-dice-dungeons-system-dragons-d20-triangle\n\n"
//			+ ""
//			+ "This program was last updated on September 30, 2023";

}
