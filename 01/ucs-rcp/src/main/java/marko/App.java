package marko;

import java.util.ArrayList;
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


    private static byte getGraphSize() {
        byte size = 1;
        for(ArrayList<Edge> adjacencyList : graph) {
            for(Edge edge : adjacencyList) {
                byte destinationId = edge.getDestinationId();
                size = destinationId >= size ? (byte) (destinationId + 1) : size;
            }
        }
        return size;
    }

    private static ArrayList<Edge>[] processGraph() {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] newGraph = (ArrayList<Edge>[]) new ArrayList[graphSize];
        byte i = 0;
        for (; i < graphSize; ++i) { newGraph[i] = new ArrayList<Edge>(); }
        i = 0;
        for (ArrayList<Edge> node : graph) {
            for (Edge edge : node) {
                newGraph[i].add(edge);
            }
            ++i;
        }
        return newGraph;
    }


    public static void main( String[] args ) throws IOException {
        OutputDevice.print("Enter the base graph: ");  //
        String input = readStringInput();       // Used for base graph
        Parser p = new Parser(input);           // Parser initialization
        graph = p.parse();    // Fill the base graph with the parsed input
        graphSize = getGraphSize();
        graph = processGraph();

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
        OutputDevice.print(UniformCostSearch.computeCostFromAtoB(graph, graphSize, initialStateId, finalStateId));
        OutputDevice.endl();
    }
}
