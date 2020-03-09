package com.company;

import java.util.Scanner;

class Graph {

    public static int[] colors = new int[16];
    public static int[][] graph = new int[16][16];
    public static boolean hasEnoughColors = false;

    // Verify given vertex has given color
    public static boolean isSafe(int vertex, int nVertices, int color) {
        // Check each vertex
        for (int i = 0; i < nVertices; i++) {
            // Check current vertex in adjacency matrix AND given color is already used
            if (graph[vertex][i] == 1 && color == colors[i]) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function
    static void mapColouring(int vertex, int nVertices, int mColor) {
        // Check each color
        for (int c =1; c <=mColor; c++) {
            // Check each vertex is hasEnoughColorsful for each color
            if (isSafe(vertex, nVertices, c)) {
                // Set the current color to the vertex in the colors array
                colors[vertex] = c;
                // Check all vertices have not been yet visited
                if ((vertex + 1) < nVertices) {
                    // Check next vertex
                    mapColouring(vertex + 1, nVertices, mColor);
                    // Clear colors array after backtracking unsuccessfully (not enough colors for graph!)
                    colors[vertex] = 0;
                } else {
                    hasEnoughColors = true;
                    return;
                }
            }
        }
    }
}


class MapColouring {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cases = Integer.valueOf(input.nextLine());
        for (int i = 0; i < cases; i++) {
            String[] country_border = input.nextLine().split(" ");
            int countries = Integer.parseInt(country_border[0]);
            int borders = Integer.parseInt(country_border[1]);

            // Initialize 2D graph (set rows and columns to 0)
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    Graph.graph[j][k] = 0;
                }
            }

            // Initialize colors array to 0
            for (int c = 0; c < 16; c++) {
                Graph.colors[c] = 0;
            }

            // Build graph
            for (int b = 0; b < borders; b++) {
                // One line for each country C and the number of borders B
                String[] src_dest = input.nextLine().split(" ");
                int src = Integer.parseInt(src_dest[0]);
                int dest = Integer.parseInt(src_dest[1]);
                // Path exists from given source to given dest (undirected graph)
                Graph.graph[src][dest] = 1;
                Graph.graph[dest][src] = 1;
            }

            // Call mapColouring for each color (1-4)
            for (int j = 1; j <= 4; j++) {
                Graph.mapColouring(0, countries, j);
                if ( Graph.hasEnoughColors ) {
                    System.out.println(j);
                    break;
                }
            }

            // Not enough colors for given graph
            if ( !Graph.hasEnoughColors ) {
                System.out.println("many");
            }

            // Reset flag to false before next iteration
            Graph.hasEnoughColors = false;
        }

    } // main
} // MapColouring