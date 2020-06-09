import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bookingaroom {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] rooms = input.nextLine().split(" ");
        int totalRooms = Integer.valueOf(rooms[0]);
        int numBookedRooms = Integer.valueOf(rooms[1]);
        if ( totalRooms == numBookedRooms ) {
            System.out.println("too late");
        } else {
            List<Integer>bookedRooms = new ArrayList<>(numBookedRooms);
            for (int i = 0; i < numBookedRooms; i++)
                bookedRooms.add(i, Integer.valueOf(input.nextLine()));
            for(int i=1; i<=100; i++) {
                if ( !bookedRooms.contains(i) ) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
