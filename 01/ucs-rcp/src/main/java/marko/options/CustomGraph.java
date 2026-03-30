package marko.options;

import java.io.IOException;

import marko.UniformCostSearch;
import marko.models.Graph;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class CustomGraph {
    private Graph graph;
    private byte startNodeId;
    private byte finalNodeId;

    public void run() throws IOException {     
        inputGraph();   
        OutputDevice.printGraphInformation(graph);
        inputStartNodeId();
        inputFinalNodeId();
        runUCS();
    }

    private void inputGraph() throws IOException {
        OutputDevice.endl();
        OutputDevice.print("Enter the base graph: ");
        String input = InputDevice.readString();
        graph = new Graph(input);
    }

    private void inputStartNodeId() throws IOException {
        OutputDevice.print("Enter the initial node: ");
        String input = InputDevice.readString();
        startNodeId = Byte.parseByte(input);
        OutputDevice.endl();
    }

    private void inputFinalNodeId() throws IOException {
        OutputDevice.print("Enter the goal node: ");
        String input = InputDevice.readString();
        finalNodeId = Byte.parseByte(input);
        OutputDevice.endl();
    }

    private void runUCS() {
        OutputDevice.print("UCS cost ::: ");
        OutputDevice.print(UniformCostSearch.computeCostFromAtoB(graph, startNodeId, finalNodeId));
        OutputDevice.endl();
    }
}
