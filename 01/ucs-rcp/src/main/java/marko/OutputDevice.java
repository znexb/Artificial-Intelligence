package marko;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class OutputDevice {
    public static void println(Object o) { System.out.println(o); }

    public static void print(Object o) { System.out.print(o); }

    public static void endl() { System.out.println(); }

    private static void printGraph(ArrayList<Edge>[] graph) {
        for(ArrayList<Edge> e : graph) { print(e + " "); }
        endl();
    }

    private static void printGraphInput(ArrayList<Edge>[] graph, short graphSize) {
        println("Graph: ");
        printGraph(graph);
        println(graphSize); // Number of cities
    }

    public static void printGraphInformation(ArrayList<Edge>[] graph, short graphSize) {
        endl();
        printGraphInput(graph, graphSize);
        // println(graph[0]); List of outgoing edges from city '0'
        // Each edge inside graph[0] -> e.destinationId = neighbor, e.weight = cost
        endl();
    }

    
    // Function for my testing purposes
    public static void printPriorityQueue(PriorityQueue<Edge> pq) {
        while(!pq.isEmpty()) {
            OutputDevice.println(pq.poll());
        }
    }
}
