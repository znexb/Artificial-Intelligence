package marko.options;

import java.io.IOException;

import marko.models.Graph;
import marko.utils.GraphUtils;
import marko.utils.Json;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class SavedGraph {
    private Graph graph;
    private byte startNodeId;
    private byte finalNodeId;

    public void run() throws IOException {
        printGraphList();
        inputGraph();
        startNodeId = GraphUtils.inputStartNodeId();
        finalNodeId = GraphUtils.inputFinalNodeId();
        GraphUtils.runUCS(graph, startNodeId, finalNodeId);
    }

    private void printGraphList() throws IOException {
        OutputDevice.endl();
        OutputDevice.println("Saved graphs: ");
        OutputDevice.endl();
        OutputDevice.printGraphList(Json.readGraphsFromFile("data/graphs.json"));
    }

    private void inputGraph() throws IOException {
        OutputDevice.print("Choose a graph: ");
        int index = InputDevice.readIntSafe();
        graph = Json.readGraphByIndex("data/graphs.json", index);
        OutputDevice.endl();
    }
}