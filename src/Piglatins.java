import java.util.Scanner;

/* This program converts regular words (or non words)
 * Into piglatin by taking the consonants before the
 * first vowel and placing them at the end and adding ay
 * or add way to the end if the word started with a vowel
 * It doesnt translate words that have special characters or 
 * numbers in them, except for '
 * also keeps capitalization, whether it was Titlecase, Uppercase, or Lowercase.
*/





public class Piglatins {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String stringOfWords;
		do {
			System.out.println("Please enter a String with multiple words to convert to pig latin.");
			stringOfWords = scan.nextLine();
			if (stringOfWords.length() == 0) {
				System.out.println("You had ONE JOB......");
				break;
			}
			// splits the string into a string array dividing it by the spaces.
			String[] unchangedWords = stringOfWords.split(" ");

			for (int i = 0; i < unchangedWords.length; i++) {
				char[] toCharArray = new char[unchangedWords[i].length()];
				toCharArray = unchangedWords[i].trim().toCharArray();
				WordConverter(toCharArray);
			}
			System.out.println("Press 'n' to quit or anything else to continue.");
		} while (scan.nextLine().charAt(0) != 'n');

		scan.close();
	}

	static void WordConverter(char unconvertedWord[]) {
		char[] consonants = new char[unconvertedWord.length];
		String changedWord = "";
		String originalWord = String.valueOf(unconvertedWord);

		// creates a for loop to check all of the char array if there a vowel.
		for (int i = 0; i < unconvertedWord.length; i++) {
			
			// uses a regular expression to check is there is a non-letter
			// character in it
			if (!originalWord.matches("[A-Za-z']*")) {
				System.out.println(
						"Unable to convert " + originalWord + " to piglatin. It contains unacceptable non-letter character(s)");
				return;
				// checks for vowels in the character array
			} else if (unconvertedWord[i] == 'a' || unconvertedWord[i] == 'e' || unconvertedWord[i] == 'i'
					|| unconvertedWord[i] == 'o' || unconvertedWord[i] == 'u' || unconvertedWord[i] == 'A'
					|| unconvertedWord[i] == 'E' || unconvertedWord[i] == 'I' || unconvertedWord[i] == 'O'
					|| unconvertedWord[i] == 'U') {

				// if there is a vowel at the first index of the array it leaves
				// it alone and concatenates it to form the piglatin word

				if (i == 0) {
					changedWord = originalWord + "way";
					changedWord = CapitalizationCheck(originalWord, changedWord);
					System.out.println(originalWord + " gets converted to: " + changedWord);
					return;
				}
				// if its not the first index it concatenates first the original
				// array and the next array made up of all the consonants
				// and then adds ayy at the end
				changedWord = String.valueOf(unconvertedWord).trim() + String.valueOf(consonants).trim() + "ay";
				
				changedWord = CapitalizationCheck(originalWord, changedWord);
				System.out.println(originalWord + " gets converted to: " + changedWord);
				return;
				// makes a second array if there was no found vowels so we can
				// add them onto the word when we find a vowel
				// added a space in the unconverted array so we can concatenate
				// them later
			} else {
				consonants[i] = unconvertedWord[i];
				unconvertedWord[i] = ' ';
				// exits out of loop if there is no vowels in the word
				if (i == originalWord.length() - 1) {
					System.out.println(originalWord + " doesnt have a vowel, you get nothing!.");
					return;
				}

			}

		}

		return;
	}

	static String CapitalizationCheck(String unconvertedWord , String pigWord) {
		char[] temp;
		//checks if the first character is uppercase and second is lowercase to format for Title Case.
		if (Character.isUpperCase(unconvertedWord.charAt(0)) && Character.isLowerCase(unconvertedWord.charAt(1))) {
			//turns everything lowercase before capitalizing the first character
			pigWord = pigWord.toLowerCase();
			//theres probably a better way to do this but I converted the string to a charcter array 
			//so i can select and capitalize it and then convert the array back to a String.
			temp = pigWord.toCharArray();
			temp[0] = Character.toUpperCase(temp[0]);
			pigWord = String.valueOf(temp);
			//checks for uppercase to convert
		}else if (Character.isUpperCase(unconvertedWord.charAt(0)) && Character.isUpperCase(unconvertedWord.charAt(1))){
			pigWord = pigWord.toUpperCase();
			//checks for lowercase to convert
		} else pigWord = pigWord.toLowerCase();
	return pigWord;

}

}
