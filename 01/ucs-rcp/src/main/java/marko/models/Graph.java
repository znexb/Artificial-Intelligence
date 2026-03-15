package marko.models;

import java.util.ArrayList;

import marko.utils.Parser;

public class Graph {
    private ArrayList<Edge>[] graph;
    private byte size = -1;             // -1 means that it was not computed yet. = sentinel value

    private Graph(byte graphSize) {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[graphSize];
        for(byte i = 0; i < graphSize; ++i) { graph[i] = new ArrayList<Edge>(); }
    }

    public Graph(String s) { // Constructor from String; alternative to fromString 
        Parser p = new Parser(s);
        ArrayList<Edge>[] rawGraph = p.parse();
        size = computeRawSize(rawGraph);
        graph = processRawGraph(rawGraph);
    }


    public static Graph fromString(String s) { // Converts a string to a graph
        ArrayList<Edge>[] rawGraph;
        Parser p = new Parser(s);
        rawGraph = p.parse();
        Graph g = new Graph((byte) 0);
        g.size = computeRawSize(rawGraph);
        g.graph = g.processRawGraph(rawGraph);
        return g;
    }

    private ArrayList<Edge>[] processRawGraph(ArrayList<Edge>[] rawGraph) { // Converts a raw graph to a fully functional graph. A raw graph is a graph with missing lists for the nodes with no edges.
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] newGraph = (ArrayList<Edge>[]) new ArrayList[size];
        byte i = 0;
        for (; i < size; ++i) { newGraph[i] = new ArrayList<Edge>(); }
        i = 0;
        for (ArrayList<Edge> node : rawGraph) {
            for (Edge edge : node) {
                newGraph[i].add(edge);
            }
            ++i;
        }
        return newGraph;
    }

    public byte getSize() { return size; }

    public static byte computeRawSize(ArrayList<Edge>[] rawGraph) {
        byte size = 1;
        for(ArrayList<Edge> adjacencyList : rawGraph) {
            for(Edge edge : adjacencyList) {
                byte destinationId = edge.getDestinationId();
                size = destinationId >= size ? (byte) (destinationId + 1) : size;
            }
        }
        return size;
    }

    public ArrayList<Edge> getAdjacencyListOf(byte id) { return graph[id]; }


    @Override
    public String toString() { 
        String output = "";
        byte i = 0;
        for(ArrayList<Edge> node : graph) {
            output += "    Node " + i + ":: ";
            for(Edge edge : node) { output += edge + " "; }
            output += "\n";
            ++i;
        } 
        return output;
    }
}
