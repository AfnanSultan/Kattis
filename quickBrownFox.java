import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class quickBrownFox {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = Integer.valueOf(input.nextLine());
        char[] letters = new char[] {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        for(int i=0; i<T; i++) {
            String str = input.nextLine().toLowerCase();
            Map<Character, Integer> charInt = new HashMap<>();
            for (Character c : str.toCharArray()) {
                if (charInt.containsKey(c)) {
                    Integer value = charInt.get(c) + 1;
                    charInt.replace(c, value);
                } else {
                    charInt.put(c, 1);
                }
            }
            boolean isPangram = true;
            for (int j = 0; j < letters.length; j++) {
                if (charInt.get(letters[j]) == null) {
                    isPangram = false;
                }
            }
            if (isPangram) {
                System.out.println("pangram");
            } else {
                System.out.print("missing ");
                for (int j = 0; j < letters.length; j++) {
                    Integer letterCount = charInt.get(letters[j]);
                    if (letterCount == null) {
                        System.out.print(letters[j]);
                    }
                }
                System.out.println();
            }
        }
    }
}
