package marko.options;

import java.io.IOException;

import marko.models.Graph;
import marko.utils.GraphUtils;
import marko.utils.Json;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class CustomGraph {
    private Graph graph;
    private byte startNodeId;
    private byte finalNodeId;

    public void run() throws IOException {     
        inputGraph();   
        OutputDevice.printGraphInformation(graph);
        startNodeId = GraphUtils.inputStartNodeId();
        finalNodeId = GraphUtils.inputFinalNodeId();
        GraphUtils.runUCS(graph, startNodeId, finalNodeId);
        saveOption();
    }

    private void inputGraph() throws IOException {
        OutputDevice.endl();
        OutputDevice.print("Enter the base graph: ");
        String input = InputDevice.readString();
        graph = new Graph(input);
    }

    private void saveOption() throws IOException {
        OutputDevice.endl();
        OutputDevice.print("Save input graph? (y/n): ");
        String input = InputDevice.readString().trim().toLowerCase();

        boolean shouldSave;

        if (input.equals("y"))      shouldSave = true;
        else if (input.equals("n")) shouldSave = false;
        else {
            try {
                shouldSave = Byte.parseByte(input) == 1;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input: expected y/n or 1/0");
            }
        }

        if (shouldSave) {
            Json.appendGraphToFile(graph, "data/graphs.json");
        }
    }
}
