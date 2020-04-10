import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class pigLatin {

    public static boolean isVowel(char c ){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        String line;
        while ( input.hasNextLine() ) {
            line = input.nextLine();
            if ( line.isEmpty() )
                break;
            String[] split = line.split(" ");
            for(int i=0; i<split.length; i++) {
                words.add(split[i]);
            }
        }

        for(int i=0; i<words.size(); i++) {
            String word = words.get(i);
            StringBuilder consonantsBeforeFirstVowel = new StringBuilder();
            char firstLetter = word.charAt(0);
            char firstVowel = word.charAt(0);
            String afterFirstVowel = "";

            // word begins with a consonant
            if ( !isVowel(firstLetter) ) {
                // Take all the letters before the first vowel
                for(int j=0; j<word.length(); j++) {
                    // keep firstVowel, and save everything AFTER firstVowel
                    if ( isVowel(word.charAt(j)) ) {
                        firstVowel = word.charAt(j);
                        afterFirstVowel = word.substring(j+1);
                        break;
                    }
                    // Save everything until vowel found
                    consonantsBeforeFirstVowel.append(word.charAt(j));
                }

                // move lettersBeforeFirstVowel to the end of the word and add "ay"
                word = firstVowel + afterFirstVowel + consonantsBeforeFirstVowel.toString() + "ay";
                System.out.print(word + " ");
                words.set(i, word);

                // word begins with a vowel (including y)
            } else {
                // add yay to the end of the word
                word = word + "yay";
                System.out.print(word + " ");
                words.set(i, word);
            }
        }
    }
}