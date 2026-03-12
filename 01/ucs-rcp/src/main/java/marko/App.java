package marko;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App 
{
    private static ArrayList<Edge>[] graph;
    private static byte initialStateId;
    private static byte finalStateId;

    private static String readStringInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }


    private final short UCS() {
        PriorityQueue<Byte> frontier = initializePriorityQueue();   // Initiliaze priority queue to be used
        boolean[] explored = new boolean[graph.length];             // Array of visited nodes -- a sort of frequency array
        short cost = 0;                                             // Cost
        while(!frontier.isEmpty()) {
            byte currentEdgeId = frontier.poll();           // Get id of node in queue
            if (currentEdgeId == finalStateId)              // If it is the goal
                return cost;                                //      Return the cost
            if (explored[currentEdgeId]) { continue; }       // If node is in visited, continue
            explored [currentEdgeId] = true;                 // Set it as visited
            for (Edge edge : graph[currentEdgeId]) {        // For every neighbour of current node
                cost += edge.getWeight();                   // Add cost
                if (!explored[edge.getDestinationId()])      // If it is not visited
                    frontier.add(edge.getDestinationId());  // Add it to the frontier
            }
        }
        return -1;
    }

    private PriorityQueue<Byte> initializePriorityQueue() { 
        PriorityQueue<Byte> pq = new PriorityQueue<Byte>();
        pq.add(initialStateId);
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
        initialStateId = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("Enter the goal node (the city to get to): ");
        input = readStringInput();
        finalStateId = Byte.parseByte(input);
        OutputDevice.endl();
    }
}
