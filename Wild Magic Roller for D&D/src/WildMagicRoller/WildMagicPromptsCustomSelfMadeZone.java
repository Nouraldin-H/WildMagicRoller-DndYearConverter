package WildMagicRoller;

public class WildMagicPromptsCustomSelfMadeZone {

	protected static String getWildMagicPrompt(int roll) {
        // Assign wild magic prompts to different values using a switch statement.
		// Conider everyone level 9.
        String prompt = "";
        String d20_init_dex = "roll a d20 and use the result closer to one's intiative or dexterity count to target said player.";
        String d8_compass = "roll a d8, assuming 1 means north, 2 means north-east, and so on counterclockwise.";
        String half_on_success = "half as much on a successful save";
        prompt = switch (roll) {
        // EACH CATEGORY SHOULD HAVE AT LEAST 10 CASES/PROMPTS
        // Within-range damage attacks
		case 1 -> "A random creature within 90 feet of you takes 3d10 + 2 force damage. The creature takes an extra 1d10 + 3 force damage if they are wearing medium or heavy armor. " + d20_init_dex;
		case 2 -> "A random creature within 150 feet of you teleports 60 feet in a random direction. " + d8_compass + " " + d20_init_dex;
		case 3 -> "Everyone within 60 feet of you takes 2d8 + 4 damage of either force/psychic/thunder damage of your choice.";
		case 4 -> "Everyone within 150 feet of you must make a DC 18 dexterity saving throw. On a fail, they take 2d12 + 4 bludgeoning damage and an extra 2d4 + 1 force damage, or " + half_on_success + ", as the ground shakes and everyone is pulled toward it. Flying creatures have advantage on the saving throw. The ground turns back to normal after one round.";
		case 5 -> "Everyone within 120 feet of you must make a DC 15 dexterity saving throw. On a fail, they take 4d10 + 5 slashing and/or piercing damage of your choice, both non-magical, splitting the rolls (e.g. 3d10 and 1d10) into slashing or piercing damage as chosen. the modifier (+5) cannot be split, and must be assigned to either damage type. On a success, take half as much damage, as shurikens, sawblades, and other sharp object fall from above. The objects disappear after one round.";
		case 6 -> "A random creature within 50 feet of you takes magical slashing damage depending on what armor they are wearing. 2d4 + 1 for heavy armor, 3d4 + 2 for medium armor, 4d4 + 3 for light armor, and 5d4 + 4 for no armor; as slash marks appear all over the target's body. " + d20_init_dex;
		case 7 -> "Everyone within 30 feet of you must make a DC 17 intelligence saving throw. On a fail, they take 3d10 + 4 psychic damage and reduce their intelligence score by 2 until they take a long rest, or " + half_on_success + " without having their intelligence score affected, as your brain loses some of the required substances for it to function properly. ";
		case 8 -> "A random creature within 60 feet of you takes 2d6 + 3 acid damage as a corrosive substance sprays from their fingertips. The target's armor class is reduced by 2 until the end of their next turn. " + d20_init_dex;
		case 9 -> "Everyone within 90 feet of you must make a DC 16 constitution saving throw. On a fail, they take 3d8 + 5 necrotic damage, or " + half_on_success + ", as dark energy drains their life force.";
		case 10 -> "A random creature within 80 feet of you takes 4d6 + 4 fire damage as a burst of flames engulfs them. The target must also make a DC 15 dexterity saving throw or be set ablaze, taking an additional 2d6 + 2 fire damage at the start of each of their turns until they or another creature extinguishes the flames. " + d20_init_dex;

		// Within-range other attacks TODO: Polish all entries
		case 11 -> "A random creature within 60 feet of you must make a DC 14 wisdom saving throw. On a fail, they are charmed by you for 1 minute as you project an aura of irresistible charm.";
		case 12 -> "Everyone within 100 feet of you must make a DC 17 strength saving throw. On a fail, they are pushed 30 feet away from you as a powerful force emanates from your position.";
		case 13 -> "A random creature within 30 feet of you must make a DC 16 dexterity saving throw. On a fail, they are paralyzed for 1 minute as you cast a spell that temporarily freezes their body.";
		case 14 -> "Everyone within 80 feet of you must make a DC 15 constitution saving throw. On a fail, they are deafened for 1 minute as a thunderous boom echoes through the area.";
		case 15 -> "A random creature within 50 feet of you must make a DC 16 intelligence saving throw. On a fail, they are confused for 1 minute as you manipulate their thoughts.";
		case 16 -> "Everyone within 120 feet of you must make a DC 16 dexterity saving throw. On a fail, they are restrained for 1 minute as magical chains appear and bind their limbs.";
		case 17 -> "A random creature within 60 feet of you must make a DC 14 constitution saving throw. On a fail, they become poisoned for 1 minute as a cloud of toxic gas surrounds them.";
		case 18 -> "Everyone within 80 feet of you must make a DC 15 wisdom saving throw. On a fail, they are frightened for 1 minute as horrifying illusions fill their minds.";
		case 19 -> "A random creature within 90 feet of you must make a DC 15 dexterity saving throw. On a fail, they are knocked prone as a sudden gust of wind sweeps them off their feet.";
		case 20 -> "Everyone within 100 feet of you must make a DC 17 intelligence saving throw. On a fail, they are stunned for 1 minute as you overload their senses with a burst of psychic energy.";

		// Within-range beneficial
		// Within-range misc
		// random creature any range beneficial
		// random creature any range attacks
		// Summoning
		// Defense/Condition affecting (vulnerable to poison, etc.)
		// 
		// Casting attacks
		// Casting-affecting
		// Saving throws
		// Sense-affecting (darkvision, passives, etc.)
		// AC-affecting
		// Skill-affecting
		// Main Skill-affecting
		// Cosmetic appearance
		// Cosmetic hullucination
		// Info-affecting (true names, statistics, etc.)
		default -> "Nothing unusual happens.";
		};
        return prompt;
	// source: https://www.angelfire.com/rpg2/vortexshadow/magic/unstablemagic.html
        
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
