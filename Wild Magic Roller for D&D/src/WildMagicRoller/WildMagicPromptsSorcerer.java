package WildMagicRoller;

import java.util.ResourceBundle;

public class WildMagicPromptsSorcerer {

	static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", WildMagicRoller.loadLanguagePreference());
	protected static String getWildMagicPrompt(int roll) {
        // Assign wild magic prompts to different values using a switch statement
        String prompt = "";
        prompt = switch (roll) {
        case 1 -> bundle.getString("sorcererPrompt1");
        case 2 -> bundle.getString("sorcererPrompt2");
        case 3 -> bundle.getString("sorcererPrompt3");
        case 4 -> bundle.getString("sorcererPrompt4");
        case 5 -> bundle.getString("sorcererPrompt5");
        case 6 -> bundle.getString("sorcererPrompt6");
        case 7 -> bundle.getString("sorcererPrompt7");
        case 8 -> bundle.getString("sorcererPrompt8");
        case 9 -> bundle.getString("sorcererPrompt9");
        case 10 -> bundle.getString("sorcererPrompt10");
        case 11 -> bundle.getString("sorcererPrompt11");
        case 12 -> bundle.getString("sorcererPrompt12");
        case 13 -> bundle.getString("sorcererPrompt13");
        case 14 -> bundle.getString("sorcererPrompt14");
        case 15 -> bundle.getString("sorcererPrompt15");
        case 16 -> bundle.getString("sorcererPrompt16");
        case 17 -> bundle.getString("sorcererPrompt17");
        case 18 -> bundle.getString("sorcererPrompt18");
        case 19 -> bundle.getString("sorcererPrompt19");
        case 20 -> bundle.getString("sorcererPrompt20");
        case 21 -> bundle.getString("sorcererPrompt21");
        case 22 -> bundle.getString("sorcererPrompt22");
        case 23 -> bundle.getString("sorcererPrompt23");
        case 24 -> bundle.getString("sorcererPrompt24");
        case 25 -> bundle.getString("sorcererPrompt25");
        case 26 -> bundle.getString("sorcererPrompt26");
        case 27 -> bundle.getString("sorcererPrompt27");
        case 28 -> bundle.getString("sorcererPrompt28");
        case 29 -> bundle.getString("sorcererPrompt29");
        case 30 -> bundle.getString("sorcererPrompt30");
        case 31 -> bundle.getString("sorcererPrompt31");
        case 32 -> bundle.getString("sorcererPrompt32");
        case 33 -> bundle.getString("sorcererPrompt33");
        case 34 -> bundle.getString("sorcererPrompt34");
        case 35 -> bundle.getString("sorcererPrompt35");
        case 36 -> bundle.getString("sorcererPrompt36");
        case 37 -> bundle.getString("sorcererPrompt37");
        case 38 -> bundle.getString("sorcererPrompt38");
        case 39 -> bundle.getString("sorcererPrompt39");
        case 40 -> bundle.getString("sorcererPrompt40");
        case 41 -> bundle.getString("sorcererPrompt41");
        case 42 -> bundle.getString("sorcererPrompt42");
        case 43 -> bundle.getString("sorcererPrompt43");
        case 44 -> bundle.getString("sorcererPrompt44");
        case 45 -> bundle.getString("sorcererPrompt45");
        case 46 -> bundle.getString("sorcererPrompt46");
        case 47 -> bundle.getString("sorcererPrompt47");
        case 48 -> bundle.getString("sorcererPrompt48");
        case 49 -> bundle.getString("sorcererPrompt49");
        case 50 -> bundle.getString("sorcererPrompt50");
        default -> bundle.getString("sorcererPromptDefault");
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
