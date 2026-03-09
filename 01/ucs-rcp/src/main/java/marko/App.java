package marko;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App 
{
    private static ArrayList<Edge>[] graph;
    private static Byte initialState;
    private static Byte finalState;

    private static String readStringInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }


    private final short UCS() {
        PriorityQueue<Edge> pq = initializePriorityQueue();
        boolean[] visited = new boolean[graph.length];
        short cost = 0;
        while(!pq.isEmpty()) {
            Edge current = pq.poll();                       // Get lowest cost edge from the priority queue
            byte currentId = current.getDestinationId();    // Save the destionation id of said node
            if (currentId == finalState)                    // If it is the goal
                return cost;                                //      Return the cost
            if (visited[currentId]) { continue; }           // If node is in visited, continue
            visited [currentId] = true;                     // Set it as visited
            for (Edge edge : graph[currentId]) {
                cost += edge.getWeight();
                if (!visited[edge.getDestinationId()]) pq.add(edge);
            }
        }
        return -1;
    }

    private PriorityQueue<Edge> initializePriorityQueue() {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(Edge e : graph[initialState]) { pq.add(e); } 
        return pq;
    }


    public static void main( String[] args ) throws IOException {
        OutputDevice.print("Enter the base graph: ");  //
        String input = readStringInput();       // Used for base graph
        Parser p = new Parser(input);           // Parser initialization
        graph = p.parse();    // Fill the base graph with the parsed input

        OutputDevice.printGraphInformation(graph);

        OutputDevice.print("Enter the initial node (the city one finds themselves in): ");
        input = readStringInput(); // Initial state
        initialState = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("Enter the goal node (the city to get to): ");
        input = readStringInput();
        finalState = Byte.parseByte(input);
        OutputDevice.endl();
    }
}
