package WildMagicRoller;

import java.util.ResourceBundle;

public class WildMagicPromptsZone {

	static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", WildMagicRoller.loadLanguagePreference());
	protected static String getWildMagicPrompt(int roll) {
        // Assign wild magic prompts to different values using a switch statement
        String prompt = "";
        prompt = switch (roll) {
        case 1 -> bundle.getString("castZonePrompt1");
        case 2 -> bundle.getString("castZonePrompt2");
        case 3 -> bundle.getString("castZonePrompt3");
        case 4 -> bundle.getString("castZonePrompt4");
        case 5 -> bundle.getString("castZonePrompt5");
        case 6 -> bundle.getString("castZonePrompt6");
        case 7 -> bundle.getString("castZonePrompt7");
        case 8 -> bundle.getString("castZonePrompt8");
        case 9 -> bundle.getString("castZonePrompt9");
        case 10 -> bundle.getString("castZonePrompt10");
        default -> bundle.getString("castZonePromptDefault");
		};
        return prompt;
    	// source: https://blackcitadelrpg.com/wild-magic-guide-5e/

	
//        // Assign sentences to different values
//        String sentence = "...";
//        if (roll <= 10) {
//        	sentence += "You stumble and trip, but manage to catch yourself.";
//        } else if (roll <= 20) {
//        	sentence += "You notice a strange symbol etched into a nearby tree.";
//        } else if (roll <= 30) {
//        	sentence += "A bird lands on your shoulder and sings a beautiful melody.";
//        } else if (roll <= 40) {
//        	sentence += "You smell the fragrant aroma of fresh-baked bread.";
//        } else if (roll <= 50) {
//        	sentence += "You enjoy butterflies in the meadow.";
//        } else if (roll <= 60) {
//        	sentence += "A sudden gust of wind blows your hair into your face.";
//        } else if (roll <= 70) {
//        	sentence += "You hear a distant howl that sends shivers down your spine.";
//        } else if (roll <= 80) {
//        	sentence += "You feel a sense of deja vu, as if you've been here before.";
//        } else if (roll <= 90) {
//        	sentence += "You see a shooting star streak across the sky.";
//        } else {
//        	sentence += "You find a small bag of gold coins hidden in a nearby bush.";
//        }
//
//        // Print out the roll and the assigned sentence
//        System.out.println("You rolled a " + roll + "!");
//        System.out.println(sentence);
    }

}
