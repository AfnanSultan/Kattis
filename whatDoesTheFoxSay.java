import java.util.*;

public class whatDoesTheFoxSay {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = Integer.valueOf(input.nextLine());

        for (int i = 0; i < T; i++) {
            String line = input.nextLine();
            Set<String> set = new HashSet<>();
            List<String> sounds = new LinkedList<>(Arrays.asList(line.split(" ")));

            while ( true ) {
                String str = input.nextLine();
                if ( str.equals("what does the fox say?") )
                    break;
                String notFoxSound = str.split(" ")[2];
                set.add(notFoxSound);
            }

            Iterator iter = sounds.iterator();
            while ( iter.hasNext() ) {
                String str = (String)iter.next();
                if ( set.contains(str) ) {
                    iter.remove();
                }
            }
            for(String sound : sounds)
                System.out.print(sound + " ");
        }
    }
}
