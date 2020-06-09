import java.util.Scanner;

public class alphabetspam {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        double whitespace = 0;
        double lowercase = 0;
        double uppercase = 0;
        double symbol = 0;
        for(int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '_') {
                whitespace++;
            } else if ( Character.isLowerCase(c) ) {
                lowercase++;
            } else if ( Character.isUpperCase(c) ) {
                uppercase++;
            } else {
                symbol++;
            }
        }

        System.out.print(whitespace/line.length() + "\n" + lowercase/line.length() + "\n" + uppercase/line.length() + "\n" + symbol/line.length());
    }
}
