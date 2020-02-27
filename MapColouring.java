package com.company;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Graph {
        private int vCount;
        private int colorCount;
        private List<Integer>[] adjList;

        public int getvCount() {
            return vCount;
        }

        public Graph(int vCount) {
            this.vCount = vCount;
            adjList = (List<Integer>[]) new List[vCount];
            for (int i = 0; i < vCount; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int src, int dest) {
            adjList[src].add(dest);
            adjList[dest].add(src);
        }
/*
        public void removeEdge(int i, int j) {
            Iterator<Integer> iter = adjList[i].iterator();
            while (iter.hasNext()) {
                if (iter.next() == j) {
                    iter.remove();
                    break;
                }
            }
            Iterator<Integer> iter2 = adjList[j].iterator();
            while (iter.hasNext()) {
                if (iter.next() == i) {
                    iter.remove();
                    break;
                }
            }
        }

        public void printGraph() {
            for (int i = 0; i < vCount; i++) {
                List<Integer> edges = neighbors(i);
                System.out.print(i + ": ");
                for (int j = 0; j < edges.size(); j++) {
                    System.out.print(edges.get(j) + " ");
                }
                System.out.println();
            }
        }
*/

        public List<Integer> neighbors(int vertex) {
            return adjList[vertex];
        }

        public boolean hasEdge(int src, int dest) {
            return adjList[src].contains(dest);
        }

        // Backtracking Coloring Utility Functions
        public static boolean isSafe(int v, Graph g, int[] colors, int clr) {
            for (int i = 0; i < g.vCount; i++) {
                if (g.hasEdge(v, i) && clr == colors[i]) {
                    return false;
                }
            }
            return true;
        }

        public static boolean graphColoringUtil(Graph g, int m, int[] colors, int v) {
            // all vertices have a color already
            if (v == g.vCount) {
                return true;
            }

            // Try different colors for v
            for (int clr = 1; clr <= m; clr++) {
                // Check if assignment of color clr to vertex v is OK
                if (isSafe(v, g, colors, clr)) {
                    colors[v] = clr;
                    // Recur to assign colors to the remaining vertices
                    if (graphColoringUtil(g, m, colors, v + 1)) {
                        return true;
                    }
                    // If assigning color clr does not lead to solution, remove it
                    colors[v] = 0;
                }
            }
            // No solution can be assigned
            return false;
        }

        // Main Backtracking Coloring Function
        public static void backTrackingColoring(Graph g, int m) {
            int V = g.getvCount();

            int[] colors = new int[V];

            // Initialize all color values to 0
            Arrays.fill(colors, 0);

            // Call graphColoringUtil for Vertex 0
            if (!graphColoringUtil(g, m, colors, 0)) {
                System.out.println("many");
                return;
            }
            int numColors = countDistinctColors(colors);
            System.out.println(numColors);
        }

        public static int countDistinctColors(int[] arr) {
            Arrays.sort(arr);
            int n = arr.length;
            int res = 0;
            for(int i=0; i<arr.length; i++) {
                // Move the index ahead while there are duplicates
                while ( i < n-1 && arr[i] == arr[i+1] ) {
                    i++;
                }
                res++;
            }
            return res;
        }

}

class MapColouring {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cases = Integer.valueOf(input.nextLine());
        for(int i=0; i<cases; i++) {
            // One line for each country C and the number of borders B
            String[] country_border = input.nextLine().split(" ");
            int country = Integer.parseInt(country_border[0]);
            int border = Integer.parseInt(country_border[1]);
            Graph graph = new Graph(country);
            for (int j = 0; j < border; j++) {
                String[] src_dest = input.nextLine().split(" ");
                int src = Integer.parseInt(src_dest[0]);
                int dest = Integer.parseInt(src_dest[1]);
                graph.addEdge(src, dest);
            }

            Graph.backTrackingColoring(graph, 4);
        }

    } // main
}