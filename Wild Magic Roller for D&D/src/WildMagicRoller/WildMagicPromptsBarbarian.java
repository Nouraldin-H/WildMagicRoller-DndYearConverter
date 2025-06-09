package WildMagicRoller;

import java.util.ResourceBundle;

public class WildMagicPromptsBarbarian {

	static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", WildMagicRoller.loadLanguagePreference());
	protected static String getWildMagicPrompt(int roll) {
        // Assign wild magic prompts to different values using a switch statement
        String prompt = "";
        prompt = switch (roll) {
        case 1 -> bundle.getString("barbarianPrompt1");
        case 2 -> bundle.getString("barbarianPrompt2");
        case 3 -> bundle.getString("barbarianPrompt3");
        case 4 -> bundle.getString("barbarianPrompt4");
        case 5 -> bundle.getString("barbarianPrompt5");
        case 6 -> bundle.getString("barbarianPrompt6");
        case 7 -> bundle.getString("barbarianPrompt7");
        case 8 -> bundle.getString("barbarianPrompt8");
        default -> bundle.getString("barbarianPromptDefault");
		};
        return prompt;
        // source: https://blackcitadelrpg.com/wild-magic-guide-5e/
	
    }

}
