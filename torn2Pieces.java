import java.util.*;

public class torn2Pieces {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pieces = Integer.parseInt(input.nextLine());
        Map<String, Vertex> adjList = new HashMap<>(); // The graph
        ArrayList<String> exists = new ArrayList<>(); // The "bag"
        for(int i=0; i<pieces; i++) {
            String line = input.nextLine();
            // Get source element aka substring from 0 to first occurence of " "
            String source = line.substring(0, line.indexOf(" "));
            Vertex vertex;
            if ( exists.contains(source) ) {
                vertex = adjList.get(source);
            } else {
                exists.add(source);
                vertex = new Vertex(source);
            }

            // Get neighbors.... aka the elements from string after " " and exlcude element from index 0
            String[] neighbors = line.substring(line.indexOf(" ")+1).split(" ");
            for(int j = 0; j < neighbors.length; j++) {
                // if the neighbor vertex is already in the bag, we add it to the neighbors
                if ( exists.contains(neighbors[j]) ) {
                    vertex.neighbors.add(adjList.get(neighbors[j]));
                    adjList.get(neighbors[j]).neighbors.add(vertex);
                } else {
                    exists.add(neighbors[j]);
                    adjList.put(neighbors[j], new Vertex(neighbors[j]));
                    vertex.neighbors.add(adjList.get(neighbors[j]));
                    adjList.get(neighbors[j]).neighbors.add(vertex);
                }
            }
            adjList.put(source, vertex); // add path from source -> dest
        }

        String[] startDest = input.nextLine().split(" ");
        String start = startDest[0];
        String end = startDest[1];

        if (adjList.get(start) == null && adjList.get(end) == null) {
            System.out.println("no route found");
        } else {
            List<Vertex> path = breadthFirstSearch(adjList.get(start), adjList.get(end));
            if ( path.size() == 1 ) {
                System.out.println("no route found");
            } else {
                // Remove [ ] and , from the answer
                System.out.println(path.toString().replace("[", "").replace("]", "").replace(",", ""));
            }
        }

    } // main

    static class Vertex {
        ArrayList<Vertex> neighbors;
        String name;
        boolean visited;

        public Vertex(String name) {
            neighbors = new ArrayList<>();
            this.name = name;
            this.visited = false;
        }

        public String toString() {
            return name;
        }

        public boolean equals(Object object) {
            return ((Vertex) object).name.equals(name); // Override equals and compare names
        }
    }

    public static List<Vertex> breadthFirstSearch(Vertex src, Vertex dest) {
        List<Vertex> path = new LinkedList<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> prev = new HashMap<>();

        // enqueue starting vertex, mark it as visited
        src.visited = true;
        queue.add(src);

        while ( !queue.isEmpty() ) {
            Vertex parent = queue.remove();
            if ( parent.equals(dest) )
                break;
            // for all edges from parent to children do
            for(Vertex child: parent.neighbors) {
                // if child is not discovered, add it to queue and mark as visited
                if ( !child.visited ) {
                    queue.add(child);
                    child.visited = true;
                    prev.put(child, parent);
                }
            }
        }
        // Start from destination, iterate until we get to starting vertex. Reverse the path.
        for(Vertex vertex = dest; vertex != null; vertex = prev.get(vertex))
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

} // torn2Pieces


