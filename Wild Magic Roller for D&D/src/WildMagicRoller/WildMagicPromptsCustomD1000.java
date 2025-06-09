package WildMagicRoller;

public class WildMagicPromptsCustomD1000 {

	/**
	 * @param roll
	 * @return
	 */
	protected static String getWildMagicPrompt(int roll) {
        // Assign wild magic prompts to different values using a switch statement.
		// Conider everyone level 9.
        String prompt = "";
        String d20_init_dex = "roll a d20 and use the result closer to one's intiative or dexterity count to target said player.";
        String d8_compass = "roll a d8, assuming 1 means north, 2 means north-east, and so on counterclockwise.";
        String half_on_success = "half as much on a successful save";
        prompt = switch (roll) {
        
		case 1 -> "";
		case 2 -> "";
		case 3 -> "";
		case 4 -> "";
		case 5 -> "";
		case 6 -> "";
		case 7 -> "";
		case 8 -> "";
		case 9 -> "";
		case 10 -> "";

		default -> "Nothing unusual happens.";
		};
        return prompt;
	// source: https://www.angelfire.com/rpg2/vortexshadow/magic/unstablemagic.html
        
    }
}
