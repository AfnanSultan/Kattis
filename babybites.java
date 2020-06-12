import java.util.Scanner;

public class babybites {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.valueOf(input.nextLine());
        String[] bites = input.nextLine().split(" ");
        for(int i=0; i<n; ++i) {
            if ( !(bites[i].equals("mumble") || Integer.valueOf(bites[i]) == i+1) ) {
                System.out.println("something is fishy");
                return;
            }
        }
        System.out.println("makes sense");
    }
}
