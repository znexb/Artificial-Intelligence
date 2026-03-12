package marko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App 
{
    private static ArrayList<Edge>[] graph;
    private static byte graphSize;
    private static byte initialStateId;
    private static byte finalStateId;


    private static String readStringInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }


    private final static short uniformCostSearch() {
        PriorityQueue<SearchNode> frontier = initializePriorityQueue(); // Initiliaze priority queue to be used
        short[] bestCost = initializeBestCost();                        // Initializes the bestCost array, which is used to store 'explored' nodes based on cost
        while(!frontier.isEmpty()) {
            SearchNode currentNode = frontier.poll();       // Get id of node in queue
            byte currentNodeId     = currentNode.getId();
            short currentCost      = currentNode.getCost();
            if (currentCost > bestCost[currentNodeId])      // If cost of current node is 'outdated'
                continue;                                   //      Skip
            if (currentNodeId == finalStateId)              // If current node is the goal
                return currentNode.getCost();               //      Return the cummulative cost
            for (Edge edge : graph[currentNodeId]) {        // For every neighbour of current node
                byte neighbourId = edge.getDestinationId();
                short newCost = (short) (edge.getWeight() + currentCost);
                if (newCost < bestCost[neighbourId])                    // If new computed cost is 'better' than that of the neighbour
                    frontier.add(new SearchNode(neighbourId, newCost)); // Add it to the frontier
            }
        }
        return -1;
    }

    private static PriorityQueue<SearchNode> initializePriorityQueue() { 
        PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
        pq.add (new SearchNode (initialStateId, (short) 0));
        return pq;
    }

    private static short[] initializeBestCost() {
        short[] bc = new short[graphSize];
        Arrays.fill(bc, Short.MAX_VALUE);
        bc[initialStateId] = 0;
        return bc;
    }


    private static byte getGraphSize() {
        byte size = 1;
        for(ArrayList<Edge> adjacencyList : graph) {
            for(Edge edge : adjacencyList) {
                byte destinationId = edge.getDestinationId();
                size = destinationId > size ? (byte) (destinationId + 1) : size;
            }
        }
        return size;
    }


    public static void main( String[] args ) throws IOException {
        OutputDevice.print("Enter the base graph: ");  //
        String input = readStringInput();       // Used for base graph
        Parser p = new Parser(input);           // Parser initialization
        graph = p.parse();    // Fill the base graph with the parsed input
        graphSize = getGraphSize();

        OutputDevice.printGraphInformation(graph, graphSize);

        OutputDevice.print("Enter the initial node (the city one finds themselves in): ");
        input = readStringInput(); // Initial state
        initialStateId = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("Enter the goal node (the city to get to): ");
        input = readStringInput();
        finalStateId = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("UCS cost ::: ");
        OutputDevice.print(uniformCostSearch());
        OutputDevice.endl();
    }
}
