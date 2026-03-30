package marko.utils.io;

import java.util.List;
import java.util.PriorityQueue;

import marko.models.Edge;
import marko.models.Graph;

public class OutputDevice {
    public static void println(Object o) { System.out.println(o); }
    public static void print(Object o) { System.out.print(o); }
    public static void endl() { System.out.println(); }
    public static void printGraph(Graph graph) { println(graph); }

    public static void printGraphInformation(Graph graph) {
        endl();
        println("Graph: ");
        println(graph);
        println("Number of nodes " + graph.getSize());
        // println(graph[0]); List of outgoing edges from city '0'
        // Each edge inside graph[0] -> e.destinationId = neighbor, e.weight = cost
        endl();
    }

    public static void printGraphList(List<Graph> graphs) {
        if(graphs.isEmpty()) {
            println("No saved graphs.");
            return;
        }

        for (int i = 0; i < graphs.size(); i++) {
            println("[" + i + "]");
            println(graphs.get(i));
        }
    }
    
    // Function for my testing purposes
    public static void printPriorityQueue(PriorityQueue<Edge> pq) {
        while(!pq.isEmpty()) {
            OutputDevice.println(pq.poll());
        }
    }
}
