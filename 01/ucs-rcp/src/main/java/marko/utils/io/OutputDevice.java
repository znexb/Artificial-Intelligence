package marko.utils.io;

import java.util.PriorityQueue;

import marko.models.Edge;
import marko.models.Graph;

public class OutputDevice {
    public static void println(Object o) { System.out.println(o); }

    public static void print(Object o) { System.out.print(o); }

    public static void endl() { System.out.println(); }


    public static void printGraphInformation(Graph graph) {
        endl();
        println("Graph: ");
        println(graph);
        println("Number of nodes " + graph.getSize());
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
